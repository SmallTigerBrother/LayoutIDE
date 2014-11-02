package com.tiger.layoutide.storage.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mn.tiger.datastorage.TGDBManager;
import com.mn.tiger.datastorage.db.exception.DbException;
import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.storage.model.ViewDBModel;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.widget.ITextView;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.IViewGroup;
import com.tiger.layoutide.widget.TGButton;
import com.tiger.layoutide.widget.TGCheckBox;
import com.tiger.layoutide.widget.TGEditText;
import com.tiger.layoutide.widget.TGImageView;
import com.tiger.layoutide.widget.TGLinearLayout;
import com.tiger.layoutide.widget.TGListView;
import com.tiger.layoutide.widget.TGRelativeLayout;
import com.tiger.layoutide.widget.TGTextView;

public class LayoutDBManager
{
	public static final String LOG_TAG = LayoutDBManager.class.getSimpleName();
	
	private static TGDBManager getDBManager(Context context, String projectName)
	{
		return TGDBManager.create(context, projectName, 1, null);
	}
	
	public static void saveLayout(Context context,String projectName,IViewGroup viewGroup)
	{
		try
		{
			getDBManager(context, projectName).saveAll(getViewDBModels(viewGroup));
		}
		catch (DbException e)
		{
			LogTools.e(LOG_TAG, e);
		}
	}
	
	public static void removeLayout(Context context,String projectName)
	{
		try
		{
			getDBManager(context, projectName).deleteAll(ViewDBModel.class);;
		}
		catch (DbException e)
		{
			LogTools.e(LOG_TAG, e);
		}
	}
	
	public static IView getLayout(Context context,String projectName)
	{
		List<ViewDBModel> viewDBModels = getViewDBModels(context, projectName);
		
		if(null != viewDBModels)
		{
			if(viewDBModels.size() == 1)
			{
				return parseView(context, viewDBModels.get(0));
			}
			else if(viewDBModels.size() > 1)
			{
				return (IView) parseViewGroup(context, viewDBModels);
			}
		}
		
		return null;
	}
	
	private static ViewGroup parseViewGroup(Context context, List<ViewDBModel> viewDBModels)
	{
		ViewGroup rootViewGroup = (ViewGroup) parseView(context, viewDBModels.get(0));
		viewDBModels.remove(0);
		while (viewDBModels.size() > 0)
		{
			IView view = parseView(context, viewDBModels.get(0));
			if(view instanceof ViewGroup)
			{
				rootViewGroup.addView(parseViewGroup(context, viewDBModels));
			}
			else 
			{
				rootViewGroup.addView((View)view);
				viewDBModels.remove(0);
			}
		}
		
		return rootViewGroup;
	}
	
	private static List<ViewDBModel> getViewDBModels(Context context,String projectName)
	{
		try
		{
			return getDBManager(context, projectName).findAll(ViewDBModel.class);
		}
		catch (DbException e)
		{
			LogTools.e(LOG_TAG, e);
		}
		return null;
	}
	
	private static List<ViewDBModel> getViewDBModels(IView view)
	{
		ArrayList<ViewDBModel> viewDBModels = new ArrayList<ViewDBModel>();
		
		viewDBModels.add(getViewDBModel(view));
		
		if(view instanceof ViewGroup)
		{
			for(int i = 0; i < (((ViewGroup)view).getChildCount()); i++)
			{
				viewDBModels.addAll(getViewDBModels((IView)((ViewGroup)view).getChildAt(i)));
			}
		}
		
		return viewDBModels;
	}
	
