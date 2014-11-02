package com.tiger.code.model;

import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;
import com.tiger.code.model.primary.Primatives;

public class JImport extends JModel
{
	private JClass clazz;
	
	public JImport(JClass clazz)
	{
		this.clazz = clazz;
	}
	
	@Override
	public String toString()
	{
		if(!Primatives.INTEGER.equals(clazz.getSimpleClassName()) && 
				!Primatives.FLOAT.equals(clazz.getSimpleClassName()) && 
				!Primatives.DOUBLE.equals(clazz.getSimpleClassName()) && 
				!Primatives.LONG.equals(clazz.getSimpleClassName()) && 
				!Primatives.BOOLEAN.equals(clazz.getSimpleClassName()) && 
				!Primatives.STRING.equals(clazz.getSimpleClassName()) && 
				!Primatives.VOID.equals(clazz.getSimpleClassName()))
		{
			return "import" + JIndentation.BETWEEN + clazz.getClassName() +
					JConstant.SIMECOLON_AND_NEWLINE;
		}
		return "";
	}

	public JClass getClazz()
	{
		return clazz;
	}
}
