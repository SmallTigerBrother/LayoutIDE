package com.tiger.layoutide.widget;

import android.view.View;


public interface IView extends ISelectable
{
	String getPackageName();
	
	String getSimpleClassName();
	
	ViewHelper getViewHelper();
	
	View newInstance();
}
