package com.tiger.layoutide.widget;

import android.content.Context;
import android.widget.RelativeLayout;

import com.tiger.layoutide.ide.ViewHelper;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 *
 */
public class TGRelativeLayout extends RelativeLayout implements IViewTreeNode
{
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	public TGRelativeLayout(Context context)
	{
		super(context);
		
		viewHelper = new ViewHelper(this)
		{
			@Override
			public String getClassSimpleName()
			{
				return super.getClassSimpleName();
			}
		};
		
		viewTreeNode = new ViewTreeNodeImp(viewHelper);
	}

	@Override
	public String getXMLString()
	{
		return viewTreeNode.toString();
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
	public void setBackgroundColor(int color)
	{
		super.setBackgroundColor(color);
		viewHelper.setBackgroundColor(color);
	}
	
}
