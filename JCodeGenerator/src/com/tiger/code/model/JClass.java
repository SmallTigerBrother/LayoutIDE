package com.tiger.code.model;

import java.util.ArrayList;
import java.util.List;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.constant.JKeyWords;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.output.JCodeBuilder;

public class JClass extends JCodeModel
{
	public static final String MODEL_NAME = "class";
	
	private JPackage jPackage;
	
	private String simpleName = "JClass";
	
	private String actionScope = JActionScope.PUBLIC;
	
	private JClass superClazz;
	
	private ArrayList<JInterface> implementInterfaces;
	
	private ImportList imports;
	
	private ArrayList<JField> fields;
	
	private ArrayList<JMethod> methods;
	
	private JGeneric generic;
	
	public JClass(JPackage jPackage, String simpleClazzName)
	{
		this.jPackage = jPackage;
		
		this.simpleName = simpleClazzName;
		
		imports = new ImportList();
		implementInterfaces = new ArrayList<JInterface>();
		fields = new ArrayList<JField>();
		methods = new ArrayList<JMethod>();
	}
	
	public void implementInterface(JInterface jInterface)
	{
		implementInterfaces.add(jInterface);
		//import�ӿ�
		imports.add(new JImport(jInterface));
		//����ӿ����������з���
		JMethod jMethod = null;
		for(int i = 0; i < jInterface.getMethods().size(); i++)
		{
			jMethod = jInterface.getMethods().get(i);
			jMethod.addAnnonation(JAnnonation.createOverrideAnnonation());
			addMethod(jMethod);
		}
	}
	
	public void implementInterfaces(List<JInterface> jInterfaces)
	{
		for(int i = 0; i < jInterfaces.size(); i++)
		{
			implementInterface(jInterfaces.get(i));
		}
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
		//��������
		jCodeBuilder.setIndentation(JIndentation.FIELD);
		
		//ƴ�Ӱ���
		if(null != jPackage)
		{
			jCodeBuilder.append(jPackage.toString());
		}
		
		//ƴ��import
		for (int i = 0; i < imports.size(); i++)
		{
			jCodeBuilder.append(imports.get(i).toString());
		}
		
		//ƴ���������
		jCodeBuilder.append(JIndentation.NEW_LINE);
		jCodeBuilder.append(actionScope + MODEL_NAME + 
				JIndentation.BETWEEN + simpleName);
		
		if(null != generic)
		{
			jCodeBuilder.append(generic.toString());
		}
		
		//ƴ�ӻ���
		if(null != superClazz)
		{
			jCodeBuilder.append(JKeyWords.EXTENDS + 
					superClazz.getSimpleName());
		}
		
		//ƴ��ʵ�ֵĽӿ�
		if(implementInterfaces.size() > 0)
		{
			jCodeBuilder.append(JKeyWords.IMPLEMENTS);
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
		
		//ƴ��ȫ�ֱ���
		jCodeBuilder = appendFields(jCodeBuilder);
		
		//ƴ�ӷ���(�ӿڷ������ڼ���ӿ�ʱ���뵽methods��)
		jCodeBuilder = appendMethods(jCodeBuilder);
		
		//TODO ƴ���ڲ���
		
		//TODO ƴ���ڲ��ӿ�
		
		
		jCodeBuilder.append(JConstant.BRACE_RIGHT);
		
		return jCodeBuilder;
	}

	protected JCodeBuilder appendFields(JCodeBuilder jCodeBuilder)
	{
		for (int i = 0; i < fields.size(); i++)
		{
			fields.get(i).setIndentation(jCodeBuilder.getIndentation());
			jCodeBuilder.append(fields.get(i).toString() + JIndentation.NEW_LINE);
		}
		return jCodeBuilder;
	}
	
	protected JCodeBuilder appendMethods(JCodeBuilder jCodeBuilder)
	{
		for (int i = 0; i < methods.size(); i++)
		{
			methods.get(i).setIndentation(jCodeBuilder.getIndentation());
			jCodeBuilder.append(methods.get(i).toString() + JIndentation.NEW_LINE);
		}
		return jCodeBuilder;
	}
	
	public JPackage getPackage()
	{
		return jPackage;
	}

	public String getActionScope()
	{
		return actionScope;
	}

	public String getSimpleName()
	{
		if(null != generic)
		{
			return simpleName + generic.toString();
		}
		return simpleName;
	}
	
	public String getClassName()
	{
		String className;
		if(null != jPackage)
		{
			className = jPackage.getPackageName() + JConstant.POINT + simpleName;
		}
		else
		{
			className = simpleName;
		}
		
		if(null != generic)
		{
			return className + generic.toString();
		}
		return className;
	}

	public void setSuperClass(JClass superClazz)
	{
		this.superClazz = superClazz;
		//import����
		imports.add(new JImport(superClazz));
	}
	
	public JClass getSuperClazz()
	{
		return superClazz;
	}

	public ArrayList<JInterface> getImplementInterfaces()
	{
		return implementInterfaces;
	}
	
	public JGeneric getGeneric()
	{
		return generic;
	}
	
	public void setGeneric(JGeneric generic)
	{
		this.generic = generic;
		for(int i = 0; i < generic.getWildCards().size(); i++)
		{
			if(null != generic.getWildCards().get(i).getSuperClass())
			{
				imports.add(new JImport(generic.getWildCards().get(i).getSuperClass()));
			}
		}
	}
	
	public static class ImportList extends ArrayList<JImport>
	{
		private static final long serialVersionUID = 1L;

		@Override
		public boolean add(JImport object)
		{
			if(null == object.getPackage())
			{
				return true;
			}
			
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
