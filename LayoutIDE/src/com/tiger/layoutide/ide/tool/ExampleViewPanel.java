package com.tiger.layoutide.ide.tool;

import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.JTGButton;
import com.tiger.layoutide.widget.JTGCheckBox;
import com.tiger.layoutide.widget.JTGEditText;
import com.tiger.layoutide.widget.JTGImageView;
import com.tiger.layoutide.widget.JTGLinearLayout;
import com.tiger.layoutide.widget.JTGRelativeLayout;
import com.tiger.layoutide.widget.JTGTextView;
import com.tiger.layoutide.widget.JTGViewPager;

public class ExampleViewPanel implements OnLongClickListener
{
	@ViewById(id = R.id.textview_model)
	private JTGTextView textView;
	
	@ViewById(id = R.id.button_model)
	private JTGButton button;
	
	@ViewById(id = R.id.edittext_model)
	private JTGEditText editText;
	
	@ViewById(id = R.id.imageview_model)
	private JTGImageView imageView;
	
	@ViewById(id = R.id.checkbox_model)
	private JTGCheckBox checkBox;
	
	@ViewById(id = R.id.linearlayout_model)
	private JTGLinearLayout linearLayout;
	
	@ViewById(id = R.id.relativelayout_model)
	private JTGRelativeLayout relativeLayout;
	
	@ViewById(id = R.id.viewpager_model)
	private JTGViewPager viewPager;
	
	private static ExampleViewPanel exampleViewPanel;
	
	public static final ExampleViewPanel getSingleInstance()
	{
		return exampleViewPanel;
	}
	
	public ExampleViewPanel(View panelView)
	{
		ViewInjector.initInjectedView(this, panelView);
		
		init();
		
		exampleViewPanel = this;
	}
	
	private void init()
	{
		textView.setOnLongClickListener(this);
		button.setOnLongClickListener(this);
		editText.setOnLongClickListener(this);
		imageView.setOnLongClickListener(this);
		checkBox.setOnLongClickListener(this);
		linearLayout.setOnLongClickListener(this);
		relativeLayout.setOnLongClickListener(this);
		viewPager.setOnLongClickListener(this);
	}

	@Override
	public boolean onLongClick(View v)
	{
		v.startDrag(null, new DragShadowBuilder(v), null, 0);
		Emulator.getSingleInstance().setNewViewModel((IView)v);
		return true;
	}
}
