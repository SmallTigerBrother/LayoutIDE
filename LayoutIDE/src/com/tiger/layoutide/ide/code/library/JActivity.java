package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JMethod;

public class JActivity extends JClass
{
	private static final long serialVersionUID = 1L;

	private JMethod onCreateMethod;
	
	private JMethod onResumeMethod;
	
	private JMethod onStopMethod;
	
	private JMethod onDestroyMethod;
	
	public JActivity(String packageName, String simpleClazzName)
	{
		super(packageName, simpleClazzName);
		
		JClass superClazz = ClassFactory.getClass(AndroidClass.Activity);
		this.setSuperClass(superClazz);
	}
	
	public void setOnCreateMethod(JMethod onCreateMethod)
	{
		if(null != this.onCreateMethod)
		{
			this.getMethods().remove(this.onCreateMethod);
		}
		this.onCreateMethod = onCreateMethod;
		this.addMethod(onCreateMethod);
	}
	
	public JMethod getOnCreateMethod()
	{
		return onCreateMethod;
	}
	
	public void setOnResumeMethod(JMethod onResumeMethod)
	{
		if(null != this.onResumeMethod)
		{
			this.getMethods().remove(this.onResumeMethod);
		}
		this.onResumeMethod = onResumeMethod;
		this.addMethod(onResumeMethod);
	}
	
	public JMethod getOnResumeMethod()
	{
		return onResumeMethod;
	}
	
	public void setOnStopMethod(JMethod onStopMethod)
	{
		if(null != this.onStopMethod)
		{
			this.getMethods().remove(this.onStopMethod);
		}
		this.onStopMethod = onStopMethod;
		this.addMethod(onStopMethod);
	}
	
	public JMethod getOnStopMethod()
	{
		return onStopMethod;
	}
	
	public void setOnDestroyMethod(JMethod onDestroyMethod)
	{
		if(null != this.onDestroyMethod)
		{
			this.getMethods().remove(this.onDestroyMethod);
		}
		this.onDestroyMethod = onDestroyMethod;
		this.addMethod(onDestroyMethod);
	}
	
	public JMethod getOnDestroyMethod()
	{
		return onDestroyMethod;
	}
}
