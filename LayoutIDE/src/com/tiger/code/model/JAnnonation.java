package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;

public class JAnnonation extends JModel
{
	private String annonationName = "";
	
	private ArrayList<ParamKeyValue> paramKeyValues;
	
	public JAnnonation(String annonatinName)
	{
		this.annonationName = annonatinName;
		this.paramKeyValues = new ArrayList<JAnnonation.ParamKeyValue>();
	}
	
	public void addKeyValue(ParamKeyValue keyValue)
	{
		paramKeyValues.add(keyValue);
	}
	
	@Override
	public String toString()
	{
		StringBuilder annonationBuilder = new StringBuilder("@" + annonationName +
				JConstant.PARENTHESES_LEFT);
		for(int i = 0; i < paramKeyValues.size(); i++)
		{
			annonationBuilder.append(paramKeyValues.get(i).toString());
			if(i != paramKeyValues.size() - 1)
			{
				annonationBuilder.append(JConstant.COMMA);
			}
		}
		
		annonationBuilder.append(JConstant.PARENTHESES_RIGHT + JIndentation.NEW_LINE);
		return annonationBuilder.toString();
	}
	
	public String getAnnonationName()
	{
		return annonationName;
	}

	public static class ParamKeyValue
	{
		private String key;
		
		private String value;
		
		public ParamKeyValue(String key, String value)
		{
			this.key = key;
			this.value = value;
		}

		public String getKey()
		{
			return key;
		}

		public String getValue()
		{
			return value;
		}

		@Override
		public String toString()
		{
			return key + JConstant.ASSIGNMENT + value+ JIndentation.BETWEEN;
		}
	}
}
