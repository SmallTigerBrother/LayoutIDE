package com.tiger.layoutide.ide;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.PropertiesToolBar.CustomTextWatcher;
import com.tiger.layoutide.utils.GravityValue;
import com.tiger.layoutide.widget.IView;

public class ContentToolBar extends FrameLayout
{
	@ViewById(id = R.id.text_editor)
	private EditText textEditText;
	
	@ViewById(id = R.id.text_color_editor)
	private EditText textColorEditText;
	
	@ViewById(id = R.id.text_size_editor)
	private EditText textSizeEditText;
	
	@ViewById(id = R.id.gravity_selector)
	private Spinner gravitySelector;
	
	private IView selectedView = null;
	
	public ContentToolBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private void init()
	{
		inflate(getContext(),R.layout.content_tool_bar , this);
		ViewInjector.initInjectedView(this, this);
		
		textEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(null != selectedView)
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
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		textColorEditText.addTextChangedListener(new CustomTextWatcher()
		{
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		textSizeEditText.addTextChangedListener(new CustomTextWatcher()
		{
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		gravitySelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setGravityValue(parent.getAdapter().getItem(position).toString());
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
	
	public void resetContent(IView selectedView)
	{
		this.selectedView = selectedView;
		if(null != selectedView)
		{
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
			
			resetGravity();
		}
	}
	
	private void resetGravity()
	{
		String layoutGravity = selectedView.getLayoutGravityValue();
		if(TextUtils.isEmpty(layoutGravity))
		{
			gravitySelector.setSelection(0);
		}
		else if(GravityValue.TOP.equals(layoutGravity))
		{
			gravitySelector.setSelection(2);
		}
		else if(GravityValue.BOTTOM.equals(layoutGravity))
		{
			gravitySelector.setSelection(3);
		}
		else if(GravityValue.LEFT.equals(layoutGravity))
		{
			gravitySelector.setSelection(4);
		}
		else if(GravityValue.RIGHT.equals(layoutGravity))
		{
			gravitySelector.setSelection(5);
		}
		else if(GravityValue.CENTER.equals(layoutGravity))
		{
			gravitySelector.setSelection(1);
		}
		else if(GravityValue.CENTER_HORIZONTAL.equals(layoutGravity))
		{
			gravitySelector.setSelection(7);
		}
		else if(GravityValue.CENTER_VERTICAL.equals(layoutGravity))
		{
			gravitySelector.setSelection(6);
		}
		else if(GravityValue.LEFT_ADN_BOTTOM.equals(layoutGravity))
		{
			gravitySelector.setSelection(9);
		}
		else if(GravityValue.LEFT_ADN_TOP.equals(layoutGravity))
		{
			gravitySelector.setSelection(8);
		}
		else if(GravityValue.LEFT_AND_CENTER_VERTICAL.equals(layoutGravity))
		{
			gravitySelector.setSelection(14);
		}
		else if(GravityValue.RIGHT_AND_BOTTOM.equals(layoutGravity))
		{
			gravitySelector.setSelection(11);
		}
		else if(GravityValue.RIGHT_AND_TOP.equals(layoutGravity))
		{
			gravitySelector.setSelection(10);
		}
		else if(GravityValue.RIGHT_AND_CENTER_VERTICAL.equals(layoutGravity))
		{
			gravitySelector.setSelection(15);
		}
		else if(GravityValue.TOP_AND_CENTER_HORIZONTAL.equals(layoutGravity))
		{
			gravitySelector.setSelection(12);
		}
		else if(GravityValue.BOTTOM_AND_CENTER_HORIZONTAL.equals(layoutGravity))
		{
			gravitySelector.setSelection(13);
		}
	}
	
}
