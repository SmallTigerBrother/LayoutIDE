package com.tiger.layoutide.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 */
public class JTGCheckBox extends CheckBox implements IViewTreeNode, IView, OnLongClickListener
{
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	public JTGCheckBox(Context context)
	{
		this(context, null);
	}
	
	public JTGCheckBox(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewHelper = new TextViewHelper(this);
		
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
	
	public ViewHelper getViewHelper()
	{
		return viewHelper;
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
		JTGCheckBox checkBox = new JTGCheckBox(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		checkBox.setLayoutParams(layoutParams);
		checkBox.setText("CheckBox");
		return checkBox;
	}
}
