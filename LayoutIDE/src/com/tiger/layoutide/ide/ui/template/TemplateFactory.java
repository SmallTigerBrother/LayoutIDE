package com.tiger.layoutide.ide.ui.template;

import android.app.Activity;
import android.view.View;

public class TemplateFactory
{
	private static TemplateFactory factory;
	
	private TemplateFactory()
	{
	}
	
	public synchronized static TemplateFactory getInstance()
	{
		if(null == factory)
		{
			factory = new TemplateFactory();
		}
		return factory;
	}
	
	public View createRealViewOfTemplate(Activity activity, int templateType)
	{
		switch (templateType)
		{
			case TemplateLayout.LINEAR_BLANK_TEMPLATE:
				return LinearBlankTemplate.getRealView(activity);

			case TemplateLayout.RELATIVE_BLANK_TEMPLATE:

				return RelativeBlankTemplate.getRealView(activity);

			default:
				break;
		}
		
		return null;
	}
}
