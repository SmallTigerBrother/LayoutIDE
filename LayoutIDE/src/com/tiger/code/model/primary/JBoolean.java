package com.tiger.code.model.primary;

import com.tiger.code.model.JClass;

public class JBoolean extends JClass
{
	public JBoolean()
	{
		super(null, null, null, null);
	}
	
	@Override
	public String getSimpleClassName()
	{
		return "boolean";
	}
	
	public String toString() 
	{
		return "boolean";
	}
}
