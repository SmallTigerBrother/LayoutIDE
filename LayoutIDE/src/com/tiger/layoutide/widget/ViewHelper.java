package com.tiger.layoutide.widget;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mn.tiger.utility.DisplayUtils;
import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.utils.GravityValue;
import com.tiger.layoutide.utils.XmlOutputConstant;

public class ViewHelper
{
	protected String LOG_TAG = this.getClass().getSimpleName();
	
	private View view;
	
	private String idName = "";

	private String backgroundColor = "";
	
	private String gravity;
	
	private String layoutGravity;
	
	private String layoutWidth = "";
	
	private String layoutHeight = "";
	
	private int leftMargin = Integer.MIN_VALUE;
	
	private int rightMargin = Integer.MIN_VALUE;
	
	private int topMargin = Integer.MIN_VALUE;
	
	private int bottomMargin = Integer.MIN_VALUE;
	
	private boolean setOnClickListener = false;
	
	public ViewHelper(View view)
	{
		this.view = view;
	}
	
	public View getView()
	{
		return view;
	}

	public void setIdName(String idName)
	{
		this.idName = idName;
		view.setTag(idName);
	}

	
	public String getIdName()
	{
		return idName;
	}

	
	public String getLayoutWidth()
	{
		if(!TextUtils.isEmpty(layoutWidth))
		{
			if(layoutWidth.equals(XmlOutputConstant.WRAP_CONTENT))
			{
				return layoutWidth;
			}
			else if(layoutWidth.equals(XmlOutputConstant.MATCH_PARENT))
			{
				return layoutWidth;
			}
			
			return layoutWidth + "dp";
		}
		
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		switch (layoutParams.width)
		{
			case ViewGroup.LayoutParams.WRAP_CONTENT:
				layoutWidth = XmlOutputConstant.WRAP_CONTENT;
				return layoutWidth;

			case ViewGroup.LayoutParams.MATCH_PARENT:
				layoutWidth = XmlOutputConstant.MATCH_PARENT;
				return layoutWidth;

			default:
				layoutWidth = DisplayUtils.px2dip(view.getContext(), layoutParams.width) + "";
				return layoutWidth + "dp";
		}
	}
	
	
	public void setLayoutWidth(String layoutWidth)
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		
		if(XmlOutputConstant.WRAP_CONTENT.equals(layoutWidth.toString()))
		{
			layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
			this.layoutWidth = layoutWidth;
		}
		else if(XmlOutputConstant.MATCH_PARENT.equals(layoutWidth.toString()))
		{
			layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
			this.layoutWidth = layoutWidth;
		}
		else 
		{
			try
			{
				this.layoutWidth =  Integer.valueOf(layoutWidth) + "";
				layoutParams.width = DisplayUtils.dip2px(view.getContext(),
						Integer.valueOf(layoutWidth));
			}
			catch (Exception e)
			{
				LogTools.e(LOG_TAG, "The layoutWidth can not be parsed from value " + layoutWidth);
			}
		}
		
