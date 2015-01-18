package com.tiger.layoutide.ide.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.ui.template.LinearBlankTemplate;
import com.tiger.layoutide.ide.ui.template.RelativeBlankTemplate;
import com.tiger.layoutide.ide.ui.template.TemplateLayout;

public class TemplateActivity extends Activity implements OnClickListener
{
	public static final int REQUEST_CODE = 1;
	
	@ViewById(id = R.id.linear_blank_template)
	private LinearBlankTemplate linearBlankTemplate;
	
	@ViewById(id = R.id.relative_blank_template)
	private RelativeBlankTemplate relativeBlankTemplate;
	
	private int selectedTemplateType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.template_select);
		
		ViewInjector.initInjectedView(this, this);
		
		linearBlankTemplate.setOnClickListener(this);
		relativeBlankTemplate.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.linear_blank_template:
				selectedTemplateType = TemplateLayout.LINEAR_BLANK_TEMPLATE;
				break;
				
			case R.id.relative_blank_template:
				selectedTemplateType = TemplateLayout.RELATIVE_BLANK_TEMPLATE;
				break;

			default:
				break;
		}
		//������һ����
		Intent intent = new Intent();
		intent.putExtra(IntentKeys.TEMPLATE_TYPE, selectedTemplateType);
		
		setResult(REQUEST_CODE, intent);
		this.finish();
	}
	
	
}
