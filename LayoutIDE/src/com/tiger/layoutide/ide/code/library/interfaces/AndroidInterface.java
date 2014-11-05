package com.tiger.layoutide.ide.code.library.interfaces;

import com.tiger.code.model.constant.JConstant;

public enum AndroidInterface
{
	OnClickListener4View("OnClickListener","android.view.View"),
	OnTouchListener("OnTouchListener","android.view.View"),
	OnKeyListener4View("OnKeyListener","android.view.View"),
	OnLongClickListener4View("OnLongClickListener","android.view.View"),
	
	OnClickListener4Dialog("OnClickListener","android.content.DialogInterface"),
	OnCancelListener4Dialog("OnCancelListener","android.content.DialogInterface"),
	OnDismissListener4Dialog("OnDismissListener","android.content.DialogInterface"),
	
	OnItemClickListener("OnItemClickListener","android.widget.AdapterView"),
	OnItemSelectedListener("OnItemSelectedListener","android.widget.AdapterView"),
	OnPageChangeListener("OnPageChangeListener","android.support.v4.view.ViewPager"),
	
	TextWatcher("TextWatcher","android.text");
	
	private final String classSimpleName;

	private final String packageName;
	
	AndroidInterface(String simpleName, String packageName)
	{
		this.classSimpleName = simpleName;
		this.packageName = packageName;
	}
	
	public String getSimpleName()
	{
		return classSimpleName;
	}
	
	public String getPackageName()
	{
		return packageName;
	}
	
	public String getClassName()
	{
		return packageName + JConstant.POINT + classSimpleName;
	}
	
}
