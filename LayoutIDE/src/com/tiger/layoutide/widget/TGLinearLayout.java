package com.tiger.layoutide.widget;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.tiger.layoutide.ide.ViewGroupHelper;
import com.tiger.layoutide.tree.IViewGroup;
import com.tiger.layoutide.tree.IViewTree;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeImp;

/**
 * @author Dalang
 *
 */
public class TGLinearLayout extends LinearLayout implements IViewGroup, IViewTree
{
	private IViewTree viewTree;
	
	private ViewGroupHelper viewGroupHelper;
	
	public TGLinearLayout(Context context)
	{
		this(context, null);
	}
	
	public TGLinearLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewGroupHelper = new ViewGroupHelper(this)
		{
			@Override
			public String getClassSimpleName()
			{
				return "LinearLayout";
			}
		};
		
		viewTree = new ViewTreeImp(viewGroupHelper);
	}


	@Override
	public String getXMLString()
	{
		return viewTree.getXMLString();
	}
	
	@Override
	public void dump()
	{
		viewTree.dump();
	}
	
	public void setIdName(String idName)
	{
		viewGroupHelper.setIdName(idName);
	}
	
	@Override
	public void setBackgroundColor(int color)
	{
		super.setBackgroundColor(color);
		viewGroupHelper.setBackgroundColor(color);
	}
	
	@Override
	public void addView(View child, int index, android.view.ViewGroup.LayoutParams params)
	{
		super.addView(child, index, params);
		
		addViewTreeNode((IViewTreeNode) child);
	}

	@Override
	public void addViewTreeNode(IViewTreeNode viewTreeNode)
	{
		viewTree.addViewTreeNode(viewTreeNode);
	}

	@Override
	public void removeViewTreeNode(IViewTreeNode viewTreeNode)
	{
		
	}

	@Override
	public void clearViewTreeNode()
	{
		
	}

	@Override
	public List<IViewTreeNode> getViewTreeNodes()
	{
		return null;
	}

	@Override
	public String getClassSimpleName()
	{
		return "LinearLayout";
	}

	@Override
	public void setLayoutWidth(String layoutWidth)
	{
		viewGroupHelper.setLayoutWidth(layoutWidth);
	}

	@Override
	public String getLayoutWidth()
	{
		return viewGroupHelper.getLayoutWidth();
	}

	@Override
	public void setLayoutHeight(String layoutHeight)
	{
		viewGroupHelper.setLayoutHeight(layoutHeight);
	}

	@Override
	public String getLayoutHeight()
	{
		return viewGroupHelper.getLayoutHeight();
	}

	@Override
	public void setLayoutWeight(float weight)
	{
		viewGroupHelper.setLayoutWeight(weight);
	}

	@Override
	public float getLayoutWeight()
	{
		return viewGroupHelper.getLayoutWeight();
	}

	@Override
	public void setLayoutMarginLeft(int marginLeft)
	{
		viewGroupHelper.setLayoutMarginLeft(marginLeft);
	}

	@Override
	public int getLayoutMarginLeft()
	{
		return viewGroupHelper.getLayoutMarginLeft();
	}

	@Override
	public int getLayoutMarginRight()
	{
		return viewGroupHelper.getLayoutMarginRight();
	}

	@Override
	public int getLayoutMarginTop()
	{
		return viewGroupHelper.getLayoutMarginTop();
	}

	@Override
	public int getLayoutMarginBottom()
	{
		return viewGroupHelper.getLayoutMarginBottom();
	}

	@Override
	public int getBackgroundColor()
	{
		return viewGroupHelper.getBackgroundColor();
	}

	@Override
	public String getGravityStringValue()
	{
		return viewGroupHelper.getGravityStringValue();
	}

	@Override
	public String getLayoutGravityStringValue()
	{
		return viewGroupHelper.getLayoutGravityStringValue();
	}
	
	@Override
	public String getIdName()
	{
		return viewGroupHelper.getIdName();
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
	public void setTextSize(float textSize)
	{
	}

	@Override
	public float getTextSize()
	{
		return 0;
	}

	@Override
	public void setTextColor(int textColor)
	{
	}

	@Override
	public int getTextColor()
	{
		return 0;
	}
	
}
