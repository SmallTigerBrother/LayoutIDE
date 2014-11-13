package com.tiger.code.model;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.output.JCodeBuilder;

public class JImport extends JCodeModel
{
	private JClass clazz;
	
	private JInterface jInterface;
	
	public JImport(JClass clazz)
	{
		this.clazz = clazz;
	}
	
	public JImport(JInterface jInterface)
	{
		this.jInterface = jInterface;
	}
	
	public JPackage getPackage()
	{
		if(null != clazz)
		{
			return clazz.getPackage();
		}
		
		if(null != jInterface)
		{
			return jInterface.getPackage();
		}
		
		return null;
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
			if(null != clazz)
			{
				jCodeBuilder.append("import" + JIndentation.BETWEEN + clazz.getClassName() +
						JConstant.SIMECOLON_AND_NEWLINE);
			}
			else if(null != jInterface)
			{
				jCodeBuilder.append("import" + JIndentation.BETWEEN + jInterface.getInterfaceName() +
						JConstant.SIMECOLON_AND_NEWLINE);
			}
		}
		return jCodeBuilder;
	}
}
