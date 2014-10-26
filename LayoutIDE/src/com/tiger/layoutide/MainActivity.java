package com.tiger.layoutide;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.LogTools;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.ide.Emulator;
import com.tiger.layoutide.ide.ExampleViewPanel;
import com.tiger.layoutide.ide.PropertiesToolBar;

public class MainActivity extends Activity
{
	@ViewById(id = R.id.main_view)
	private RelativeLayout mainView;
	
	@ViewById(id = R.id.emulator_screen)
	private Emulator emulatorLayout;
	
	@ViewById(id = R.id.properties_tool_bar)
	private ScrollView propertiesToolBarView;
	
	@ViewById(id = R.id.example_panel)
	private LinearLayout examplePanelLayout;

	@ViewById(id = R.id.output_xml)
	private Button outputButton;
	
	@ViewById(id = R.id.garbage_bin)
	private TextView garbageBin;
	
	private PropertiesToolBar propertiesToolBar;
	
	private ExampleViewPanel exampleViewPanel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		ViewInjector.initInjectedView(this, this);
		
		propertiesToolBar = new PropertiesToolBar(propertiesToolBarView);
		
		exampleViewPanel = new ExampleViewPanel(examplePanelLayout);
		
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
		
		mainView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				propertiesToolBar.setSelectedView(null);
			}
		});
		
		garbageBin.setOnDragListener(new OnDragListener()
		{
			@Override
			public boolean onDrag(View v, DragEvent event)
			{
				if(event.getAction() == DragEvent.ACTION_DROP)
				{
					View curDragView = (View)Emulator.getSingleInstance().getCurDragView();
					if(null != curDragView)
					{
						((ViewGroup)curDragView.getParent()).removeView(curDragView);
						Emulator.getSingleInstance().setCurDragView(null);
					}
				}
				return true;
			}
		});
	}
}
