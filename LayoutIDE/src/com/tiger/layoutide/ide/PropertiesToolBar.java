package com.tiger.layoutide.ide;

import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.utils.Constant;
import com.tiger.layoutide.widget.IView;
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
	
	@ViewById(id = R.id.layout_width_editor)
	private EditText layoutWidthEditText;
	
	@ViewById(id = R.id.layout_height_eidtor)
	private EditText layoutHeightEditText;
	
	@ViewById(id = R.id.layout_marginleft_editor)
	private EditText marginLeftEditText;
	
	@ViewById(id = R.id.layout_marginRight_editor)
	private EditText marginRightEditText;
	
	@ViewById(id = R.id.layout_marginTop_editor)
	private EditText marginTopEditText;
	
	@ViewById(id = R.id.layout_marginBottom_editor)
	private EditText marginBottomEditText;
	
	/*********************************** LinearLayout Layout *********************************/
	
	@ViewById(id = R.id.layout_weight_editor)
	private EditText layoutWeightEditText;
	
	/*********************************** Background *********************************/
	
	@ViewById(id = R.id.background_color_editor)
	private EditText backgroundColorEditText;
	
	/*********************************** Content *********************************/
	
	@ViewById(id = R.id.text_editor)
	private EditText textEditText;
	
	@ViewById(id = R.id.text_color_editor)
	private EditText textColorEditText;
	
	@ViewById(id = R.id.text_size_editor)
	private EditText textSizeEditText;
	
	@ViewById(id = R.id.gravity_selector)
	private Spinner gravitySelector;
	
	
	/*********************************** Relativie Position *********************************/
	
	@ViewById(id = R.id.align_parent_left_selector)
	private Spinner alignParentLeftSelector;
	
	@ViewById(id = R.id.align_parent_right_selector)
	private Spinner alignParentRightSelector;
	
	@ViewById(id = R.id.align_parent_top_selector)
	private Spinner alignParentTopSelector;
	
	@ViewById(id = R.id.align_parent_bottom_selector)
	private Spinner alignParentBottomSelector;
	
	@ViewById(id = R.id.align_left_selector)
	private Spinner alignLeftSelector;
	
	@ViewById(id = R.id.align_right_selector)
	private Spinner alignRightSelector;
	
	@ViewById(id = R.id.align_top_selector)
	private Spinner alignTopSelector;
	
	@ViewById(id = R.id.align_bottom_selector)
	private Spinner alignBottomSelector;
	
	@ViewById(id = R.id.below_selector)
	private Spinner belowSelector;
	
	@ViewById(id = R.id.above_selector)
	private Spinner aboveSelector;
	
	@ViewById(id = R.id.toleftof_selector)
	private Spinner toLeftOfSelector;
	
	@ViewById(id = R.id.torightof_selector)
	private Spinner toRightOfSelector;
	
	@ViewById(id = R.id.center_in_parent_selector)
	private Spinner centerInParentSelector;
	
	@ViewById(id = R.id.center_vertical_selector)
	private Spinner centerVerticalSelector;
	
	@ViewById(id = R.id.center_horizontal_selector)
	private Spinner centerHorizontalSelector;
	
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
		
		initCommonPositionPropertyEditors();
		
		initBackgroundPropertyEditors();
		
		initContentPropertyEditors();
		
		initRelativePositionPropertiesEditors();
		
		propertiesToolBar = this;
	}
	
	private void initCommonPositionPropertyEditors()
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
	
	private void initRelativePositionPropertiesEditors()
	{
		alignParentLeftSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(Constant.TRUE.equals(parent.getAdapter().getItem(position).toString()))
				{
					if(null != selectedView)
					{
						selectedView.setAlignParentLeft(Constant.TRUE);
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
						selectedView.setAlignParentLeft(Constant.FALSE);
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignParentRightSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(Constant.TRUE.equals(parent.getAdapter().getItem(position).toString()))
				{
					if(null != selectedView)
					{
						selectedView.setAlignParentRight(Constant.TRUE);
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
						selectedView.setAlignParentRight(Constant.FALSE);
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignParentTopSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(Constant.TRUE.equals(parent.getAdapter().getItem(position).toString()))
				{
					if(null != selectedView)
					{
						selectedView.setAlignParentTop(Constant.TRUE);
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
						selectedView.setAlignParentTop(Constant.FALSE);
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignParentBottomSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(Constant.TRUE.equals(parent.getAdapter().getItem(position).toString()))
				{
					if(null != selectedView)
					{
						selectedView.setAlignParentBottom(Constant.TRUE);
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
						selectedView.setAlignParentBottom(Constant.FALSE);
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		centerInParentSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(Constant.TRUE.equals(parent.getAdapter().getItem(position).toString()))
				{
					if(null != selectedView)
					{
						selectedView.setCenterInParent(Constant.TRUE);
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
						selectedView.setCenterInParent(Constant.FALSE);
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		centerVerticalSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(Constant.TRUE.equals(parent.getAdapter().getItem(position).toString()))
				{
					if(null != selectedView)
					{
						selectedView.setCenterVertical(Constant.TRUE);
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
						selectedView.setCenterVertical(Constant.FALSE);
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		centerHorizontalSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(Constant.TRUE.equals(parent.getAdapter().getItem(position).toString()))
				{
					if(null != selectedView)
					{
						selectedView.setCenterHorizontal(Constant.TRUE);
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
						selectedView.setCenterHorizontal(Constant.FALSE);
					}
					else
					{
						Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		belowSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setBelow(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		aboveSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAbove(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		toLeftOfSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setToLeftOf(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		toRightOfSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setToRightOf(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignLeftSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignLeft(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignRightSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignRight(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignTopSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignTop(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignBottomSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignBottom(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(context, "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
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
			
			/*********************************** Common Position *********************************/
			
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
			
			/*********************************** Background *********************************/
			
			if(!TextUtils.isEmpty(selectedView.getBackgroundColor()))
			{
				backgroundColorEditText.setText(selectedView.getBackgroundColor());
			}
			else
			{
				backgroundColorEditText.setText("");
			}
			
			/*********************************** Content *********************************/
			
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
			
			/*********************************** Relativie Position *********************************/
			
			if(((View)selectedView).getParent() instanceof TGRelativeLayout)
			{
				if(!TextUtils.isEmpty(selectedView.getAlignParentLeft()))
				{
					// 1 为true
					alignParentLeftSelector.setSelection(1);
				}
				
				ArrayList<CharSequence> parentChildIdList = ((TGRelativeLayout)((View)selectedView).getParent()).getChildIdList();
				//移除自身的id
				if(null != selectedView.getIdName())
				{
					parentChildIdList.remove(selectedView.getIdName());
				}
				CharSequence[] parentChildIds = new CharSequence[parentChildIdList.size()];
				parentChildIdList.toArray(parentChildIds);
				
				ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(context,
						android.R.layout.simple_spinner_item, parentChildIds);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
				
				belowSelector.setAdapter(adapter);
				if(!TextUtils.isEmpty(selectedView.getBelow()))
				{
					belowSelector.setSelection(parentChildIdList.indexOf(selectedView.getBelow()));
				}
				
				aboveSelector.setAdapter(adapter);
				if(!TextUtils.isEmpty(selectedView.getAbove()))
				{
					belowSelector.setSelection(parentChildIdList.indexOf(selectedView.getAbove()));
				}
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
