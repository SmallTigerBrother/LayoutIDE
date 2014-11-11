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
	
	public JActivity(JPackage jPackage, String actionScope, 
			String simpleClazzName, JClass superClaszz)
	{
		super(jPackage, actionScope, simpleClazzName, superClaszz);
		
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
	}
	
	private JMethod initSuperMethod(String methodName, Parameter... parameters)
	{
		JMethod method = new JMethod(JActionScope.PROTECTED, "onDestroy", parameters);
		
		method.addAnnonation(JAnnonation.createOverrideAnnonation());
		
		JCodeBlock jCodeBlock = new JCodeBlock();
		jCodeBlock.addCode(method.getCallSuperCode());
		method.setCodeBlock(jCodeBlock);
		return method;
	}
	
	private JMethod initProcessArgsMethod()
	{
		JMethod method = new JMethod(JActionScope.PRIVATE, "processArgs", new Parameter[]{});
		JCodeBlock jCodeBlock = new JCodeBlock();
		jCodeBlock.addCode("Bundle args = this.getIntent().getExtras();");
		method.setCodeBlock(jCodeBlock);
		return method;
	}
	
	private JMethod initSetupViewsMethod()
	{
		JMethod method = new JMethod(JActionScope.PRIVATE, "setupViews", new Parameter[]{});
		method.setCodeBlock(new JCodeBlock());
		return method;
	}
	
	@Override
	protected JCodeBuilder appendMethods(JCodeBuilder jCodeBuilder)
	{
		//先写入基类的方法，再写入其他方法
		onCreateMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(onCreateMethod.toString());
		
		processArgsMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(onCreateMethod.toString());
		
		setupViewsMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(setupViewsMethod.toString());
		
		onResumeMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(onResumeMethod.toString());
		
		if(null != onActivityResultMethod)
		{
			onActivityResultMethod.setIndentation(jCodeBuilder.getIndentation());
			jCodeBuilder.append(onActivityResultMethod.toString());
		}
		
		onStopMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(onStopMethod.toString());
		
		onDestroyMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(onDestroyMethod.toString());
		
		return super.appendMethods(jCodeBuilder);
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
			
			onActivityResultMethod = new JMethod(JActionScope.PUBLIC, "onActivityResult", 
					requestCode, resultCode, intent);
		}
		return onActivityResultMethod;
	}
}
