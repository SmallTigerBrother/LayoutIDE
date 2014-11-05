package com.tiger.code.model;

import com.tiger.code.output.JCodeBuilder;

public class JCodeModel
{
	private String indentation = "";
	
	private String codeString = "";
	
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.appendWithIndentation(codeString);
		return jCodeBuilder;
	}

	public String getIndentation()
	{
		return indentation;
	}

	public void setIndentation(String indentation)
	{
		this.indentation = indentation;
	}
	
	@Override
	public final String toString()
	{
		JCodeBuilder jCodeBuilder = new JCodeBuilder();
		jCodeBuilder.setIndentation(indentation);
		
		return write2Code(jCodeBuilder).toString();
	}

	public String getCodeString()
	{
		return codeString;
	}

	public void setCodeString(String codeString)
	{
		this.codeString = codeString;
	}
}
