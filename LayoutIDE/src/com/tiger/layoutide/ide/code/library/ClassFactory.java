package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JGeneric;
import com.tiger.code.model.JGeneric.WildCardType;
import com.tiger.code.model.JPackage;

public class ClassFactory
{
	public static JClass getClass(AndroidClass classDeclare)
	{
		JPackage jPackage = new JPackage((classDeclare).getPackageName());
		JClass jClass = new JClass(jPackage, (classDeclare).getSimpleName());
		
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
