package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.output.JCodeBuilder;


public class JField extends JCodeModel
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
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		for(int i = 0; i < annonations.size(); i++)
		{
			jCodeBuilder.appendWithIndentation(annonations.get(i).toString());
		}
		
		jCodeBuilder.appendWithIndentation(actionScope + 
				valueType.getSimpleName() + JIndentation.BETWEEN + filedName);
		if(null != initValue)
		{
			jCodeBuilder.append(JConstant.ASSIGNMENT + initValue);
		}
		jCodeBuilder.append(JConstant.SIMECOLON_AND_NEWLINE);
		
		return jCodeBuilder;
	}
}
