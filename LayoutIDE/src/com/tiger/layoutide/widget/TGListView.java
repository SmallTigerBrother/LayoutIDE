package com.tiger.layoutide.widget;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ListView;

import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.ide.Emulator;
import com.tiger.layoutide.ide.ViewGroupHelper;
import com.tiger.layoutide.ide.ViewHelper;
import com.tiger.layoutide.tree.IViewTree;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeImp;

/**
 * @author Dalang
 *
 */
public class TGListView extends ListView implements IViewGroup, IViewTree, OnLongClickListener
{
	private static String LOG_TAG = TGListView.class.getSimpleName();
	
	private IViewTree viewTree;
	
	private ViewGroupHelper viewGroupHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	public TGListView(Context context)
	{
		this(context, null);
	}
	
	public TGListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewGroupHelper = new ViewGroupHelper(this);
		
		viewTree = new ViewTreeImp(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
		this.setOnLongClickListener(this);
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
	public String getSimpleClassName()
	{
		return "ListView";
	}
	
	@Override
	public String getPackageName()
	{
		return "android.widget";
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
	public void setLayoutWeight(String weight)
	{
		viewGroupHelper.setLayoutWeight(weight);
	}

	@Override
	public void setLayoutMarginLeft(String marginLeft)
	{
		viewGroupHelper.setLayoutMarginLeft(marginLeft);
	}

	@Override
	public void setLayoutMarginRight(String marginRight)
	{
		viewGroupHelper.setLayoutMarginRight(marginRight);
	}

	@Override
	public void setLayoutMarginTop(String marginTop)
	{
		viewGroupHelper.setLayoutMarginTop(marginTop);
	}

	@Override
	public void setLayoutMarginBottom(String marginBottom)
	{
		viewGroupHelper.setLayoutMarginBottom(marginBottom);
	}

	@Override
	public float getLayoutWeight()
	{
		return viewGroupHelper.getLayoutWeight();
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
	public String getBackgroundColor()
	{
		return viewGroupHelper.getBackgroundColor();
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
				viewGroupHelper.setBackgroundColor(color);
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
		return viewGroupHelper.getGravityValue();
	}

	@Override
	public String getLayoutGravityValue()
	{
		return viewGroupHelper.getLayoutGravityValue();
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
	public void setTextSize(String textSize)
	{
	}

	@Override
	public float getTextSize()
	{
		return 0;
	}

	@Override
	public void setTextColor(String textColor)
	{
	}

	@Override
	public String getTextColor()
	{
		return null;
	}

	@Override
	public boolean isRootViewGroup()
	{
		return viewGroupHelper.isRootViewGroup();
	}

	@Override
	public void setRootViewGroup(boolean isRootView)
	{
		this.viewGroupHelper.setRootViewGroup(isRootView);
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
		return null;
	}
	
	@Override
	public void setAlignParentLeft(String value)
	{
		viewGroupHelper.setAlignParentLeft(value);
	}

	@Override
	public String getAlignParentLeft()
	{
		return viewGroupHelper.getAlignParentLeft();
	}

	@Override
	public void setAlignParentRight(String value)
	{
		viewGroupHelper.setAlignParentRight(value);
	}

	@Override
	public String getAlignParentRight()
	{
		return viewGroupHelper.getAlignParentRight();
	}

	@Override
	public void setAlignParentTop(String value)
	{
		viewGroupHelper.setAlignParentTop(value);
	}

	@Override
	public String getAlignParentTop()
	{
		return viewGroupHelper.getAlignParentTop();
	}

	@Override
	public void setAlignParentBottom(String value)
	{
		viewGroupHelper.setAlignParentBottom(value);
	}

	@Override
	public String getAlignParentBottom()
	{
		return viewGroupHelper.getAlignParentBottom();
	}
	
	@Override
	public void setToLeftOf(String anchorIdName)
	{
		viewGroupHelper.setToLeftOf(anchorIdName);
	}
	
	@Override
	public String getToLeftOf()
	{
		return viewGroupHelper.getToLeftOf();
	}
	
	@Override
	public void setToRightOf(String anchorIdName)
	{
		viewGroupHelper.setToRightOf(anchorIdName);
	}
	
	@Override
	public String getToRightOf()
	{
		return viewGroupHelper.getToRightOf();
	}

	@Override
	public void setBelow(String anchorIdName)
	{
		viewGroupHelper.setBelow(anchorIdName);
	}

	@Override
	public String getBelow()
	{
		return viewGroupHelper.getBelow();
	}

	@Override
	public void setAbove(String anchorIdName)
	{
		viewGroupHelper.setAbove(anchorIdName);
	}

	@Override
	public String getAbove()
	{
		return viewGroupHelper.getAbove();
	}

	@Override
	public void setAlignLeft(String anchorIdName)
	{
		viewGroupHelper.setAlignLeft(anchorIdName);
	}

	@Override
	public String getAlignLeft()
	{
		return viewGroupHelper.getAlignLeft();
	}

	@Override
	public void setAlignRight(String anchorIdName)
	{
		viewGroupHelper.setAlignRight(anchorIdName);
	}

	@Override
	public String getAlignRight()
	{
		return viewGroupHelper.getAlignRight();
	}

	@Override
	public void setAlignTop(String anchorIdName)
	{
		viewGroupHelper.setAlignTop(anchorIdName);
	}

	@Override
	public String getAlignTop()
	{
		return viewGroupHelper.getAlignTop();
	}

	@Override
	public void setAlignBottom(String anchorIdName)
	{
		viewGroupHelper.setAlignBottom(anchorIdName);
	}

	@Override
	public String getAlignBottom()
	{
		return viewGroupHelper.getAlignBottom();
	}
	
	@Override
	public void setCenterInParent(String value)
	{
		viewGroupHelper.setCenterInParent(value);
	}

	@Override
	public String getCenterInParent()
	{
		return viewGroupHelper.getCenterInParent();
	}

	@Override
	public void setCenterVertical(String value)
	{
		viewGroupHelper.setCenterVertical(value);
	}

	@Override
	public String getCenterVertical()
	{
		return viewGroupHelper.getCenterVertical();
	}

	@Override
	public void setCenterHorizontal(String value)
	{
		viewGroupHelper.setCenterHorizontal(value);
	}

	@Override
	public String getCenterHorizontal()
	{
		return viewGroupHelper.getCenterHorizontal();
	}

	@Override
	public void setOrientationValue(String orientation)
	{
	}

	@Override
	public String getOrientationValue()
	{
		return null;
	}

	@Override
	public void setGravityValue(String gravity)
	{
		
	}

	@Override
	public void setLayoutGravityValue(String gravity)
	{
		viewGroupHelper.setLayoutGravityValue(gravity);
	}
}
