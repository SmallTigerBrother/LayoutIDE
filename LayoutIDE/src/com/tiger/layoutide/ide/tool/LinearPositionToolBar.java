package com.tiger.layoutide.ide.tool;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.tool.PropertiesToolBar.CustomTextWatcher;
import com.tiger.layoutide.utils.XmlOutputConstant;
import com.tiger.layoutide.utils.GravityValue;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGLinearLayout;

public class LinearPositionToolBar extends FrameLayout
{
	@ViewById(id = R.id.layout_weight_editor)
	private EditText layoutWeightEditText;
	
	@ViewById(id = R.id.orientation_property)
	private LinearLayout orientationProperty;
	
	@ViewById(id = R.id.layout_orientation_selector)
	private Spinner orientationSelector;
	
	@ViewById(id = R.id.layout_gravity_property)
	private LinearLayout layoutGravityProperty;
	
	@ViewById(id = R.id.layout_gravity_selector)
	private Spinner layoutGravitySelector;
	
	private IView selectedView = null;

	public LinearPositionToolBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private void init()
	{
		inflate(getContext(), R.layout.linear_position_tool_bar, this);
		ViewInjector.initInjectedView(this, this);
		
		layoutWeightEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(null != selectedView)
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
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		orientationSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					if(selectedView instanceof LinearLayout)
					{
						if(XmlOutputConstant.ORIENTATION_HORIZONTAL.equals(parent.getAdapter().getItem(position).toString()))
						{
							((TGLinearLayout)selectedView).setOrientationValue(XmlOutputConstant.ORIENTATION_HORIZONTAL);
						}
						else
						{
							((TGLinearLayout)selectedView).setOrientationValue(XmlOutputConstant.ORIENTATION_VERTICAL);
						}
					}
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		layoutGravitySelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setLayoutGravityValue(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
	}
	
	public void resetLinearPosition(IView selectedView)
	{
		this.selectedView = selectedView;
		if(null != selectedView)
		{
			if(selectedView instanceof LinearLayout)
			{
				orientationProperty.setVisibility(View.VISIBLE);
				
				if(((TGLinearLayout)selectedView).getOrientationValue().equals(XmlOutputConstant.ORIENTATION_HORIZONTAL))
				{
					orientationSelector.setSelection(0);
				}
				else
				{
					orientationSelector.setSelection(1);
				}
			}
			else
			{
				orientationProperty.setVisibility(View.GONE);
			}
			
			if(((View)selectedView).getParent() instanceof LinearLayout)
			{
				layoutGravityProperty.setVisibility(View.VISIBLE);
				
				resetLayoutGravity(selectedView);
			}
			else
			{
				layoutGravityProperty.setVisibility(View.GONE);
			}
			
			if(selectedView.getLayoutWeight() > 0)
			{
				layoutWeightEditText.setText(selectedView.getLayoutWeight() + "");
			}
			else
			{
				layoutWeightEditText.setText("");
			}
		}
	}
	
	private void resetLayoutGravity(IView selectedView)
	{
		String layoutGravity = selectedView.getLayoutGravityValue();
		if(TextUtils.isEmpty(layoutGravity))
		{
			layoutGravitySelector.setSelection(0);
		}
		else if(GravityValue.TOP.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(2);
		}
		else if(GravityValue.BOTTOM.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(3);
		}
		else if(GravityValue.LEFT.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(4);
		}
		else if(GravityValue.RIGHT.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(5);
		}
		else if(GravityValue.CENTER.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(1);
		}
		else if(GravityValue.CENTER_HORIZONTAL.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(7);
		}
		else if(GravityValue.CENTER_VERTICAL.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(6);
		}
		else if(GravityValue.LEFT_ADN_BOTTOM.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(9);
		}
		else if(GravityValue.LEFT_ADN_TOP.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(8);
		}
		else if(GravityValue.LEFT_AND_CENTER_VERTICAL.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(14);
		}
		else if(GravityValue.RIGHT_AND_BOTTOM.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(11);
		}
		else if(GravityValue.RIGHT_AND_TOP.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(10);
		}
		else if(GravityValue.RIGHT_AND_CENTER_VERTICAL.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(15);
		}
		else if(GravityValue.TOP_AND_CENTER_HORIZONTAL.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(12);
		}
		else if(GravityValue.BOTTOM_AND_CENTER_HORIZONTAL.equals(layoutGravity))
		{
			layoutGravitySelector.setSelection(13);
		}
	}

}
