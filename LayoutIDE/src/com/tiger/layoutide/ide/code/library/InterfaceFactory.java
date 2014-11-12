package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.JPackage;
import com.tiger.code.model.Primatives;

public class InterfaceFactory
{
	public static JInterface createInterface(AndroidInterface classDeclare)
	{
		JPackage jPackage = new JPackage((classDeclare).getPackageName());
		JInterface jInterface = new JInterface(jPackage,
				(classDeclare).getSimpleName());
		jInterface = addMethods(jInterface, classDeclare);
		return jInterface;
	}
	
	private static JInterface addMethods(JInterface jInterface, AndroidInterface key)
	{
		Parameter viewParam = new Parameter("view", ClassFactory.getClass(
				AndroidClass.View));
		Parameter motionEventParam = new Parameter("event", ClassFactory.getClass(
				AndroidClass.MotionEvent));
		Parameter adapterViewParam = new Parameter("parent", ClassFactory.getClass(
				AndroidClass.AdapterView));
		
		switch (key)
		{
			case OnClickListener4View:
				JMethod onClick = new JMethod("onClick");
				onClick.setParameters(viewParam);
				jInterface.addMethod(onClick);
				break;
				
			case OnItemClickListener:
				JMethod onItemClick = new JMethod("onItemClick");
				onItemClick.setParameters(adapterViewParam, viewParam, 
						new Parameter("position", Primatives.newIntegerClass()),
						new Parameter("id", Primatives.newLongClass()));
				
				break;
				
			case OnTouchListener:
				JMethod onTouch = new JMethod("onTouch");
				onTouch.setParameters(viewParam, motionEventParam);
				jInterface.addMethod(onTouch);
				break;
				
			default:
				break;
		}
		
		return jInterface;
	}
	
}
