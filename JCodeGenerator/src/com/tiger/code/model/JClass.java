package com.tiger.code.model;

import java.util.ArrayList;
import java.util.List;

import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;
import com.tiger.code.model.constant.JModifier;
import com.tiger.code.model.output.JCodeBuilder;

public class JClass extends JModel
{
	public static final String MODEL_NAME = "class";
	
	private JPackage jPackage = new JPackage();
	
	private String simpleName = "JClass";
	
	private String actionScope = JActionScope.DEFAULT;
	
	private JClass superClazz;
	
	private ArrayList<JInterface> implementInterfaces;
	
	private ImportList imports;
	
	private ArrayList<JField> fields;
	
	private ArrayList<JMethod> methods;
	
	public JClass(JPackage jPackage, String actionScope, String simpleClazzName, 
			JClass superClaszz)
	{
		if(null != jPackage)
		{
			this.jPackage = jPackage;
		}
		
		if(null != actionScope)
		{
			this.actionScope = actionScope;
		}
		
		if(null != simpleClazzName)
		{
			this.simpleName = simpleClazzName;
		}
		
		this.superClazz = superClaszz;
		
		imports = new ImportList();
		implementInterfaces = new ArrayList<JInterface>();
		fields = new ArrayList<JField>();
		methods = new ArrayList<JMethod>();
	}
	
	public void implementInterface(JInterface jInterface)
	{
		implementInterfaces.add(jInterface);
		//插入接口声明的所有方法
		JMethod jMethod = null;
		for(int i = 0; i < jInterface.getMethods().size(); i++)
		{
			jMethod = jInterface.getMethods().get(i);
			jMethod.addAnnonation(JAnnonation.createOverrideAnnonation());
			methods.add(jMethod);
		}
	}
	
	public void addImport(JImport jImport)
	{
		imports.add(jImport);
	}
	
	public void addField(JField field)
	{
		//加入引用类
		imports.add(new JImport(field.getValueType()));
		fields.add(field);
	}
	
	public void addFields(List<JField> fields)
	{
		for (int i = 0; i < fields.size(); i++)
		{
			addField(fields.get(i));
		}
	}
	
	public void addMethod(JMethod method)
	{
		Parameter[] parameters = method.getParameters();
		if(null != parameters)
		{
			for(int i = 0; i < parameters.length; i++)
			{
				imports.add(new JImport(parameters[i].getParameterType()));
			}
		}
		methods.add(method);
	}
	
	public void addMethods(List<JMethod> methods)
	{
		for (int i = 0; i < methods.size(); i++)
		{
			addMethod(methods.get(i));
		}
	}
	
	public void addInterface(JInterface jInterface)
	{
		
	}
	
	public void addInnerClass(JInnerClass innerClass)
	{
		
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		//设置缩进
		setIndentation(JIndentation.FIELD);
		
		//拼接包名
		jCodeBuilder.append(jPackage.toString());
		
		//拼接import
		for (int i = 0; i < imports.size(); i++)
		{
			jCodeBuilder.append(imports.get(i).toString());
		}
		
		//拼接类的声明
		jCodeBuilder.append(JIndentation.NEW_LINE);
		jCodeBuilder.append(actionScope + MODEL_NAME + 
				JIndentation.BETWEEN + simpleName);
		
		//拼接基类
		if(null != superClazz)
		{
			jCodeBuilder.append(JIndentation.BETWEEN + JModifier.EXTENDS + 
					superClazz.getSimpleName());
		}
		
		//拼接实现的接口
		if(implementInterfaces.size() > 0)
		{
			jCodeBuilder.append(JIndentation.BETWEEN + JModifier.IMPLEMENTS);
			for(int i = 0; i < implementInterfaces.size(); i++)
			{
				jCodeBuilder.append(implementInterfaces.get(i).getSimpleName());
				if(i != implementInterfaces.size() - 1)
				{
					jCodeBuilder.append(JConstant.COMMA + JIndentation.BETWEEN);
				}
			}
		}
		
		jCodeBuilder.append(JIndentation.NEW_LINE);
		jCodeBuilder.append(JConstant.BRACE_LEFT);
		
		//拼接全局变量
		for (int i = 0; i < fields.size(); i++)
		{
			fields.get(i).setIndentation(getIndentation());
			jCodeBuilder.append(fields.get(i).toString() + JIndentation.NEW_LINE);
		}
		
		//拼接方法(接口方法已在加入接口时插入到methods中)
		for(int i = 0; i < methods.size(); i++)
		{
			methods.get(i).setIndentation(getIndentation());
			jCodeBuilder.append(methods.get(i).toString() + JIndentation.NEW_LINE);
		}
		
		//TODO 拼接内部类
		
		//TODO 拼接内部接口
		
		
		jCodeBuilder.append(JConstant.BRACE_RIGHT);
		
		return jCodeBuilder;
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
	
	public String getClassName()
	{
		return jPackage.getPackageName() + JConstant.POINT + simpleName;
	}

	public JClass getSuperClazz()
	{
		return superClazz;
	}

	public ArrayList<JInterface> getImplementInterfaces()
	{
		return implementInterfaces;
	}
	
	public static class ImportList extends ArrayList<JImport>
	{
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(JImport object)
		{
			for(int i = 0; i < this.size(); i++)
			{
				if(this.get(i).toString().equals(object.toString()))
				{
					return true;
				}
			}
			return super.add(object);
		}
	}
}
