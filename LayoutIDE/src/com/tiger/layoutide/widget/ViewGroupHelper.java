package com.tiger.layoutide.widget;

import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;

import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.ide.tool.PropertiesToolBar;

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
			if(null != Emulator.getSingleInstance().getNewViewModel())
			{
				if(getView() instanceof JTGLinearLayout || getView() instanceof JTGRelativeLayout)
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
