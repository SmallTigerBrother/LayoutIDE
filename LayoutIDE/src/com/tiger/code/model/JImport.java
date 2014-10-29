package com.tiger.code.model;

import com.tiger.code.model.primary.JBoolean;
import com.tiger.code.model.primary.JDouble;
import com.tiger.code.model.primary.JFloat;
import com.tiger.code.model.primary.JInteger;
import com.tiger.code.model.primary.JLong;
import com.tiger.code.model.primary.JString;


public class JImport extends JModel
{
	private JClass clazz;
	
	public JImport(JClass clazz)
	{
		this.clazz = clazz;
	}
	
	@Override
	public String toString()
	{
		if(!(clazz instanceof JInteger) && !(clazz instanceof JLong) && 
				!(clazz instanceof JFloat) && !(clazz instanceof JDouble) &&
				!(clazz instanceof JString) && !(clazz instanceof JBoolean))
		{
			return "import" + JIndentation.BETWEEN + clazz.getClassName() +
					JConstant.SIMECOLON_AND_NEWLINE;
		}
		return "";
	}

	public JClass getClazz()
	{
		return clazz;
	}
}
