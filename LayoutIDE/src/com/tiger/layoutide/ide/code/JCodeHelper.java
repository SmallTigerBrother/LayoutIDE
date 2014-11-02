package com.tiger.layoutide.ide.code;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mn.tiger.annonation.ViewById;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JAnnonation.ParamKeyValue;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JField;
import com.tiger.code.model.JPackage;
import com.tiger.code.model.constant.JActionScope;
import com.tiger.layoutide.R;
import com.tiger.layoutide.widget.IView;

public class JCodeHelper
{
	@ViewById(id = R.id.layout_width_editor)
	private EditText layoutWidthEditText;
	
	public static String outputInjectViewById(ViewGroup viewGroup)
	{
		JClass jClass = new JClass(null, JActionScope.DEFAULT, "ViewById", null);
		jClass.addFields(getInjectViewFields(viewGroup));
		return jClass.toString();
	}
	
	private static List<JField> getInjectViewFields(View view)
	{
		List<JField> fields = new ArrayList<JField>();
		
		if(!TextUtils.isEmpty(((IView)view).getIdName()))
		{
			JPackage jPackage = new JPackage(((IView)view).getPackageName());
			JClass valueType = new JClass(jPackage, JActionScope.DEFAULT, 
					((IView)view).getSimpleClassName(), null);
			JField jField = new JField(JActionScope.PRIVATE, valueType, getViewName(
					((IView)view).getIdName()));
			JAnnonation jAnnonation = new JAnnonation(ViewById.class.getSimpleName());
			jAnnonation.addKeyValue(new ParamKeyValue("id", "R.id." + ((IView)view).getIdName()));
			jField.addAnnonation(jAnnonation);
			
			fields.add(jField);
		}
		
		if(view instanceof ViewGroup)
		{
			for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
			{
				View childView = ((ViewGroup)view).getChildAt(i);
				if(!TextUtils.isEmpty(((IView)childView).getIdName()))
				{
					fields.addAll(getInjectViewFields(childView));
				}
			}
		}
		
		return fields;
	}
	
	@SuppressLint("DefaultLocale")
	private static String getViewName(String idName)
	{
		String[] strings = idName.split("_");
		String viewName = strings[0];
		for(int i = 1; i < strings.length; i++)
		{
			viewName = viewName + strings[i].substring(0, 1).toUpperCase() + strings[i].substring(1); 
		}
		return viewName;
	}
}
