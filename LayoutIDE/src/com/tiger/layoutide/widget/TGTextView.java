package com.tiger.layoutide.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tiger.layoutide.ide.ViewHelper;
import com.tiger.layoutide.tree.IView;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 *
 */
public class TGTextView extends TextView implements IViewTreeNode, IView
{
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	public TGTextView(Context context)
	{
		this(context, null);
	}
	
	public TGTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewHelper = new ViewHelper(this)
		{
			@Override
			public String getClassSimpleName()
			{
				return "TextView";
			}
		};
		
		viewTreeNode = new ViewTreeNodeImp(viewHelper);
	}

	@Override
	public String getXMLString()
	{
		return viewTreeNode.getXMLString();
	}
	
	@Override
	public void setText(CharSequence text, BufferType type)
	{
		super.setText(text, type);
		if(!TextUtils.isEmpty(text))
		{
			viewHelper.setText(text.toString());
		}
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
	
	@Override
	public void setTextColor(int color)
	{
		super.setTextColor(color);
		viewHelper.setTextColor(color);
	}
	
	@Override
	public int getTextColor()
	{
		return viewHelper.getTextColor();
	}
	
	@Override
	public void setTextSize(int unit, float size)
	{
		super.setTextSize(unit, size);
		viewHelper.setTextSize(size);
	}
	
	@Override
	public void setBackgroundColor(int color)
	{
		super.setBackgroundColor(color);
		viewHelper.setBackgroundColor(color);
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
	public int getBackgroundColor()
	{
		return viewHelper.getBackgroundColor();
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
	public void setLayoutWeight(float weight)
	{
		viewHelper.setLayoutWeight(weight);
	}

	@Override
	public void setLayoutMarginLeft(int marginLeft)
	{
		viewHelper.setLayoutMarginLeft(marginLeft);
	}

	
}
