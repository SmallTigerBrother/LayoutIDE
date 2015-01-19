package com.tiger.layoutide.ide.code.activity;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JMethod;
import com.tiger.layoutide.ide.code.library.JActivity;

public class SlidingActivityBuilder extends JActivityBuilder
{
	private boolean slideRightEnable;
	
	private boolean slideLeftEnable;
	
	public SlidingActivityBuilder(String packageName, String simpleClazzName)
	{
		super(packageName, simpleClazzName);
	}
	
	@Override
	public void buildSetupViews(JActivity activity, JCodeBlock setupViewsBlock)
	{
		super.buildSetupViews(activity, setupViewsBlock);
		
		if(slideRightEnable)
		{
			
		}
		else
		{
			
		}
		
		if(slideLeftEnable)
		{
			
		}
		else
		{
			
		}
	}

	public void setSlideRightEnable(boolean slideRightEnable)
	{
		this.slideRightEnable = slideRightEnable;
	}

	public void setSlideLeftEnable(boolean slideLeftEnable)
	{
		this.slideLeftEnable = slideLeftEnable;
	}
}
