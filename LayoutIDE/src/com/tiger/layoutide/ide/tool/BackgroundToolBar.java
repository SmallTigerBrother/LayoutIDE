package com.tiger.layoutide.ide.tool;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.tool.PropertiesToolBar.CustomTextWatcher;
import com.tiger.layoutide.widget.IView;

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
		
		backgroundColorEditText.addTextChangedListener(new CustomTextWatcher()
		{
			@Override
			public void afterTextChanged(Editable s)
			{
				if(!TextUtils.isEmpty(s))
				{
					if(null != selectedView)
					{
						selectedView.getViewHelper().setBackgroundColor(s.toString());
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
			if(!TextUtils.isEmpty(selectedView.getViewHelper().getBackgroundColor()))
			{
				backgroundColorEditText.setText(selectedView.getViewHelper().getBackgroundColor());
			}
			else
			{
				backgroundColorEditText.setText("");
			}
		}
	}
}
