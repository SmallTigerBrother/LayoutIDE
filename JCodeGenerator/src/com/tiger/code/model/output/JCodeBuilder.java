package com.tiger.code.model.output;



public class JCodeBuilder
{
	private String indentation = "";
	
	private StringBuilder stringBuilder;
	
	public JCodeBuilder()
	{
		stringBuilder = new StringBuilder();
	}
	
	public String getIndentation()
	{
		return indentation;
	}

	public void setIndentation(String indentation)
	{
		this.indentation = indentation;
	}
	
	public void append(String string)
	{
		stringBuilder.append(string);
	}
	
	public void appendWithIndentation(String string)
	{
		stringBuilder.append(indentation);
		stringBuilder.append(string);
	}
	
	@Override
	public String toString()
	{
		return stringBuilder.toString();
	}
}
