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

public class BackgroundToolBar extends FrameLayout
{
	@ViewById(id = R.id.background_color_editor)
	private EditText backgroundColorEditText;
	
	private IView selectedView = null;
	
	public BackgroundToolBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private void init()
	{
		inflate(getContext(),R.layout.background_tool_bar , this);
		ViewInjector.initInjectedView(this, this);
		
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}
	
	public void resetBackground(IView selectedView)
	{
		this.selectedView = selectedView;
		if(null != selectedView)
		{
			if(!TextUtils.isEmpty(selectedView.getBackgroundColor()))
			{
				backgroundColorEditText.setText(selectedView.getBackgroundColor());
			}
			else
			{
				backgroundColorEditText.setText("");
			}
		}
	}
}
