package com.tiger.layoutide.ide.code.activity;

import com.tiger.code.model.JCodeBlock;
import com.tiger.layoutide.ide.code.library.JActivity;

public class EventBusActivityBuilder extends JActivityBuilder
{
	private JActivityBuilder activityBuilder;
	
	public EventBusActivityBuilder(JActivityBuilder activityBuilder)
	{
		super(null, null);
		this.activityBuilder = activityBuilder;
	}
	
	@Override
	public JActivity buildActivity()
	{
		JActivity activity = activityBuilder.buildActivity();
		
		JCodeBlock onCreateBlock = activity.getOnCreateMethod().getCodeBlock();
		onCreateBlock.addCode("QuizUpApplication.getBus().register(this);");
		
		JCodeBlock onDestroyBlock = activity.getOnDestroyMethod().getCodeBlock();
		onDestroyBlock.addCode("QuizUpApplication.getBus().unregister(this);");
		
		return activity;
	}
}
