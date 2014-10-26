package com.tiger.layoutide.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.ide.Emulator;
import com.tiger.layoutide.ide.ViewHelper;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 *
 */
public class TGButton extends Button implements IViewTreeNode, IView, OnLongClickListener
{
	private static String LOG_TAG = TGCheckBox.class.getSimpleName();
	
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	public TGButton(Context context)
	{
		this(context, null);
	}
	
	public TGButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewHelper = new ViewHelper(this);
		
		viewTreeNode = new ViewTreeNodeImp(viewHelper);
		
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
	public void setText(CharSequence text, BufferType type)
	{
		super.setText(text, type);
		if(!TextUtils.isEmpty(text) && null != viewHelper)
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
				viewHelper.setTextColor(color);
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
	
	@Override
	public void setTextSize(String textSize)
	{
		try
		{
			super.setTextSize(Float.valueOf(textSize));
			viewHelper.setTextSize(textSize);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, "The textSize can not be parsed from value " + textSize);
		}
	}
	
	@Override
	public String getTextColor()
	{
		return viewHelper.getTextColor();
	}
	
	@Override
	public String getClassSimpleName()
	{
		return "Button";
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
	public void onSelected()
	{
		this.isSelected = true;
		//执行重绘
		this.invalidate();
	}
	
	@Override
	public void onUnSelected()
	{
		//执行重绘
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
		TGButton button = new TGButton(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		button.setLayoutParams(layoutParams);
		button.setText("Button");
		return button;
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
	
}
