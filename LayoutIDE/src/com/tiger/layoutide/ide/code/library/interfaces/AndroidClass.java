package com.tiger.layoutide.ide.code.library.interfaces;

import com.tiger.code.model.constant.JConstant;

public enum AndroidClass
{
	Activity("Activity","android.app"),
	Fragment("Fragment","android.support.v4.app"),
	FragmentActivity("FragmentActivity","android.support.v4.app"),
	FragmentManager("FragmentManager","android.support.v4.app"),
	Intent("Intent", "android.content"),
	Handler("Handler","android.os"),
	Bundle("Bundle","android.os"),
	
	TextView("TextView","android.widget"),
	Button("Button","android.widget"),
	EditText("EditText","android.widget"),
	View("View","android.view"),
	ViewGroup("ViewGroup","android.view"),
	LinearLayout("LinearLayout","android.widget"),
	RelativeLayout("RelativeLayout","android.widget"),
	
	SharedPreferences("SharedPreferences", "android.content");
	
	private final String classSimpleName;

	private final String packageName;
	
	AndroidClass(String simpleName, String packageName)
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
