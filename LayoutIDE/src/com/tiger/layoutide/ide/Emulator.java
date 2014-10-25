package com.tiger.layoutide.ide;

import android.content.Context;
import android.util.AttributeSet;

import com.tiger.layoutide.widget.TGLinearLayout;

public class Emulator extends TGLinearLayout
{
	public Emulator(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setRootViewGroup(true);
	}

	@Override
	public String getXMLString()
	{
		return super.getXMLString();
	}
}
