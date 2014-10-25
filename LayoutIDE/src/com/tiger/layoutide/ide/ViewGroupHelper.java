package com.tiger.layoutide.ide;

import com.tiger.layoutide.widget.IViewGroup;

import android.view.ViewGroup;

public class ViewGroupHelper extends ViewHelper implements IViewGroup
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
}
