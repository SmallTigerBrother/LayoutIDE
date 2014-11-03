package com.tiger.layoutide.ide.tool;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.tool.PropertiesToolBar.CustomTextWatcher;
import com.tiger.layoutide.ide.ui.CreateLayoutActivity;
import com.tiger.layoutide.ide.ui.IntentKeys;
import com.tiger.layoutide.storage.db.LayoutDBManager;
import com.tiger.layoutide.storage.model.LayoutDBModel;
import com.tiger.layoutide.utils.GravityValue;
import com.tiger.layoutide.widget.IAdapterView;
import com.tiger.layoutide.widget.ITextView;
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
	
	@ViewById(id = R.id.adapter_item_layout_name)
	private Spinner adapterLayoutSelector;
	
	@ViewById(id = R.id.create_adapter_item_layout)
	private Button createAdapterLayoutBtn;
	
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
					if(selectedView instanceof ITextView)
					{
						if(!TextUtils.isEmpty(s))
						{
							((ITextView)selectedView).setText(s.toString());
						}
						else
						{
							((ITextView)selectedView).setText("");
						}
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
						if(selectedView instanceof ITextView )
						{
							((ITextView)selectedView).setTextColor(s.toString());
						}
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
						if(selectedView instanceof ITextView)
						{
							((ITextView)selectedView).setTextSize(s.toString());
						}
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
		
		adapterLayoutSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					if(selectedView instanceof IAdapterView)
					{
						((IAdapterView)selectedView).setItemLayout(parent.getAdapter().getItem(position).toString());
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
		
		createAdapterLayoutBtn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getContext(), CreateLayoutActivity.class);
				intent.putExtra(IntentKeys.LAYOUT_TYPE, LayoutDBModel.CUSTOM_VIEW_LAYOUT);
				getContext().startActivity(intent);
			}
		});
		
	}
	
	public void resetContent(IView selectedView)
	{
		this.selectedView = selectedView;
		if(null != selectedView)
		{
			if(selectedView instanceof ITextView)
			{
				textEditText.setVisibility(View.VISIBLE);
				textSizeEditText.setVisibility(View.VISIBLE);
				textColorEditText.setVisibility(View.VISIBLE);
				
				adapterLayoutSelector.setVisibility(View.GONE);
				createAdapterLayoutBtn.setVisibility(View.GONE);
				
				if(!TextUtils.isEmpty(((ITextView)selectedView).getText()))
				{
					textEditText.setText(((ITextView)selectedView).getText());
				}
				else
				{
					textEditText.setText("");
				}
				
				if(((ITextView)selectedView).getTextSize() > 0)
				{
					textSizeEditText.setText(((ITextView)selectedView).getTextSize() + "");
				}
				else
				{
					textSizeEditText.setText("");
				}
				
				if(!TextUtils.isEmpty(((ITextView)selectedView).getTextColor()))
				{
					textColorEditText.setText(((ITextView)selectedView).getTextColor());
				}
				else
				{
					textColorEditText.setText("");
				}
			}
			else if(selectedView instanceof IAdapterView)
			{
				textEditText.setVisibility(View.GONE);
				textSizeEditText.setVisibility(View.GONE);
				textColorEditText.setVisibility(View.GONE);
				
				adapterLayoutSelector.setVisibility(View.VISIBLE);
				createAdapterLayoutBtn.setVisibility(View.VISIBLE);
				
				textEditText.setText("");
				textSizeEditText.setText("");
				textColorEditText.setText("");
				
				//Ìî³äadapterSelector
				resetAdapterSelector();
			}
			else
			{
				textEditText.setVisibility(View.GONE);
				textSizeEditText.setVisibility(View.GONE);
				textColorEditText.setVisibility(View.GONE);
				
				adapterLayoutSelector.setVisibility(View.GONE);
				createAdapterLayoutBtn.setVisibility(View.GONE);
				
				textEditText.setText("");
				textSizeEditText.setText("");
				textColorEditText.setText("");
			}
			
			resetGravity();
		}
	}
	
	private void resetAdapterSelector()
	{
		List<LayoutDBModel> layoutDBModels = LayoutDBManager.getAllCustomViewLayout(getContext());
		
		if(null == layoutDBModels)
		{
			return;
		}
		
		List<String> layoutNameList = new ArrayList<String>();
		layoutNameList.add("NONE");
		String layoutName = "NONE";
		int curSelectIndex = 0;
		for(int i = 0; i < layoutDBModels.size(); i++)
		{
			layoutName = layoutDBModels.get(i).getLayoutName();
			layoutNameList.add(layoutName);
			
			if(((IAdapterView)selectedView).getItemLayout().equals(layoutName))
			{
				curSelectIndex = i;
			}
		}
		
		CharSequence[] layoutNames = new CharSequence[layoutNameList.size()];
		layoutNameList.toArray(layoutNames);
		
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(),
				android.R.layout.simple_spinner_item, layoutNames);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		adapterLayoutSelector.setAdapter(adapter);
		adapterLayoutSelector.setSelection(curSelectIndex);
	}
	
	private void resetGravity()
	{
		String layoutGravity = selectedView.getGravityValue();
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
