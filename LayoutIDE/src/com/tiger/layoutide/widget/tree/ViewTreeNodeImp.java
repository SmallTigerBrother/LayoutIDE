package com.tiger.layoutide.widget.tree;

import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiger.layoutide.utils.XmlOutputConstant;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGButton;
import com.tiger.layoutide.widget.TGCheckBox;
import com.tiger.layoutide.widget.TGEditText;
import com.tiger.layoutide.widget.TGLinearLayout;
import com.tiger.layoutide.widget.TGTextView;

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
		
		if(!TextUtils.isEmpty(view.getIdName()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ID, view.getIdName()) + "\n");
		}
		
		propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_WIDTH, view.getLayoutWidth()) + "\n");
		propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_HEIGHT, view.getLayoutHeight()) + "\n");
		
		/********************************* Common Position *************************************/
		
		if(view.getLayoutMarginLeft() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_LEFT, view.getLayoutMarginLeft()) + "\n");
		}
		
		if(view.getLayoutMarginRight() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_RIGHT, view.getLayoutMarginRight()) + "\n");
		}
		
		if(view.getLayoutMarginTop() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_TOP, view.getLayoutMarginTop()) + "\n");
		}
		
		if(view.getLayoutMarginBottom() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_MARGIN_BOTTOM, view.getLayoutMarginBottom()) + "\n");
		}
		
		/********************************* LinearLayout *************************************/
		
		if(view.getLayoutWeight() > 0)
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_WEIGHT, view.getLayoutWeight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getLayoutGravityValue()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_GRAVITY, view.getLayoutGravityValue()) + "\n");
		}
		
		/********************************* Content *************************************/
		
		if(!TextUtils.isEmpty(view.getGravityValue()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.GRAVITY, view.getGravityValue()) + "\n");
		}
		
		/********************************* BackGround *************************************/
		
		if(!TextUtils.isEmpty(view.getBackgroundColor()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.BACKGROUND_COLOR, view.getBackgroundColor()) + "\n");
		}
		
		/********************************* RelativeLayout *************************************/
		
		if(!TextUtils.isEmpty(view.getAlignParentLeft()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_LEFT, view.getAlignParentLeft()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignParentRight()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_RIGHT, view.getAlignParentRight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignParentTop()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_TOP, view.getAlignParentTop()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignParentBottom()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_PARENT_BOTTOM, view.getAlignParentBottom()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getToLeftOf()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.TO_LEFT_OF, view.getToLeftOf()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getToRightOf()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.TO_RIGHT_OF, view.getToRightOf()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getBelow()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.BELOW, view.getBelow()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAbove()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ABOVE, view.getAbove()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignLeft()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_LEFT, view.getAlignLeft()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignRight()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_RIGHT, view.getAlignRight()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignTop()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_TOP, view.getAlignTop()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getAlignBottom()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.ALIGN_BOTTOM, view.getAlignBottom()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getCenterInParent()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.CENTER_IN_PARENT, view.getCenterInParent()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getCenterHorizontal()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.CENTER_HORIZONTAL, view.getCenterHorizontal()) + "\n");
		}
		
		if(!TextUtils.isEmpty(view.getCenterVertical()))
		{
			propertiesStrBuilder.append(String.format(XmlOutputConstant.CENTER_VERTICAL, view.getCenterVertical()) + "\n");
		}
		
		
		propertiesStrBuilder = appendLinearLayoutProperties(propertiesStrBuilder);
		
		propertiesStrBuilder = appendTextViewProperties(propertiesStrBuilder);
		
		return propertiesStrBuilder.toString();
	}
	
	private StringBuilder appendLinearLayoutProperties(StringBuilder propertiesStrBuilder)
	{
		if(view instanceof LinearLayout)
		{
			if(!TextUtils.isEmpty(((TGLinearLayout)view).getOrientationValue()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.LAYOUT_ORIENTATION, ((TGLinearLayout)view).getOrientationValue()) + "\n");
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
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT, ((TextView)view).getText()) + "\n");
			}
		}
		
		if(view instanceof TGTextView)
		{
			if(((TGTextView)view).getTextSize() > 0)
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_SIZE, ((TGTextView)view).getTextSize()) + "\n");
			}
			
			if(!TextUtils.isEmpty(((TGTextView)view).getTextColor()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_COLOR, ((TGTextView)view).getTextColor()) + "\n");
			}
		}
		else if(view instanceof TGButton)
		{
			if(((TGButton)view).getTextSize() > 0)
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_SIZE, ((TGButton)view).getTextSize()) + "\n");
			}
			
			if(!TextUtils.isEmpty(((TGButton)view).getTextColor()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_COLOR, ((TGButton)view).getTextColor()) + "\n");
			}
		}
		else if(view instanceof TGEditText)
		{
			if(((TGEditText)view).getTextSize() > 0)
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_SIZE, ((TGEditText)view).getTextSize()) + "\n");
			}
			
			if(!TextUtils.isEmpty(((TGEditText)view).getTextColor()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_COLOR, ((TGEditText)view).getTextColor()) + "\n");
			}
		}
		else if(view instanceof TGCheckBox)
		{
			if(((TGCheckBox)view).getTextSize() > 0)
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_SIZE, ((TGCheckBox)view).getTextSize()) + "\n");
			}
			
			if(!TextUtils.isEmpty(((TGCheckBox)view).getTextColor()))
			{
				propertiesStrBuilder.append(String.format(XmlOutputConstant.TEXT_COLOR, ((TGCheckBox)view).getTextColor()) + "\n");
			}
		}
		
		return propertiesStrBuilder;
	}
	
	@Override
	public void dump()
	{
		
	}

}
