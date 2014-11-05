package com.tiger.code.model;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.output.JCodeBuilder;


public class JPackage extends JModel
{
	private String packageName = "com.tiger.code";
	
	public JPackage()
	{
		
	}
	
	public JPackage(String packageName)
	{
		this.packageName = packageName;
	}
	
	public String getPackageName()
	{
		return packageName;
	}

	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.append("package" + JIndentation.BETWEEN + packageName + 
				JConstant.SIMECOLON_AND_NEWLINE);
		return jCodeBuilder;
	}

}
