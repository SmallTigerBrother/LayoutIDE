package com.tiger.layoutide.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.utils.XmlOutputConstant;
import com.tiger.layoutide.utils.GravityValue;
import com.tiger.layoutide.widget.tree.IViewTree;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeImp;

/**
 * @author Dalang
 *
 */
public class TGRelativeLayout extends RelativeLayout implements IViewTree,IViewGroup, OnLongClickListener
{
	
	private static String LOG_TAG = TGRelativeLayout.class.getSimpleName();
	
	private IViewTree viewTree;
	
	private ViewGroupHelper viewGroupHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	private int curChildViewId = 1;
	
	public TGRelativeLayout(Context context)
	{
		this(context, null);
	}
	
	public TGRelativeLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		viewGroupHelper = new ViewGroupHelper(this);
		
		viewTree = new ViewTreeImp(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
		this.setOnDragListener(viewGroupHelper);
		
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
	public void addView(View child, int index, ViewGroup.LayoutParams params)
	{
		child.setId(createChildViewId());
		params = new TGRelativeLayout.LayoutParams(params);
		super.addView(child, index, params);
		
		addViewTreeNode((IViewTreeNode) child);
	}

	@Override
	public void addViewTreeNode(IViewTreeNode viewTreeNode)
	{
		viewTree.addViewTreeNode(viewTreeNode);
	}
	
	@Override
	public void removeAllViews()
	{
		super.removeAllViews();
		viewTree.clearViewTreeNode();
	}
	
	@Override
	public void removeView(View view)
	{
		super.removeView(view);
		viewTree.removeViewTreeNode((IViewTreeNode)view);
	}
	
	@Override
	public void removeViewAt(int index)
	{
		viewTree.removeViewTreeNode((IViewTreeNode)getChildAt(index));
		super.removeViewAt(index);
	}

	@Override
	public void removeViewTreeNode(IViewTreeNode viewTreeNode)
	{
		viewTree.removeViewTreeNode(viewTreeNode);
	}

	@Override
	public void clearViewTreeNode()
	{
		viewTree.clearViewTreeNode();
	}

	@Override
	public List<IViewTreeNode> getViewTreeNodes()
	{
		return viewTree.getViewTreeNodes();
	}

	@Override
	public String getSimpleClassName()
	{
		return WidgetSimpleName.RELATIVE_LAYOUT;
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
		TGRelativeLayout relativeLayout = new TGRelativeLayout(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				100, 100);
		relativeLayout.setLayoutParams(layoutParams);
		relativeLayout.setBackgroundColor(Color.BLUE);
		return relativeLayout;
	}
	
	private int createChildViewId()
	{
		return ++curChildViewId;
	}
	
	public int getChildId(String childIdName)
	{
		View view = findViewWithTag(childIdName);
		if(null != view)
		{
			return view.getId();
		}
		return 0;
	}
	
	public ArrayList<CharSequence> getChildIdList()
	{
		ArrayList<CharSequence> childIdList = new ArrayList<CharSequence>();
		childIdList.add(XmlOutputConstant.ANCHOR_NONE);
		View view = null;
		for(int i = 0; i < getChildCount(); i++)
		{
			view = getChildAt(i);
			if(!TextUtils.isEmpty(((IView)view).getIdName()))
			{
				childIdList.add(((IView)view).getIdName());
			}
		}
		
		return childIdList;
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
	
	public static class LayoutParams extends RelativeLayout.LayoutParams
	{
		private String leftOfAnchorId;
		
		private String rightOfAnchorId;
		
		private String belowAnchorId;
		
		private String aboveAnchorId;
		
		private String alignLeftAnchorId;
		
		private String alignRightAnchorId;
		
		private String alignTopAnchorId;
		
		private String alignBottomAnchorId;
		
		public LayoutParams(Context c, AttributeSet attrs)
		{
			super(c, attrs);
		}
		
		public LayoutParams(ViewGroup.LayoutParams source)
		{
			super(source);
		}

		public LayoutParams(int w, int h)
		{
			super(w, h);
		}
		
		public LayoutParams(MarginLayoutParams source)
		{
			super(source);
		}
		
		public void addRule(int verb, int anchor, String anchorIdName)
		{
			super.addRule(verb, anchor);
			switch (verb)
			{
				case RelativeLayout.LEFT_OF:
					leftOfAnchorId = anchorIdName;
					break;
				
				case RelativeLayout.RIGHT_OF:
					rightOfAnchorId = anchorIdName;
					break;
					
				case RelativeLayout.BELOW:
					belowAnchorId = anchorIdName;
					break;

				case RelativeLayout.ABOVE:
					aboveAnchorId = anchorIdName;
					break;

				case RelativeLayout.ALIGN_LEFT:
					alignLeftAnchorId = anchorIdName;
					break;

				case RelativeLayout.ALIGN_RIGHT:
					alignRightAnchorId = anchorIdName;
					break;

				case RelativeLayout.ALIGN_TOP:
					alignTopAnchorId = anchorIdName;
					break;

				case RelativeLayout.ALIGN_BOTTOM:
					alignBottomAnchorId = anchorIdName;
					break;

				default:
					break;
			}
		}
		
		public String getLeftOfAnchorId()
		{
			return leftOfAnchorId;
		}
		
		public String getRightOfAnchorId()
		{
			return rightOfAnchorId;
		}

		public String getBelowAnchorId()
		{
			return belowAnchorId;
		}

		public String getAlignLeftAnchorId()
		{
			return alignLeftAnchorId;
		}

		public String getAboveAnchorId()
		{
			return aboveAnchorId;
		}

		public String getAlignTopAnchorId()
		{
			return alignTopAnchorId;
		}

		public String getAlignRightAnchorId()
		{
			return alignRightAnchorId;
		}

		public String getAlignBottomAnchorId()
		{
			return alignBottomAnchorId;
		}
	}

	@Override
	public void setGravityValue(String gravity)
	{
		int intGravity = GravityValue.getIntValue(gravity);
		this.setGravity(intGravity);
		if(intGravity != Gravity.NO_GRAVITY)
		{
			viewGroupHelper.setGravityValue(gravity);
		}
		else
		{
			viewGroupHelper.setGravityValue(null);
		}
	}

	@Override
	public void setLayoutGravityValue(String gravity)
	{
		viewGroupHelper.setLayoutGravityValue(gravity);
	}
}
