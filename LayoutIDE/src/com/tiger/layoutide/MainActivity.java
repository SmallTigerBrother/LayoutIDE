package com.tiger.layoutide;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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
import com.tiger.code.json.JSONClassGenerator;
import com.tiger.code.model.JClass;
import com.tiger.layoutide.ide.Emulator;
import com.tiger.layoutide.ide.ExampleViewPanel;
import com.tiger.layoutide.ide.JCodeHelper;
import com.tiger.layoutide.ide.PropertiesToolBar;
import com.tiger.layoutide.storage.db.LayoutDBManager;
import com.tiger.layoutide.widget.IViewGroup;

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
				String json = "{\"count\":1,\"name\":\"tiger\",\"textSize\":0.1,\"isVisible\":true,\"time\":92233720368547758,\"Child\":{\"childcount\":1}," + 
			                   "\"array\":[{\"text\":\"text1\"},{\"text\":\"text2\"}]}";
				
				List<JClass> clazzes = new JSONClassGenerator().json2Classes(json);
				
//				LayoutDBManager.saveLayout(MainActivity.this, "emulatorLayout", (IViewGroup)emulatorLayout);
				LayoutDBManager.getLayout(MainActivity.this, "emulatorLayout");
				
				try
				{
//					FileOutputStream outputStream = MainActivity.this.openFileOutput("Test.java", MODE_WORLD_WRITEABLE);
//					
//					outputStream.write(clazzes.get(0).toString().getBytes());
//					
//					outputStream.flush();
//					
//					outputStream.close();
					
					FileOutputStream outputStream = MainActivity.this.openFileOutput("AAAAAAA.xml", MODE_WORLD_WRITEABLE);
					
					outputStream.write(emulatorLayout.getXMLString().getBytes());
					
					outputStream.flush();
					
					outputStream.close();
					
					FileOutputStream outputStream2 = MainActivity.this.openFileOutput("BBBB.java", MODE_WORLD_WRITEABLE);
					
					outputStream2.write(JCodeHelper.outputInjectViewById(emulatorLayout).getBytes());
					
					outputStream2.flush();
					
					outputStream2.close();
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
//				LogTools.d(emulatorLayout.getXMLString());
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
