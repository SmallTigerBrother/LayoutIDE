package com.tiger.code.model;

import java.util.ArrayList;
import java.util.List;

import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;

public class JClass extends JModel
{
	public static final String MODEL_NAME = "class";
	
	private JPackage jPackage = new JPackage();
	
	private String simpleName = "JClass";
	
	private String actionScope = JActionScope.DEFAULT;
	
	private JClass superClazz;
	
	private ArrayList<JInterface> implementInterfaces;
	
	private ArrayList<JImport> imports;
	
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
		
		imports = new ArrayList<JImport>();
		implementInterfaces = new ArrayList<JInterface>();
		fields = new ArrayList<JField>();
		methods = new ArrayList<JMethod>();
	}
	
	public void implementInterface(JInterface jInterface)
	{
		implementInterfaces.add(jInterface);
	}
	
	public void addImport(JImport jImport)
	{
		imports.add(jImport);
	}
	
	public void addField(JField field)
	{
		//����������
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
		methods.add(method);
	}
	
	public void addInterface(JInterface jInterface)
	{
		
	}
	
	public void addInnerClass(JInnerClass innerClass)
	{
		
	}
	

	@Override
	public String toString()
	{
		StringBuilder jCodeBuilder = new StringBuilder();
		//ƴ�Ӱ���
		jCodeBuilder.append(jPackage.toString());
		
		//ƴ��import
		for (int i = 0; i < imports.size(); i++)
		{
			jCodeBuilder.append(imports.get(i).toString());
		}
		
		//ƴ���������
		jCodeBuilder.append(JIndentation.NEW_LINE);
		jCodeBuilder.append(actionScope + MODEL_NAME + 
				JIndentation.BETWEEN + simpleName + JIndentation.NEW_LINE);
		jCodeBuilder.append(JConstant.BRACE_LEFT);
		
		//ƴ��ȫ�ֱ���
		for (int i = 0; i < fields.size(); i++)
		{
			jCodeBuilder.append(fields.get(i).toString() + JIndentation.NEW_LINE);
		}
		
		//TODO ƴ�ӷ���
		
		//TODO ƴ���ڲ���
		
		//TODO ƴ���ڲ��ӿ�
		
		
		jCodeBuilder.append(JConstant.BRACE_RIGHT);
		
		return jCodeBuilder.toString();
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
	
	
}