	private static ViewDBModel getViewDBModel(IView view)
	{
		ViewDBModel viewDBModel = new ViewDBModel();
		viewDBModel.setSimpleClassName(view.getSimpleClassName());
		if(((View)view).getParent() instanceof LinearLayout)
		{
			viewDBModel.setParentViewClassName(WidgetSimpleName.LINEAR_LAYOUT);
		}
		else if(((View)view).getParent() instanceof RelativeLayout)
		{
			viewDBModel.setParentViewClassName(WidgetSimpleName.RELATIVE_LAYOUT);
		}
		else
		{
			viewDBModel.setParentViewClassName(WidgetSimpleName.VIEWGROUP);
		}
		
		viewDBModel.setIdName(view.getIdName());
		
		if(!TextUtils.isEmpty(view.getLayoutWidth()))
		{
			String width = view.getLayoutWidth();
			if(width.contains("dp"))
			{
				width = width.replace("dp", "");
			}
			viewDBModel.setLayoutWidth(width);
		}
		
		if(!TextUtils.isEmpty(view.getLayoutHeight()))
		{
			String height = view.getLayoutHeight();
			if(height.contains("dp"))
			{
				height = height.replace("dp", "");
			}
			viewDBModel.setLayoutHeight(height);
		}
		
		viewDBModel.setLeftMargin(view.getLayoutMarginLeft());
		viewDBModel.setRightMargin(view.getLayoutMarginRight());
		viewDBModel.setTopMargin(view.getLayoutMarginTop());
		viewDBModel.setBottomMargin(view.getLayoutMarginBottom());
		
		viewDBModel.setLayoutWeight(view.getLayoutWeight());
		viewDBModel.setLayoutGravity(view.getLayoutGravityValue());
		
		viewDBModel.setAlignParentLeft(view.getAlignParentLeft());
		viewDBModel.setAlignParentRight(view.getAlignParentRight());
		viewDBModel.setAlignParentTop(view.getAlignParentTop());
		viewDBModel.setAlignParentBottom(view.getAlignParentBottom());
		
		viewDBModel.setAbove(view.getAbove());
		viewDBModel.setBelow(view.getBelow());
		viewDBModel.setToLeftOf(view.getToLeftOf());
		viewDBModel.setToRightOf(view.getToRightOf());
		
		viewDBModel.setAlignLeft(view.getAlignLeft());
		viewDBModel.setAlignRight(view.getAlignRight());
		viewDBModel.setAlignTop(view.getAlignTop());
		viewDBModel.setAlignBottom(view.getAlignBottom());
		
		viewDBModel.setCenterInParent(view.getCenterInParent());
		viewDBModel.setCenterHorizontal(view.getCenterHorizontal());
		viewDBModel.setCenterVertical(view.getCenterVertical());
		
		if(view instanceof ITextView)
		{
			if(TextUtils.isEmpty(((ITextView)view).getText()))
			{
				viewDBModel.setText("");
			}
			else
			{
				viewDBModel.setText(((ITextView)view).getText().toString());
			}
			
			viewDBModel.setTextSize(((ITextView)view).getTextSize());
			
			viewDBModel.setTextColor(((ITextView)view).getTextColor());
		}
		else if(view instanceof TGLinearLayout)
		{
			viewDBModel.setOrientation(((TGLinearLayout)view).getOrientationValue());
			viewDBModel.setRootViewGroup(((TGLinearLayout)view).isRootViewGroup());
		}
		else if(view instanceof TGRelativeLayout)
		{
			viewDBModel.setRootViewGroup(((TGRelativeLayout)view).isRootViewGroup());
		}
		
		viewDBModel.setGravity(view.getGravityValue());
		
		viewDBModel.setBackgroundColor(view.getBackgroundColor());
		
		return viewDBModel;
	}
	
