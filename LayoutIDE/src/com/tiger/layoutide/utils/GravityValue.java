package com.tiger.layoutide.utils;

import android.text.TextUtils;
import android.view.Gravity;

public class GravityValue
{
	public static final String NO_GRAVITY = "no_gravity";
	
	public static final String CENTER = "center";
	
	public static final String TOP = "top";
	
	public static final String BOTTOM = "bottom";
	
	public static final String LEFT = "left";
	
	public static final String RIGHT = "right";
	
	public static final String CENTER_VERTICAL = "center_vertical";
	
	public static final String CENTER_HORIZONTAL = "center_horizontal";
	
	
	public static final String LEFT_ADN_TOP = "left|top";
	
	public static final String LEFT_ADN_BOTTOM = "left|bottom";
	
	public static final String RIGHT_AND_TOP = "right|top";
	
	public static final String RIGHT_AND_BOTTOM = "right|bottom";
	
	public static final String TOP_AND_CENTER_HORIZONTAL = "top|center_horizontal";
	
	public static final String BOTTOM_AND_CENTER_HORIZONTAL = "bottom|center_horizontal";
	
	public static final String LEFT_AND_CENTER_VERTICAL = "left|center_vertical";
	
	public static final String RIGHT_AND_CENTER_VERTICAL = "right|center_vertical";
	
	public static int getIntValue(String gravity)
	{
		if(!TextUtils.isEmpty(gravity))
		{
			if(NO_GRAVITY.equals(gravity))
			{
				return Gravity.NO_GRAVITY;
			}
			else if(TOP.equals(gravity))
			{
				return Gravity.TOP;
			}
			else if(BOTTOM.equals(gravity))
			{
				return Gravity.BOTTOM;
			}
			else if(LEFT.equals(gravity))
			{
				return Gravity.LEFT;
			}
			else if(RIGHT.equals(gravity))
			{
				return Gravity.RIGHT;
			}
			else if(CENTER.equals(gravity))
			{
				return Gravity.CENTER;
			}
			else if(CENTER_HORIZONTAL.equals(gravity))
			{
				return Gravity.CENTER_HORIZONTAL;
			}
			else if(CENTER_VERTICAL.equals(gravity))
			{
				return Gravity.CENTER_VERTICAL;
			}
			else if(LEFT_ADN_BOTTOM.equals(gravity))
			{
				return Gravity.LEFT | Gravity.BOTTOM;
			}
			else if(LEFT_ADN_TOP.equals(gravity))
			{
				return Gravity.LEFT | Gravity.TOP;
			}
			else if(LEFT_AND_CENTER_VERTICAL.equals(gravity))
			{
				return Gravity.LEFT | Gravity.CENTER_VERTICAL;
			}
			else if(RIGHT_AND_BOTTOM.equals(gravity))
			{
				return Gravity.RIGHT | Gravity.BOTTOM;
			}
			else if(RIGHT_AND_TOP.equals(gravity))
			{
				return Gravity.RIGHT | Gravity.TOP;
			}
			else if(RIGHT_AND_CENTER_VERTICAL.equals(gravity))
			{
				return Gravity.RIGHT | Gravity.CENTER_VERTICAL;
			}
			else if(TOP_AND_CENTER_HORIZONTAL.equals(gravity))
			{
				return Gravity.TOP | Gravity.CENTER_HORIZONTAL;
			}
			else if(BOTTOM_AND_CENTER_HORIZONTAL.equals(gravity))
			{
				return Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
			}
		}
		
		return Gravity.NO_GRAVITY;
	}
}
