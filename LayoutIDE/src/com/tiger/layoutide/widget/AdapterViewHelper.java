package com.tiger.layoutide.widget;

import android.view.View;

public class AdapterViewHelper extends ViewHelper
{
	private boolean setOnItemClickListener = false;
	
	public AdapterViewHelper(View view)
	{
		super(view);
	}

	public boolean isSetOnItemClickListener()
	{
		return setOnItemClickListener;
	}

	public void setOnItemClickListener(boolean setOnItemClickListener)
	{
		this.setOnItemClickListener = setOnItemClickListener;
	}

}
