package com.tiger.code.model.primary;

import com.tiger.code.model.JClass;

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
			public String toString()
			{
				return "int";
			}
		};
	}
	
	public static JClass newLongClass()
	{
		return new JClass(null, null, "long", null)
		{
			@Override
			public String toString()
			{
				return "long";
			}
		};
	}
	
	public static JClass newFloatClass()
	{
		return new JClass(null, null, "float", null)
		{
			@Override
			public String toString()
			{
				return "float";
			}
		};
	}
	
	public static JClass newDoubleClass()
	{
		return new JClass(null, null, "double", null)
		{
			@Override
			public String toString()
			{
				return "double";
			}
		};
	}
	
	public static JClass newBooleanClass()
	{
		return new JClass(null, null, "boolean", null)
		{
			@Override
			public String toString()
			{
				return "boolean";
			}
		};
	}
	
	public static JClass newStringClass()
	{
		return new JClass(null, null, "String", null)
		{
			@Override
			public String toString()
			{
				return "String";
			}
		};
	}
	
	public static JClass newVoidClass()
	{
		return new JClass(null, null, "void", null)
		{
			@Override
			public String toString()
			{
				return "void";
			}
		};
	}
}