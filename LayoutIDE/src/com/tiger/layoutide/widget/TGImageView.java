package com.tiger.layoutide.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView.BufferType;

import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.ide.ViewHelper;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 *
 */
public class TGImageView extends ImageView implements IViewTreeNode, IView
{
	private static String LOG_TAG = TGImageView.class.getSimpleName();
	
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	public TGImageView(Context context)
	{
		super(context);
		
		viewHelper = new ViewHelper(this)
		{
			@Override
			public String getClassSimpleName()
			{
				return "EditText";
			}
		};
		
		viewTreeNode = new ViewTreeNodeImp(viewHelper);
	}

	@Override
	public String getXMLString()
	{
		return viewTreeNode.getXMLString();
	}
	
	public void setText(CharSequence text, BufferType type)
	{
	}
	
	@Override
	public void dump()
	{
		viewTreeNode.dump();
	}
	
	public void setIdName(String idName)
	{
		viewHelper.setIdName(idName);
	}
	
	public void setTextColor(int color)
	{
	}
	
	public String getTextColor()
	{
		return null;
	}
	
	public void setTextSize(int unit, float size)
	{
	}
	
	@Override
	public String getClassSimpleName()
	{
		return "TextView";
	}

	@Override
	public String getIdName()
	{
		return viewHelper.getIdName();
	}

	@Override
	public String getLayoutWidth()
	{
		return viewHelper.getLayoutWidth();
	}

	@Override
	public String getLayoutHeight()
	{
		return viewHelper.getLayoutHeight();
	}

	@Override
	public float getLayoutWeight()
	{
		return viewHelper.getLayoutWeight();
	}

	@Override
	public int getLayoutMarginLeft()
	{
		return viewHelper.getLayoutMarginLeft();
	}

	@Override
	public int getLayoutMarginRight()
	{
		return viewHelper.getLayoutMarginRight();
	}

	@Override
	public int getLayoutMarginTop()
	{
		return viewHelper.getLayoutMarginTop();
	}

	@Override
	public int getLayoutMarginBottom()
	{
		return viewHelper.getLayoutMarginBottom();
	}

	@Override
	public String getBackgroundColor()
	{
		return viewHelper.getBackgroundColor();
	}
	
	@Override
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
				super.setBackgroundColor(rgbColorInt);
				viewHelper.setBackgroundColor(color);
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

	@Override
	public String getGravityStringValue()
	{
		return viewHelper.getGravityStringValue();
	}

	@Override
	public String getLayoutGravityStringValue()
	{
		return viewHelper.getLayoutGravityStringValue();
	}

	@Override
	public void setLayoutWidth(String layoutWidth)
	{
		viewHelper.setLayoutWidth(layoutWidth);
	}

	@Override
	public void setLayoutHeight(String layoutHeight)
	{
		viewHelper.setLayoutHeight(layoutHeight);
	}

	@Override
	public void setLayoutWeight(String weight)
	{
		viewHelper.setLayoutWeight(weight);
	}

	@Override
	public void setLayoutMarginLeft(String marginLeft)
	{
		viewHelper.setLayoutMarginLeft(marginLeft);
	}

	@Override
	public void setLayoutMarginRight(String marginRight)
	{
		viewHelper.setLayoutMarginRight(marginRight);
	}

	@Override
	public void setLayoutMarginTop(String marginTop)
	{
		viewHelper.setLayoutMarginTop(marginTop);
	}

	@Override
	public void setLayoutMarginBottom(String marginBottom)
	{
		viewHelper.setLayoutMarginBottom(marginBottom);
	}

	@Override
	public void setText(CharSequence text)
	{
	}

	@Override
	public CharSequence getText()
	{
		return null;
	}

	@Override
	public void setTextSize(String textSize)
	{
	}

	@Override
	public float getTextSize()
	{
		return 0;
	}

	@Override
	public void setTextColor(String textColor)
	{
		
	}
}
