package com.tiger.layoutide.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.utils.GravityValue;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 *
 */
public class TGCheckBox extends CheckBox implements IViewTreeNode, IView, OnLongClickListener
{
	
	private static String LOG_TAG = TGCheckBox.class.getSimpleName();
	
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	private String textColor = "";
	
	public TGCheckBox(Context context)
	{
		this(context, null);
	}
	
	public TGCheckBox(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewHelper = new ViewHelper(this);
		
		viewTreeNode = new ViewTreeNodeImp(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
		this.setOnLongClickListener(this);
	}

	@Override
	public String getXMLString()
	{
		return viewTreeNode.getXMLString();
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
	
	public String getTextColor()
	{
		return textColor;
	}
	
	public void setTextColor(String color)
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
				super.setTextColor(rgbColorInt);
				this.textColor = color;
			}
			else
			{
				LogTools.w(LOG_TAG, "The textColor can not be parsed from value " + color);
			}
		}
		catch (Exception e)
		{
			LogTools.w(LOG_TAG, "The textColor can not be parsed from value " + color);
		}
	}
	
	public void setTextSize(String textSize)
	{
		try
		{
			super.setTextSize(Float.valueOf(textSize));
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The textSize can not be parsed from value " + textSize);
		}
	}
	
	@Override
	public String getSimpleClassName()
	{
		return WidgetSimpleName.CHECK_BOX;
	}

	@Override
	public String getPackageName()
	{
		return "android.widget";
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
	public String getGravityValue()
	{
		return viewHelper.getGravityValue();
	}

	@Override
	public String getLayoutGravityValue()
	{
		return viewHelper.getLayoutGravityValue();
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
	public void onSelected()
	{
		this.isSelected = true;
		//ִ���ػ�
		this.invalidate();
	}
	
	@Override
	public void onUnSelected()
	{
		//ִ���ػ�
		this.isSelected = false;
		this.invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isSelected)
		{
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			super.onDraw(canvas);
		}
		else
		{
			super.onDraw(canvas);
		}
	}
	
	@Override
	public boolean onLongClick(View v) 
	{
		this.startDrag(null, new DragShadowBuilder(v) , null, 0);
		Emulator.getSingleInstance().setCurDragView(this);
		return true;
	}
	
	@Override
	public View newInstance()
	{
		TGCheckBox checkBox = new TGCheckBox(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		checkBox.setLayoutParams(layoutParams);
		checkBox.setText("CheckBox");
		return checkBox;
	}
	
	@Override
	public void setAlignParentLeft(String value)
	{
		viewHelper.setAlignParentLeft(value);
	}

	@Override
	public String getAlignParentLeft()
	{
		return viewHelper.getAlignParentLeft();
	}

	@Override
	public void setAlignParentRight(String value)
	{
		viewHelper.setAlignParentRight(value);
	}

	@Override
	public String getAlignParentRight()
	{
		return viewHelper.getAlignParentRight();
	}

	@Override
	public void setAlignParentTop(String value)
	{
		viewHelper.setAlignParentTop(value);
	}

	@Override
	public String getAlignParentTop()
	{
		return viewHelper.getAlignParentTop();
	}

	@Override
	public void setAlignParentBottom(String value)
	{
		viewHelper.setAlignParentBottom(value);
	}

	@Override
	public String getAlignParentBottom()
	{
		return viewHelper.getAlignParentBottom();
	}
	
	@Override
	public void setToLeftOf(String anchorIdName)
	{
		viewHelper.setToLeftOf(anchorIdName);
	}
	
	@Override
	public String getToLeftOf()
	{
		return viewHelper.getToLeftOf();
	}
	
	@Override
	public void setToRightOf(String anchorIdName)
	{
		viewHelper.setToRightOf(anchorIdName);
	}
	
	@Override
	public String getToRightOf()
	{
		return viewHelper.getToRightOf();
	}

	@Override
	public void setBelow(String anchorIdName)
	{
		viewHelper.setBelow(anchorIdName);
	}

	@Override
	public String getBelow()
	{
		return viewHelper.getBelow();
	}

	@Override
	public void setAbove(String anchorIdName)
	{
		viewHelper.setAbove(anchorIdName);
	}

	@Override
	public String getAbove()
	{
		return viewHelper.getAbove();
	}

	@Override
	public void setAlignLeft(String anchorIdName)
	{
		viewHelper.setAlignLeft(anchorIdName);
	}

	@Override
	public String getAlignLeft()
	{
		return viewHelper.getAlignLeft();
	}

	@Override
	public void setAlignRight(String anchorIdName)
	{
		viewHelper.setAlignRight(anchorIdName);
	}

	@Override
	public String getAlignRight()
	{
		return viewHelper.getAlignRight();
	}

	@Override
	public void setAlignTop(String anchorIdName)
	{
		viewHelper.setAlignTop(anchorIdName);
	}

	@Override
	public String getAlignTop()
	{
		return viewHelper.getAlignTop();
	}

	@Override
	public void setAlignBottom(String anchorIdName)
	{
		viewHelper.setAlignBottom(anchorIdName);
	}

	@Override
	public String getAlignBottom()
	{
		return viewHelper.getAlignBottom();
	}
	
	@Override
	public void setCenterInParent(String value)
	{
		viewHelper.setCenterInParent(value);
	}

	@Override
	public String getCenterInParent()
	{
		return viewHelper.getCenterInParent();
	}

	@Override
	public void setCenterVertical(String value)
	{
		viewHelper.setCenterVertical(value);
	}

	@Override
	public String getCenterVertical()
	{
		return viewHelper.getCenterVertical();
	}

	@Override
	public void setCenterHorizontal(String value)
	{
		viewHelper.setCenterHorizontal(value);
	}

	@Override
	public String getCenterHorizontal()
	{
		return viewHelper.getCenterHorizontal();
	}

	@Override
	public void setGravityValue(String gravity)
	{
		int intGravity = GravityValue.getIntValue(gravity);
		this.setGravity(intGravity);
		if(intGravity != Gravity.NO_GRAVITY)
		{
			viewHelper.setGravityValue(gravity);
		}
		else
		{
			viewHelper.setGravityValue(null);
		}
	}

	@Override
	public void setLayoutGravityValue(String gravity)
	{
		viewHelper.setLayoutGravityValue(gravity);
	}
}
