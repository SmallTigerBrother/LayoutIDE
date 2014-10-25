package com.tiger.layoutide.ide;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGLinearLayout;

public class PropertiesToolBar
{
	@ViewById(id = R.id.emulator_screen)
	private TGLinearLayout emulatorLayout;
	
	
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
	
	@ViewById(id = R.id.test_btn)
	private Button testButton;
	
	@ViewById(id = R.id.output_xml)
	private Button outputButton;
	
	private IView selectedView = null;
	
	public PropertiesToolBar(View mainView)
	{
		ViewInjector.initInjectedView(this, mainView);
		
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
					selectedView.setIdName(s.toString());
				}
			}
		});
		
		initPositionPropertyEditors();
		
		initBackgroundPropertyEditors();
		
		initContentPropertyEditors();
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
					selectedView.setLayoutWidth(s.toString());
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
					selectedView.setLayoutHeight(s.toString());
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
					selectedView.setLayoutWeight(s.toString());
				}
				else
				{
					selectedView.setLayoutWeight("");
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
					selectedView.setLayoutMarginLeft(s.toString());
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
					selectedView.setLayoutMarginRight(s.toString());
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
					selectedView.setLayoutMarginTop(s.toString());
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
					selectedView.setLayoutMarginBottom(s.toString());
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
					selectedView.setBackgroundColor(s.toString());
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
					selectedView.setText(s.toString());
				}
				else
				{
					selectedView.setText("");
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
					selectedView.setTextColor(s.toString());
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
					selectedView.setTextSize(s.toString());
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
		this.selectedView = selectedView;
	}
}
