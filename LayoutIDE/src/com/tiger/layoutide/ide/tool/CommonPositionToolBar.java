package com.tiger.layoutide.ide.tool;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.tool.PropertiesToolBar.CustomTextWatcher;
import com.tiger.layoutide.widget.IView;

public class CommonPositionToolBar extends FrameLayout implements OnClickListener, OnFocusChangeListener
{
	@ViewById(id = R.id.layout_width_editor)
	private AutoCompleteTextView layoutWidthEditText;
	
	@ViewById(id = R.id.layout_height_eidtor)
	private AutoCompleteTextView layoutHeightEditText;
	
	@ViewById(id = R.id.layout_marginleft_editor)
	private AutoCompleteTextView marginLeftEditText;
	
	@ViewById(id = R.id.layout_marginRight_editor)
	private AutoCompleteTextView marginRightEditText;
	
	@ViewById(id = R.id.layout_marginTop_editor)
	private AutoCompleteTextView marginTopEditText;
	
	@ViewById(id = R.id.layout_marginBottom_editor)
	private AutoCompleteTextView marginBottomEditText;
	
	private IView selectedView = null;
	
	public CommonPositionToolBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private void init()
	{
		inflate(getContext(),R.layout.common_position_tool_bar , this);
		ViewInjector.initInjectedView(this, this);
		
		final CharSequence[] selfAdaptions = {"match_parent","wrap_content"}; 
		final ArrayAdapter<CharSequence> widthAndHeightadapter = new ArrayAdapter<CharSequence>(
				getContext(), android.R.layout.simple_spinner_item, selfAdaptions)
		{
			private Filter filter;
			@Override
			public Filter getFilter()
			{
				if(null == filter)
				{
					filter = new Filter()
					{
						@Override
						protected FilterResults performFiltering(CharSequence constraint)
						{
							return null;
						}

						@Override
						protected void publishResults(CharSequence constraint, FilterResults results)
						{
						}
					};
				}
				return filter; 
			}
		};
		
		layoutWidthEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setLayoutWidth(s.toString());
					}
					else
					{
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		layoutWidthEditText.setAdapter(widthAndHeightadapter);
		layoutWidthEditText.setOnClickListener(this);
		layoutWidthEditText.setOnFocusChangeListener(this);
		layoutWidthEditText.setThreshold(1);
		
		layoutHeightEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setLayoutHeight(s.toString());
					}
					else
					{
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		layoutHeightEditText.setAdapter(widthAndHeightadapter);
		layoutHeightEditText.setThreshold(1);
		layoutHeightEditText.setOnClickListener(this);
		layoutHeightEditText.setOnFocusChangeListener(this);
		
		//TODO 可自行替换显示内容
		CharSequence[] customMargins = {"5","10","15","20","30"}; 
		ArrayAdapter<CharSequence> marginAdapter = new ArrayAdapter<CharSequence>(getContext(),
				android.R.layout.simple_spinner_item, customMargins)
		{
			private Filter filter;
			@Override
			public Filter getFilter()
			{
				if (null == filter)
				{
					filter = new Filter()
					{
						@Override
						protected FilterResults performFiltering(CharSequence constraint)
						{
							return null;
						}

						@Override
						protected void publishResults(CharSequence constraint, FilterResults results)
						{
						}
					};
				}
				return filter;
			}
		};
				
		marginLeftEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setLayoutMarginLeft(s.toString());
					}
					else
					{
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		marginLeftEditText.setAdapter(marginAdapter);
		marginLeftEditText.setThreshold(1);
		marginLeftEditText.setOnClickListener(this);
		marginLeftEditText.setOnFocusChangeListener(this);
		
		
		marginRightEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setLayoutMarginRight(s.toString());
					}
					else
					{
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		marginRightEditText.setAdapter(marginAdapter);
		marginRightEditText.setThreshold(1);
		marginRightEditText.setOnClickListener(this);
		marginRightEditText.setOnFocusChangeListener(this);
		
		marginTopEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setLayoutMarginTop(s.toString());
					}
					else
					{
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		marginTopEditText.setAdapter(marginAdapter);
		marginTopEditText.setThreshold(1);
		marginTopEditText.setOnClickListener(this);
		marginTopEditText.setOnFocusChangeListener(this);
		
		marginBottomEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setLayoutMarginBottom(s.toString());
					}
					else
					{
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		marginBottomEditText.setAdapter(marginAdapter);
		marginBottomEditText.setThreshold(1);
		marginBottomEditText.setOnClickListener(this);
		marginBottomEditText.setOnFocusChangeListener(this);
	}
	
