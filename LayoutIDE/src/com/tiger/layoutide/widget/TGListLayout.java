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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;

import com.mn.tiger.utility.LogTools;
import com.mn.tiger.widget.adpter.TGListAdapter;
import com.tiger.layoutide.ide.Emulator;
import com.tiger.layoutide.ide.ViewGroupHelper;
import com.tiger.layoutide.ide.ViewHelper;
import com.tiger.layoutide.storage.db.LayoutDBManager;
import com.tiger.layoutide.tree.IViewTree;
import com.tiger.layoutide.tree.IViewTreeNode;
import com.tiger.layoutide.tree.ViewTreeImp;
import com.tiger.layoutide.utils.WidgetSimpleName;

/**
 * ListView实现，使用ScrollView伪装成列表进行展示，代码输出时，输出成ListView
 * @author Dalang
 */
public class TGListLayout extends ScrollView implements IViewGroup, IViewTree, OnLongClickListener
{
	private static String LOG_TAG = TGListLayout.class.getSimpleName();
	
	private IViewTree viewTree;
	
	private ViewGroupHelper viewGroupHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	private LinearLayout mainLayout;
	
	private String listItemLayout = "";
	
	public TGListLayout(Context context)
	{
		this(context, null);
	}
	
	public TGListLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewGroupHelper = new ViewGroupHelper(this);
		
		viewTree = new ViewTreeImp(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
		this.setOnLongClickListener(this);
		
		initView();
	}
	
	private void initView()
	{
		mainLayout = new LinearLayout(getContext());
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		mainLayout.setLayoutParams(layoutParams);
		this.addView(mainLayout);
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
		return WidgetSimpleName.LISTVIEW;
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
		TGLinearLayout linearLayout = new TGLinearLayout(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				100, 100);
		linearLayout.setLayoutParams(layoutParams);
		linearLayout.setBackgroundColor(Color.GREEN);
		return linearLayout;
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
	public void setGravityValue(String gravity)
	{
		
	}

	@Override
	public void setLayoutGravityValue(String gravity)
	{
		viewGroupHelper.setLayoutGravityValue(gravity);
	}
	
	private void setAdapter(ListAdapter adapter)
	{
		//清空所有子视图
		mainLayout.removeAllViews();
		
		//添加新视图
		int count = adapter.getCount();
		View view = null;
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);
		for(int i = 0; i < count; i++)
		{
			view = adapter.getView(i, null, null);
			view.setLayoutParams(layoutParams);
			mainLayout.addView(view);
		}
	}
	
	public static class DefaultAdapter extends TGListAdapter<Void>
	{
		private String listItemLayoutName = "";
		
		public DefaultAdapter(Context context, String listItemLayoutName)
		{
			super(context, null);
			this.listItemLayoutName = listItemLayoutName;
		}
		
		@Override
		public int getCount()
		{
			return 10;
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			//TODO 自动装配View
			return (View) LayoutDBManager.getLayout(getContext(), listItemLayoutName);
		};
	}

	public void setListItemLayout(String layoutName)
	{
		// 替换当前的Adapter
		DefaultAdapter adapter = new DefaultAdapter(getContext(), layoutName);
		this.setAdapter(adapter);

		this.listItemLayout = layoutName;
	}

	public String getListItemLayout()
	{
		return listItemLayout;
	}
}
