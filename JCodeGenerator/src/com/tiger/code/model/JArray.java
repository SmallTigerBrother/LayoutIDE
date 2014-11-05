package com.tiger.code.model;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.output.JCodeBuilder;

public class JArray extends JField
{
	public JArray(String actionScope, JClass valueType, String filedName)
	{
		super(actionScope, valueType, filedName);
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.append(JIndentation.FIELD + getActionScope() + 
				JIndentation.BETWEEN + getValueType().getSimpleName() + 
				JConstant.BRACKET_LEFT + JConstant.BRACKET_RIGHT + 
				JIndentation.BETWEEN + getFiledName());
		if(null != getInitValue())
		{
			jCodeBuilder.append(JConstant.EQUAL + getInitValue());
		}
		jCodeBuilder.append(JConstant.SIMECOLON_AND_NEWLINE);
		
		return jCodeBuilder;
	}

}
