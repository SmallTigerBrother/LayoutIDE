package com.tiger.layoutide.widget;

import android.content.Context;
import android.widget.EditText;

import com.tiger.layoutide.ide.ViewHelper;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 *
 */
public class TGEditText extends EditText implements IViewTreeNode
{
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	public TGEditText(Context context)
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
	public void setText(CharSequence text, BufferType type)
	{
		super.setText(text, type);
		viewHelper.setText(text.toString());
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
	
}
