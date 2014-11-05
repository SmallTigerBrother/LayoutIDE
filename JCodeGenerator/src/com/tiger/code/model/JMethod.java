package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;
import com.tiger.code.model.constant.JModifier;
import com.tiger.code.model.output.JCodeBuilder;

public class JMethod extends JModel
{
	private String methodName = "method";
	
	private String actionScope = JActionScope.PUBLIC;
	
	private boolean isStatic = false;
	
	private boolean isSynchronized = false;
	
	private boolean isAbstract = false;
	
	private boolean isForInterface = false;
	
	private boolean isFinal = false;
	
	private Parameter[]  parameters;
	
	private ArrayList<JAnnonation> annonations;
	
	public JMethod(String actionScope, String methodName, 
			Parameter... parameters)
	{
		this.actionScope = actionScope;
		this.methodName = methodName;
		this.parameters = parameters;
		annonations = new ArrayList<JAnnonation>();
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

	public Parameter[] getParameters()
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
	
	public ArrayList<JAnnonation> getAnnonations()
	{
		return annonations;
	}
	
	public void addAnnonation(JAnnonation annonation)
	{
		annonations.add(annonation);
	}

	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		//拼接注解
		for(int i = 0; i < annonations.size(); i++)
		{
			jCodeBuilder.appendWithIndentation(annonations.get(i).toString());
		}
		
		//拼接方法的声明
		jCodeBuilder.appendWithIndentation(actionScope);
		
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
		if(null != parameters)
		{
			for (int i = 0; i < parameters.length; i++)
			{
				jCodeBuilder.append(parameters[i].toString());
				if(i < parameters.length - 1)
				{
					jCodeBuilder.append(JConstant.COMMA + JIndentation.BETWEEN);
				}
			}
		}
		
		jCodeBuilder.append(JConstant.PARENTHESES_RIGHT);
		
		if(isAbstract || isForInterface)
		{
			jCodeBuilder.append(JConstant.SIMECOLON);
		}
		else
		{
			//拼接方法体
			jCodeBuilder.append(JIndentation.NEW_LINE);
			jCodeBuilder.appendWithIndentation(JConstant.BRACE_LEFT + 
					JIndentation.NEW_LINE);
			
			
			jCodeBuilder.appendWithIndentation(JConstant.BRACE_RIGHT + JIndentation.NEW_LINE);
		}
		
		return jCodeBuilder;
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
		public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
		{
			if(isFinal)
			{
				jCodeBuilder.append(JModifier.FINAL);
			}
			
			jCodeBuilder.append(parameterType.getSimpleName() + JIndentation.BETWEEN + 
					parameterName);
			
			return jCodeBuilder;
		}
	}
}
