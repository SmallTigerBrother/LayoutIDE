package com.tiger.layoutide.ide.code.library;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.JPackage;
import com.tiger.code.output.JCodeBuilder;

public class JActivity extends JClass
{
	private JMethod onCreateMethod;
	
	private JMethod onResumeMethod;
	
	private JMethod onStopMethod;
	
	private JMethod onDestroyMethod;
	
	public JActivity(JPackage jPackage, String actionScope, 
			String simpleClazzName, JClass superClaszz)
	{
		super(jPackage, actionScope, simpleClazzName, superClaszz);
		
		//����onCreate����
		Parameter parameter = new Parameter("savedInstanceState", 
				ClassDictionary.getClass(AndroidClass.Bundle));
		onCreateMethod = initSuperMethod("onCreate", parameter);
		
		//����onResume����
		onResumeMethod = initSuperMethod("onResume", new Parameter[]{});
		
		//����onStop����
		
		onStopMethod = initSuperMethod("onStop", new Parameter[]{});
		
		//����onDestroy����
		onDestroyMethod = initSuperMethod("onDestroy", new Parameter[]{});
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
	
	@Override
	protected JCodeBuilder appendMethods(JCodeBuilder jCodeBuilder)
	{
		//��д�����ķ�������д����������
		onCreateMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(onCreateMethod.toString());
		
		onResumeMethod.setIndentation(jCodeBuilder.getIndentation());
		jCodeBuilder.append(onResumeMethod.toString());
		
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
}
