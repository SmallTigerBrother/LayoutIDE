package com.tiger.layoutide.ide;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.tiger.layoutide.utils.Constant;
import com.tiger.layoutide.widget.IView;

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
					if(Constant.ORIENTATION_HORIZONTAL.equals(parent.getAdapter().getItem(position).toString()))
					{
						selectedView.setOrientationValue(Constant.ORIENTATION_HORIZONTAL);
					}
					else
					{
						selectedView.setOrientationValue(Constant.ORIENTATION_VERTICAL);
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
	}
	
	public void resetLinearPosition(IView selectedView)
	{
		this.selectedView = selectedView;
		if(null != selectedView)
		{
			if(selectedView instanceof LinearLayout)
			{
				orientationProperty.setVisibility(View.VISIBLE);
				layoutGravityProperty.setVisibility(View.VISIBLE);
			}
			else
			{
				orientationProperty.setVisibility(View.GONE);
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

}
