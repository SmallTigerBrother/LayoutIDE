package com.tiger.layoutide.tree;

import android.text.TextUtils;

import com.tiger.layoutide.utils.Constant;
import com.tiger.layoutide.widget.IView;

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
		
		xmlOutputStr.append(getPropertiesString());
		
		xmlOutputStr.append("/>\n");
		
		return xmlOutputStr.toString();
	}
	
	protected String getPropertiesString()
	{
		StringBuilder propertiesStrBuilder = new StringBuilder();
		
		if(!TextUtils.isEmpty(view.getIdName()))
		{
			propertiesStrBuilder.append(String.format(Constant.ID, view.getIdName()) + "\n");
		}
		
		propertiesStrBuilder.append(String.format(Constant.LAYOUT_WIDTH, view.getLayoutWidth()) + "\n");
		propertiesStrBuilder.append(String.format(Constant.LAYOUT_HEIGHT, view.getLayoutHeight()) + "\n");
		
		if(view.getLayoutWeight() > 0)
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_WEIGHT, view.getLayoutWeight()) + "\n");
		}
		
		if(view.getLayoutMarginLeft() > 0)
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_MARGIN_LEFT, view.getLayoutMarginLeft()) + "\n");
		}
		
		if(view.getLayoutMarginRight() > 0)
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_MARGIN_RIGHT, view.getLayoutMarginRight()) + "\n");
		}
		
		if(view.getLayoutMarginTop() > 0)
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_MARGIN_TOP, view.getLayoutMarginTop()) + "\n");
		}
		
		if(view.getLayoutMarginBottom() > 0)
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_MARGIN_BOTTOM, view.getLayoutMarginBottom()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getText()))
		{
			propertiesStrBuilder.append(String.format(Constant.TEXT, view.getText()) + "\n");
		}
		
		if(view.getTextSize() > 0)
		{
			propertiesStrBuilder.append(String.format(Constant.TEXT_SIZE, view.getTextSize()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getTextColor()))
		{
			propertiesStrBuilder.append(String.format(Constant.TEXT_COLOR, view.getTextColor()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getBackgroundColor()))
		{
			propertiesStrBuilder.append(String.format(Constant.BACKGROUND_COLOR, view.getBackgroundColor()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getGravityStringValue()))
		{
			propertiesStrBuilder.append(String.format(Constant.GRAVITY, view.getGravityStringValue()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getLayoutGravityStringValue()))
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_GRAVITY, view.getLayoutGravityStringValue()) + "\n");
		}
		
		return propertiesStrBuilder.toString();
	}

	@Override
	public void dump()
	{
		
	}

}
