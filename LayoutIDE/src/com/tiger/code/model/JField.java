package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;


public class JField extends JModel
{
	private String filedName = "JClass";
	
	private String actionScope = JActionScope.PUBLIC;
	
	private JClass valueType;
	
	private String initValue;
	
	private ArrayList<JAnnonation> annonations;
	
	public JField(String actionScope, JClass valueType, String filedName)
	{
		this.actionScope = actionScope;
		this.valueType = valueType;
		this.filedName = filedName;
		this.annonations = new ArrayList<JAnnonation>();
	}
	
	public void addAnnonation(JAnnonation annonation)
	{
		annonations.add(annonation);
	}
	
	public ArrayList<JAnnonation> getAnnonations()
	{
		return annonations;
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

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		
		for(int i = 0; i < annonations.size(); i++)
		{
			stringBuilder.append(JIndentation.FIELD + annonations.get(i).toString());
		}
		
		stringBuilder.append(JIndentation.FIELD + actionScope + 
				valueType.getSimpleClassName() + JIndentation.BETWEEN + filedName);
		if(null != initValue)
		{
			stringBuilder.append(JConstant.ASSIGNMENT + initValue);
		}
		stringBuilder.append(JConstant.SIMECOLON_AND_NEWLINE);
		
		return stringBuilder.toString();
	}
}
