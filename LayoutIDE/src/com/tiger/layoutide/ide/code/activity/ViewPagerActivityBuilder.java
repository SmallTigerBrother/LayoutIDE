package com.tiger.layoutide.ide.code.activity;

import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JField;
import com.tiger.layoutide.ide.code.library.AndroidClass;
import com.tiger.layoutide.ide.code.library.ClassFactory;
import com.tiger.layoutide.ide.code.library.JActivity;

public class ViewPagerActivityBuilder extends JActivityBuilder
{
	public ViewPagerActivityBuilder(String packageName, String simpleClazzName)
	{
		super(packageName, simpleClazzName);
	}
	
	@Override
	public void buildSetupViews(JActivity activity, JCodeBlock setupViewsBlock)
	{
		super.buildSetupViews(activity, setupViewsBlock);
		
		JField viewPager = activity.findFieldsByType(
				ClassFactory.getClass(AndroidClass.View)).get(0);
		
	}
	
	@Override
	public JActivity buildActivity()
	{
		return super.buildActivity();
	}
}
