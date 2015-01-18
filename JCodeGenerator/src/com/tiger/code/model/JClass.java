package com.tiger.code.model;

import java.lang.reflect.Method;
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
	private static final long serialVersionUID = 1L;

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
	
	private boolean isAbstract = false;
	
	public JClass(String packageName, String simpleClazzName)
	{
		this.jPackage = new JPackage(packageName);
		
		this.simpleName = simpleClazzName;
		
		imports = new ImportList();
		implementInterfaces = new ArrayList<JInterface>();
		fields = new ArrayList<JField>();
		methods = new ArrayList<JMethod>();
	}
	
	public void implementInterface(JInterface jInterface)
	{
		implementInterfaces.add(jInterface);
		//import接口
		imports.add(new JImport(jInterface));
		//插入接口声明的所有方法
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
		if(method.equals(JMethod.NULL_METHOD))
		{
			return;
		}
		
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
		jCodeBuilder.setIndentation(JIndentation.FIELD);
		
		//拼接包名
		if(null != jPackage)
		{
			jCodeBuilder.append(jPackage.toString());
		}
		
		//拼接import
		for (int i = 0; i < imports.size(); i++)
		{
			jCodeBuilder.append(imports.get(i).toString());
		}
		
		//拼接类的声明
		jCodeBuilder.append(JIndentation.NEW_LINE);
		jCodeBuilder.append(actionScope + MODEL_NAME + 
				JIndentation.BETWEEN + simpleName);
		
		if(null != generic)
		{
			jCodeBuilder.append(generic.toString());
		}
		
		//拼接基类
		if(null != superClazz)
		{
			jCodeBuilder.append(JKeyWords.EXTENDS + 
					superClazz.getSimpleName());
		}
		
		//拼接实现的接口
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
		
		//拼接全局变量
		jCodeBuilder = appendFields(jCodeBuilder);
		
		//拼接方法(接口方法已在加入接口时插入到methods中)
		jCodeBuilder = appendMethods(jCodeBuilder);
		
		//TODO 拼接内部类
		
		//TODO 拼接内部接口
		
		
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
	
	public ArrayList<JMethod> getMethods()
	{
		return methods;
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
		//import基类
		imports.add(new JImport(superClazz));
		
		if(superClazz.isAbstract())
		{
			//写入abstract方法
			ArrayList<JMethod> jMethods = superClazz.getMethods();
			JMethod method;
			for(int i = 0; i < jMethods.size(); i++)
			{
				if(jMethods.get(i).isAbstract())
				{
					method = jMethods.get(i);
					method.setAbstract(false);
					this.methods.add(method);
				}
			}
		}
		
		//TODO 写入未实现的接口方法
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
	
	public boolean isAbstract()
	{
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract)
	{
		this.isAbstract = isAbstract;
	}
	
	public ArrayList<JField> findFieldsByType(JClass type)
	{
		ArrayList<JField> results = new ArrayList<JField>();
		for (int i = 0; i < fields.size(); i++)
		{
			if(fields.get(i).getValueType().isSampeType(type))
			{
				results.add(fields.get(i));
			}
		}
		
		return results;
	}
	
	public boolean isSampeType(JClass jClass)
	{
		if(this.getClassName().equals(jClass.getClassName()))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * 从class中提取JClass
	 * @param clazz
	 * @return
	 */
	public static JClass refClass(Class<?> clazz)
	{
		JClass jClass = new JClass(clazz.getPackage().getName(), clazz.getSimpleName());
		if(!clazz.equals(Object.class)) 
		{
			jClass.setSuperClass(JClass.refClass(clazz.getSuperclass()));
		}
		
		Method[] methods = clazz.getDeclaredMethods();
		JMethod jMethod;
		Method method;
		for(int i = 0; i < methods.length; i++)
		{
			method = methods[i];
			jMethod = JMethod.refMethod(method);
			
			jClass.addMethod(jMethod);
		}
		
		return jClass;
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
