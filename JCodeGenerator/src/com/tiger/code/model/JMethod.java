package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;
import com.tiger.code.model.constant.JModifier;

public class JMethod extends JModel
{
	private String methodName = "method";
	
	private String actionScope = JActionScope.PUBLIC;
	
	private boolean isStatic = false;
	
	private boolean isSynchronized = false;
	
	private boolean isAbstract = false;
	
	private boolean isForInterface = false;
	
	private boolean isFinal = false;
	
	private ArrayList<Parameter> parameters;
	
	private JMethod(String actionScope, String methodName, 
			ArrayList<Parameter> parameters)
	{
		this.actionScope = actionScope;
		this.methodName = methodName;
		this.parameters = new ArrayList<JMethod.Parameter>();
		if(null != parameters)
		{
			this.parameters.addAll(parameters);
		}
	}
	
	public String getActionScope()
	{
		return actionScope;
	}
	
	public boolean isStatic()
	{
		return isStatic;
	}

	public void setStatic(boolean isStatic)
	{
		this.isStatic = isStatic;
	}

	public boolean isSynchronized()
	{
		return isSynchronized;
	}

	public void setSynchronized(boolean isSynchronized)
	{
		this.isSynchronized = isSynchronized;
	}

	public boolean isAbstract()
	{
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract)
	{
		this.isAbstract = isAbstract;
	}

	public boolean isForInterface()
	{
		return isForInterface;
	}

	public void setForInterface(boolean isForInterface)
	{
		this.isForInterface = isForInterface;
	}

	public ArrayList<Parameter> getParameters()
	{
		return parameters;
	}

	public String getMethodName()
	{
		return methodName;
	}
	
	public boolean isFinal()
	{
		return isFinal;
	}

	public void setFinal(boolean isFinal)
	{
		this.isFinal = isFinal;
	}
	
	@Override
	public String toString()
	{
		StringBuilder jCodeBuilder = new StringBuilder();
		
		//拼接方法的声明
		jCodeBuilder.append(actionScope);
		
		if(isSynchronized)
		{
			jCodeBuilder.append(JModifier.SYNCHRONIZED);
		}
		
		if(isStatic)
		{
			jCodeBuilder.append(JModifier.STATIC);
		}
		
		if(isFinal)
		{
			jCodeBuilder.append(JModifier.FINAL);
		}
		
		jCodeBuilder.append(methodName + JConstant.PARENTHESES_LEFT);
		
		//拼接所有参数
		for (int i = 0; i < parameters.size(); i++)
		{
			jCodeBuilder.append(parameters.get(i).toString());
			if(i < parameters.size() - 1)
			{
				jCodeBuilder.append(JConstant.COMMA);
			}
		}
		
		jCodeBuilder.append(methodName + JConstant.PARENTHESES_RIGHT);
		
		if(isAbstract || isForInterface)
		{
			jCodeBuilder.append(JConstant.SIMECOLON);
		}
		else
		{
			//拼接方法体
			jCodeBuilder.append(JIndentation.NEW_LINE + JIndentation.METHOD + 
					JConstant.BRACE_LEFT);
			
			jCodeBuilder.append(JIndentation.METHOD + JConstant.BRACE_RIGHT);
		}
		
		return jCodeBuilder.toString();
	}

	public static class Parameter extends JModel
	{
		private boolean isFinal = false;
		
		private String parameterName;
		
		private JClass parameterType;
		
		public Parameter(String parameterName, JClass parameterType)
		{
			this.parameterName = parameterName;
			this.parameterType = parameterType;
		}

		public boolean isFinal()
		{
			return isFinal;
		}

		public void setFinal(boolean isFinal)
		{
			this.isFinal = isFinal;
		}

		public String getParameterName()
		{
			return parameterName;
		}

		public JClass getParameterType()
		{
			return parameterType;
		}
		
		@Override
		public String toString()
		{
			StringBuilder jCodeBuilder = new StringBuilder();
			if(isFinal)
			{
				jCodeBuilder.append(JModifier.FINAL);
			}
			
			jCodeBuilder.append(JIndentation.BETWEEN + parameterType.getSimpleName() + 
					JIndentation.BETWEEN + parameterName);
			
			return jCodeBuilder.toString();
		}
	}
}