	private static IView parseView(Context context, ViewDBModel viewDBModel)
	{
		IView view = createNewView(context, viewDBModel);
		
		view.setIdName(viewDBModel.getIdName());
		
		if(!TextUtils.isEmpty(viewDBModel.getLayoutWidth()))
		{
			String width = viewDBModel.getLayoutWidth();
			if(width.contains("dp"))
			{
				width = width.replace("dp", "");
			}
			view.setLayoutWidth(width);
		}
		
		if(!TextUtils.isEmpty(viewDBModel.getLayoutHeight()))
		{
			String height = viewDBModel.getLayoutHeight();
			if(height.contains("dp"))
			{
				height = height.replace("dp", "");
			}
			view.setLayoutHeight(height);
		}
		
		view.setLayoutMarginLeft(viewDBModel.getLeftMargin() + "");
		view.setLayoutMarginRight(viewDBModel.getRightMargin() + "");
		view.setLayoutMarginTop(viewDBModel.getTopMargin() + "");
		view.setLayoutMarginBottom(viewDBModel.getBottomMargin() + "");
		
		view.setLayoutWeight(viewDBModel.getLayoutWeight() + "");
		view.setLayoutGravityValue(viewDBModel.getLayoutGravity());
		
		if(view instanceof LinearLayout)
		{
			((TGLinearLayout)view).setOrientationValue(viewDBModel.getOrientation());
		}
		
		view.setAlignParentLeft(viewDBModel.getAlignParentLeft());
		view.setAlignParentRight(viewDBModel.getAlignParentRight());
		view.setAlignParentTop(viewDBModel.getAlignParentTop());
		view.setAlignParentBottom(viewDBModel.getAlignParentBottom());
		
		view.setAbove(viewDBModel.getAbove());
		view.setBelow(viewDBModel.getBelow());
		view.setToLeftOf(viewDBModel.getToLeftOf());
		view.setToRightOf(viewDBModel.getToRightOf());
		
		view.setAlignLeft(viewDBModel.getAlignLeft());
		view.setAlignRight(viewDBModel.getAlignRight());
		view.setAlignTop(viewDBModel.getAlignTop());
		view.setAlignBottom(viewDBModel.getAlignBottom());
		
		view.setCenterInParent(viewDBModel.getCenterInParent());
		view.setCenterHorizontal(viewDBModel.getCenterHorizontal());
		view.setCenterVertical(viewDBModel.getCenterVertical());
		
		if(view instanceof ITextView)
		{
			((ITextView)view).setText(viewDBModel.getText());
			((ITextView)view).setTextSize(viewDBModel.getTextSize() + "");
			((ITextView)view).setTextColor(viewDBModel.getTextColor());
		}
		else if(view instanceof TGLinearLayout)
		{
			((TGLinearLayout)view).setOrientationValue(viewDBModel.getOrientation());
			((TGLinearLayout)view).setRootViewGroup(viewDBModel.isRootViewGroup());
		}
		else if(view instanceof TGRelativeLayout)
		{
			((TGRelativeLayout)view).setRootViewGroup(viewDBModel.isRootViewGroup());
		}
		
		view.setGravityValue(viewDBModel.getGravity());
		
		view.setBackgroundColor(viewDBModel.getBackgroundColor());
		
		return view;
	}
	
	private static IView createNewView(Context context, ViewDBModel viewDBModel)
	{
		View view = null;
		if(WidgetSimpleName.TEXT_VIEW.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGTextView(context);
		}
		else if(WidgetSimpleName.BUTTON.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGButton(context);
		}
		else if(WidgetSimpleName.EDIT_TEXT.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGEditText(context);
		}
		else if(WidgetSimpleName.IMAGE_VIEW.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGImageView(context);
		}
		else if(WidgetSimpleName.CHECK_BOX.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGCheckBox(context);
		}
		else if(WidgetSimpleName.LINEAR_LAYOUT.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGLinearLayout(context);
		}
		else if(WidgetSimpleName.RELATIVE_LAYOUT.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGRelativeLayout(context);
		}
		else if(WidgetSimpleName.LISTVIEW.equals(viewDBModel.getSimpleClassName()))
		{
			view = new TGListView(context);
		}
		
		if(WidgetSimpleName.LINEAR_LAYOUT.equals(viewDBModel.getParentViewClassName()))
		{
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
					(LinearLayout.LayoutParams.WRAP_CONTENT, 
							LinearLayout.LayoutParams.WRAP_CONTENT);
			view.setLayoutParams(layoutParams);
		}
		else if(WidgetSimpleName.RELATIVE_LAYOUT.equals(viewDBModel.getParentViewClassName()))
		{
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
					(RelativeLayout.LayoutParams.WRAP_CONTENT, 
							RelativeLayout.LayoutParams.WRAP_CONTENT);
			view.setLayoutParams(layoutParams);
		}
		else
		{
			ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams
					(ViewGroup.LayoutParams.WRAP_CONTENT, 
							ViewGroup.LayoutParams.WRAP_CONTENT);
			view.setLayoutParams(layoutParams);
		}
		
		return (IView) view;
	}
}
