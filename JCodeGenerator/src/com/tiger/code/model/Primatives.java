package com.tiger.code.model;

import com.tiger.code.output.JCodeBuilder;

public class Primatives
{
	 public static final String INTEGER = "integer";
	 
	 public static final String LONG = "long";
	 
	 public static final String FLOAT = "float";
	 
	 public static final String DOUBLE = "double";
	 
	 public static final String BOOLEAN = "boolean";
	 
	 public static final String STRING = "String";
	 
	 public static final String VOID = "void";
	
	public static JClass newIntegerClass()
	{
		return new JClass(null, null, "int")
		{
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
		return new JClass(null, null, "long")
		{
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
		return new JClass(null, null, "float")
		{
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
		return new JClass(null, null, "double")
		{
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
		return new JClass(null, null, "boolean")
		{
			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("boolean");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newStringClass()
	{
		return new JClass(null, null, "String")
		{
			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("String");
				return jCodeBuilder;
			}
		};
	}
	
	public static JClass newVoidClass()
	{
		return new JClass(null, null, "void")
		{
			@Override
			public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
			{
				jCodeBuilder.append("void");
				return jCodeBuilder;
			}
		};
	}
}