package com.tiger.code.model.model;

import com.tiger.code.model.output.JCodeBuilder;

public abstract class JModel
{
	private String indentation = "";
	
	@Override
	public final String toString()
	{
		JCodeBuilder jCodeBuilder = new JCodeBuilder();
		jCodeBuilder.setIndentation(indentation);
		
		return write2Code(jCodeBuilder).toString();
	}
	
	public abstract JCodeBuilder write2Code(JCodeBuilder jCodeBuilder);

	public String getIndentation()
	{
		return indentation;
	}

	public void setIndentation(String indentation)
	{
		this.indentation = indentation;
	}
}
