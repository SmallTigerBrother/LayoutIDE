package com.tiger.code.model;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.constant.JKeyWords;
import com.tiger.code.output.JCodeBuilder;

public class JMethod extends JCodeModel
{
	private static final long serialVersionUID = 1L;

	public static final JMethod NULL_METHOD = new JMethod();
	
	private String methodName = "method";
	
	private String actionScope = JActionScope.PUBLIC;
	
	private boolean isStatic = false;
	
	private boolean isSynchronized = false;
	
	private boolean isAbstract = false;
	
	private boolean isForInterface = false;
	
	private boolean isFinal = false;
	
	private Parameter[]  parameters;
	
	private ArrayList<JAnnonation> annonations;
	
	private JCodeBlock jCodeBlock;
	
	private JClass returnType;
	
	private JMethod()
	{}
	
	public JMethod(String methodName)
	{
		this.methodName = methodName;
		annonations = new ArrayList<JAnnonation>();
		returnType = Primatives.newVoidClass();
	}
	
	public void setActionScope(String actionScope)
	{
		this.actionScope = actionScope;
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
	
	public void setParameters(Parameter... parameters)
	{
		this.parameters = parameters;
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
		//ƴ��ע��
		for(int i = 0; i < annonations.size(); i++)
		{
			jCodeBuilder.appendWithIndentation(annonations.get(i).toString());
		}
		
		//ƴ�ӷ���������
		jCodeBuilder.appendWithIndentation(actionScope);
		
		if(isSynchronized)
		{
			jCodeBuilder.append(JKeyWords.SYNCHRONIZED);
		}
		
		if(isStatic)
		{
			jCodeBuilder.append(JKeyWords.STATIC);
		}
		
		if(isFinal)
		{
			jCodeBuilder.append(JKeyWords.FINAL);
		}
		
		jCodeBuilder.append(returnType.toString() + methodName + JConstant.PARENTHESES_LEFT);
		
		//ƴ�����в���
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
		
		//���󷽷����ӿڷ����޷�����
		if(isAbstract || isForInterface)
		{
			jCodeBuilder.append(JConstant.SIMECOLON);
		}
		else
		{
			//ƴ�ӷ�����
			jCodeBuilder.append(JIndentation.NEW_LINE);
			jCodeBuilder.appendWithIndentation(JConstant.BRACE_LEFT + 
					JIndentation.NEW_LINE);
			
			//ƴ�ӷ�����
			if(null != jCodeBlock)
			{
				jCodeBlock.setIndentation(getIndentation() + JIndentation.METHOD);
				jCodeBuilder.append(jCodeBlock.toString());
				jCodeBuilder.setIndentation(getIndentation());
			}
			
			jCodeBuilder.appendWithIndentation(JConstant.BRACE_RIGHT + JIndentation.NEW_LINE);
		}
		
		return jCodeBuilder;
	}
	
	public JCodeModel getCallSuperCode()
	{
		JCodeBuilder jCodeBuilder = new JCodeBuilder();
		jCodeBuilder.append(JKeyWords.SUPER + JConstant.POINT + 
				methodName + JConstant.PARENTHESES_LEFT);
		//ƴ�����в���
		if(null != parameters)
		{
			for (int i = 0; i < parameters.length; i++)
			{
				jCodeBuilder.append(parameters[i].getParameterName());
				if(i < parameters.length - 1)
				{
					jCodeBuilder.append(JConstant.COMMA + JIndentation.BETWEEN);
				}
			}
		}
		jCodeBuilder.append(JConstant.PARENTHESES_RIGHT + JConstant.SIMECOLON);
		
		JCodeModel jCodeModel = new JCodeModel();
		jCodeModel.setCodeString(jCodeBuilder.toString());
		
		return jCodeModel;
	}
	
	public JCodeModel getCallCode(String... params)
	{
		JCodeBuilder jCodeBuilder = new JCodeBuilder();
		jCodeBuilder.append(methodName + JConstant.PARENTHESES_LEFT);
		if(null != params && params.length > 0)
		{
			//ƴ�����в���
			for (int i = 0; i < params.length; i++)
			{
				jCodeBuilder.append(params[i]);
				if(i < params.length - 1)
				{
					jCodeBuilder.append(JConstant.COMMA + JIndentation.BETWEEN);
				}
			}
		}
		
		jCodeBuilder.append(JConstant.PARENTHESES_RIGHT + JConstant.SIMECOLON);
		
		JCodeModel jCodeModel = new JCodeModel();
		jCodeModel.setCodeString(jCodeBuilder.toString());
		
		return jCodeModel;
	}
	
	public JCodeBlock getCodeBlock()
	{
		return jCodeBlock;
	}

	public void setCodeBlock(JCodeBlock jCodeBlock)
	{
		this.jCodeBlock = jCodeBlock;
	}

	public JClass getReturnType()
	{
		return returnType;
	}

	public void setReturnType(JClass returnType)
	{
		this.returnType = returnType;
	}
	
	/**
	 * @param method
	 * @return
	 */
	public static JMethod refMethod(Method method)
	{
		JMethod jMethod = new JMethod(method.getName());
		
		int modifiers = method.getModifiers();
		if(!Modifier.isFinal(modifiers) && !Modifier.isPrivate(modifiers) && 
				!Modifier.isStatic(modifiers))
		{
			jMethod.setAbstract(Modifier.isAbstract(modifiers));
			jMethod.setSynchronized(Modifier.isSynchronized(modifiers));
			jMethod.setActionScope(getActionScope(modifiers));
			jMethod.setReturnType(JClass.refClass(method.getReturnType()));
			jMethod.setParameters(getParameters(method));
			return jMethod;
		}
		
		return NULL_METHOD;
	}
	
	private static Parameter[] getParameters(Method method)
	{
		Class<?>[] paramClasses = method.getParameterTypes();
		int count = paramClasses.length;
		if(null != paramClasses && count > 0)
		{
			Parameter[] parameters = new Parameter[count];
			Parameter parameter;
			for(int i = 0; i < count; i++)
			{
				parameter = new Parameter("arg_" + i, JClass.refClass(paramClasses[i]));
				parameters[i] = parameter;
			}
			return parameters;
		}
		
		return null;
	}
	
	public static class Parameter extends JCodeModel
	{
		private static final long serialVersionUID = 1L;

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
				jCodeBuilder.append(JKeyWords.FINAL);
			}
			
			jCodeBuilder.append(parameterType.getSimpleName() + JIndentation.BETWEEN + 
					parameterName);
			
			return jCodeBuilder;
		}
	}
}
