package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.model.JPackage;

public class CustomInterfaces
{
	public static JInterface newIXListViewListener()
	{
		JPackage jPackage = new JPackage("com.medialab.ui.xlistview");
		JInterface xlistviewListener = new JInterface(jPackage, "IXListViewListener");
		
		JMethod onRefresh = new JMethod("onRefresh");
		xlistviewListener.addMethod(onRefresh);
		
		JMethod onLoadMore = new JMethod("onLoadMore");
		xlistviewListener.addMethod(onLoadMore);
		
		return xlistviewListener;
	}
	
	public static JInterface newOnItemClickListener()
	{
		return null;
	}
	
	
	public static JInterface newOnTouchEventListener()
	{
		return null;
	}
}
