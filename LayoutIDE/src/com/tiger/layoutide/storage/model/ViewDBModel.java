package com.tiger.layoutide.storage.model;

import com.tiger.layoutide.utils.XmlOutputConstant;

public class ViewDBModel
{
	private int _id;
	
	private boolean isRootViewGroup = false;
	
	private String simpleClassName = "";
	
	private String idName = "";
	
	private String parentViewClassName = "";

	private String textColor = "";

	private String backgroundColor = "";
	
	private String text = "";
	
	private float textSize = 16;
	
	private String orientation;
	
	private String gravity;
	
	private String layoutGravity;
	
	private String layoutWidth = "";
	
	private String layoutHeight = "";
	
	private int leftMargin = Integer.MIN_VALUE;
	
	private int rightMargin = Integer.MIN_VALUE;
	
	private int topMargin = Integer.MIN_VALUE;
	
	private int bottomMargin = Integer.MIN_VALUE;
	
	private float layoutWeight = 0;
	
	private String alignParentLeft = XmlOutputConstant.FALSE;
	
	private String alignParentRight = XmlOutputConstant.FALSE;
	
	private String alignParentTop = XmlOutputConstant.FALSE;
	
	private String alignParentBottom = XmlOutputConstant.FALSE;
	
	private String toLeftOf = "";
	
	private String toRightOf = "";
	
	private String below = "";
	
	private String above = "";
	
	private String alignLeft = "";
	
	private String alignRight = "";
	
	private String alignTop = "";
	
	private String alignBottom = "";
	
	private String centerInParent = "";
	
	private String centerHorizontal = "";
	
	private String centerVertical = "";
	
	private String itemLayout = "";
	
	public String getAlignParentLeft()
	{
		return alignParentLeft;
	}

	public void setAlignParentLeft(String alignParentLeft)
	{
		this.alignParentLeft = alignParentLeft;
	}

	public String getAlignParentRight()
	{
		return alignParentRight;
	}

	public void setAlignParentRight(String alignParentRight)
	{
		this.alignParentRight = alignParentRight;
	}

	public String getAlignParentTop()
	{
		return alignParentTop;
	}

	public void setAlignParentTop(String alignParentTop)
	{
		this.alignParentTop = alignParentTop;
	}

	public String getAlignParentBottom()
	{
		return alignParentBottom;
	}

	public void setAlignParentBottom(String alignParentBottom)
	{
		this.alignParentBottom = alignParentBottom;
	}

	public String getToLeftOf()
	{
		return toLeftOf;
	}

	public void setToLeftOf(String toLeftOf)
	{
		this.toLeftOf = toLeftOf;
	}

	public String getToRightOf()
	{
		return toRightOf;
	}

	public void setToRightOf(String toRightOf)
	{
		this.toRightOf = toRightOf;
	}

	public String getBelow()
	{
		return below;
	}

	public void setBelow(String below)
	{
		this.below = below;
	}

	public String getAbove()
	{
		return above;
	}

	public void setAbove(String above)
	{
		this.above = above;
	}

	public String getAlignLeft()
	{
		return alignLeft;
	}

	public void setAlignLeft(String alignLeft)
	{
		this.alignLeft = alignLeft;
	}

	public String getAlignRight()
	{
		return alignRight;
	}

	public void setAlignRight(String alignRight)
	{
		this.alignRight = alignRight;
	}

	public String getAlignTop()
	{
		return alignTop;
	}

	public void setAlignTop(String alignTop)
	{
		this.alignTop = alignTop;
	}

	public String getAlignBottom()
	{
		return alignBottom;
	}

	public void setAlignBottom(String alignBottom)
	{
		this.alignBottom = alignBottom;
	}

	public String getCenterInParent()
	{
		return centerInParent;
	}

	public void setCenterInParent(String centerInParent)
	{
		this.centerInParent = centerInParent;
	}

	public String getCenterHorizontal()
	{
		return centerHorizontal;
	}

	public void setCenterHorizontal(String centerHorizontal)
	{
		this.centerHorizontal = centerHorizontal;
	}

	public String getCenterVertical()
	{
		return centerVertical;
	}

	public void setCenterVertical(String centerVertical)
	{
		this.centerVertical = centerVertical;
	}

	public String getIdName()
	{
		return idName;
	}

	public void setIdName(String idName)
	{
		this.idName = idName;
	}

	public String getTextColor()
	{
		return textColor;
	}

	public void setTextColor(String textColor)
	{
		this.textColor = textColor;
	}

	public String getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor)
	{
		this.backgroundColor = backgroundColor;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public float getTextSize()
	{
		return textSize;
	}

	public void setTextSize(float textSize)
	{
		this.textSize = textSize;
	}

	public String getOrientation()
	{
		return orientation;
	}

	public void setOrientation(String orientation)
	{
		this.orientation = orientation;
	}

	public String getLayoutGravity()
	{
		return layoutGravity;
	}

	public void setLayoutGravity(String layoutGravity)
	{
		this.layoutGravity = layoutGravity;
	}

	public String getLayoutWidth()
	{
		return layoutWidth;
	}

	public void setLayoutWidth(String layoutWidth)
	{
		this.layoutWidth = layoutWidth;
	}

	public String getLayoutHeight()
	{
		return layoutHeight;
	}

	public void setLayoutHeight(String layoutHeight)
	{
		this.layoutHeight = layoutHeight;
	}

	public String getGravity()
	{
		return gravity;
	}

	public void setGravity(String gravity)
	{
		this.gravity = gravity;
	}

	public int getLeftMargin()
	{
		return leftMargin;
	}

	public void setLeftMargin(int leftMargin)
	{
		this.leftMargin = leftMargin;
	}

	public int getRightMargin()
	{
		return rightMargin;
	}

	public void setRightMargin(int rightMargin)
	{
		this.rightMargin = rightMargin;
	}

	public int getTopMargin()
	{
		return topMargin;
	}

	public void setTopMargin(int topMargin)
	{
		this.topMargin = topMargin;
	}

	public int getBottomMargin()
	{
		return bottomMargin;
	}

	public void setBottomMargin(int bottomMargin)
	{
		this.bottomMargin = bottomMargin;
	}

	public String getSimpleClassName()
	{
		return simpleClassName;
	}

	public void setSimpleClassName(String simpleClassName)
	{
		this.simpleClassName = simpleClassName;
	}

	public float getLayoutWeight()
	{
		return layoutWeight;
	}

	public void setLayoutWeight(float layoutWeight)
	{
		this.layoutWeight = layoutWeight;
	}

	public String getParentViewClassName()
	{
		return parentViewClassName;
	}

	public void setParentViewClassName(String parentViewClassName)
	{
		this.parentViewClassName = parentViewClassName;
	}

	public int get_id()
	{
		return _id;
	}

	public void set_id(int _id)
	{
		this._id = _id;
	}
	
	public boolean isRootViewGroup()
	{
		return isRootViewGroup;
	}

	public void setRootViewGroup(boolean isRootViewGroup)
	{
		this.isRootViewGroup = isRootViewGroup;
	}

	public String getItemLayout()
	{
		return itemLayout;
	}

	public void setItemLayout(String itemLayout)
	{
		this.itemLayout = itemLayout;
	}
}
