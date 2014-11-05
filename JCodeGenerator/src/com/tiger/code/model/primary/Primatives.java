package com.tiger.code.model.primary;

import com.tiger.code.model.JClass;
import com.tiger.code.model.output.JCodeBuilder;

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
		return new JClass(null, null, "int", null)
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
		return new JClass(null, null, "long", null)
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
		return new JClass(null, null, "float", null)
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
		return new JClass(null, null, "double", null)
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
		return new JClass(null, null, "boolean", null)
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
		return new JClass(null, null, "String", null)
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
		return new JClass(null, null, "void", null)
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