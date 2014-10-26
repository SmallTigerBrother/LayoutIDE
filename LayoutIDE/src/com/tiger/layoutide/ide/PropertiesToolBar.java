package com.tiger.layoutide.ide;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;

public class PropertiesToolBar
{
	@ViewById(id = R.id.emulator_screen)
	private Emulator emulatorLayout;
	
	@ViewById(id = R.id.curview_name)
	private TextView curViewName;
	
	@ViewById(id = R.id.id_editor)
	private EditText idEditText;
	
	@ViewById(id = R.id.layout_width_editor)
	private EditText layoutWidthEditText;
	
	@ViewById(id = R.id.layout_height_eidtor)
	private EditText layoutHeightEditText;
	
	@ViewById(id = R.id.layout_weight_editor)
	private EditText layoutWeightEditText;
	
	@ViewById(id = R.id.layout_marginleft_editor)
	private EditText marginLeftEditText;
	
	@ViewById(id = R.id.layout_marginRight_editor)
	private EditText marginRightEditText;
	
	@ViewById(id = R.id.layout_marginTop_editor)
	private EditText marginTopEditText;
	
	@ViewById(id = R.id.layout_marginBottom_editor)
	private EditText marginBottomEditText;
	
	@ViewById(id = R.id.background_color_editor)
	private EditText backgroundColorEditText;
	
	@ViewById(id = R.id.text_editor)
	private EditText textEditText;
	
	@ViewById(id = R.id.text_color_editor)
	private EditText textColorEditText;
	
	@ViewById(id = R.id.text_size_editor)
	private EditText textSizeEditText;
	
	@ViewById(id = R.id.gravity_selector)
	private Spinner gravitySelector;
	
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
		
		initPositionPropertyEditors();
		
		initBackgroundPropertyEditors();
		
		initContentPropertyEditors();
		
		propertiesToolBar = this;
	}
	
	private void initPositionPropertyEditors()
	{
		layoutWidthEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setLayoutWidth(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		layoutHeightEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setLayoutHeight(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		layoutWeightEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setLayoutWeight(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					if(null != selectedView)
					{
						selectedView.setLayoutWeight("");
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		marginLeftEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setLayoutMarginLeft(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		marginRightEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setLayoutMarginRight(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		marginTopEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setLayoutMarginTop(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		marginBottomEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setLayoutMarginBottom(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
	}
	
	private void initBackgroundPropertyEditors()
	{
		backgroundColorEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setBackgroundColor(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
	
	private void initContentPropertyEditors()
	{
		textEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setText(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
				else
				{
					if(null != selectedView)
					{
						selectedView.setText("");
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		textColorEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setTextColor(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		textSizeEditText.addTextChangedListener(new TextWatcher()
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
						selectedView.setTextSize(s.toString());
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		gravitySelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				parent.getAdapter().getItem(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				
			}
		});
	}
	
	public void setSelectedView(IView selectedView)
	{
		defaultSelectedViewChanagedListener.onSelectedViewChanaged(this.selectedView, selectedView);
		
		this.selectedView = selectedView;
		
		if(null != selectedView)
		{
			curViewName.setText(selectedView.getClassSimpleName());
			
			if(!TextUtils.isEmpty(selectedView.getIdName()))
			{
				idEditText.setText(selectedView.getIdName());
			}
			else
			{
				idEditText.setText("");
			}
			
			if(!TextUtils.isEmpty(selectedView.getLayoutWidth()))
			{
				String width = selectedView.getLayoutWidth();
				if(width.contains("dp"))
				{
					width = width.replace("dp", "");
				}
				layoutWidthEditText.setText(width);
			}
			else
			{
				layoutWidthEditText.setText("");
			}
			
			if(!TextUtils.isEmpty(selectedView.getLayoutHeight()))
			{
				String height = selectedView.getLayoutHeight();
				if(height.contains("dp"))
				{
					height = height.replace("dp", "");
				}
				layoutHeightEditText.setText(height);
			}
			else
			{
				layoutHeightEditText.setText("");
			}
			
			if(selectedView.getLayoutWeight() > 0)
			{
				layoutWeightEditText.setText(selectedView.getLayoutWeight() + "");
			}
			else
			{
				layoutWeightEditText.setText("");
			}
			
			if(selectedView.getLayoutMarginLeft() > 0)
			{
				marginLeftEditText.setText(selectedView.getLayoutMarginLeft() + "");
			}
			else
			{
				marginLeftEditText.setText("");
			}
			
			if(selectedView.getLayoutMarginRight() > 0)
			{
				marginRightEditText.setText(selectedView.getLayoutMarginRight() + "");
			}
			else
			{
				marginRightEditText.setText("");
			}
			
			if(selectedView.getLayoutMarginTop() > 0)
			{
				marginTopEditText.setText(selectedView.getLayoutMarginTop() + "");
			}
			else
			{
				marginTopEditText.setText("");
			}
			
			if(selectedView.getLayoutMarginBottom() > 0)
			{
				marginBottomEditText.setText(selectedView.getLayoutMarginBottom() + "");
			}
			else
			{
				marginBottomEditText.setText("");
			}
			
			if(!TextUtils.isEmpty(selectedView.getBackgroundColor()))
			{
				backgroundColorEditText.setText(selectedView.getBackgroundColor());
			}
			else
			{
				backgroundColorEditText.setText("");
			}
			
			if(!TextUtils.isEmpty(selectedView.getText()))
			{
				textEditText.setText(selectedView.getText());
			}
			else
			{
				textEditText.setText("");
			}
			
			if(selectedView.getTextSize() > 0)
			{
				textSizeEditText.setText(selectedView.getTextSize() + "");
			}
			else
			{
				textSizeEditText.setText("");
			}
			
			if(!TextUtils.isEmpty(selectedView.getTextColor()))
			{
				textColorEditText.setText(selectedView.getTextColor());
			}
			else
			{
				textColorEditText.setText("");
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
