package com.tiger.layoutide.ide.ui.template;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiger.layoutide.ide.tool.PropertiesToolBar;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.JTGLinearLayout;

public class LinearBlankTemplate extends TemplateLayout
{
	public LinearBlankTemplate(Context context)
	{
		super(context);
	}

	public LinearBlankTemplate(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public LinearBlankTemplate(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}
	
	protected void initTemplate()
	{
		this.setBackgroundColor(Color.GREEN);
		TextView textView = new TextView(getContext());
		textView.setText("LinearLayout");
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.gravity = Gravity.CENTER;
		this.addView(textView, layoutParams);
	}

	public static View getRealView(Activity activity)
	{
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.MATCH_PARENT);
		
		JTGLinearLayout realView = new JTGLinearLayout(activity);
		realView.setRootViewGroup(true);
		realView.setLayoutParams(layoutParams);
		realView.setBackgroundColor(Color.GREEN);
		realView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				PropertiesToolBar.getSingleInstance().setSelectedView((IView)v);
			}
		});
		return realView;
	}
	
}
