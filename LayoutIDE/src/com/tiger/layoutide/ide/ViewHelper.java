package com.tiger.layoutide.ide;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mn.tiger.utility.DisplayUtils;
import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.utils.Constant;
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
	
	public View getView()
	{
		return view;
	}

	@Override
	public String getClassSimpleName()
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
		// View 自行返回
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
			layoutParams.topMargin = DisplayUtils.dip2px(view.getContext(), this.topMargin);
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
			layoutParams.bottomMargin = DisplayUtils.dip2px(view.getContext(), this.bottomMargin);
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		view.setLayoutParams(layoutParams);
	}

	@Override
	public String getAlignParentLeft()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			return containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_LEFT) + "";
		}
		return null;
	}

	@Override
	public void setAlignParentRight(String value)
	{
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		view.setLayoutParams(layoutParams);
	}
	
	@Override
	public String getAlignParentRight()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			return containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_RIGHT) + "";
		}
		return null;
	}
	
	@Override
	public void setAlignParentTop(String value)
	{
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		view.setLayoutParams(layoutParams);
	}

	@Override
	public String getAlignParentTop()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			return containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_TOP) + "";
		}
		return null;
	}

	@Override
	public void setAlignParentBottom(String value)
	{
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		view.setLayoutParams(layoutParams);
	}
	
	@Override
	public String getAlignParentBottom()
	{
		ViewGroup.LayoutParams layoutParams = (ViewGroup.LayoutParams) view.getLayoutParams();
		if(layoutParams instanceof RelativeLayout.LayoutParams)
		{
			RelativeLayout.LayoutParams relativieLayoutParams = (RelativeLayout.LayoutParams)layoutParams;
			return containsRule(relativieLayoutParams.getRules(), RelativeLayout.ALIGN_PARENT_BOTTOM) + "";
		}
		return null;
	}
	
	@Override
	public void setToLeftOf(String anchorIdName)
	{
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.LEFT_OF, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.RIGHT_OF, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.BELOW, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.ABOVE, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.ALIGN_LEFT, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.ALIGN_RIGHT, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.ALIGN_TOP, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
		int anchorId = ((TGRelativeLayout)view.getParent()).getChildId(anchorIdName);
		if(anchorId > 0)
		{
			layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, anchorId);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			LogTools.w(LOG_TAG, "Can not find child view which id equals " + anchorIdName);
		}
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
	
	private boolean containsRule(int[] rules, int verb)
	{
		//未添加该属性时，值为0
		return (rules[verb] != 0);
	}

	
	
}
