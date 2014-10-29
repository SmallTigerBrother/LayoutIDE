package com.tiger.code.model;


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
