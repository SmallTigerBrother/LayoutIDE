package com.tiger.code.model;

import com.tiger.code.output.JCodeBuilder;

public class Primatives
{
	 public static final String INTEGER = "integer";
	 
	 public static final String LONG = "long";
	 
	 public static final String FLOAT = "float";
	 
	 public static final String DOUBLE = "double";
	 
	 public static final String BOOLEAN = "boolean";
	 
	 public static final String VOID = "void";
	
	public static JClass newIntegerClass()
	{
		return new JClass(null, "int")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("int");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newLongClass()
	{
		return new JClass(null, "long")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("long");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newFloatClass()
	{
		return new JClass(null, "float")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("float");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newDoubleClass()
	{
		return new JClass(null, "double")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("double");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newBooleanClass()
	{
		return new JClass(null, "boolean")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("boolean");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newVoidClass()
	{
		return new JClass(null, "void")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("void");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newGenericClass(final String name)
	{
		JClass jClass = new JClass(null, name)
		{
			private static final long serialVersionUID = 1L;

			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append(name);
				return jCodeBuilder;
			}
		};
		
		return jClass;
	}
}