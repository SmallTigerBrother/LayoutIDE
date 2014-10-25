package com.tiger.layoutide;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.LogTools;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.tree.IView;
import com.tiger.layoutide.widget.TGLinearLayout;
import com.tiger.layoutide.widget.TGTextView;

public class MainActivity extends Activity
{
	@ViewById(id = R.id.emulator_screen)
	private TGLinearLayout emulatorLayout;
	

	
	
	@ViewById(id = R.id.id_editor)
	private EditText idEditText;
	
	@ViewById(id = R.id.layout_width_editor)
	private EditText layoutWidthEditText;
	
	@ViewById(id = R.id.layout_height_eidtor)
	private EditText layoutHeightEditText;
	
	@ViewById(id = R.id.layout_weight_editor)
	private EditText layoutWeightEditText;
	
	@ViewById(id = R.id.layout_marginleft_editor)
	private EditText marginLeftEditText;
	
	@ViewById(id = R.id.layout_marginRight_editor)
	private EditText marginRightEditText;
	
	@ViewById(id = R.id.layout_marginTop_editor)
	private EditText marginTopEditText;
	
	@ViewById(id = R.id.layout_marginBottom_editor)
	private EditText marginBottomEditText;
	
	@ViewById(id = R.id.background_color_editor)
	private EditText backgroundColorEditText;
	
	@ViewById(id = R.id.text_editor)
	private EditText textEditText;
	
	@ViewById(id = R.id.text_color_editor)
	private EditText textColorEditText;
	
	@ViewById(id = R.id.text_size_editor)
	private EditText textSizeEditText;
	
	@ViewById(id = R.id.gravity_selector)
	private Spinner gravitySelector;
	
	@ViewById(id = R.id.test_btn)
	private Button testButton;
	
	@ViewById(id = R.id.output_xml)
	private Button outputButton;
	
	private IView selectedView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		ViewInjector.initInjectedView(this, this);
		
		outputButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					FileOutputStream outputStream = MainActivity.this.openFileOutput("AAAAAAA.xml", MODE_WORLD_WRITEABLE);
					
					outputStream.write(emulatorLayout.getXMLString().getBytes());
					
					outputStream.flush();
					
					outputStream.close();
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				LogTools.d(emulatorLayout.getXMLString());
			}
		});
		
		testButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				final TGTextView textView = new TGTextView(MainActivity.this);
				
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
				
				textView.setLayoutParams(layoutParams);
				
				textView.setText("text");
				textView.setBackgroundColor(Color.BLACK);
				
				textView.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						selectedView = textView;
					}
				});
				
				emulatorLayout.addView(textView);
			}
		});
		
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
					selectedView.setIdName(s.toString());
				}
			}
		});
		
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
					selectedView.setLayoutWidth(s.toString());
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
					selectedView.setLayoutHeight(s.toString());
				}
			}
		});
		
		
	}
}
