package com.tiger.layoutide.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mn.tiger.log.LogTools;
import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 *
 */
public class JTGEditText extends EditText implements IViewTreeNode, IView, OnLongClickListener
{
	private static String LOG_TAG = JTGCheckBox.class.getSimpleName();
	
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	public JTGEditText(Context context)
	{
		this(context, null);
	}

	public JTGEditText(Context context, AttributeSet attrs)
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
		return WidgetSimpleName.EDIT_TEXT;
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
		JTGEditText editText = new JTGEditText(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		editText.setLayoutParams(layoutParams);
		editText.setText("EditText");
		return editText;
	}
	
	@Override
	public boolean onDragEvent(DragEvent event)
	{
		try
		{
			return super.onDragEvent(event);
		}
		catch (Exception e)
		{
			LogTools.e(LOG_TAG, e);
			return false;
		}
	}
}
