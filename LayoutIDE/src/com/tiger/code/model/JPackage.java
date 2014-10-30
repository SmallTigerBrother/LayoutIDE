package com.tiger.code.model;

import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;


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
	public String toString()
	{
		return "package" + JIndentation.BETWEEN + packageName + JConstant.SIMECOLON_AND_NEWLINE;
	}

}
