package com.tiger.code.operator;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.constant.JKeyWords;
import com.tiger.code.model.JCodeModel;
import com.tiger.code.output.JCodeBuilder;

public class JElse extends JCodeModel
{
	public JElse()
	{
		
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.appendWithIndentation(JKeyWords.ELSE + JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JConstant.BRACE_LEFT + JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JConstant.BRACE_RIGHT + JIndentation.NEW_LINE);
		return jCodeBuilder;
	}
}
