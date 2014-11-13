package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JGeneric;
import com.tiger.code.model.JGeneric.WildCardType;

public class ClassFactory
{
	public static JClass getClass(AndroidClass classDeclare)
	{
		JClass jClass = new JClass((classDeclare).getPackageName(), 
				(classDeclare).getSimpleName());
		
		switch (classDeclare)
		{
			case AdapterView:
				JGeneric generic = new JGeneric();
				generic.addWildCard(new WildCardType());
				jClass.setGeneric(generic);
				break;

			default:
				break;
		}
		
		return jClass;
	}
}
