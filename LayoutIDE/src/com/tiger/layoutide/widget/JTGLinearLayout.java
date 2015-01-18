package com.tiger.layoutide.widget;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.utils.XmlOutputConstant;
import com.tiger.layoutide.widget.tree.IViewTree;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeImp;

/**
 * @author Dalang
 *
 */
public class JTGLinearLayout extends LinearLayout implements IViewGroup, IViewTree
{
	private IViewTree viewTree;
	
	private ViewGroupHelper viewGroupHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	public JTGLinearLayout(Context context)
	{
		this(context, null);
	}
	
	public JTGLinearLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		this.setOrientation(HORIZONTAL);
		
		viewGroupHelper = new LinearLayoutHelper(this);
		
		viewTree = new ViewTreeImp(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
		this.setOnDragListener(viewGroupHelper);
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
		return WidgetSimpleName.LINEAR_LAYOUT;
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
	public View newInstance()
	{
		JTGLinearLayout linearLayout = new JTGLinearLayout(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				100, 100);
		linearLayout.setLayoutParams(layoutParams);
		linearLayout.setBackgroundColor(Color.GREEN);
		return linearLayout;
	}

	public static class LinearLayoutHelper extends ViewGroupHelper
	{
		private String orientation = "";
		
		public LinearLayoutHelper(ViewGroup viewGroup)
		{
			super(viewGroup);
		}
		
		public void setOrientationValue(String orientation)
		{
			this.orientation = orientation;
			if(orientation.equals(XmlOutputConstant.ORIENTATION_HORIZONTAL))
			{
				((LinearLayout)getView()).setOrientation(LinearLayout.HORIZONTAL);
			}
			else
			{
				((LinearLayout)getView()).setOrientation(LinearLayout.VERTICAL);
			}
		}
		
		public String getOrientationValue()
		{
			if(!TextUtils.isEmpty(orientation))
			{
				return orientation;
			}
			
			int orientation = ((LinearLayout)getView()).getOrientation();
			if(orientation == LinearLayout.HORIZONTAL)
			{
				this.orientation = XmlOutputConstant.ORIENTATION_HORIZONTAL;
			}
			else
			{
				this.orientation = XmlOutputConstant.ORIENTATION_VERTICAL;
			}
			
			return this.orientation;
		}
	}
}
