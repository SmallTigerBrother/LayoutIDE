package com.tiger.layoutide.widget;

import com.mn.tiger.utility.LogTools;

import android.view.View;
import android.widget.TextView;

public class TextViewHelper extends ViewHelper
{
	private String textColor;
	
	public TextViewHelper(View view)
	{
		super(view);
	}

	public String getTextColor()
	{
		return textColor;
	}

	public void setTextColor(String color)
	{
		try
		{
			if(color.contains("#"))
			{
				color.replace("#", "");
			}
			if(color.contains("0x"))
			{
				color.replace("0x", "");
			}
			
			int rgbColorInt = ViewHelper.getColorInt(color);
			if(rgbColorInt > Integer.MIN_VALUE)
			{
				((TextView)getView()).setTextColor(rgbColorInt);
				this.textColor = color;
			}
			else
			{
				LogTools.w(LOG_TAG, "The textColor can not be parsed from value " + color);
			}
		}
		catch (Exception e)
		{
			LogTools.w(LOG_TAG, "The textColor can not be parsed from value " + color);
		}
	}

	public void setTextSize(String textSize)
	{
		try
		{
			((TextView)getView()).setTextSize(Float.valueOf(textSize));
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The textSize can not be parsed from value " + textSize);
		}
	}

	public float getTextSize()
	{
		return ((TextView)getView()).getTextSize();
	}

	public CharSequence getText()
	{
		return ((TextView)getView()).getText();
	}

	public void setText(String text)
	{
		((TextView)getView()).setText(text);
	}

}
