package com.tiger.layoutide.ide;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;

import com.mn.tiger.utility.DisplayUtils;
import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.utils.Constant;
import com.tiger.layoutide.widget.IView;

public class ViewHelper implements IView
{
	private static String LOG_TAG = ViewHelper.class.getSimpleName();
	
	private View view;
	
	private String idName = "";

	private String textColor = "";

	private String backgroundColor = "";
	
	private String text;
	
	private float textSize;
	
	private int layoutWidth = Integer.MIN_VALUE;;
	
	private int layoutHeight = Integer.MIN_VALUE;
	
	private int leftMargin = Integer.MIN_VALUE;
	
	private int rightMargin = Integer.MIN_VALUE;
	
	private int topMargin = Integer.MIN_VALUE;
	
	private int bottomMargin = Integer.MIN_VALUE;

	public ViewHelper(View view)
	{
		this.view = view;
	}

	@Override
	public String getClassSimpleName()
	{
		return null;
	}

	public void setIdName(String idName)
	{
		this.idName = idName;
	}

	@Override
	public String getIdName()
	{
		return idName;
	}

	public void setText(String text)
	{
		this.text = text;
	}
	
	@Override
	public CharSequence getText()
	{
		return text;
	}
	
	@Override
	public float getTextSize()
	{
		return textSize;
	}

	@Override
	public String getTextColor()
	{
		return textColor;
	}
	
	@Override
	public void setTextColor(String textColor)
	{
		this.textColor = textColor;
	}

	@Override
	public String getLayoutWidth()
	{
		if(layoutWidth > Integer.MIN_VALUE)
		{
			return layoutWidth + "dp";
		}
		
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if (null != layoutParams)
		{
			switch (layoutParams.width)
			{
				case ViewGroup.LayoutParams.WRAP_CONTENT:
					return Constant.WRAP_CONTENT;

				case ViewGroup.LayoutParams.MATCH_PARENT:
					return Constant.MATCH_PARENT;

				default:
					return DisplayUtils.px2dip(view.getContext(), layoutParams.width) + "dp";
			}
		}
		else
		{
			return "";
		}
	}

	@Override
	public String getLayoutHeight()
	{
		if(layoutHeight > Integer.MIN_VALUE)
		{
			return layoutHeight + "dp";
		}
		
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if (null != layoutParams)
		{
			switch (layoutParams.height)
			{
				case ViewGroup.LayoutParams.WRAP_CONTENT:
					return Constant.WRAP_CONTENT;

				case ViewGroup.LayoutParams.MATCH_PARENT:
					return Constant.MATCH_PARENT;

				default:
					return DisplayUtils.px2dip(view.getContext(), layoutParams.height) + "dp";
			}
		}
		else
		{
			return "";
		}
	}