	public void resetCommonPosition(IView selectedView)
	{
		this.selectedView = selectedView;
		
		if(null != selectedView)
		{
			if(!TextUtils.isEmpty(selectedView.getViewHelper().getLayoutWidth()))
			{
				String width = selectedView.getViewHelper().getLayoutWidth();
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
			
			if(!TextUtils.isEmpty(selectedView.getViewHelper().getLayoutHeight()))
			{
				String height = selectedView.getViewHelper().getLayoutHeight();
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
			
			if(selectedView.getViewHelper().getLayoutMarginLeft() > 0)
			{
				marginLeftEditText.setText(selectedView.getViewHelper().getLayoutMarginLeft() + "");
			}
			else
			{
				marginLeftEditText.setText("");
			}
			
			if(selectedView.getViewHelper().getLayoutMarginRight() > 0)
			{
				marginRightEditText.setText(selectedView.getViewHelper().getLayoutMarginRight() + "");
			}
			else
			{
				marginRightEditText.setText("");
			}
			
			if(selectedView.getViewHelper().getLayoutMarginTop() > 0)
			{
				marginTopEditText.setText(selectedView.getViewHelper().getLayoutMarginTop() + "");
			}
			else
			{
				marginTopEditText.setText("");
			}
			
			if(selectedView.getViewHelper().getLayoutMarginBottom() > 0)
			{
				marginBottomEditText.setText(selectedView.getViewHelper().getLayoutMarginBottom() + "");
			}
			else
			{
				marginBottomEditText.setText("");
			}
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		switch (v.getId())
		{
			case R.id.layout_width_editor:
				if(hasFocus)
				{
					layoutWidthEditText.showDropDown();
				}
				else
				{
					layoutWidthEditText.dismissDropDown();
				}
				break;

			case R.id.layout_height_eidtor:
				if(hasFocus)
				{
					layoutHeightEditText.showDropDown();
				}
				else
				{
					layoutHeightEditText.dismissDropDown();
				}
				break;

			case R.id.layout_marginleft_editor:
				if(hasFocus)
				{
					marginLeftEditText.showDropDown();
				}
				else
				{
					marginLeftEditText.dismissDropDown();
				}
				break;

			case R.id.layout_marginRight_editor:
				if(hasFocus)
				{
					marginRightEditText.showDropDown();
				}
				else
				{
					marginRightEditText.dismissDropDown();
				}
				break;

			case R.id.layout_marginTop_editor:
				if(hasFocus)
				{
					marginTopEditText.showDropDown();
				}
				else
				{
					marginTopEditText.dismissDropDown();
				}
				break;

			case R.id.layout_marginBottom_editor:
				if(hasFocus)
				{
					marginBottomEditText.showDropDown();
				}
				else
				{
					marginBottomEditText.dismissDropDown();
				}
				break;

			default:
				break;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.layout_width_editor:
				layoutWidthEditText.showDropDown();
				break;

			case R.id.layout_height_eidtor:
				layoutHeightEditText.showDropDown();
				break;

			case R.id.layout_marginleft_editor:
				marginLeftEditText.showDropDown();
				break;

			case R.id.layout_marginRight_editor:
				marginRightEditText.showDropDown();
				break;

			case R.id.layout_marginTop_editor:
				marginTopEditText.showDropDown();
				break;

			case R.id.layout_marginBottom_editor:
				marginBottomEditText.showDropDown();
				break;

			default:
				break;
		}
	}
}
