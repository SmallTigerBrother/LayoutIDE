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
import android.widget.Spinner;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
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
						Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
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
				parent.getAdapter().getItem(position).toString();
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
		}
	}
	
}
