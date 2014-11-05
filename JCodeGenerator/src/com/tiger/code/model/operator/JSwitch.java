package com.tiger.code.model.operator;

import java.util.ArrayList;

import com.tiger.code.model.constant.JConstant;
import com.tiger.code.model.constant.JIndentation;
import com.tiger.code.model.constant.JKeyWords;
import com.tiger.code.model.model.JModel;
import com.tiger.code.model.output.JCodeBuilder;

public class JSwitch extends JModel
{
	private String keyValue = "";
	
	private ArrayList<String> caseValues;
	
	public JSwitch(String keyValue)
	{
		this.keyValue = keyValue;
	}
	
	public void addCase(String caseValue)
	{
		caseValues.add(caseValue);
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		//写入swith
		jCodeBuilder.appendWithIndentation(JKeyWords.SWITCH + JConstant.PARENTHESES_LEFT + 
				keyValue + JConstant.PARENTHESES_RIGHT + JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JConstant.BRACE_LEFT + JIndentation.NEW_LINE);
		jCodeBuilder.setIndentation(getIndentation() + JIndentation.METHOD);
		
		//写入各种case
		for(int i = 0; i < caseValues.size(); i++)
		{
			jCodeBuilder.appendWithIndentation(JKeyWords.CASE + JIndentation.BETWEEN +
					caseValues.get(i) + JConstant.COLON + JIndentation.NEW_LINE + 
					JIndentation.NEW_LINE);
			
			//TODO 插入具体代码
			
			jCodeBuilder.appendWithIndentation(JKeyWords.BREAK + JConstant.SIMECOLON_AND_NEWLINE +
					JIndentation.NEW_LINE);
		}
		
		//写入default语句
		jCodeBuilder.appendWithIndentation(JKeyWords.DEFAULT + JConstant.COLON + 
				JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JKeyWords.BREAK + JConstant.SIMECOLON_AND_NEWLINE);
		
		jCodeBuilder.setIndentation(getIndentation());
		
		jCodeBuilder.appendWithIndentation(JConstant.BRACKET_RIGHT + JIndentation.NEW_LINE);
		
		return null;
	}

	public String getSwitchValue()
	{
		return keyValue;
	}

	public void setSwitchValue(String switchValue)
	{
		this.keyValue = switchValue;
	}

	public ArrayList<String> getCaseValues()
	{
		return caseValues;
	}

	public void setCaseValues(ArrayList<String> caseValues)
	{
		this.caseValues = caseValues;
	}

}
