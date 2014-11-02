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
import com.tiger.layoutide.widget.TGRelativeLayout;

public class RelativeBlankTemplate extends TemplateLayout
{
	public RelativeBlankTemplate(Context context)
	{
		super(context);
	}

	public RelativeBlankTemplate(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public RelativeBlankTemplate(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}
	
	protected void initTemplate()
	{
		this.setBackgroundColor(Color.BLUE);
		
		TextView textView = new TextView(getContext());
		textView.setText("RelativeLayout");
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
		
		TGRelativeLayout realView = new TGRelativeLayout(activity);
		realView.setRootViewGroup(true);
		realView.setLayoutParams(layoutParams);
		realView.setBackgroundColor(Color.BLUE);
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
