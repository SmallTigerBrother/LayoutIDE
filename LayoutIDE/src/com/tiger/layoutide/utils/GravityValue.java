package com.tiger.layoutide.utils;

import android.text.TextUtils;
import android.view.Gravity;

public class GravityValue
{
	public static final String NO_GRAVITY = "NO_GRAVITY";
	
	public static final String CENTER = "CENTER";
	
	public static final String TOP = "TOP";
	
	public static final String BOTTOM = "BOTTOM";
	
	public static final String LEFT = "LEFT";
	
	public static final String RIGHT = "RIGHT";
	
	public static final String CENTER_VERTICAL = "CENTER_VERTICAL";
	
	public static final String CENTER_HORIZONTAL = "CENTER_HORIZONTAL";
	
	
	public static final String LEFT_ADN_TOP = "LEFT|TOP";
	
	public static final String LEFT_ADN_BOTTOM = "LEFT|BOTTOM";
	
	public static final String RIGHT_AND_TOP = "RIGHT|TOP";
	
	public static final String RIGHT_AND_BOTTOM = "RIGHT|BOTTOM";
	
	public static final String TOP_AND_CENTER_HORIZONTAL = "TOP|CENTER_HORIZONTAL";
	
	public static final String BOTTOM_AND_CENTER_HORIZONTAL = "BOTTOM|CENTER_HORIZONTAL";
	
	public static final String LEFT_AND_CENTER_VERTICAL = "LEFT|CENTER_VERTICAL";
	
	public static final String RIGHT_AND_CENTER_VERTICAL = "RIGHT|CENTER_VERTICAL";
	
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
