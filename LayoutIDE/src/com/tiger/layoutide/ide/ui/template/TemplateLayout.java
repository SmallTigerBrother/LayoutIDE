package com.tiger.layoutide.ide.ui.template;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public abstract class TemplateLayout extends FrameLayout
{
	public static final int LINEAR_BLANK_TEMPLATE = 1;
	
	public static final int RELATIVE_BLANK_TEMPLATE = 2;
	
	public TemplateLayout(Context context)
	{
		this(context, null);
	}

	public TemplateLayout(Context context, AttributeSet attrs)
	{
		this(context, attrs, 0);
	}

	public TemplateLayout(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		initTemplate();
	}
	
	protected abstract void initTemplate();
}
