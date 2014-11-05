package com.tiger.layoutide.ide.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.MainActivity;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.code.JCodeHelper;
import com.tiger.layoutide.ide.ui.template.TemplateLayout;
import com.tiger.layoutide.storage.db.LayoutDBManager;
import com.tiger.layoutide.storage.model.LayoutDBModel;

public class CreateLayoutActivity extends Activity implements OnClickListener
{
	@ViewById(id = R.id.layout_name_edit)
	private EditText layoutName;
	
	@ViewById(id = R.id.select_template_btn)
	private Button selectTemplateBtn;
	
	@ViewById(id = R.id.template_name_txt)
	private TextView templateName;
	
	@ViewById(id = R.id.create_layout_btn)
	private Button createLayoutBtn;
	
	@ViewById(id = R.id.layout_type_selector)
	private Spinner layoutTypeSelector;
	
	private int templateType = TemplateLayout.RELATIVE_BLANK_TEMPLATE;
	
	private int layoutType = LayoutDBModel.ACTIVITY_LAYOUT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.create_layout);
		
		ViewInjector.initInjectedView(this, this);
		
		templateName.setText("RelativeBlank");
		
		selectTemplateBtn.setOnClickListener(this);
		createLayoutBtn.setOnClickListener(this);
		
		layoutType = getIntent().getIntExtra(IntentKeys.LAYOUT_TYPE, LayoutDBModel.ACTIVITY_LAYOUT);
		layoutTypeSelector.setSelection(layoutType - 1);
		
		layoutTypeSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				layoutType = position + 1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(requestCode == 0 && resultCode == TemplateActivity.REQUEST_CODE)
		{
			templateType = data.getIntExtra(IntentKeys.TEMPLATE_TYPE, TemplateLayout.RELATIVE_BLANK_TEMPLATE);
			switch (templateType)
			{
				case TemplateLayout.LINEAR_BLANK_TEMPLATE:
					templateName.setText("LinearBlank");
					break;

				case TemplateLayout.RELATIVE_BLANK_TEMPLATE:
					templateName.setText("RelativeBlank");
					break;

				default:
					break;
			}
			
		}
	}

	@Override
	public void onClick(View v)
	{
		Intent intent = new Intent();
		switch (v.getId())
		{
			case R.id.select_template_btn:
				JCodeHelper.outputActivityCode("test", null);
//				intent.setClass(this, TemplateActivity.class);
//				startActivityForResult(intent, 0);
				break;
				
			case R.id.create_layout_btn:
				if(TextUtils.isEmpty(layoutName.getText()))
				{
					Toast.makeText(this, "please input the layout's name", Toast.LENGTH_SHORT).show();
				}
				else
				{
					LayoutDBModel layoutDBModel = new LayoutDBModel();
					layoutDBModel.setLayoutName(layoutName.getText().toString());
					layoutDBModel.setLayoutType(layoutType);
					LayoutDBManager.saveLayout(CreateLayoutActivity.this, layoutDBModel);
					
					intent.setClass(this, MainActivity.class);
					intent.putExtra(IntentKeys.TEMPLATE_TYPE, templateType);
					intent.putExtra(IntentKeys.LAYOUT_NAME, layoutName.getText().toString());
					startActivity(intent);
					this.finish();
				}
				break;

			default:
				break;
		}
		
	}
	
}
