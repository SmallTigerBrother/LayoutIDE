package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.Primatives;

public class CustomAdapters
{
	public static JClass newQuizUpViewHolder()
	{
		JClass viewHolder = new JClass("com.medialab.quizup.adapter", 
				"QuizUpBaseViewHolder");
		
		//写入onInit方法
		Parameter itemView = new Parameter("itemView", ClassFactory.getClass(AndroidClass.View));
		JMethod onInit = new JMethod("onInit");
		onInit.setAbstract(true);
		onInit.setParameters(itemView);
		viewHolder.addMethod(onInit);
		
		//写入onFillData方法
		JMethod onFillData = new JMethod("onFillData");
		Parameter position = new Parameter("position", Primatives.newIntegerClass());
		Parameter itemData = new Parameter("itemData", Primatives.newGenericClass("T"));
		onFillData.setParameters(position, itemData);
		onFillData.setAbstract(true);
		
		viewHolder.addMethod(onFillData);
		
		//写入onClick方法
		JMethod onClick = new JMethod("onClick");
		Parameter viewParam = new Parameter("view", ClassFactory.getClass(
				AndroidClass.View));
		onClick.setParameters(viewParam);
		onClick.setAbstract(true);
		viewHolder.addMethod(onClick);
		
		return viewHolder;
	}
	
	public static JClass newQuizupListAdapter()
	{
		return null;
	}
	
}
