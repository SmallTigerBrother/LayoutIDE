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
		
		/********************************* Common Position *************************************/
		
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
		
		/********************************* LinearLayout *************************************/
		
		if(view.getLayoutWeight() > 0)
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_WEIGHT, view.getLayoutWeight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getGravityValue()))
		{
			propertiesStrBuilder.append(String.format(Constant.GRAVITY, view.getGravityValue()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getLayoutGravityValue()))
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_GRAVITY, view.getLayoutGravityValue()) + "\n");
		}
		
		/********************************* Content *************************************/
		
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
		
		/********************************* BackGround *************************************/
		
		if(!TextUtils.isEmpty(view.getBackgroundColor()))
		{
			propertiesStrBuilder.append(String.format(Constant.BACKGROUND_COLOR, view.getBackgroundColor()) + "\n");
		}
		
		/********************************* RelativeLayout *************************************/
		
		if(!TextUtils.isEmpty(view.getAlignParentLeft()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_PARENT_LEFT, view.getAlignParentLeft()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignParentRight()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_PARENT_RIGHT, view.getAlignParentRight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignParentTop()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_PARENT_TOP, view.getAlignParentTop()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignParentBottom()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_PARENT_BOTTOM, view.getAlignParentBottom()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getToLeftOf()))
		{
			propertiesStrBuilder.append(String.format(Constant.TO_LEFT_OF, view.getToLeftOf()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getToRightOf()))
		{
			propertiesStrBuilder.append(String.format(Constant.TO_RIGHT_OF, view.getToRightOf()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getBelow()))
		{
			propertiesStrBuilder.append(String.format(Constant.BELOW, view.getBelow()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAbove()))
		{
			propertiesStrBuilder.append(String.format(Constant.ABOVE, view.getAbove()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignLeft()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_LEFT, view.getAlignLeft()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignRight()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_RIGHT, view.getAlignRight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignTop()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_TOP, view.getAlignTop()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignBottom()))
		{
			propertiesStrBuilder.append(String.format(Constant.ALIGN_BOTTOM, view.getAlignBottom()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getCenterInParent()))
		{
			propertiesStrBuilder.append(String.format(Constant.CENTER_IN_PARENT, view.getCenterInParent()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getCenterHorizontal()))
		{
			propertiesStrBuilder.append(String.format(Constant.CENTER_HORIZONTAL, view.getCenterHorizontal()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getCenterVertical()))
		{
			propertiesStrBuilder.append(String.format(Constant.CENTER_VERTICAL, view.getCenterVertical()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getOrientationValue()))
		{
			propertiesStrBuilder.append(String.format(Constant.LAYOUT_ORIENTATION, view.getOrientationValue()) + "\n");
		}
		
		return propertiesStrBuilder.toString();
	}

	@Override
	public void dump()
	{
		
	}

}
