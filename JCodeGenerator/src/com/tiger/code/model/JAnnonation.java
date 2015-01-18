package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.output.JCodeBuilder;

public class JAnnonation extends JCodeModel
{
	private static final long serialVersionUID = 1L;

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
	
	public String getAnnonationName()
	{
		return annonationName;
	}
	
	public static JAnnonation createOverrideAnnonation()
	{
		return new JAnnonation("Override");
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.append("@" + annonationName);
		if(paramKeyValues.size() > 0)
		{
			jCodeBuilder.append(JConstant.PARENTHESES_LEFT);
			for(int i = 0; i < paramKeyValues.size(); i++)
			{
				jCodeBuilder.append(paramKeyValues.get(i).toString());
				if(i != paramKeyValues.size() - 1)
				{
					jCodeBuilder.append(JConstant.COMMA + JIndentation.BETWEEN);
				}
			}
			jCodeBuilder.append(JConstant.PARENTHESES_RIGHT);
		}
		jCodeBuilder.append(JIndentation.NEW_LINE);
		
		return jCodeBuilder;
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
			return key + JConstant.ASSIGNMENT + value;
		}
	}
}
