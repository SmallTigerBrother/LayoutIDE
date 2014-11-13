package com.tiger.layoutide.ide.code.activity;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JMethod;
import com.tiger.layoutide.ide.code.library.JActivity;

public class EventBusActivityBuilder extends JActivityBuilder
{
	private JActivityBuilder activityBuilder;
	
	public EventBusActivityBuilder(JActivityBuilder activityBuilder)
	{
		this.activityBuilder = activityBuilder;
	}
	
	@Override
	public JClass buildSuperClass()
	{
		return null;
	}

	@Override
	public JMethod buildOnRequestSuccess(JActivity activity)
	{
		return null;
	}
	
	@Override
	public JActivity buildActivity()
	{
		JActivity activity = activityBuilder.buildActivity();
		
		//onCreate中加入register
		JCodeBlock onCreateBlock = activity.getOnCreateMethod().getCodeBlock();
		onCreateBlock.addCode("QuizUpApplication.getBus().register(this);");
		
		//onDestroy中加入unregister
		JCodeBlock onDestroyBlock = activity.getOnDestroyMethod().getCodeBlock();
		onDestroyBlock.addCode("QuizUpApplication.getBus().unregister(this);");
		
		return activity;
	}
}
