package com.tiger.layoutide.ide;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CommonPositionToolBar extends FrameLayout
{
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
	
	public void resetCommonPosition(IView selectedView)
	{
		this.selectedView = selectedView;
		
		if(null != selectedView)
		{
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
		}
	}
}
