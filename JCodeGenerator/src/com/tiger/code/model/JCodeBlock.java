package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.constant.JIndentation;
import com.tiger.code.output.JCodeBuilder;

public class JCodeBlock extends JCodeModel
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JCodeModel> codeModels;
	
	public JCodeBlock()
	{
		codeModels = new ArrayList<JCodeModel>();
	}
	
	public void addCode(JCodeModel code)
	{
		codeModels.add(code);
	}
	
	public void addCode(String codeString)
	{
		JCodeModel jCodeModel = new JCodeModel();
		jCodeModel.setCodeString(codeString);
		codeModels.add(jCodeModel);
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		for(int i = 0; i < codeModels.size(); i++)
		{
			codeModels.get(i).setIndentation(getIndentation());
			jCodeBuilder.append(codeModels.get(i).toString() + JIndentation.NEW_LINE);
		}
		
		return jCodeBuilder;
	}

}
