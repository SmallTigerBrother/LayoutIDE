package com.tiger.layoutide.widget;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.ViewGroup;


public class ViewGroupHelper extends ViewHelper implements OnDragListener
{
	private boolean isRootViewGroup = false;
	
	public ViewGroupHelper(ViewGroup viewGroup)
	{
		super(viewGroup);
	}

	public boolean isRootViewGroup()
	{
		return isRootViewGroup;
	}

	public void setRootViewGroup(boolean isRootView)
	{
		this.isRootViewGroup = isRootView;
	}

	@Override
	public boolean onDrag(View v, DragEvent event)
	{
		if(event.getAction() == DragEvent.ACTION_DROP)
		{
		}
		return true;
	} 
}
