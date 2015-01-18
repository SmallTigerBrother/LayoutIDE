package com.tiger.layoutide.widget;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import com.mn.tiger.widget.viewpager.TGPagerAdapter;
import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.storage.db.LayoutDBManager;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeNodeImp;

public class TGViewPager extends ViewPager implements IAdapterView, IViewTreeNode, OnLongClickListener
{
	private IViewTreeNode viewTreeNode;
	
	private ViewHelper viewGroupHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	private String pageItemLayout = "";
	
	private CheckForLongPress mPendingCheckForLongPress;
	
	private boolean mHasPerformedLongPress;
	 
	private PerformClick mPerformClick;
	
	public TGViewPager(Context context)
	{
		this(context, null);
	}

	public TGViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewGroupHelper = new ViewHelper(this);
		
		viewTreeNode = new ViewTreeNodeImp(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
		this.setOnLongClickListener(this);
		this.setLongClickable(true);
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
	
	@Override
	public ViewHelper getViewHelper()
	{
		return viewGroupHelper;
	}
	
	@Override
	public String getSimpleClassName()
	{
		return WidgetSimpleName.VIEWPAGER;
	}
	
	@Override
	public String getPackageName()
	{
		return "android.support.v4.view";
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
		TGViewPager viewPager = new TGViewPager(getContext());
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 
				ViewGroup.LayoutParams.MATCH_PARENT);
		
		viewPager.setLayoutParams(layoutParams);
		viewPager.setBackgroundColor(Color.YELLOW);
		return viewPager;
	}
	
	private class DefaultAdapter extends TGPagerAdapter
	{
		private String listItemLayoutName = "";
		
		public DefaultAdapter(String listItemlayoutName)
		{
			super(null);
			this.listItemLayoutName = listItemlayoutName;
			ArrayList<View> views = new ArrayList<View>(4);
			for(int i = 0; i < 4; i++)
			{
				views.add((View) LayoutDBManager.getLayout(getContext(), listItemLayoutName));
			}
			this.updatePagers(views);
		}
	}
	
	@Override
	public String getItemLayout()
	{
		return pageItemLayout;
	}
	
	@Override
	public void setItemLayout(String itemLayout)
	{
		this.pageItemLayout = itemLayout;
		if(!TextUtils.isEmpty(itemLayout) && !itemLayout.equals("NONE"))
		{
			this.setAdapter(new DefaultAdapter(pageItemLayout));
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent)
	{
		switch (motionEvent.getAction())
		{
            case MotionEvent.ACTION_UP:
				if (!mHasPerformedLongPress)
				{
					// This is a tap, so remove the longpress check
					removeLongPressCallback();

					if (mPerformClick == null)
					{
						mPerformClick = new PerformClick();
					}
					if (!post(mPerformClick))
					{
						performClick();
					}
					
					return true;
				}
				break;

            case MotionEvent.ACTION_DOWN:
                mHasPerformedLongPress = false;

                setPressed(true);
                checkForLongClick(0);
                
                return true;

            case MotionEvent.ACTION_CANCEL:
                setPressed(false);
                removeLongPressCallback();
                break;

            case MotionEvent.ACTION_MOVE:
            	removeLongPressCallback();
                setPressed(false);
                break;
        }
		 
		
		return super.onTouchEvent(motionEvent);
	}
	
	private void checkForLongClick(int delayOffset)
	{
		mHasPerformedLongPress = false;
		
		if (mPendingCheckForLongPress == null)
		{
			mPendingCheckForLongPress = new CheckForLongPress();
		}
		
		postDelayed(mPendingCheckForLongPress, ViewConfiguration.getLongPressTimeout() - delayOffset);
	}
	
	private final class PerformClick implements Runnable 
	{
        public void run()
        {
            performClick();
        }
    }
	
	private void removeLongPressCallback()
	{
        if (mPendingCheckForLongPress != null) 
        {
          removeCallbacks(mPendingCheckForLongPress);
        }
    }

	class CheckForLongPress implements Runnable
	{
		public void run()
		{
			if (isPressed() && (getParent() != null))
			{
				if(performLongClick())
				{
					mHasPerformedLongPress = true;
				}
			}
		}
	}
	
	public static class ViewPagerHelper extends ViewHelper
	{
		private boolean setOnPagerChangedListener;
		
		public ViewPagerHelper(View view)
		{
			super(view);
		}

		public boolean isSetOnPagerChangedListener()
		{
			return setOnPagerChangedListener;
		}

		public void setOnPagerChangedListener(boolean setOnPagerChangedListener)
		{
			this.setOnPagerChangedListener = setOnPagerChangedListener;
		}
	}
}
