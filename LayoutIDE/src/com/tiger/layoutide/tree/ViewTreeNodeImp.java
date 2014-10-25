package com.tiger.layoutide.tree;

import android.text.TextUtils;

import com.tiger.layoutide.utils.Constant;

public class ViewTreeNodeImp implements IViewTreeNode
{
	private IView view;
	
	public ViewTreeNodeImp(IView view)
	{
		this.view = view;
	}
	
	@Override
	public String getXMLString()
	{
		StringBuilder xmlOutputStr = new StringBuilder("<" + view.getClassSimpleName() + "\n");
		
		if(!TextUtils.isEmpty(view.getIdName()))
		{
			xmlOutputStr.append(String.format(Constant.ID, view.getIdName()) + "\n");
		}
		
		xmlOutputStr.append(String.format(Constant.LAYOUT_WIDTH, view.getLayoutWidth()) + "\n");
		xmlOutputStr.append(String.format(Constant.LAYOUT_HEIGHT, view.getLayoutHeight()) + "\n");
		
		if(view.getLayoutWeight() > 0)
		{
			xmlOutputStr.append(String.format(Constant.LAYOUT_WEIGHT, view.getLayoutWeight()) + "\n");
		}
		
		if(view.getLayoutMarginLeft() > 0)
		{
			xmlOutputStr.append(String.format(Constant.LAYOUT_MARGIN_LEFT, view.getLayoutMarginLeft() + "dp") + "\n");
		}
		
		if(view.getLayoutMarginRight() > 0)
		{
			xmlOutputStr.append(String.format(Constant.LAYOUT_MARGIN_RIGHT, view.getLayoutMarginRight() + "dp") + "\n");
		}
		
		if(view.getLayoutMarginTop() > 0)
		{
			xmlOutputStr.append(String.format(Constant.LAYOUT_MARGIN_TOP, view.getLayoutMarginTop() + "dp") + "\n");
		}
		
		if(view.getLayoutMarginBottom() > 0)
		{
			xmlOutputStr.append(String.format(Constant.LAYOUT_MARGIN_BOTTOM, view.getLayoutMarginBottom() + "dp") + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getText()))
		{
			xmlOutputStr.append(String.format(Constant.TEXT, view.getText()) + "\n");
		}
		
		if(view.getTextSize() > 0)
		{
			xmlOutputStr.append(String.format(Constant.TEXT_SIZE, view.getTextSize() + "sp") + "\n");
		}
		
		if(view.getTextColor() > 0)
		{
			//TODO 十六进制转xml中的color
			xmlOutputStr.append(String.format(Constant.TEXT_COLOR, view.getTextColor()) + "\n");
		}
		
		if(view.getBackgroundColor() > 0)
		{
			//TODO 十六进制转xml中的color
			xmlOutputStr.append(String.format(Constant.BACKGROUND_COLOR, view.getBackgroundColor()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getGravityStringValue()))
		{
			xmlOutputStr.append(String.format(Constant.GRAVITY, view.getGravityStringValue()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getLayoutGravityStringValue()))
		{
			xmlOutputStr.append(String.format(Constant.LAYOUT_GRAVITY, view.getLayoutGravityStringValue()) + "\n");
		}
		
		xmlOutputStr.append("/>\n");
		
		return xmlOutputStr.toString();
	}

	@Override
	public void dump()
	{
		
	}

}
