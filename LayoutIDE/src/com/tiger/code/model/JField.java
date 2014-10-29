package com.tiger.code.model;


public class JField extends JModel
{
	private String filedName = "JClass";
	
	private String actionScope = JActionScope.PUBLIC;
	
	private JClass valueType;
	
	private String initValue;
	
	public JField(String actionScope, JClass valueType, String filedName)
	{
		this.actionScope = actionScope;
		this.valueType = valueType;
		this.filedName = filedName;
	}
	
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(JIndentation.FIELD + actionScope + JIndentation.BETWEEN + 
				valueType.getSimpleClassName() + JIndentation.BETWEEN + filedName);
		if(null != initValue)
		{
			stringBuilder.append(JConstant.EQUAL + initValue);
		}
		stringBuilder.append(JConstant.SIMECOLON_AND_NEWLINE);
		
		return stringBuilder.toString();
	}

	public String getActionScope()
	{
		return actionScope;
	}

	public JClass getValueType()
	{
		return valueType;
	}


	public String getFiledName()
	{
		return filedName;
	}

	public String getInitValue()
	{
		return initValue;
	}

	public void setInitValue(String initValue)
	{
		this.initValue = initValue;
	}

}
