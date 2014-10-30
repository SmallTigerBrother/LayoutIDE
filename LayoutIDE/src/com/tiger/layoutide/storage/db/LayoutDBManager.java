package com.tiger.layoutide.storage.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.mn.tiger.datastorage.TGDBManager;
import com.mn.tiger.datastorage.db.exception.DbException;
import com.mn.tiger.utility.LogTools;
import com.tiger.layoutide.storage.model.ViewDBModel;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.IViewGroup;

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
	
	public static IViewGroup getLayout(Context context,String projectName)
	{
		return null;
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
		viewDBModel.setOrientation(view.getOrientationValue());
		
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
		
		viewDBModel.setText(view.getText().toString());
		viewDBModel.setTextSize(view.getTextSize());
		viewDBModel.setTextColor(view.getTextColor());
		viewDBModel.setGravity(view.getGravityValue());
		
		viewDBModel.setBackgroundColor(view.getBackgroundColor());
		
		return viewDBModel;
	}
	
	private static IView parseView(ViewDBModel viewDBModel)
	{
		return null;
	}
}
