package com.tiger.layoutide.ide;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;

import com.mn.tiger.utility.DisplayUtils;
import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.tree.IView;
import com.tiger.layoutide.utils.Constant;

public class ViewHelper implements IView
{
	private static String LOG_TAG = ViewHelper.class.getSimpleName();
	
	private String idName = "";

	private int textColor = -1;

	private int backgroundColor = -1;
	
	private String text;
	
	private float textSize;

	private View view;
	
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
	
	public void setTextSize(float textSize)
	{
		this.textSize = textSize;
	}

	@Override
	public float getTextSize()
	{
		return textSize;
	}

	public void setTextColor(int textColor)
	{
		this.textColor = textColor;
	}

	@Override
	public int getTextColor()
	{
		return textColor;
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

	public void setBackgroundColor(int backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}
	
	@Override
	public int getBackgroundColor()
	{
		return backgroundColor;
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
	public void setLayoutWeight(float weight)
	{
		LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
		try
		{
			layoutParams.weight = Float.valueOf(weight);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout weight can not be parsed from value " + weight);
		}
		
		view.setLayoutParams(layoutParams);
	}

	@Override
	public void setLayoutMarginLeft(int marginLeft)
	{
		this.leftMargin = marginLeft;
		
		MarginLayoutParams layoutParams = (MarginLayoutParams)view.getLayoutParams();
		try
		{
			layoutParams.leftMargin = DisplayUtils.dip2px(view.getContext(), this.leftMargin);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout marginLeft can not be parsed from value " + marginLeft);
		}
		
		view.setLayoutParams(layoutParams);
		
	}
	
	
}
