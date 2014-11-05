package com.tiger.layoutide.ide.code.library.interfaces;

import java.util.WeakHashMap;

import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.model.JInterface;
import com.tiger.code.model.model.JMethod;
import com.tiger.code.model.model.JMethod.Parameter;
import com.tiger.code.model.model.JPackage;

public class InterfaceDictionary extends WeakHashMap<AndroidInterface, JInterface>
{
	private static InterfaceDictionary instance;
	
	private synchronized static InterfaceDictionary getInstance()
	{
		if(null == instance)
		{
			instance = new InterfaceDictionary();
		}
		return instance;
	}
	
	public static JInterface getInterface(AndroidInterface classDeclare)
	{
		return getInstance().get(classDeclare);
	}
	
	@Override
	public JInterface get(Object key)
	{
		JInterface jInterface =  super.get(key);
		if(null == jInterface)
		{
			JPackage jPackage = new JPackage(((AndroidInterface)key).getPackageName());
			jInterface = new JInterface(jPackage, JActionScope.PUBLIC,
					((AndroidInterface)key).getSimpleName(), null);
			//Ìí¼Ó·½·¨
			this.put(((AndroidInterface)key), addMethods(jInterface, (AndroidInterface)key));
		}
		
		return jInterface;
	}
	
	private JInterface addMethods(JInterface jInterface, AndroidInterface key)
	{
		switch (key)
		{
			case OnClickListener4View:
				Parameter parameter = new Parameter("view", ClassDictionary.getClass(
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
