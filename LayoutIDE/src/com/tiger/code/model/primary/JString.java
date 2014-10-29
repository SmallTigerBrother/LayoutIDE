package com.tiger.code.model.primary;

import com.tiger.code.model.JClass;

public class JString extends JClass
{
	public JString()
	{
		super(null, null, null, null);
	}
	
	@Override
	public String getSimpleClassName()
	{
		return "String";
	}
	
	public String toString() 
	{
		return "String";
	};
}
