package com.tiger.layoutide.ide;

import java.util.ArrayList;
import java.util.List;

import com.mn.tiger.annonation.ViewById;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JAnnonation.ParamKeyValue;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JField;
import com.tiger.code.model.JStaticFinalField;
import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.primary.JInteger;
import com.tiger.layoutide.R;
import com.tiger.layoutide.utils.Constant;
import com.tiger.layoutide.widget.IView;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class JCodeHelper
{
	@ViewById(id = R.id.layout_width_editor)
	private EditText layoutWidthEditText;
	
	public static String outputInjectViewById(ViewGroup viewGroup)
	{
		JClass jClass = new JClass(null, JActionScope.DEFAULT, "ViewById", null);
		View view = null;
		for (int i = 0; i < viewGroup.getChildCount(); i++)
		{
			view = viewGroup.getChildAt(i);
			if(!TextUtils.isEmpty(((IView)view).getIdName()))
			{
				jClass.addField(getInjectViewById(((IView)view).getClassSimpleName(),
						((IView)view).getIdName()));
			}
			
			if(view instanceof ViewGroup)
			{
				jcodeStr.append(outputInjectViewById((ViewGroup)view));
			}
		}
		
		return jClass.toString();
	}
	
	private static List<JField> getInjectViewFields(View view)
	{
		List<JField> fields = new ArrayList<JField>();
		
		if(!TextUtils.isEmpty(((IView)view).getIdName()))
		{
			JClass valueType = new JClass(null, JActionScope.DEFAULT, 
					((IView)view).getClassSimpleName(), null);
			JField jField = new JField(JActionScope.PRIVATE, valueType, getViewName(
					((IView)view).getIdName()));
			JAnnonation jAnnonation = new JAnnonation(ViewById.class.getSimpleName());
			jAnnonation.addKeyValue(new ParamKeyValue("id", "R.id." + ((IView)view).getIdName()));
			jField.addAnnonation(jAnnonation);
			
			fields.add(jField);
		}
		
		if(view instanceof ViewGroup)
		{
			fields.addAll(getInjectViewFields(view));
		}
		
		for (int i = 0; i < viewGroup.getChildCount(); i++)
		{
			view = viewGroup.getChildAt(i);
			if(!TextUtils.isEmpty(((IView)view).getIdName()))
			{
				jClass.addField(getInjectViewById(((IView)view).getClassSimpleName(),
						((IView)view).getIdName()));
			}
			
			if(view instanceof ViewGroup)
			{
				jcodeStr.append(outputInjectViewById((ViewGroup)view));
			}
		}
		
		return fields;
	}
	
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
