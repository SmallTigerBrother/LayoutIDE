package com.tiger.code.model;

import java.util.ArrayList;

import com.tiger.code.constant.JConstant;
import com.tiger.code.constant.JKeyWords;
import com.tiger.code.output.JCodeBuilder;

public class JGeneric extends JCodeModel
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<WildCardType> wildCards;
	
	public JGeneric()
	{
		this.wildCards = new ArrayList<JGeneric.WildCardType>();
	}
	
	@Override
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
	{
		if(wildCards.size() > 0)
		{
			jCodeBuilder.append(JConstant.ANGLE_BRACKET_LEFT);
			for(int i = 0; i < wildCards.size(); i++)
			{
				jCodeBuilder.append(wildCards.toString());
				if(i != wildCards.size() - 1)
				{
					jCodeBuilder.append(JConstant.COMMA);
				}
			}
			
			jCodeBuilder.append(JConstant.ANGLE_BRACKET_RIGHT);
		}
		
		return jCodeBuilder;
	}
	
	public ArrayList<WildCardType> getWildCards()
	{
		return wildCards;
	}
	
	public void addWildCard(WildCardType wildCardType)
	{
		this.wildCards.add(wildCardType);
	}
	
	public static class WildCardType extends JCodeModel
	{
		private static final long serialVersionUID = 1L;

		private JClass superClazz;
		
		private String name = "?";

		public JClass getSuperClass()
		{
			return superClazz;
		}

		public void setSuperClass(JClass superClazz)
		{
			this.superClazz = superClazz;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}
		
		@Override
		public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder)
		{
			if(null == superClazz)
			{
				jCodeBuilder.append(name);
			}
			else
			{
				jCodeBuilder.append(name + JKeyWords.EXTENDS + superClazz.getSimpleName());
			}
			
			return jCodeBuilder;
		}
	}
}
