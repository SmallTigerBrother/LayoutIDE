package com.tiger.layoutide.widget;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;

import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.ide.tool.PropertiesToolBar;

public class ViewGroupHelper extends ViewHelper implements IViewGroup, OnDragListener
{
	private boolean isRootViewGroup = false;
	
	public ViewGroupHelper(ViewGroup viewGroup)
	{
		super(viewGroup);
	}

	@Override
	public boolean isRootViewGroup()
	{
		return isRootViewGroup;
	}

	@Override
	public void setRootViewGroup(boolean isRootView)
	{
		this.isRootViewGroup = isRootView;
	}

	@Override
	public boolean onDrag(View v, DragEvent event)
	{
		if(event.getAction() == DragEvent.ACTION_DROP)
		{
			if(null != Emulator.getSingleInstance().getNewViewModel())
			{
				if(getView() instanceof TGLinearLayout || getView() instanceof TGRelativeLayout)
				{
					ViewGroup viewGroup = (ViewGroup)getView();
					
					View newView = (Emulator.getSingleInstance().getNewViewModel()).newInstance();
					newView.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							PropertiesToolBar.getSingleInstance().setSelectedView((IView)v);
						}
					});
					
					viewGroup.addView(newView);
					Emulator.getSingleInstance().setNewViewModel(null);
				}
			}
		}
		return true;
	} 
}
