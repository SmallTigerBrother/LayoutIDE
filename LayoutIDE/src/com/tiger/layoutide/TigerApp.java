package com.tiger.layoutide;

import com.mn.tiger.app.TGApplication;

public class TigerApp extends TGApplication
{
	private static String curLayoutName;

	public static String getCurLayoutName()
	{
		return curLayoutName;
	}

	public static void setCurLayoutName(String curLayoutName)
	{
		TigerApp.curLayoutName = curLayoutName;
	}
}
