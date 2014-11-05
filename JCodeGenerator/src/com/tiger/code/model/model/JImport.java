package com.tiger.code.model.model;

import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;
import com.tiger.code.model.output.JCodeBuilder;
import com.tiger.code.model.primary.Primatives;

public class JImport extends JModel
{
	private JClass clazz;
	
	public JImport(JClass clazz)
	{
		this.clazz = clazz;
	}
	
	public JClass getClazz()
	{
		return clazz;
	}

	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		if(!Primatives.INTEGER.equals(clazz.getSimpleName()) && 
				!Primatives.FLOAT.equals(clazz.getSimpleName()) && 
				!Primatives.DOUBLE.equals(clazz.getSimpleName()) && 
				!Primatives.LONG.equals(clazz.getSimpleName()) && 
				!Primatives.BOOLEAN.equals(clazz.getSimpleName()) && 
				!Primatives.STRING.equals(clazz.getSimpleName()) && 
				!Primatives.VOID.equals(clazz.getSimpleName()))
		{
			jCodeBuilder.append("import" + JIndentation.BETWEEN + clazz.getClassName() +
					JConstant.SIMECOLON_AND_NEWLINE);
		}
		return jCodeBuilder;
	}
}
