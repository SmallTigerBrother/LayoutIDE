package com.tiger.layoutide.ide.code.library;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.JPackage;
import com.tiger.code.model.Primatives;
import com.tiger.code.output.JCodeBuilder;

public class JActivity extends JClass
{
	private JMethod onCreateMethod;
	
	private JMethod onResumeMethod;
	
	private JMethod onStopMethod;
	
	private JMethod onDestroyMethod;
	
	private JMethod processArgsMethod;
	
	private JMethod setupViewsMethod;
	
	private JMethod onActivityResultMethod;
	
	public JActivity(JPackage jPackage, String simpleClazzName)
	{
		super(jPackage, simpleClazzName);
		
		//设置默认基类
		JClass superClazz = ClassFactory.getClass(AndroidClass.Activity);
		this.setSuperClass(superClazz);
		
		//加入onCreate方法
		Parameter parameter = new Parameter("savedInstanceState", 
				ClassFactory.getClass(AndroidClass.Bundle));
		onCreateMethod = initSuperMethod("onCreate", parameter);
		
		//加入onResume方法
		onResumeMethod = initSuperMethod("onResume", new Parameter[]{});
		
		//加入onStop方法
		onStopMethod = initSuperMethod("onStop", new Parameter[]{});
		
		//加入onDestroy方法
		onDestroyMethod = initSuperMethod("onDestroy", new Parameter[]{});
		
		processArgsMethod = initProcessArgsMethod();
		
		setupViewsMethod = initSetupViewsMethod();
		
		onCreateMethod.getCodeBlock().addCode(processArgsMethod.getCallCode(new String[]{}));
		onCreateMethod.getCodeBlock().addCode(setupViewsMethod.getCallCode(new String[]{}));
		
		this.addMethod(onCreateMethod);
		this.addMethod(processArgsMethod);
		this.addMethod(setupViewsMethod);
		this.addMethod(onResumeMethod);
		this.addMethod(onStopMethod);
		this.addMethod(onDestroyMethod);
	}
	
	private JMethod initSuperMethod(String methodName, Parameter... parameters)
	{
		JMethod method = new JMethod(methodName);
		method.setActionScope(JActionScope.PROTECTED);
		method.setParameters(parameters);
		method.addAnnonation(JAnnonation.createOverrideAnnonation());
		
		JCodeBlock jCodeBlock = new JCodeBlock();
		jCodeBlock.addCode(method.getCallSuperCode());
		method.setCodeBlock(jCodeBlock);
		return method;
	}
	
	private JMethod initProcessArgsMethod()
	{
		JMethod method = new JMethod("processArgs");
		method.setActionScope(JActionScope.PRIVATE);
		JCodeBlock jCodeBlock = new JCodeBlock();
		jCodeBlock.addCode("Bundle args = this.getIntent().getExtras();");
		method.setCodeBlock(jCodeBlock);
		return method;
	}
	
	private JMethod initSetupViewsMethod()
	{
		JMethod method = new JMethod("setupViews");
		method.setActionScope(JActionScope.PRIVATE);
		method.setCodeBlock(new JCodeBlock());
		return method;
	}
	
	public JMethod getOnCreateMethod()
	{
		return onCreateMethod;
	}
	
	public JMethod getOnResumeMethod()
	{
		return onResumeMethod;
	}
	
	public JMethod getOnStopMethod()
	{
		return onStopMethod;
	}
	
	public JMethod getOnDestroyMethod()
	{
		return onDestroyMethod;
	}

	public JMethod getProcessArgsMethod()
	{
		return processArgsMethod;
	}

	public JMethod getOnActivityResultMethod()
	{
		if(null == onActivityResultMethod)
		{
			Parameter requestCode = new Parameter("requestCode", Primatives.newIntegerClass());
			Parameter resultCode = new Parameter("resultCode", Primatives.newIntegerClass());
			Parameter intent = new Parameter("intent", ClassFactory.getClass(AndroidClass.Intent));
			
			onActivityResultMethod = new JMethod("onActivityResult");
			onActivityResultMethod.setParameters(requestCode, resultCode, intent);
			
			this.addMethod(onActivityResultMethod);
		}
		return onActivityResultMethod;
	}
}
