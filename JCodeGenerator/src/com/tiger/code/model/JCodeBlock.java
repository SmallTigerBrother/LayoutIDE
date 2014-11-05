package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.output.JCodeBuilder;

public class JCodeBlock extends JModel
{
	private ArrayList<String> codeStrings = new ArrayList<String>();
	
	public JCodeBlock()
	{
		
	}
	
	public void addCode(String code)
	{
		codeStrings.add(code);
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		return null;
	}

	public ArrayList<String> getCodeStrings()
	{
		return codeStrings;
	}
}
