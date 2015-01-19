package com.tiger.code.operator;

import com.tiger.code.constant.JIndentation;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JCodeModel;
import com.tiger.code.output.JCodeBuilder;

public class JFor extends JCodeModel
{
	private static final long serialVersionUID = 1L;
	
	private JCodeBlock codeBlock;
	
	/**
	 * 初始化
	 */
	private JCodeModel initCode;
	
	/**
	 * 判断语句
	 */
	private JCodeModel judgeMentCode;
	
	/**
	 * 改变语句，比如i++
	 */
	private JCodeModel changeCode;
	
	/**
	 * 循环执行的代码
	 */
	private JCodeModel loopCode;
	
	public JFor()
	{
		codeBlock = new JCodeBlock();
	}
	
	public JCodeBlock getCodeBlock()
	{
		return codeBlock;
	}
	
	public JCodeBuilder write2Code(JCodeBuilder jCodeBuilder) 
	{
		jCodeBuilder.append("for(");
		jCodeBuilder.append(initCode.toString() + ";" + JIndentation.BETWEEN);
		jCodeBuilder.append(judgeMentCode.toString() + ";" + JIndentation.BETWEEN);
		jCodeBuilder.append(changeCode.toString() + ")" + JIndentation.NEW_LINE);
		
		jCodeBuilder.append("{" + JIndentation.NEW_LINE);
		jCodeBuilder.append(loopCode.toString());
		jCodeBuilder.append("}");
		
		return jCodeBuilder;
	}

	public void setInitCode(JCodeModel initCode)
	{
		this.initCode = initCode;
	}

	public void setJudgeMentCode(JCodeModel judgeMentCode)
	{
		this.judgeMentCode = judgeMentCode;
	}

	public void setChangeCode(JCodeModel changeCode)
	{
		this.changeCode = changeCode;
	}
	
	public void setLoopCode(JCodeModel loopCode)
	{
		this.loopCode = loopCode;
	}
}