	@Override
	public float getLayoutWeight()
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if (null != layoutParams && layoutParams instanceof LinearLayout.LayoutParams)
		{
			return ((LinearLayout.LayoutParams) layoutParams).weight;
		}
		else
		{
			return -1;
		}
	}
	
	@Override
	public void setLayoutWeight(String weight)
	{
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
		
		if(!TextUtils.isEmpty(weight))
		{
			try
			{
				layoutParams.weight = Float.valueOf(weight);
			}
			catch (Exception e)
			{
				LogTools.e(LOG_TAG, "The layout weight can not be parsed from value " + weight);
			}
		}
		else
		{
			layoutParams.weight = 0;
		}
		
		view.setLayoutParams(layoutParams);
	}

	@Override
	public int getLayoutMarginLeft()
	{
		if(leftMargin > Integer.MIN_VALUE)
		{
			return leftMargin;
		}
		
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if(null != layoutParams && layoutParams instanceof MarginLayoutParams)
		{
			return DisplayUtils.px2dip(view.getContext(), ((MarginLayoutParams)layoutParams).leftMargin);
		}
		else
		{
			return -1;
		}
	}

	@Override
	public int getLayoutMarginRight()
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if(null != layoutParams && layoutParams instanceof MarginLayoutParams)
		{
			return DisplayUtils.px2dip(view.getContext(), ((MarginLayoutParams)layoutParams).rightMargin);
		}
		else
		{
			return -1;
		}
	}

	@Override
	public int getLayoutMarginTop()
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if(null != layoutParams && layoutParams instanceof MarginLayoutParams)
		{
			return DisplayUtils.px2dip(view.getContext(), ((MarginLayoutParams)layoutParams).topMargin);
		}
		else
		{
			return -1;
		}
	}

	@Override
	public int getLayoutMarginBottom()
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if(null != layoutParams && layoutParams instanceof MarginLayoutParams)
		{
			return DisplayUtils.px2dip(view.getContext(), ((MarginLayoutParams)layoutParams).bottomMargin);
		}
		else
		{
			return -1;
		}
	}

	@Override
	public String getBackgroundColor()
	{
		return backgroundColor;
	}
	
	@Override
	public void setBackgroundColor(String color)
	{
		this.backgroundColor = color;
	}

	@Override
	public String getGravityStringValue()
	{
		return null;
	}

	@Override
	public String getLayoutGravityStringValue()
	{
		return null;
	}

	@Override
	public void setText(CharSequence text)
	{
		// View ×ÔÐÐ·µ»Ø
	}

	@Override
	public void setLayoutWidth(String layoutWidth)
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		
		if(Constant.WRAP_CONTENT.equals(layoutWidth.toString()))
		{
			layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
		}
		else if(Constant.MATCH_PARENT.equals(layoutWidth.toString()))
		{
			layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
		}
		else 
		{
			try
			{
				this.layoutWidth =  Integer.valueOf(layoutWidth);
				layoutParams.width = DisplayUtils.dip2px(view.getContext(),this.layoutWidth);
			}
			catch (Exception e)
			{
				LogTools.e(LOG_TAG, "The layoutWidth can not be parsed from value " + layoutWidth);
			}
		}
		
		view.setLayoutParams(layoutParams);
	}

	@Override
	public void setLayoutHeight(String layoutHeight)
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if(Constant.WRAP_CONTENT.equals(layoutHeight.toString()))
		{
			layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		}
		else if(Constant.MATCH_PARENT.equals(layoutHeight.toString()))
		{
			layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
		}
		else 
		{
			try
			{
				this.layoutHeight = Integer.valueOf(layoutHeight);
				layoutParams.height = DisplayUtils.dip2px(view.getContext(), this.layoutHeight);
			}
			catch (Exception e)
			{
				LogTools.e(LOG_TAG, "The layoutHeight can not be parsed from value " + layoutHeight);
			}
		}
		
		view.setLayoutParams(layoutParams);
	}

	@Override
	public void setLayoutMarginLeft(String marginLeft)
	{
		MarginLayoutParams layoutParams = (MarginLayoutParams)view.getLayoutParams();
		try
		{
			this.leftMargin = Integer.valueOf(marginLeft);
			layoutParams.leftMargin = DisplayUtils.dip2px(view.getContext(), this.leftMargin);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout marginLeft can not be parsed from value " + marginLeft);
		}
		
		view.setLayoutParams(layoutParams);
		
	}

	public void setLayoutMarginRight(String marginRight)
	{
		MarginLayoutParams layoutParams = (MarginLayoutParams)view.getLayoutParams();
		try
		{
			this.rightMargin = Integer.valueOf(marginRight);
			layoutParams.rightMargin = DisplayUtils.dip2px(view.getContext(), this.rightMargin);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout marginRight can not be parsed from value " + marginRight);
		}
		
		view.setLayoutParams(layoutParams);
	}


	public void setLayoutMarginTop(String marginTop)
	{
		MarginLayoutParams layoutParams = (MarginLayoutParams)view.getLayoutParams();
		try
		{
			this.topMargin = Integer.valueOf(marginTop);
			layoutParams.topMargin = DisplayUtils.dip2px(view.getContext(), this.leftMargin);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout marginTop can not be parsed from value " + topMargin);
		}
		
		view.setLayoutParams(layoutParams);
	}

	public void setLayoutMarginBottom(String marginBottom)
	{
		MarginLayoutParams layoutParams = (MarginLayoutParams)view.getLayoutParams();
		try
		{
			this.bottomMargin = Integer.valueOf(marginBottom);
			layoutParams.bottomMargin = DisplayUtils.dip2px(view.getContext(), this.leftMargin);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout marginBottom can not be parsed from value " + bottomMargin);
		}
		
		view.setLayoutParams(layoutParams);
	}

	@Override
	public void setTextSize(String textSize)
	{
		try
		{
			this.textSize = Float.valueOf(textSize);
		}
		catch (Exception e)
		{
			LogTools.w(LOG_TAG, "The textSize can not be parsed from value " + textSize);
		}
	}

	public static int getColorInt(String colorStr)
	{
		if(colorStr.length() == 6)
		{
			return Color.rgb(Integer.valueOf(colorStr.substring(0, 2), 16),
					Integer.valueOf(colorStr.substring(2, 4), 16), 
					Integer.valueOf(colorStr.substring(4, 6), 16));
		}
		else if(colorStr.length() == 8)
		{
			return Color.argb(Integer.valueOf(colorStr.substring(0, 2), 16),
					Integer.valueOf(colorStr.substring(2, 4), 16), 
					Integer.valueOf(colorStr.substring(4, 6), 16),
					Integer.valueOf(colorStr.substring(6, 8), 16));
		}
		
		return Integer.MIN_VALUE;
	}
}
