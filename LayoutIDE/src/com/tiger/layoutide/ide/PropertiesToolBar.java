package com.tiger.layoutide.ide;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGLinearLayout;
import com.tiger.layoutide.widget.TGRelativeLayout;

public class PropertiesToolBar
{
	@ViewById(id = R.id.emulator_screen)
	private Emulator emulatorLayout;
	
	@ViewById(id = R.id.curview_name)
	private TextView curViewName;
	
	@ViewById(id = R.id.id_editor)
	private EditText idEditText;
	
	/*********************************** Common Position *********************************/
	
	@ViewById(id = R.id.common_positin_properties)
	private CommonPositionToolBar commonPositionToolBar;
	/*********************************** LinearLayout Layout *********************************/
	
	@ViewById(id = R.id.linear_position_properties)
	private LinearPositionToolBar linearPositionToolBar;
	/*********************************** Background *********************************/
	
	@ViewById(id = R.id.background_properties)
	private BackgroundToolBar backgroundToolBar;
	/*********************************** Content *********************************/
	
	@ViewById(id = R.id.content_properties)
	private ContentToolBar contentToolBar;
	/*********************************** Relativie Position *********************************/
	
	@ViewById(id = R.id.relative_position_properties)
	private RelativiePositionToolBar relativiePositionToolBar;
	
	
	private IView selectedView = null;
	
	private Context context;
	
	private static PropertiesToolBar propertiesToolBar;
	
	public static PropertiesToolBar getSingleInstance()
	{
		return propertiesToolBar;
	}
	
	public PropertiesToolBar(View mainView)
	{
		ViewInjector.initInjectedView(this, mainView);
		
		this.context = mainView.getContext();
		
		idEditText.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
			}
			
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.setIdName(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		
		propertiesToolBar = this;
	}
	
	public void setSelectedView(IView selectedView)
	{
		defaultSelectedViewChanagedListener.onSelectedViewChanaged(this.selectedView, selectedView);
		
		this.selectedView = selectedView;
		
		if(null != selectedView)
		{
			if(((View)selectedView).getParent() instanceof LinearLayout)
			{
				relativiePositionToolBar.setVisibility(View.GONE);
				linearPositionToolBar.setVisibility(View.VISIBLE);
			}
			else if(((View)selectedView).getParent() instanceof RelativeLayout)
			{
				relativiePositionToolBar.setVisibility(View.VISIBLE);
				linearPositionToolBar.setVisibility(View.GONE);
			}
			
			if(selectedView instanceof TextView || selectedView instanceof Button || 
					selectedView instanceof CheckBox || selectedView instanceof EditText)
			{
				contentToolBar.setVisibility(View.VISIBLE);
			}
			else
			{
				contentToolBar.setVisibility(View.GONE);
			}
			
			curViewName.setText(selectedView.getClassSimpleName());
			
			if(!TextUtils.isEmpty(selectedView.getIdName()))
			{
				idEditText.setText(selectedView.getIdName());
			}
			else
			{
				idEditText.setText("");
			}
			
			/*********************************** Common Position *********************************/
			
			commonPositionToolBar.resetCommonPosition(selectedView);
			
			/*********************************** Background *********************************/
			
			backgroundToolBar.resetBackground(selectedView);
			
			/*********************************** Content *********************************/
			
			contentToolBar.resetContent(selectedView);
			/*********************************** Linear Position *********************************/
			
			if(((View)selectedView).getParent() instanceof TGLinearLayout)
			{
				linearPositionToolBar.resetLinearPosition(selectedView);
			}
			
			/*********************************** Relativie Position *********************************/
			if(((View)selectedView).getParent() instanceof TGRelativeLayout)
			{
				relativiePositionToolBar.resetRelativePosition(selectedView);
			}
		}
	}
	
	private OnSelectedViewChanagedListener defaultSelectedViewChanagedListener = new OnSelectedViewChanagedListener()
	{
		@Override
		public void onSelectedViewChanaged(IView lastSelectedView, IView curSelectedView)
		{
			if(lastSelectedView != curSelectedView)
			{
				//设置上一次选中的View的状态为未选中状态
				if(null != lastSelectedView)
				{
					lastSelectedView.onUnSelected();
				}
				
				if(null != curSelectedView)
				{
					//设置当前选中的View的状态为选中状态
					curSelectedView.onSelected();
				}
			}
		}
	};
	
	public static interface OnSelectedViewChanagedListener
	{
		void onSelectedViewChanaged(IView lastSelectedView, IView curSelectedView);
	}
}
