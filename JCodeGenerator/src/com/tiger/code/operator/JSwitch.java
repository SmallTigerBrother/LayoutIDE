package com.tiger.code.operator;

import java.util.ArrayList;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JIndentation;
import com.tiger.code.constant.JKeyWords;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JCodeModel;
import com.tiger.code.output.JCodeBuilder;

public class JSwitch extends JCodeModel
{
	private static final long serialVersionUID = 1L;

	private String keyValue = "";
	
	private ArrayList<String> caseValues;
	
	private JCodeBlock jCodeBlock;
	
	public JSwitch(String keyValue)
	{
		this.keyValue = keyValue;
		caseValues = new ArrayList<String>();
	}
	
	public void addCase(String caseValue)
	{
		caseValues.add(caseValue);
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		jCodeBuilder.appendWithIndentation(JKeyWords.SWITCH + JConstant.PARENTHESES_LEFT + 
				keyValue + JConstant.PARENTHESES_RIGHT + JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JConstant.BRACE_LEFT + JIndentation.NEW_LINE);
		jCodeBuilder.setIndentation(getIndentation() + JIndentation.METHOD);
		
		for(int i = 0; i < caseValues.size(); i++)
		{
			jCodeBuilder.appendWithIndentation(JKeyWords.CASE + JIndentation.BETWEEN +
					caseValues.get(i) + JConstant.COLON + JIndentation.NEW_LINE + 
					JIndentation.NEW_LINE);
			
			if(null != jCodeBlock)
			{
				jCodeBlock.setIndentation(jCodeBuilder.getIndentation() + JIndentation.METHOD);
				jCodeBuilder.append(jCodeBlock.toString());
			}
			
			jCodeBuilder.appendWithIndentation(JIndentation.METHOD + JKeyWords.BREAK + 
					JConstant.SIMECOLON_AND_NEWLINE + JIndentation.NEW_LINE);
		}
		
		jCodeBuilder.appendWithIndentation(JKeyWords.DEFAULT + JConstant.COLON + 
				JIndentation.NEW_LINE);
		jCodeBuilder.appendWithIndentation(JIndentation.METHOD + JKeyWords.BREAK + 
				JConstant.SIMECOLON_AND_NEWLINE);
		
		jCodeBuilder.setIndentation(getIndentation());
		
		jCodeBuilder.appendWithIndentation(JConstant.BRACE_RIGHT + JIndentation.NEW_LINE);
		
		return jCodeBuilder;
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

	public JCodeBlock getCodeBlock()
	{
		return jCodeBlock;
	}

	public void setCodeBlock(JCodeBlock jCodeBlock)
	{
		this.jCodeBlock = jCodeBlock;
	}

}
