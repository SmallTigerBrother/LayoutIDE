package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;

public class JInterface extends JModel
{
	public static final String MODEL_NAME = "interface";
	
	private JInterface superInterface;
	
	private JPackage jPackage = new JPackage();
	
	private String actionScope = JActionScope.DEFAULT;
	
	private String interfaceName;
	
	private ArrayList<JImport> imports;
	
	private ArrayList<JMethod> methods;
	
	private String simpleName = "JClass";
	
	public JInterface(JPackage jPackage, String actionScope, String simpleName, 
			JInterface superInterface)
	{
		if(null != jPackage)
		{
			this.jPackage = jPackage;
		}
		
		if(null != actionScope)
		{
			this.actionScope = actionScope;
		}
		
		if(null != simpleName)
		{
			this.simpleName = simpleName;
		}
		
		this.superInterface = superInterface;
		
		imports = new ArrayList<JImport>();
		methods = new ArrayList<JMethod>();
	}

	public String getInterfaceName()
	{
		return interfaceName;
	}

	public JPackage getjPackage()
	{
		return jPackage;
	}

	public String getActionScope()
	{
		return actionScope;
	}

	public String getSimpleName()
	{
		return simpleName;
	}

	public ArrayList<JMethod> getMethods()
	{
		return methods;
	}

	public ArrayList<JImport> getImports()
	{
		return imports;
	}

	public JInterface getSuperInterface()
	{
		return superInterface;
	}

	@Override
	public String toString()
	{
		StringBuilder jCodeBuilder = new StringBuilder();
		//拼接包名
		jCodeBuilder.append(jPackage.toString());
		
		//拼接import
		for (int i = 0; i < imports.size(); i++)
		{
			jCodeBuilder.append(imports.get(i).toString());
		}
		
		//拼接口的声明
		jCodeBuilder.append(JIndentation.NEW_LINE);
		jCodeBuilder.append(actionScope + MODEL_NAME + 
				JIndentation.BETWEEN + simpleName + JIndentation.NEW_LINE);
		jCodeBuilder.append(JConstant.BRACE_LEFT);
		
		//拼接方法
		for(int i = 0; i < methods.size(); i++)
		{
			jCodeBuilder.append(methods.get(i).toString());
			jCodeBuilder.append(JIndentation.NEW_LINE);
		}
		
		jCodeBuilder.append(JConstant.BRACE_RIGHT);
		
		return jCodeBuilder.toString();
	}
}
