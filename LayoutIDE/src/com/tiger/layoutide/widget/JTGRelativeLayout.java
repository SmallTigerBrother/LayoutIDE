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
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.utils.XmlOutputConstant;
import com.tiger.layoutide.widget.tree.IViewTree;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeImp;

/**
 * @author Dalang
 *
 */
public class JTGRelativeLayout extends RelativeLayout implements IViewTree,IViewGroup, OnLongClickListener
{
	private IViewTree viewTree;
	
	private ViewGroupHelper viewGroupHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	private int curChildViewId = 1;
	
	public JTGRelativeLayout(Context context)
	{
		this(context, null);
	}
	
	public JTGRelativeLayout(Context context, AttributeSet attrs)
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
	
	@Override
	public ViewHelper getViewHelper()
	{
		return viewGroupHelper;
	}
	
	@Override
	public void addView(View child, int index, ViewGroup.LayoutParams params)
	{
		child.setId(createChildViewId());
		if(!(params instanceof JTGRelativeLayout.LayoutParams))
		{
			if(params instanceof MarginLayoutParams)
			{
				params = new JTGRelativeLayout.LayoutParams((MarginLayoutParams)params);
			}
			else
			{
				params = new JTGRelativeLayout.LayoutParams(params);
			}
		}
		
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
		JTGRelativeLayout relativeLayout = new JTGRelativeLayout(getContext());
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
			if(!TextUtils.isEmpty(((IView)view).getViewHelper().getIdName()))
			{
				childIdList.add(((IView)view).getViewHelper().getIdName());
			}
		}
		
		return childIdList;
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
}
