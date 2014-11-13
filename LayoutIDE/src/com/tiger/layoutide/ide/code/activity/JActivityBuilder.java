package com.tiger.layoutide.ide.code.activity;

import java.util.ArrayList;
import java.util.List;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JField;
import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.Primatives;
import com.tiger.layoutide.ide.code.library.AndroidClass;
import com.tiger.layoutide.ide.code.library.ClassFactory;
import com.tiger.layoutide.ide.code.library.JActivity;

public abstract class JActivityBuilder
{
	private String packageName;
	
	private String simpleClazzName;
	
	private String layoutName;
	
	private ArrayList<JInterface> interfaces;
	
	private ArrayList<JField> fields;
	
	public JActivityBuilder()
	{
		interfaces = new ArrayList<JInterface>();
		fields = new ArrayList<JField>();
	}
	
	public JActivityBuilder(String packageName, String simpleClazzName)
	{
		this();
		this.packageName = packageName;
		this.simpleClazzName = simpleClazzName;
	}
	
	public abstract JClass buildSuperClass();
	
	public void buildOnCreate(JActivity activity, JCodeBlock onCreateBlock, String layoutName)
	{
		//添加setContentView方法
		onCreateBlock.addCode("setContentView(R.layout." + layoutName +");");
	}
	
	public void buildProcessArgs(JCodeBlock processArgsBlock)
	{
		processArgsBlock.addCode("Bundle args = this.getIntent().getExtras();");
	}
	
	public void buildSetupViews(JActivity activity, JCodeBlock setupViewsBlock)
	{
	}
	
	private JMethod initProcessArgsMethod()
	{
		JMethod method = new JMethod("processArgs");
		method.setActionScope(JActionScope.PRIVATE);
		JCodeBlock jCodeBlock = new JCodeBlock();
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
	
	public void buildOnResume(JCodeBlock onResumeBlock)
	{
	}
	
	public void buildOnStop(JCodeBlock onStopBlock)
	{
	}
	
	public void buildOnDestroy(JCodeBlock onDestroyBlock)
	{
	}
	
	public JCodeBlock buildOnActivityResult()
	{
		return null;
	}
	
	protected final JMethod buildOnActivityResultMethod()
	{
		Parameter requestCode = new Parameter("requestCode", Primatives.newIntegerClass());
		Parameter resultCode = new Parameter("resultCode", Primatives.newIntegerClass());
		Parameter intent = new Parameter("intent", ClassFactory.getClass(AndroidClass.Intent));
		
		JMethod onActivityResultMethod = new JMethod("onActivityResult");
		onActivityResultMethod.addAnnonation(JAnnonation.createOverrideAnnonation());
		onActivityResultMethod.setParameters(requestCode, resultCode, intent);
		
		return onActivityResultMethod;
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
	
	public abstract JMethod buildOnRequestSuccess(JActivity activity);
	
	public void setLayoutName(String layoutName)
	{
		this.layoutName = layoutName;
	}
	
	public String getLayoutName()
	{
		return layoutName;
	}
	
	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}
	
	public void setSimpleClassName(String simpleClazzName)
	{
		this.simpleClazzName = simpleClazzName;
	}
	
	public void addFields(List<JField> fields)
	{
		this.fields.addAll(fields);
	}
	
	public void implementsInterfaces(List<JInterface> interfaces)
	{
		this.interfaces.addAll(interfaces);
	}
	
	public JActivity buildActivity()
	{
		JActivity activity = new JActivity(packageName, simpleClazzName);
		activity.setSuperClass(buildSuperClass());
		
		activity.implementInterfaces(interfaces);
		
		activity.addFields(fields);
		
		//加入onCreate方法
		Parameter parameter = new Parameter("savedInstanceState", 
				ClassFactory.getClass(AndroidClass.Bundle));
		JMethod onCreateMethod = initSuperMethod("onCreate", parameter);
		buildOnCreate(activity, onCreateMethod.getCodeBlock(), layoutName);
		activity.setOnCreateMethod(onCreateMethod);
		
		//加入processArgs方法
		JMethod processArgsMethod = initProcessArgsMethod();
		buildProcessArgs(processArgsMethod.getCodeBlock());
		activity.addMethod(processArgsMethod);
		onCreateMethod.getCodeBlock().addCode(processArgsMethod.getCallCode());
		
		//加入setupViews方法
		JMethod setupViewsMethod = initSetupViewsMethod();
		onCreateMethod.getCodeBlock().addCode(setupViewsMethod.getCallCode());
		buildSetupViews(activity, setupViewsMethod.getCodeBlock());
		activity.addMethod(setupViewsMethod);

		// 加入onResume方法
		JMethod onResumeMethod = initSuperMethod("onResume");
		buildOnResume(onResumeMethod.getCodeBlock());
		activity.setOnResumeMethod(onResumeMethod);
		
		// 加入onStop方法
		JMethod onStopMethod = initSuperMethod("onStop");
		buildOnStop(onStopMethod.getCodeBlock());
		activity.setOnStopMethod(onStopMethod);

		// 加入onDestroy方法
		JMethod onDestroyMethod = initSuperMethod("onDestroy");
		buildOnDestroy(onDestroyMethod.getCodeBlock());
		activity.setOnDestroyMethod(onDestroyMethod);
		
		//加入onActivityResult方法
		JCodeBlock onActivityResultBlock = buildOnActivityResult();
		if(null != onActivityResultBlock)
		{
			JMethod jMethod = buildOnActivityResultMethod();
			jMethod.setCodeBlock(onActivityResultBlock);
		}
		
		JMethod onRequestSuccessMethod = buildOnRequestSuccess(activity);
		if(null != onRequestSuccessMethod)
		{
			activity.addMethod(onRequestSuccessMethod);
		}
		
		return activity;
	}
	
}
