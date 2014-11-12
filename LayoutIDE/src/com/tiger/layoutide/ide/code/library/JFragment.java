package com.tiger.layoutide.ide.code.library;

import android.app.Fragment;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JPackage;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.output.JCodeBuilder;

public class JFragment extends JClass
{
	private JMethod onCreateViewMethod;
	
	public JFragment(JPackage jPackage,String simpleClazzName)
	{
		super(jPackage, null, simpleClazzName, null);
		
		JClass superClass = ClassFactory.getClass(AndroidClass.Fragment);
		this.setSuperClass(superClass);
		
		Parameter inflater = new Parameter("inflater", ClassFactory.getClass(AndroidClass.LayoutInflater));
		Parameter container = new Parameter("container", ClassFactory.getClass(AndroidClass.ViewGroup));
		Parameter savedInstanceState = new Parameter("savedInstanceState", ClassFactory.getClass(AndroidClass.Bundle));
		
		onCreateViewMethod = new JMethod(actionScope, "onCreateView", inflater, container, savedInstanceState);
		onCreateViewMethod.setReturnType(ClassFactory.getClass(AndroidClass.View));
		onCreateViewMethod.setCodeBlock(new JCodeBlock());
		
		this.addMethod(onCreateViewMethod);
	}
	
	public JMethod getOnCreateViewMethod()
	{
		return onCreateViewMethod;
	}
}
