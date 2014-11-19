package com.tiger.layoutide.ide.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;

public class EventListenerToolBar extends FrameLayout implements OnCheckedChangeListener
{
	@ViewById(id = R.id.onclick_listener_check)
	private CheckBox onClickCheckBox;
	
	@ViewById(id = R.id.onitemclick_listener_check)
	private CheckBox onItemClickCheckBox;
	
	private IView selectedView = null;
	
	public EventListenerToolBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}
	
	private void init()
	{
		View mainView = inflate(getContext(), R.layout.event_listener_tool_bar, this);
		ViewInjector.initInjectedView(this, mainView);
		
		onClickCheckBox.setOnCheckedChangeListener(this);
		
		onItemClickCheckBox.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		switch (buttonView.getId())
		{
			case R.id.onclick_listener_check:
				
				break;
				
			case R.id.onitemclick_listener_check:
				
				break;

			default:
				break;
		}
	}
	
	public void resetContent(IView selectedView)
	{
		this.selectedView = selectedView;
		if(null != selectedView)
		{
		}
	}
}
