package com.tiger.layoutide;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.LogTools;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.ide.Emulator;
import com.tiger.layoutide.ide.PropertiesToolBar;
import com.tiger.layoutide.widget.TGTextView;

public class MainActivity extends Activity
{
	@ViewById(id = R.id.emulator_screen)
	private Emulator emulatorLayout;
	
	@ViewById(id = R.id.properties_tool_bar)
	private ScrollView propertiesToolBarView;

	@ViewById(id = R.id.test_btn)
	private Button testButton;
	
	@ViewById(id = R.id.output_xml)
	private Button outputButton;
	
	private PropertiesToolBar propertiesToolBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		ViewInjector.initInjectedView(this, this);
		
		propertiesToolBar = new PropertiesToolBar(propertiesToolBarView);
		
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
						propertiesToolBar.setSelectedView(textView);
					}
				});
				
				emulatorLayout.addView(textView);
			}
		});
		
	}
}
