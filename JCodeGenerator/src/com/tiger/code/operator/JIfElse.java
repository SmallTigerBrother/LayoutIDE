package com.tiger.code.operator;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.constant.JKeyWords;
import com.tiger.code.model.JCodeModel;
import com.tiger.code.output.JCodeBuilder;

public class JIfElse extends JCodeModel
{
	private String condition;
	
	public JIfElse(String condition)
	{
		this.condition = condition;
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.appendWithIndentation(JKeyWords.IF);
		jCodeBuilder.append(JConstant.PARENTHESES_LEFT + condition + JConstant.PARENTHESES_RIGHT + JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JConstant.BRACE_LEFT + JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JConstant.BRACE_RIGHT + JIndentation.NEW_LINE);
		
		return jCodeBuilder;
	}
}
