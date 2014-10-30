package com.tiger.layoutide.ide;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mn.tiger.utility.DisplayUtils;
import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.utils.Constant;
import com.tiger.layoutide.utils.GravityValue;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGRelativeLayout;

public class ViewHelper implements IView
{
	private static String LOG_TAG = ViewHelper.class.getSimpleName();
	
	private View view;
	
	private String idName = "";

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

	public ViewHelper(View view)
	{
		this.view = view;
	}
	
	public View getView()
	{
		return view;
	}

	@Override
	public String getSimpleClassName()
	{
		return null;
	}
	
	@Override
	public String getPackageName()
	{
		return null;
	}

	public void setIdName(String idName)
	{
		this.idName = idName;
		view.setTag(idName);
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
		if(!TextUtils.isEmpty(layoutWidth))
		{
			if(layoutWidth.equals(Constant.WRAP_CONTENT))
			{
				return layoutWidth;
			}
			else if(layoutWidth.equals(Constant.MATCH_PARENT))
			{
				return layoutWidth;
			}
			
			return layoutWidth + "dp";
		}
		
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		switch (layoutParams.width)
		{
			case ViewGroup.LayoutParams.WRAP_CONTENT:
				layoutWidth = Constant.WRAP_CONTENT;
				return layoutWidth;

			case ViewGroup.LayoutParams.MATCH_PARENT:
				layoutWidth = Constant.MATCH_PARENT;
				return layoutWidth;

			default:
				layoutWidth = DisplayUtils.px2dip(view.getContext(), layoutParams.width) + "";
				return layoutWidth + "dp";
		}
	}
	
	@Override
	public void setLayoutWidth(String layoutWidth)
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		
		if(Constant.WRAP_CONTENT.equals(layoutWidth.toString()))
		{
			layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
			this.layoutWidth = layoutWidth;
		}
		else if(Constant.MATCH_PARENT.equals(layoutWidth.toString()))
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

	@Override
	public String getLayoutHeight()
	{
		if(!TextUtils.isEmpty(layoutHeight))
		{
			if(layoutHeight.equals(Constant.WRAP_CONTENT))
			{
				return layoutHeight;
			}
			else if(layoutHeight.equals(Constant.MATCH_PARENT))
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
					layoutHeight = Constant.WRAP_CONTENT;
					return Constant.WRAP_CONTENT;

				case ViewGroup.LayoutParams.MATCH_PARENT:
					layoutHeight = Constant.MATCH_PARENT;
					return Constant.MATCH_PARENT;

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
	
	@Override
	public void setLayoutHeight(String layoutHeight)
	{
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		if(Constant.WRAP_CONTENT.equals(layoutHeight.toString()))
		{
			layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
			this.layoutHeight = layoutHeight;
		}
		else if(Constant.MATCH_PARENT.equals(layoutHeight.toString()))
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

	@Override
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

	@Override
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

	@Override
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
			LogTools.e(LOG_TAG, "The layout marginBottom can not be parsed from value " + bottomMargin);
		}
		
		view.setLayoutParams(layoutParams);
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
	public String getGravityValue()
	{
		return gravity;
	}
	
	@Override
	public void setGravityValue(String gravity)
	{
		this.gravity = gravity;
	}

	@Override
	public String getLayoutGravityValue()
	{
		return layoutGravity;
	}
	
	@Override
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

	@Override
	public void setText(CharSequence text)
	{
		// View 自行返回
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
	
	@Override
	public void onSelected()
	{
	}
	
	@Override
	public void onUnSelected()
	{
	}
	
	@Override
	public View newInstance()
	{
		return null;
	}
	
	@Override
	public void setAlignParentLeft(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(Constant.TRUE.equals(value))
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

	@Override
	public String getAlignParentLeft()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_LEFT))
			{
				return Constant.TRUE;
			}
		}
		return null;
	}

