package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.model.JClass.ImportList;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.output.JCodeBuilder;

public class JInterface extends JCodeModel
{
	public static final String MODEL_NAME = "interface";
	
	private JInterface superInterface;
	
	private JPackage jPackage = new JPackage();
	
	private String actionScope = JActionScope.PUBLIC;
	
	private ImportList imports;
	
	private ArrayList<JMethod> methods;
	
	private String simpleName = "JClass";
	
	public JInterface(String packageName, String simpleName)
	{
		this.jPackage = new JPackage(packageName);
		
		if(null == simpleName)
		{
			throw new NullPointerException("simpleName can not be null");
		}
		
		this.simpleName = simpleName;
		
		imports = new ImportList();
		methods = new ArrayList<JMethod>();
	}
	
	

	public String getInterfaceName()
	{
		return jPackage.getPackageName() + JConstant.POINT + simpleName;
	}

	public JPackage getPackage()
	{
		return jPackage;
	}
	
	public void setActionScope(String actionScope)
	{
		this.actionScope = actionScope;
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
	
	public void addMethod(JMethod jMethod)
	{
		Parameter[] parameters = jMethod.getParameters();
		if(null != parameters)
		{
			for(int i = 0; i < parameters.length; i++)
			{
				imports.add(new JImport(parameters[i].getParameterType()));
			}
		}
		methods.add(jMethod);
	}

	public ArrayList<JImport> getImports()
	{
		return imports;
	}
	
	public void setSuperInterface(JInterface superInterface)
	{
		this.superInterface = superInterface;
	}

	public JInterface getSuperInterface()
	{
		return superInterface;
	}

	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.setIndentation(JIndentation.FIELD);
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
			methods.get(i).setIndentation(jCodeBuilder.getIndentation());
			jCodeBuilder.append(methods.get(i).toString());
			jCodeBuilder.append(JIndentation.NEW_LINE);
		}
		
		jCodeBuilder.append(JConstant.BRACE_RIGHT);
		
		return jCodeBuilder;
	}
}
