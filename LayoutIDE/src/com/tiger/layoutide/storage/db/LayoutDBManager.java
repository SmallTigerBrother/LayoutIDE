package com.tiger.layoutide.storage.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mn.tiger.datastorage.TGDBManager;
import com.mn.tiger.datastorage.db.exception.DbException;
import com.mn.tiger.datastorage.db.sqlite.WhereBuilder;
import com.mn.tiger.log.LogTools;
import com.tiger.layoutide.ide.tool.PropertiesToolBar;
import com.tiger.layoutide.storage.model.LayoutDBModel;
import com.tiger.layoutide.storage.model.ViewDBModel;
import com.tiger.layoutide.utils.WidgetSimpleName;
import com.tiger.layoutide.widget.IAdapterView;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.IViewGroup;
import com.tiger.layoutide.widget.TGButton;
import com.tiger.layoutide.widget.TGCheckBox;
import com.tiger.layoutide.widget.TGEditText;
import com.tiger.layoutide.widget.TGImageView;
import com.tiger.layoutide.widget.TGLinearLayout;
import com.tiger.layoutide.widget.TGLinearLayout.LinearLayoutHelper;
import com.tiger.layoutide.widget.TGListView;
import com.tiger.layoutide.widget.TGRelativeLayout;
import com.tiger.layoutide.widget.TGTextView;
import com.tiger.layoutide.widget.TextViewHelper;

public class LayoutDBManager
{
	public static final String LOG_TAG = LayoutDBManager.class.getSimpleName();
	
	public static final String ALL_LAYOUT = "layouts";
	
	private static TGDBManager getDBManager(Context context, String projectName)
	{
		return TGDBManager.create(context, projectName, 1, null);
	}
	
	public static List<LayoutDBModel> getAllLayout(Context context)
	{
		try
		{
			return TGDBManager.create(context, ALL_LAYOUT, 1, null).findAll(LayoutDBModel.class);
		}
		catch (DbException e)
		{
			LogTools.e(LOG_TAG, e);
		}
		return null;
	}
	
	public static List<LayoutDBModel> getAllCustomViewLayout(Context context)
	{
		try
		{
			WhereBuilder whereBuilder = WhereBuilder.b("layoutType", "=", 
					LayoutDBModel.CUSTOM_VIEW_LAYOUT);
			return TGDBManager.create(context, ALL_LAYOUT, 1, null).findAll(LayoutDBModel.class, 
					whereBuilder);
		}
		catch (DbException e)
		{
			LogTools.e(LOG_TAG, e);
		}
		return null;
	}
	
	public static void saveLayout(Context context, LayoutDBModel layoutDBModel)
	{
		try
		{
			TGDBManager.create(context, ALL_LAYOUT, 1, null).save(layoutDBModel);
		}
		catch (DbException e)
		{
			LogTools.e(LOG_TAG, e);
		}
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
			ViewDBModel viewDBModel = viewDBModels.get(0);
			IView view = parseView(context, viewDBModel);
			if(view instanceof ViewGroup)
			{
				rootViewGroup.addView(parseViewGroup(context, viewDBModels));
			}
			else 
			{
				rootViewGroup.addView((View)view);
				viewDBModels.remove(0);
			}
			
			setRelativePostions(view, viewDBModel);
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
		
		viewDBModel.setIdName(view.getViewHelper().getIdName());
		
		if(!TextUtils.isEmpty(view.getViewHelper().getLayoutWidth()))
		{
			String width = view.getViewHelper().getLayoutWidth();
			if(width.contains("dp"))
			{
				width = width.replace("dp", "");
			}
			viewDBModel.setLayoutWidth(width);
		}
		
		if(!TextUtils.isEmpty(view.getViewHelper().getLayoutHeight()))
		{
			String height = view.getViewHelper().getLayoutHeight();
			if(height.contains("dp"))
			{
				height = height.replace("dp", "");
			}
			viewDBModel.setLayoutHeight(height);
		}
		
		viewDBModel.setLeftMargin(view.getViewHelper().getLayoutMarginLeft());
		viewDBModel.setRightMargin(view.getViewHelper().getLayoutMarginRight());
		viewDBModel.setTopMargin(view.getViewHelper().getLayoutMarginTop());
		viewDBModel.setBottomMargin(view.getViewHelper().getLayoutMarginBottom());
		
		viewDBModel.setLayoutWeight(view.getViewHelper().getLayoutWeight());
		viewDBModel.setLayoutGravity(view.getViewHelper().getLayoutGravityValue());
		
		viewDBModel.setAlignParentLeft(view.getViewHelper().getAlignParentLeft());
		viewDBModel.setAlignParentRight(view.getViewHelper().getAlignParentRight());
		viewDBModel.setAlignParentTop(view.getViewHelper().getAlignParentTop());
		viewDBModel.setAlignParentBottom(view.getViewHelper().getAlignParentBottom());
		
		viewDBModel.setAbove(view.getViewHelper().getAbove());
		viewDBModel.setBelow(view.getViewHelper().getBelow());
		viewDBModel.setToLeftOf(view.getViewHelper().getToLeftOf());
		viewDBModel.setToRightOf(view.getViewHelper().getToRightOf());
		
		viewDBModel.setAlignLeft(view.getViewHelper().getAlignLeft());
		viewDBModel.setAlignRight(view.getViewHelper().getAlignRight());
		viewDBModel.setAlignTop(view.getViewHelper().getAlignTop());
		viewDBModel.setAlignBottom(view.getViewHelper().getAlignBottom());
		
		viewDBModel.setCenterInParent(view.getViewHelper().getCenterInParent());
		viewDBModel.setCenterHorizontal(view.getViewHelper().getCenterHorizontal());
		viewDBModel.setCenterVertical(view.getViewHelper().getCenterVertical());
		
		if(view instanceof TextView)
		{
			TextViewHelper viewHelper = (TextViewHelper) view.getViewHelper();
			if(TextUtils.isEmpty(viewHelper.getText()))
			{
				viewDBModel.setText("");
			}
			else
			{
				viewDBModel.setText(viewHelper.getText().toString());
			}
			
			viewDBModel.setTextSize(viewHelper.getTextSize());
			
			viewDBModel.setTextColor(viewHelper.getTextColor());
		}
		else if(view instanceof TGLinearLayout)
		{
			LinearLayoutHelper layoutHelper = (LinearLayoutHelper)view.getViewHelper();
			viewDBModel.setOrientation(layoutHelper.getOrientationValue());
			viewDBModel.setRootViewGroup(((TGLinearLayout)view).isRootViewGroup());
		}
		else if(view instanceof TGRelativeLayout)
		{
			viewDBModel.setRootViewGroup(((TGRelativeLayout)view).isRootViewGroup());
		}
		else if(view instanceof IAdapterView)
		{
			viewDBModel.setItemLayout(((IAdapterView)view).getItemLayout());
		}
		
		viewDBModel.setGravity(view.getViewHelper().getGravityValue());
		
		viewDBModel.setBackgroundColor(view.getViewHelper().getBackgroundColor());
		
		return viewDBModel;
	}
	
