package com.tiger.code.model;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
	
	private boolean isFinal = false;
	
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
		imports.add(new JImport(jInterface));
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
		jCodeBuilder.setIndentation(JIndentation.FIELD);
		
		if(null != jPackage)
		{
			jCodeBuilder.append(jPackage.toString());
		}
		
		for (int i = 0; i < imports.size(); i++)
		{
			jCodeBuilder.append(imports.get(i).toString());
		}
		
		jCodeBuilder.append(JIndentation.NEW_LINE);
		jCodeBuilder.append(actionScope + MODEL_NAME + 
				JIndentation.BETWEEN + simpleName);
		
		if(null != generic)
		{
			jCodeBuilder.append(generic.toString());
		}
		
		if(null != superClazz)
		{
			jCodeBuilder.append(JKeyWords.EXTENDS + 
					superClazz.getSimpleName());
		}
		
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
		
		jCodeBuilder = appendFields(jCodeBuilder);
		
		jCodeBuilder = appendMethods(jCodeBuilder);
		
		
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
	
	public void setActionScope(String actionScope)
	{
		this.actionScope = actionScope;
	}
	
	public boolean isFinal()
	{
		return isFinal;
	}
	
	public void setFinal(boolean isFinal)
	{
		this.isFinal = isFinal;
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
		imports.add(new JImport(superClazz));
		
		if(superClazz.isAbstract())
		{
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
		
		int modifiers = clazz.getModifiers();
		jClass.setAbstract(Modifier.isAbstract(modifiers));
		jClass.setActionScope(getActionScope(modifiers));
		jClass.setFinal(Modifier.isFinal(modifiers));
		
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
