package com.tiger.layoutide.ide.code;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mn.tiger.annonation.ViewById;
import com.tiger.code.model.constant.JActionScope;
import com.tiger.code.model.model.JAnnonation;
import com.tiger.code.model.model.JAnnonation.ParamKeyValue;
import com.tiger.code.model.model.JClass;
import com.tiger.code.model.model.JField;
import com.tiger.code.model.model.JInterface;
import com.tiger.code.model.model.JMethod;
import com.tiger.code.model.model.JMethod.Parameter;
import com.tiger.code.model.model.JPackage;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.code.library.interfaces.AndroidClass;
import com.tiger.layoutide.ide.code.library.interfaces.AndroidInterface;
import com.tiger.layoutide.ide.code.library.interfaces.ClassDictionary;
import com.tiger.layoutide.ide.code.library.interfaces.InterfaceDictionary;
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
				fields.addAll(getInjectViewFields(childView));
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
	
	public static String outputActivityCode(String activityName, ViewGroup viewGroup)
	{
		JClass superClaszz = ClassDictionary.getClass(AndroidClass.Activity);
		JClass jClazz = new JClass(null, JActionScope.PUBLIC, activityName, superClaszz);
		
//		jClazz.addFields(getInjectViewFields(viewGroup));
		jClazz.addMethods(getMethods(viewGroup));
		
		JInterface jInterface = InterfaceDictionary.getInterface(
				AndroidInterface.OnClickListener4View);
		jClazz.implementInterface(jInterface);
		
		Log.d("outputActivityCode", jClazz.toString());
		return jClazz.toString();
	}
	
	private static List<JMethod> getMethods(ViewGroup viewGroup)
	{
		List<JMethod> methods = new ArrayList<JMethod>();
		
		Parameter parameter = new Parameter("savedInstanceState", 
				ClassDictionary.getClass(AndroidClass.Bundle));
		JMethod method = new JMethod(JActionScope.PROTECTED, "onCreate", parameter);
		method.addAnnonation(JAnnonation.createOverrideAnnonation());
		method.setOverrideSuper(true);
		methods.add(method);
		
		return methods;
	}
}