	private static IView parseView(Context context, ViewDBModel viewDBModel)
	{
		IView view = createNewView(context, viewDBModel);
		
		view.getViewHelper().setIdName(viewDBModel.getIdName());
		
		if(!TextUtils.isEmpty(viewDBModel.getLayoutWidth()))
		{
			String width = viewDBModel.getLayoutWidth();
			if(width.contains("dp"))
			{
				width = width.replace("dp", "");
			}
			view.getViewHelper().setLayoutWidth(width);
		}
		
		if(!TextUtils.isEmpty(viewDBModel.getLayoutHeight()))
		{
			String height = viewDBModel.getLayoutHeight();
			if(height.contains("dp"))
			{
				height = height.replace("dp", "");
			}
			view.getViewHelper().setLayoutHeight(height);
		}
		
		view.getViewHelper().setLayoutMarginLeft(viewDBModel.getLeftMargin() + "");
		view.getViewHelper().setLayoutMarginRight(viewDBModel.getRightMargin() + "");
		view.getViewHelper().setLayoutMarginTop(viewDBModel.getTopMargin() + "");
		view.getViewHelper().setLayoutMarginBottom(viewDBModel.getBottomMargin() + "");
		
		view.getViewHelper().setLayoutWeight(viewDBModel.getLayoutWeight() + "");
		view.getViewHelper().setLayoutGravityValue(viewDBModel.getLayoutGravity());
		
		if(view instanceof TextView)
		{
			TextViewHelper viewHelper = (TextViewHelper) view.getViewHelper();
			viewHelper.setText(viewDBModel.getText());
			viewHelper.setTextSize(viewDBModel.getTextSize() + "");
			viewHelper.setTextColor(viewDBModel.getTextColor());
		}
		else if(view instanceof TGLinearLayout)
		{
			LinearLayoutHelper layoutHelper = (LinearLayoutHelper)view.getViewHelper();
			layoutHelper.setOrientationValue(viewDBModel.getOrientation());
			((TGLinearLayout)view).setRootViewGroup(viewDBModel.isRootViewGroup());
		}
		else if(view instanceof TGRelativeLayout)
		{
			((TGRelativeLayout)view).setRootViewGroup(viewDBModel.isRootViewGroup());
		}
		else if(view instanceof IAdapterView)
		{
			((IAdapterView)view).setItemLayout(viewDBModel.getItemLayout());
		}
		
		view.getViewHelper().setGravityValue(viewDBModel.getGravity());
		
		view.getViewHelper().setBackgroundColor(viewDBModel.getBackgroundColor());
		
		return view;
	}
	
	private static void setRelativePostions(IView view, ViewDBModel viewDBModel)
	{
		view.getViewHelper().setAlignParentLeft(viewDBModel.getAlignParentLeft());
		view.getViewHelper().setAlignParentRight(viewDBModel.getAlignParentRight());
		view.getViewHelper().setAlignParentTop(viewDBModel.getAlignParentTop());
		view.getViewHelper().setAlignParentBottom(viewDBModel.getAlignParentBottom());
		
		view.getViewHelper().setAbove(viewDBModel.getAbove());
		view.getViewHelper().setBelow(viewDBModel.getBelow());
		view.getViewHelper().setToLeftOf(viewDBModel.getToLeftOf());
		view.getViewHelper().setToRightOf(viewDBModel.getToRightOf());
		
		view.getViewHelper().setAlignLeft(viewDBModel.getAlignLeft());
		view.getViewHelper().setAlignRight(viewDBModel.getAlignRight());
		view.getViewHelper().setAlignTop(viewDBModel.getAlignTop());
		view.getViewHelper().setAlignBottom(viewDBModel.getAlignBottom());
		
		view.getViewHelper().setCenterInParent(viewDBModel.getCenterInParent());
		view.getViewHelper().setCenterHorizontal(viewDBModel.getCenterHorizontal());
		view.getViewHelper().setCenterVertical(viewDBModel.getCenterVertical());
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
			TGRelativeLayout.LayoutParams layoutParams = new TGRelativeLayout.LayoutParams
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
		
		view.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				PropertiesToolBar.getSingleInstance().setSelectedView((IView) v);
			}
		});
		
		return (IView) view;
	}
}
