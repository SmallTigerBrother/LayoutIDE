package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;

public class JFragment extends JClass
{
	private JMethod onCreateViewMethod;
	
	public JFragment(String packageName, String simpleClazzName)
	{
		super(packageName, simpleClazzName);
		
		JClass superClass = ClassFactory.getClass(AndroidClass.Fragment);
		this.setSuperClass(superClass);
		
		Parameter inflater = new Parameter("inflater", ClassFactory.getClass(AndroidClass.LayoutInflater));
		Parameter container = new Parameter("container", ClassFactory.getClass(AndroidClass.ViewGroup));
		Parameter savedInstanceState = new Parameter("savedInstanceState", ClassFactory.getClass(AndroidClass.Bundle));
		
		onCreateViewMethod = new JMethod("onCreateView");
		onCreateViewMethod.setParameters(inflater, container, savedInstanceState);
		onCreateViewMethod.setReturnType(ClassFactory.getClass(AndroidClass.View));
		onCreateViewMethod.setCodeBlock(new JCodeBlock());
		
		this.addMethod(onCreateViewMethod);
	}
	
	public JMethod getOnCreateViewMethod()
	{
		return onCreateViewMethod;
	}
}
