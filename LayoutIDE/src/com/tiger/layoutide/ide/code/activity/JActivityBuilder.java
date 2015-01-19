package com.tiger.layoutide.ide.code.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.mn.tiger.app.TGActionBarActivity;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JField;
import com.tiger.code.model.JImport;
import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.Primatives;
import com.tiger.layoutide.ide.code.library.AndroidClass;
import com.tiger.layoutide.ide.code.library.ClassFactory;
import com.tiger.layoutide.ide.code.library.JActivity;

public class JActivityBuilder
{
	private String packageName;
	
	private String simpleClazzName;
	
	private String layoutName;
	
	private ArrayList<JInterface> interfaces;
	
	private ArrayList<JField> fields;
	
	private JClass superClass = JClass.refClass(TGActionBarActivity.class);
	
	public JActivityBuilder(String packageName, String simpleClazzName)
	{
		interfaces = new ArrayList<JInterface>();
		fields = new ArrayList<JField>();
		this.packageName = packageName;
		this.simpleClazzName = simpleClazzName;
	}
	
	public void setSuperClass(JClass superClass)
	{
		this.superClass = superClass;
	}
	
	protected void buildOnCreate(JActivity activity, JCodeBlock onCreateBlock, String layoutName)
	{
		onCreateBlock.addCode("setContentView(R.layout." + layoutName +");");
	}
	
	protected void buildProcessArgs(JCodeBlock processArgsBlock)
	{
		processArgsBlock.addCode("Bundle args = this.getIntent().getExtras();");
	}
	
	protected void buildSetupViews(JActivity activity, JCodeBlock setupViewsBlock)
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
	
	protected void buildOnResume(JCodeBlock onResumeBlock)
	{
	}
	
	protected void buildOnStop(JCodeBlock onStopBlock)
	{
	}
	
	protected void buildOnDestroy(JCodeBlock onDestroyBlock)
	{
	}
	
	protected JCodeBlock buildOnActivityResult()
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
	
	public void setLayoutName(String layoutName)
	{
		this.layoutName = layoutName;
	}
	
	public String getLayoutName()
	{
		return layoutName;
	}
	
	public void addFields(List<JField> fields)
	{
		this.fields.addAll(fields);
	}
	
	public void addField(JField field)
	{
		this.fields.add(field);
	}
	
	public void implementsInterfaces(List<JInterface> interfaces)
	{
		this.interfaces.addAll(interfaces);
	}
	
	public void implementsInterface(JInterface jInterface)
	{
		this.interfaces.add(jInterface);
	}
	
	public JActivity buildActivity()
	{
		JActivity activity = new JActivity(packageName, simpleClazzName);
		activity.addImport(new JImport(JClass.refClass(ViewInjector.class)));
		activity.setSuperClass(this.superClass);
		activity.implementInterfaces(interfaces);
		
		activity.addFields(fields);
		
		Parameter parameter = new Parameter("savedInstanceState", 
				JClass.refClass(Bundle.class));
		JMethod onCreateMethod = initSuperMethod("onCreate", parameter);
		buildOnCreate(activity, onCreateMethod.getCodeBlock(), layoutName);
		activity.setOnCreateMethod(onCreateMethod);
		
		onCreateMethod.getCodeBlock().addCode("ViewInjector.initInjectedView(this, this);");
		
		JMethod processArgsMethod = initProcessArgsMethod();
		buildProcessArgs(processArgsMethod.getCodeBlock());
		activity.addMethod(processArgsMethod);
		onCreateMethod.getCodeBlock().addCode(processArgsMethod.getCallCode());
		
		JMethod setupViewsMethod = initSetupViewsMethod();
		onCreateMethod.getCodeBlock().addCode(setupViewsMethod.getCallCode());
		buildSetupViews(activity, setupViewsMethod.getCodeBlock());
		activity.addMethod(setupViewsMethod);

		JMethod onResumeMethod = initSuperMethod("onResume");
		buildOnResume(onResumeMethod.getCodeBlock());
		activity.setOnResumeMethod(onResumeMethod);
		
		JMethod onStopMethod = initSuperMethod("onStop");
		buildOnStop(onStopMethod.getCodeBlock());
		activity.setOnStopMethod(onStopMethod);

		JMethod onDestroyMethod = initSuperMethod("onDestroy");
		buildOnDestroy(onDestroyMethod.getCodeBlock());
		activity.setOnDestroyMethod(onDestroyMethod);
		
		JCodeBlock onActivityResultBlock = buildOnActivityResult();
		if(null != onActivityResultBlock)
		{
			JMethod jMethod = buildOnActivityResultMethod();
			jMethod.setCodeBlock(onActivityResultBlock);
		}
		
		return activity;
	}
	
}
