package com.tiger.code.model;

public class JArray extends JField
{
	public JArray(String actionScope, JClass valueType, String filedName)
	{
		super(actionScope, valueType, filedName);
	}
	
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(JIndentation.FIELD + getActionScope() + 
				JIndentation.BETWEEN + getValueType().getSimpleClassName() + 
				JConstant.BRACKET_LEFT + JConstant.BRACKET_RIGHT + 
				JIndentation.BETWEEN + getFiledName());
		if(null != getInitValue())
		{
			stringBuilder.append(JConstant.EQUAL + getInitValue());
		}
		stringBuilder.append(JConstant.SIMECOLON_AND_NEWLINE);
		
		return stringBuilder.toString();
	}

}
