package com.tiger.layoutide.ide.code.library;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JPackage;

public class ClassFactory
{
	public static JClass getClass(AndroidClass classDeclare)
	{
		JPackage jPackage = new JPackage((classDeclare).getPackageName());
		JClass jClass = new JClass(jPackage, JActionScope.PUBLIC,
				(classDeclare).getSimpleName(), null);
		return jClass;
	}
}
