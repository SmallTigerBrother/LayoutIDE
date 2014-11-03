package com.tiger.layoutide.storage.model;

public class LayoutDBModel
{
	public static final int ACTIVITY_LAYOUT = 1;
	
	public static final int FRAGMENT_LAYOUT = 2;
	
	public static final int CUSTOM_VIEW_LAYOUT = 3;
	
	private int _id;
	
	private String layoutName;
	
	private int layoutType = ACTIVITY_LAYOUT;

	public String getLayoutName()
	{
		return layoutName;
	}

	public void setLayoutName(String layoutName)
	{
		this.layoutName = layoutName;
	}

	public int get_id()
	{
		return _id;
	}

	public void set_id(int _id)
	{
		this._id = _id;
	}

	public int getLayoutType()
	{
		return layoutType;
	}

	public void setLayoutType(int layoutType)
	{
		this.layoutType = layoutType;
	}
}
