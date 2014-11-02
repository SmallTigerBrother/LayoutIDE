package com.tiger.layoutide;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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
import com.tiger.layoutide.ide.code.JCodeHelper;
import com.tiger.layoutide.ide.tool.Emulator;
import com.tiger.layoutide.ide.tool.ExampleViewPanel;
import com.tiger.layoutide.ide.tool.PropertiesToolBar;
import com.tiger.layoutide.ide.ui.IntentKeys;
import com.tiger.layoutide.ide.ui.TemplateActivity;
import com.tiger.layoutide.ide.ui.template.TemplateFactory;
import com.tiger.layoutide.ide.ui.template.TemplateLayout;
import com.tiger.layoutide.storage.db.LayoutDBManager;
import com.tiger.layoutide.widget.IViewGroup;

public class MainActivity extends Activity
{
	@ViewById(id = R.id.main_view)
	private RelativeLayout mainView;
	
	@ViewById(id = R.id.emulator_screen)
	private Emulator emulator;
	
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
	
	@ViewById(id = R.id.select_template)
	private Button selectTemplate;
	
	
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
				
				LayoutDBManager.saveLayout(MainActivity.this, "emulatorLayout", (IViewGroup)emulator);
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
					
					outputStream.write(emulator.getXMLString().getBytes());
					
					outputStream.flush();
					
					outputStream.close();
					
					FileOutputStream outputStream2 = MainActivity.this.openFileOutput("BBBB.java", MODE_WORLD_WRITEABLE);
					
					outputStream2.write(JCodeHelper.outputInjectViewById(emulator).getBytes());
					
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
		
		selectTemplate.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, TemplateActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 0 && resultCode == TemplateActivity.RESULT_CODE_MAIN)
		{
			int selecteTemplateType = data.getIntExtra(IntentKeys.SELECTED_TEMPLATE_TYPE, TemplateLayout.LINEAR_BLANK_TEMPLATE);
			View rootView = TemplateFactory.getInstance().createRealViewOfTemplate(this, selecteTemplateType);
			emulator.removeAllViews();
			emulator.addView(rootView);
			//ÒÆ³ý±£´æ¼ÇÂ¼
			LayoutDBManager.removeLayout(this, "emulatorLayout");
		}
	}
}
