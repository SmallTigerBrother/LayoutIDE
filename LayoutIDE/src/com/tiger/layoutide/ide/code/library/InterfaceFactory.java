package com.tiger.layoutide.ide.code.library;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.JPackage;

public class InterfaceFactory
{
	public static JInterface createInterface(AndroidInterface classDeclare)
	{
		JPackage jPackage = new JPackage((classDeclare).getPackageName());
		JInterface jInterface = new JInterface(jPackage, JActionScope.PUBLIC,
				(classDeclare).getSimpleName(), null);
		jInterface = addMethods(jInterface, classDeclare);
		return jInterface;
	}
	
	private static JInterface addMethods(JInterface jInterface, AndroidInterface key)
	{
		switch (key)
		{
			case OnClickListener4View:
				Parameter parameter = new Parameter("view", ClassFactory.getClass(
						AndroidClass.View));
				JMethod method = new JMethod(JActionScope.PUBLIC, "onClick", parameter);
				jInterface.addMethod(method);
				break;

			default:
				break;
		}
		
		return jInterface;
	}
	
}