		view.setLayoutParams(layoutParams);
	}

	
	public String getLayoutHeight()
	{
		if(!TextUtils.isEmpty(layoutHeight))
		{
			if(layoutHeight.equals(XmlOutputConstant.WRAP_CONTENT))
			{
				return layoutHeight;
			}
			else if(layoutHeight.equals(XmlOutputConstant.MATCH_PARENT))
			{
				return layoutHeight;
			}
			
			return layoutHeight + "dp";
		}
		
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if (null != layoutParams)
		{
			switch (layoutParams.height)
			{
				case ViewGroup.LayoutParams.WRAP_CONTENT:
					layoutHeight = XmlOutputConstant.WRAP_CONTENT;
					return XmlOutputConstant.WRAP_CONTENT;

				case ViewGroup.LayoutParams.MATCH_PARENT:
					layoutHeight = XmlOutputConstant.MATCH_PARENT;
					return XmlOutputConstant.MATCH_PARENT;

				default:
					layoutHeight = DisplayUtils.px2dip(view.getContext(), layoutParams.height) + "";
					
					return  layoutHeight + "dp";
			}
		}
		else
		{
			return "";
		}
	}
	
	
	public void setLayoutHeight(String layoutHeight)
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if(XmlOutputConstant.WRAP_CONTENT.equals(layoutHeight.toString()))
		{
			layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			this.layoutHeight = layoutHeight;
		}
		else if(XmlOutputConstant.MATCH_PARENT.equals(layoutHeight.toString()))
		{
			layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
			this.layoutHeight = layoutHeight;
		}
		else 
		{
			try
			{
				this.layoutHeight = Integer.valueOf(layoutHeight) + "";
				layoutParams.height = DisplayUtils.dip2px(view.getContext(), 
						Integer.valueOf(layoutHeight));
			}
			catch (Exception e)
			{
				LogTools.e(LOG_TAG, "The layoutHeight can not be parsed from value " + layoutHeight);
			}
		}
		
		view.setLayoutParams(layoutParams);
	}

	
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
	
	
	public void setLayoutWeight(String weight)
	{
		if(view.getLayoutParams() instanceof RelativeLayout.LayoutParams)
		{
			return;
		}
		
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

	
	public int getLayoutMarginRight()
	{
		if(rightMargin > Integer.MIN_VALUE)
		{
			return rightMargin;
		}
		
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

	
	public int getLayoutMarginTop()
	{
		if(topMargin > Integer.MIN_VALUE)
		{
			return topMargin;
		}
		
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

	public void setLayoutMarginTop(String marginTop)
	{
		MarginLayoutParams layoutParams = (MarginLayoutParams)view.getLayoutParams();
		try
		{
			this.topMargin = Integer.valueOf(marginTop);
			layoutParams.topMargin = DisplayUtils.dip2px(view.getContext(), this.topMargin);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout marginTop can not be parsed from value " + topMargin);
		}
		
		view.setLayoutParams(layoutParams);
	}

	
	public int getLayoutMarginBottom()
	{
		if(bottomMargin > Integer.MIN_VALUE)
		{
			return bottomMargin;
		}
		
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
	
	public void setLayoutMarginBottom(String marginBottom)
	{
		MarginLayoutParams layoutParams = (MarginLayoutParams)view.getLayoutParams();
		try
		{
			this.bottomMargin = Integer.valueOf(marginBottom);
			layoutParams.bottomMargin = DisplayUtils.dip2px(view.getContext(), this.bottomMargin);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The layout marginBottom can not be parsed from value " + marginBottom);
		}
		
		view.setLayoutParams(layoutParams);
	}

	
	public String getBackgroundColor()
	{
		return backgroundColor;
	}
	
	
	public void setBackgroundColor(String color)
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
				view.setBackgroundColor(rgbColorInt);
				this.backgroundColor = color;
			}
			else
			{
				LogTools.w(LOG_TAG, "The backgroundColor can not be parsed from value " + color);
			}
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The backgroundColor can not be parsed from value " + color);
		}
	}

	
	public String getGravityValue()
	{
		return gravity;
	}
	
	
	public void setGravityValue(String gravity)
	{
		int intGravity = GravityValue.getIntValue(gravity);
		if(view instanceof TextView)
		{
			((TextView)view).setGravity(intGravity);
			if(intGravity != Gravity.NO_GRAVITY)
			{
				this.gravity = gravity;
			}
			else
			{
				this.gravity = null;
			}
		}
		else if(view instanceof LinearLayout)
		{
			((LinearLayout)view).setGravity(intGravity);
			if(intGravity != Gravity.NO_GRAVITY)
			{
				this.gravity = gravity;
			}
			else
			{
				this.gravity = null;
			}
		}
		else if(view instanceof RelativeLayout)
		{
			((RelativeLayout)view).setGravity(intGravity);
			if(intGravity != Gravity.NO_GRAVITY)
			{
				this.gravity = gravity;
			}
			else
			{
				this.gravity = null;
			}
		}
	}

	
	public String getLayoutGravityValue()
	{
		return layoutGravity;
	}
	
	
	public void setLayoutGravityValue(String gravity)
	{
		if(view.getLayoutParams() instanceof LinearLayout.LayoutParams)
		{
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
			layoutParams.gravity = GravityValue.getIntValue(gravity);
			view.setLayoutParams(layoutParams);
			if(layoutParams.gravity == Gravity.NO_GRAVITY)
			{
				this.layoutGravity = null;
			}
			else
			{
				this.layoutGravity = gravity;
			}
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
	
	
	public void setAlignParentLeft(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(XmlOutputConstant.TRUE.equals(value))
			{
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			}
			else
			{
				//将值置为0，即消除相对位置设置
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
			}
			view.setLayoutParams(layoutParams);
		}
	}

	
	public String getAlignParentLeft()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_LEFT))
			{
				return XmlOutputConstant.TRUE;
			}
		}
		return null;
	}

	
	public void setAlignParentRight(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(XmlOutputConstant.TRUE.equals(value))
			{
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			}
			else
			{
				//将值置为0，即消除相对位置设置
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
			}
			view.setLayoutParams(layoutParams);
		}
	}
	
	
	public String getAlignParentRight()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_RIGHT))
			{
				return XmlOutputConstant.TRUE;
			}
		}
		return null;
	}
	
	
	public void setAlignParentTop(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(XmlOutputConstant.TRUE.equals(value))
			{
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_TOP);
			}
			else
			{
				//将值置为0，即消除相对位置设置
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
			}
			view.setLayoutParams(layoutParams);
		}
	}

	
	public String getAlignParentTop()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_TOP))
			{
				return XmlOutputConstant.TRUE;
			}
		}
		return null;
	}

	
	public void setAlignParentBottom(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(XmlOutputConstant.TRUE.equals(value))
			{
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			}
			else
			{
				//将值置为0，即消除相对位置设置
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
			}
			view.setLayoutParams(layoutParams);
		}
	}
	
	
	public String getAlignParentBottom()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_BOTTOM))
			{
				return XmlOutputConstant.TRUE;
			}
		}
		return null;
	}
	
	
	public void setToLeftOf(String anchorIdName)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
			((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.LEFT_OF, anchorId, anchorIdName);
			view.setLayoutParams(layoutParams);
		}
	}
	
	
	public String getToLeftOf()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.LEFT_OF))
			{
				return relativieLayoutParams.getBelowAnchorId();
			}
		}
		return null;
	}
	
	
	public void setToRightOf(String anchorIdName)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
			((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.RIGHT_OF, anchorId, anchorIdName);
			view.setLayoutParams(layoutParams);
		}
	}
	
	
	public String getToRightOf()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.RIGHT_OF))
			{
				return relativieLayoutParams.getBelowAnchorId();
			}
		}
		return null;
	}
	
	
	public void setBelow(String anchorIdName)
	{
		if(!TextUtils.isEmpty(anchorIdName))
		{
			ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
			if(layoutParams instanceof TGRelativeLayout.LayoutParams)
			{
				int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
				((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.BELOW, anchorId, anchorIdName);
				view.setLayoutParams(layoutParams);
			}
		}
	}

	
	public String getBelow()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.BELOW))
			{
				return relativieLayoutParams.getBelowAnchorId();
			}
		}
		return null;
	}
	
	
	public void setAbove(String anchorIdName)
	{
		if(!TextUtils.isEmpty(anchorIdName))
		{
			ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
			if(layoutParams instanceof TGRelativeLayout.LayoutParams)
			{
				int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
				((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ABOVE, anchorId, anchorIdName);
				view.setLayoutParams(layoutParams);
			}
		}
	}

	
	public String getAbove()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ABOVE))
			{
				return relativieLayoutParams.getAboveAnchorId();
			}
		}
		return null;
	}

	
	public void setAlignLeft(String anchorIdName)
	{
		if(!TextUtils.isEmpty(anchorIdName))
		{
			ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
			if(layoutParams instanceof TGRelativeLayout.LayoutParams)
			{
				int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
				((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_LEFT, anchorId, anchorIdName);
				view.setLayoutParams(layoutParams);
			}
		}
	}

	
	public String getAlignLeft()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_LEFT))
			{
				return relativieLayoutParams.getAlignLeftAnchorId();
			}
		}
		return null;
	}
	
	
	public void setAlignRight(String anchorIdName)
	{
		if(!TextUtils.isEmpty(anchorIdName))
		{
			ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
			if(layoutParams instanceof TGRelativeLayout.LayoutParams)
			{
				int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
				((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_RIGHT, anchorId, anchorIdName);
				view.setLayoutParams(layoutParams);
			}
		}
	}

	
	public String getAlignRight()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_RIGHT))
			{
				return relativieLayoutParams.getAlignRightAnchorId();
			}
		}
		return null;
	}
	
	
	public void setAlignTop(String anchorIdName)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
			((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_TOP, anchorId, anchorIdName);
			view.setLayoutParams(layoutParams);
		}
	}

	
	public String getAlignTop()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_TOP))
			{
				return relativieLayoutParams.getAlignTopAnchorId();
			}
		}
		return null;
	}
	
	
	public void setAlignBottom(String anchorIdName)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
			((TGRelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.ALIGN_BOTTOM, anchorId, anchorIdName);
			view.setLayoutParams(layoutParams);
		}
	}

	
	public String getAlignBottom()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof TGRelativeLayout.LayoutParams)
		{
			TGRelativeLayout.LayoutParams relativieLayoutParams = (TGRelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_BOTTOM))
			{
				return relativieLayoutParams.getAlignBottomAnchorId();
			}
		}
		return null;
	}
	
	
	public void setCenterInParent(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(XmlOutputConstant.TRUE.equals(value))
			{
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.CENTER_IN_PARENT);
			}
			else
			{
				//将值置为0，即消除相对位置设置
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.CENTER_IN_PARENT, 0);
			}
			view.setLayoutParams(layoutParams);
		}
	}
	
	
	public String getCenterInParent()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.CENTER_IN_PARENT))
			{
				return XmlOutputConstant.TRUE;
			}
		}
		return null;
	}
	
	
	public void setCenterHorizontal(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(XmlOutputConstant.TRUE.equals(value))
			{
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.CENTER_HORIZONTAL);
			}
			else
			{
				//将值置为0，即消除相对位置设置
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.CENTER_HORIZONTAL, 0);
			}
			view.setLayoutParams(layoutParams);
		}
	}
	
	
	public String getCenterHorizontal()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.CENTER_HORIZONTAL))
			{
				return XmlOutputConstant.TRUE;
			}
		}
		return null;
	}
	
	
	public void setCenterVertical(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(XmlOutputConstant.TRUE.equals(value))
			{
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.CENTER_VERTICAL);
			}
			else
			{
				//将值置为0，即消除相对位置设置
				((RelativeLayout.LayoutParams)layoutParams).addRule(RelativeLayout.CENTER_VERTICAL, 0);
			}
			view.setLayoutParams(layoutParams);
		}
	}
	
	
	public String getCenterVertical()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.CENTER_VERTICAL))
			{
				return XmlOutputConstant.TRUE;
			}
		}
		return null;
	}
	
	private boolean containsRule(int[] rules, int verb)
	{
		//未添加该属性时，值为0
		return (rules[verb] != 0);
	}

	public void setOnClickListener(boolean isSetOnClickListener)
	{
		this.setOnClickListener = isSetOnClickListener;
	}

	public boolean isSetOnClickListener()
	{
		return setOnClickListener;
	}
}