	@Override
	public void setAlignParentRight(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(Constant.TRUE.equals(value))
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
	
	@Override
	public String getAlignParentRight()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_RIGHT))
			{
				return Constant.TRUE;
			}
		}
		return null;
	}
	
	@Override
	public void setAlignParentTop(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(Constant.TRUE.equals(value))
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

	@Override
	public String getAlignParentTop()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_TOP))
			{
				return Constant.TRUE;
			}
		}
		return null;
	}

	@Override
	public void setAlignParentBottom(String value)
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			if(Constant.TRUE.equals(value))
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
	
	@Override
	public String getAlignParentBottom()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_BOTTOM))
			{
				return Constant.TRUE;
			}
		}
		return null;
	}
	
	@Override
	public void setToLeftOf(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.LEFT_OF, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}
	
	@Override
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
	
	@Override
	public void setToRightOf(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.RIGHT_OF, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}
	
	@Override
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
	
	@Override
	public void setBelow(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.BELOW, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}

	@Override
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
	
	@Override
	public void setAbove(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.ABOVE, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}

	@Override
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

	@Override
	public void setAlignLeft(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.ALIGN_LEFT, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}

	@Override
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
	
	@Override
	public void setAlignRight(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.ALIGN_RIGHT, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}

	@Override
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
	
	@Override
	public void setAlignTop(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.ALIGN_TOP, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}

	@Override
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
	
	@Override
	public void setAlignBottom(String anchorIdName)
	{
		TGRelativeLayout.LayoutParams layoutParams = (TGRelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, anchorId, anchorIdName);
		view.setLayoutParams(layoutParams);
	}

	@Override
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
	
	@Override
	public void setCenterInParent(String value)
	{
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		if(Constant.TRUE.equals(value))
		{
			layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		}
		else
		{
			//将值置为0，即消除相对位置设置
			layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, 0);
		}
		view.setLayoutParams(layoutParams);
	}
	
	@Override
	public String getCenterInParent()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.CENTER_IN_PARENT))
			{
				return Constant.TRUE;
			}
		}
		return null;
	}
	
	@Override
	public void setCenterHorizontal(String value)
	{
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		if(Constant.TRUE.equals(value))
		{
			layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		}
		else
		{
			//将值置为0，即消除相对位置设置
			layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 0);
		}
		view.setLayoutParams(layoutParams);
	}
	
	@Override
	public String getCenterHorizontal()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.CENTER_HORIZONTAL))
			{
				return Constant.TRUE;
			}
		}
		return null;
	}
	
	@Override
	public void setCenterVertical(String value)
	{
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		if(Constant.TRUE.equals(value))
		{
			layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
		}
		else
		{
			//将值置为0，即消除相对位置设置
			layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, 0);
		}
		view.setLayoutParams(layoutParams);
	}
	
	@Override
	public String getCenterVertical()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			if(containsRule(relativieLayoutParams.getRules(), RelativeLayout.CENTER_VERTICAL))
			{
				return Constant.TRUE;
			}
		}
		return null;
	}
	
	private boolean containsRule(int[] rules, int verb)
	{
		//未添加该属性时，值为0
		return (rules[verb] != 0);
	}

	@Override
	public void setOrientationValue(String orientation)
	{
		if(view instanceof LinearLayout)
		{
			this.orientation = orientation;
			if(orientation.equals(Constant.ORIENTATION_HORIZONTAL))
			{
				((LinearLayout)view).setOrientation(LinearLayout.HORIZONTAL);
			}
			else
			{
				((LinearLayout)view).setOrientation(LinearLayout.VERTICAL);
			}
		}
	}

	@Override
	public String getOrientationValue()
	{
		if(!TextUtils.isEmpty(orientation))
		{
			return orientation;
		}
		
		if(view instanceof LinearLayout)
		{
			int orientation = ((LinearLayout)view).getOrientation();
			if(orientation == LinearLayout.HORIZONTAL)
			{
				this.orientation = Constant.ORIENTATION_HORIZONTAL;
			}
			else
			{
				this.orientation = Constant.ORIENTATION_VERTICAL;
			}
		}
		
		return this.orientation;
	}
	
}
