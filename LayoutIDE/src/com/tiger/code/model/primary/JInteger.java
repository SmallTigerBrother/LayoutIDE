package com.tiger.code.model.primary;

import com.tiger.code.model.JClass;

public class JInteger extends JClass
{
	public JInteger()
	{
		super(null, null, null, null);
	}
	
	@Override
	public String getSimpleClassName()
	{
		return "int";
	}
	
	public String toString() 
	{
		return "int";
	};
}
