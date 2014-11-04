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
		if(!Primatives.INTEGER.equals(clazz.getSimpleName()) && 
				!Primatives.FLOAT.equals(clazz.getSimpleName()) && 
				!Primatives.DOUBLE.equals(clazz.getSimpleName()) && 
				!Primatives.LONG.equals(clazz.getSimpleName()) && 
				!Primatives.BOOLEAN.equals(clazz.getSimpleName()) && 
				!Primatives.STRING.equals(clazz.getSimpleName()) && 
				!Primatives.VOID.equals(clazz.getSimpleName()))
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
