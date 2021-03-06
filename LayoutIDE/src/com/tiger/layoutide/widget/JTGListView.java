package com.tiger.layoutide.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;

import com.mn.tiger.widget.adpter.TGListAdapter;
import com.tiger.layoutide.storage.db.LayoutDBManager;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.widget.tree.IViewTreeNode;
import com.tiger.layoutide.widget.tree.ViewTreeNodeImp;

/**
 * @author Dalang
 */
public class JTGListView extends ScrollView implements IAdapterView, IViewTreeNode
{
	private IViewTreeNode viewTree;
	
	private ViewHelper viewHelper;
	
	private boolean isSelected = false;
	
	private Paint paint = null;
	
	private LinearLayout mainLayout;
	
	private String listItemLayout = "";
	
	public JTGListView(Context context)
	{
		this(context, null);
	}
	
	public JTGListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		viewHelper = new AdapterViewHelper(this);
		
		viewTree = new ViewTreeNodeImp(this);
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(10);
		
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


	private void setAdapter(ListAdapter adapter)
	{
		//�����������ͼ
		mainLayout.removeAllViews();
		
		//�������ͼ
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
		
		public DefaultAdapter(Activity activity, String listItemLayoutName)
		{
			super(activity, null, - 1, null);
			this.listItemLayoutName = listItemLayoutName;
		}
		
		@Override
		public int getCount()
		{
			return 10;
		}
		
		public View getView(int position, View convertView, ViewGroup parent)
		{
			return (View) LayoutDBManager.getLayout(getActivity(), listItemLayoutName);
		};
	}

	public void setListItemLayout(String layoutName)
	{
		DefaultAdapter adapter = new DefaultAdapter((Activity)getContext(), layoutName);
		this.setAdapter(adapter);

		this.listItemLayout = layoutName;
	}

	public String getListItemLayout()
	{
		return listItemLayout;
	}

	@Override
	public ViewHelper getViewHelper()
	{
		return viewHelper;
	}

	@Override
	public String getItemLayout()
	{
		return null;
	}

	@Override
	public void setItemLayout(String itemLayout)
	{
		
	}
}
