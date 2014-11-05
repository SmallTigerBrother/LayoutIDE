package com.tiger.layoutide.ide.code.library.interfaces;

import java.util.WeakHashMap;

import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.model.JClass;
import com.tiger.code.model.model.JPackage;

public class ClassDictionary extends WeakHashMap<AndroidClass, JClass>
{
	private static ClassDictionary instance;
	
	private synchronized static ClassDictionary getInstance()
	{
		if(null == instance)
		{
			instance = new ClassDictionary();
		}
		return instance;
	}
	
	public static JClass getClass(AndroidClass classDeclare)
	{
		return getInstance().get(classDeclare);
	}
	
	@Override
	public JClass get(Object key)
	{
		JClass jClass =  super.get(key);
		if(null == jClass)
		{
			JPackage jPackage = new JPackage(((AndroidClass)key).getPackageName());
			jClass = new JClass(jPackage, JActionScope.PUBLIC,
					((AndroidClass)key).getSimpleName(), null);
			this.put(((AndroidClass)key), jClass);
		}
		
		return jClass;
	}
	
	
}
