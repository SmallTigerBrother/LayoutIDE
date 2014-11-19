package com.tiger.layoutide.widget.tree;

import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiger.layoutide.utils.XmlOutputConstant;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGLinearLayout;
import com.tiger.layoutide.widget.TGLinearLayout.LinearLayoutHelper;
import com.tiger.layoutide.widget.TextViewHelper;

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
		StringBuilder xmlOutputStr = new StringBuilder("<" + view.getSimpleClassName() + "\n");
		
		xmlOutputStr.append(getPropertiesString());
		
		xmlOutputStr.append("/>\n");
		
		return xmlOutputStr.toString();
	}
	
	protected String getPropertiesString()
	{
		StringBuilder propertiesStrBuilder = new StringBuilder();
		
		if(!TextUtils.isEmpty(view.getViewHelper().getIdName()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ID, 
					view.getViewHelper().getIdName()) + "\n");
		}
		
		propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_WIDTH, 
				view.getViewHelper().getLayoutWidth()) + "\n");
		propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_HEIGHT, 
				view.getViewHelper().getLayoutHeight()) + "\n");
		
		/********************************* Common Position *************************************/
		
		if(view.getViewHelper().getLayoutMarginLeft() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_LEFT, 
					view.getViewHelper().getLayoutMarginLeft()) + "\n");
		}
		
		if(view.getViewHelper().getLayoutMarginRight() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_RIGHT, 
					view.getViewHelper().getLayoutMarginRight()) + "\n");
		}
		
		if(view.getViewHelper().getLayoutMarginTop() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_TOP, 
					view.getViewHelper().getLayoutMarginTop()) + "\n");
		}
		
		if(view.getViewHelper().getLayoutMarginBottom() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_BOTTOM, 
					view.getViewHelper().getLayoutMarginBottom()) + "\n");
		}
		
		/********************************* LinearLayout *************************************/
		
		if(view.getViewHelper().getLayoutWeight() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_WEIGHT, 
					view.getViewHelper().getLayoutWeight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getLayoutGravityValue()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_GRAVITY, 
					view.getViewHelper().getLayoutGravityValue()) + "\n");
		}
		
		/********************************* Content *************************************/
		
		if(!TextUtils.isEmpty(view.getViewHelper().getGravityValue()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.GRAVITY, 
					view.getViewHelper().getGravityValue()) + "\n");
		}
		
		/********************************* BackGround *************************************/
		
		if(!TextUtils.isEmpty(view.getViewHelper().getBackgroundColor()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.BACKGROUND_COLOR, 
					view.getViewHelper().getBackgroundColor()) + "\n");
		}
		
		/********************************* RelativeLayout *************************************/
		
		propertiesStrBuilder = appendRelativePositionProperties(propertiesStrBuilder);
		
		propertiesStrBuilder = appendLinearLayoutProperties(propertiesStrBuilder);
		
		propertiesStrBuilder = appendTextViewProperties(propertiesStrBuilder);
		
		return propertiesStrBuilder.toString();
	}
	
	private StringBuilder appendLinearLayoutProperties(StringBuilder propertiesStrBuilder)
	{
		if(view instanceof LinearLayout)
		{
			LinearLayoutHelper layoutHelper = (LinearLayoutHelper) ((TGLinearLayout)view).getViewHelper();
			if(!TextUtils.isEmpty(layoutHelper.getOrientationValue()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_ORIENTATION,
						layoutHelper.getOrientationValue()) + "\n");
			}
		}
		
		return propertiesStrBuilder;
	}

	private StringBuilder appendTextViewProperties(StringBuilder propertiesStrBuilder)
	{
		if(view instanceof TextView)
		{
			if(!TextUtils.isEmpty(((TextView)view).getText()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT, 
						((TextView)view).getText()) + "\n");
			}
			
			TextViewHelper viewHelper = (TextViewHelper) view.getViewHelper();
			
			if(viewHelper.getTextSize() > 0)
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_SIZE,
						viewHelper.getTextSize()) + "\n");
			}
			
			if(!TextUtils.isEmpty(viewHelper.getTextColor()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_COLOR, 
						viewHelper.getTextColor()) + "\n");
			}
		}
		return propertiesStrBuilder;
	}
	
	private StringBuilder appendRelativePositionProperties(StringBuilder propertiesStrBuilder)
	{
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignParentLeft()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_LEFT, 
					view.getViewHelper().getAlignParentLeft()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignParentRight()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_RIGHT, 
					view.getViewHelper().getAlignParentRight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignParentTop()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_TOP, 
					view.getViewHelper().getAlignParentTop()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignParentBottom()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_BOTTOM, 
					view.getViewHelper().getAlignParentBottom()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getToLeftOf()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.TO_LEFT_OF, 
					view.getViewHelper().getToLeftOf()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getToRightOf()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.TO_RIGHT_OF,
					view.getViewHelper().getToRightOf()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getBelow()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.BELOW, 
					view.getViewHelper().getBelow()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAbove()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ABOVE, 
					view.getViewHelper().getAbove()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignLeft()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_LEFT, 
					view.getViewHelper().getAlignLeft()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignRight()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_RIGHT,
					view.getViewHelper().getAlignRight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignTop()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_TOP, 
					view.getViewHelper().getAlignTop()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getAlignBottom()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_BOTTOM, 
					view.getViewHelper().getAlignBottom()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getCenterInParent()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.CENTER_IN_PARENT, 
					view.getViewHelper().getCenterInParent()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getCenterHorizontal()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.CENTER_HORIZONTAL, 
					view.getViewHelper().getCenterHorizontal()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getCenterVertical()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.CENTER_VERTICAL, 
					view.getViewHelper().getCenterVertical()) + "\n");
		}
		
		return propertiesStrBuilder;
	}
	
	@Override
	public void dump()
	{
		
	}

}
