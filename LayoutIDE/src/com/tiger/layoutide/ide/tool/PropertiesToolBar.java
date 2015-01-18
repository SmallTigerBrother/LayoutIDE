package com.tiger.layoutide.ide.tool;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IAdapterView;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.JTGLinearLayout;
import com.tiger.layoutide.widget.JTGRelativeLayout;

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
	private RelativePositionToolBar relativiePositionToolBar;
	
	
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
		
		idEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setIdName(s.toString());
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
	}
	
	private OnSelectedViewChanagedListener defaultSelectedViewChanagedListener = new OnSelectedViewChanagedListener()
	{
		@Override
		public void onSelectedViewChanaged(IView lastSelectedView, IView curSelectedView)
		{
			if(lastSelectedView != curSelectedView)
			{
				//������һ��ѡ�е�View��״̬Ϊδѡ��״̬
				if(null != lastSelectedView)
				{
					lastSelectedView.onUnSelected();
				}
				
				if(null != curSelectedView)
				{
					//���õ�ǰѡ�е�View��״̬Ϊѡ��״̬
					curSelectedView.onSelected();
				}
				
				PropertiesToolBar.this.selectedView = curSelectedView;
				
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
					
					if(selectedView instanceof TextView || selectedView instanceof ImageView 
							|| selectedView instanceof IAdapterView)
					{
						contentToolBar.setVisibility(View.VISIBLE);
					}
					else
					{
						contentToolBar.setVisibility(View.GONE);
					}
					
					curViewName.setText(selectedView.getSimpleClassName());
					
					if(!TextUtils.isEmpty(selectedView.getViewHelper().getIdName()))
					{
						idEditText.setText(selectedView.getViewHelper().getIdName());
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
					
					if(((View)selectedView).getParent() instanceof JTGLinearLayout)
					{
						linearPositionToolBar.resetLinearPosition(selectedView);
					}
					
					/*********************************** Relativie Position *********************************/
					if(((View)selectedView).getParent() instanceof JTGRelativeLayout)
					{
						relativiePositionToolBar.resetRelativePosition(selectedView);
					}
				}
			}
		}
	};
	
	public static interface OnSelectedViewChanagedListener
	{
		void onSelectedViewChanaged(IView lastSelectedView, IView curSelectedView);
	}
	
	public static abstract class CustomTextWatcher implements TextWatcher
	{
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			
		}
	}
}
