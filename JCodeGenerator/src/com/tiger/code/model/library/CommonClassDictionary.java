package com.tiger.code.model.library;

import java.util.WeakHashMap;

import com.tiger.code.model.JClass;
import com.tiger.code.model.JPackage;
import com.tiger.code.model.constant.JActionScope;

public class CommonClassDictionary extends WeakHashMap<CommonClass, JClass>
{
	public JClass getClass(CommonClass classDeclare)
	{
		return get(classDeclare);
	}
	
	@Override
	public JClass get(Object key)
	{
		JClass jClass =  super.get(key);
		if(null == jClass)
		{
			JPackage jPackage = new JPackage(((CommonClass)key).getPackageName());
			jClass = new JClass(jPackage, JActionScope.PUBLIC,
					((CommonClass)key).getSimpleName(), null);
			this.put(((CommonClass)key), jClass);
		}
		
		return jClass;
	}
	
	
}
