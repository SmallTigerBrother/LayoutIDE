package com.tiger.code.model.primary;

import com.tiger.code.model.JClass;

public class JVoid extends JClass
{
	public JVoid()
	{
		super(null, null, null, null);
	}
	
	@Override
	public String getSimpleClassName()
	{
		return "void";
	}
	
	public String toString() 
	{
		return "void";
	};

}
