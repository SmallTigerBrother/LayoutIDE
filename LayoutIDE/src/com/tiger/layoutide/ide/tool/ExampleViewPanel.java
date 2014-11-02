package com.tiger.layoutide.ide.tool;

import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnLongClickListener;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGButton;
import com.tiger.layoutide.widget.TGCheckBox;
import com.tiger.layoutide.widget.TGEditText;
import com.tiger.layoutide.widget.TGImageView;
import com.tiger.layoutide.widget.TGLinearLayout;
import com.tiger.layoutide.widget.TGRelativeLayout;
import com.tiger.layoutide.widget.TGTextView;

public class ExampleViewPanel implements OnLongClickListener
{
	@ViewById(id = R.id.textview_model)
	private TGTextView textView;
	
	@ViewById(id = R.id.button_model)
	private TGButton button;
	
	@ViewById(id = R.id.edittext_model)
	private TGEditText editText;
	
	@ViewById(id = R.id.imageview_model)
	private TGImageView imageView;
	
	@ViewById(id = R.id.checkbox_model)
	private TGCheckBox checkBox;
	
	@ViewById(id = R.id.linearlayout_model)
	private TGLinearLayout linearLayout;
	
	@ViewById(id = R.id.relativelayout_model)
	private TGRelativeLayout relativeLayout;
	
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
	}

	@Override
	public boolean onLongClick(View v)
	{
		v.startDrag(null, new DragShadowBuilder(v), null, 0);
		Emulator.getSingleInstance().setNewViewModel((IView)v);
		return true;
	}
}
