package com.tiger.layoutide.ide.code.library;

import java.util.WeakHashMap;

import com.tiger.code.constant.JActionScope;
import com.tiger.code.constant.JConstant;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.JPackage;
import com.tiger.code.operator.JSwitch;

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
			//添加方法
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
				JCodeBlock jCodeBlock = new JCodeBlock();
				
				//TODO 需要迁移代码
				JSwitch jSwitch = new JSwitch(method.getParameters()[0].getParameterName() + JConstant.POINT + 
						"getId()");
				
				jCodeBlock.addCode(jSwitch);
				
				break;

			default:
				break;
		}
		
		return jInterface;
	}
	
}
