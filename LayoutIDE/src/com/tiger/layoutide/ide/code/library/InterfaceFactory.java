package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JMethod.Parameter;
import com.tiger.code.model.Primatives;
import com.tiger.code.operator.JSwitch;

public class InterfaceFactory
{
	public static JInterface createInterface(AndroidInterface classDeclare)
	{
		JInterface jInterface = new JInterface((classDeclare).getPackageName(),
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
				
				JSwitch jSwitch = new JSwitch("event.getAction()");
				jSwitch.addCase("MotionEvent.ACTION_DOWN");
				jSwitch.addCase("MotionEvent.ACTION_MOVE");
				jSwitch.addCase("MotionEvent.ACTION_UP");
				jSwitch.addCase("MotionEvent.ACTION_CANCEL");
				
				JCodeBlock jCodeBlock = new JCodeBlock();
				jCodeBlock.addCode(jSwitch);
				jCodeBlock.addCode("\n + return false");
				onTouch.setCodeBlock(jCodeBlock);
				
				jInterface.addMethod(onTouch);
				break;
				
			default:
				break;
		}
		
		return jInterface;
	}
	
}
