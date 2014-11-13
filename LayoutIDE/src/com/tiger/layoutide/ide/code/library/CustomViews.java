package com.tiger.layoutide.ide.code.library;

import com.tiger.code.model.JClass;

public class CustomViews
{
	public static JClass newXListView()
	{
		JClass xListView = new JClass("com.medialab.ui", "xlistview");
		return xListView;
	}
}
