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
import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JAnnonation.ParamKeyValue;
import com.tiger.code.model.JGeneric.WildCardType;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JField;
import com.tiger.code.model.JGeneric;
import com.tiger.code.model.JInterface;
import com.tiger.code.model.JPackage;
import com.tiger.layoutide.R;
import com.tiger.layoutide.ide.code.library.AndroidClass;
import com.tiger.layoutide.ide.code.library.AndroidInterface;
import com.tiger.layoutide.ide.code.library.ClassFactory;
import com.tiger.layoutide.ide.code.library.CustomAdapters;
import com.tiger.layoutide.ide.code.library.InterfaceFactory;
import com.tiger.layoutide.ide.code.library.JActivity;
import com.tiger.layoutide.widget.IView;

public class JCodeHelper
{
	@ViewById(id = R.id.layout_width_editor)
	private EditText layoutWidthEditText;
	
	public static String outputInjectViewById(ViewGroup viewGroup)
	{
		JClass jClass = new JClass(null, "ViewById");
		jClass.addFields(getInjectViewFields(viewGroup));
		return jClass.toString();
	}
	
	private static List<JField> getInjectViewFields(View view)
	{
		List<JField> fields = new ArrayList<JField>();
		
		if(!TextUtils.isEmpty(((IView)view).getIdName()))
		{
			JPackage jPackage = new JPackage(((IView)view).getPackageName());
			JClass valueType = new JClass(jPackage, ((IView)view).getSimpleClassName());
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
		JClass superClaszz = ClassFactory.getClass(AndroidClass.Activity);
		JClass jClazz = new JClass(null, activityName);
		jClazz.setSuperClass(superClaszz);
		
//		jClazz.addFields(getInjectViewFields(viewGroup));
		
		JInterface jInterface = InterfaceFactory.createInterface(
				AndroidInterface.OnClickListener4View);
		
		jClazz.implementInterface(jInterface);
		
		Log.d("outputActivityCode", jClazz.toString());
		return jClazz.toString();
	}
	
	public static JActivity outputActivityCode(String activityName, ViewGroup viewGroup,
			OutputParams params)
	{
		JActivity jActivity = new JActivity(null, activityName);
		
		//实现接口
		jActivity.implementInterfaces(params.getInterfaces());
		
		//添加所有 注入的View声明
		jActivity.addFields(getInjectViewFields(viewGroup));
		
		//setContentView
		if(!TextUtils.isEmpty(params.getLayoutName()))
		{
			JCodeBlock onCreateCodeBlock = jActivity.getOnCreateMethod().getCodeBlock();
			//添加setContentView方法
			onCreateCodeBlock.addCode("setContentView(R.layout." + params.getLayoutName() +");");
		}
		
		//注册事件总线
		if(params.isRegisterEventBus())
		{
			//注册事件总线
			JCodeBlock onCreateCodeBlock = jActivity.getOnCreateMethod().getCodeBlock();
			onCreateCodeBlock.addCode("QuizUpApplication.getBus().register(this);");
			
			//取消注册事件总线
			JCodeBlock onDestroyCodeBlock = jActivity.getOnDestroyMethod().getCodeBlock();
			onDestroyCodeBlock.addCode("QuizUpApplication.getBus().unregister(this);");
		}
		
		return jActivity;
	}
	
	public JClass createNewViewHolder(String packageName, String simpleClazzName, String requestType)
	{
		JClass superClass = CustomAdapters.newQuizUpViewHolder();
		JGeneric generic = new JGeneric();
		WildCardType wildCardType = new WildCardType();
		wildCardType.setName(requestType);
		generic.addWildCard(wildCardType);
		
		superClass.setGeneric(generic);
		
		JClass viewholder = new JClass(new JPackage(packageName), simpleClazzName);
		viewholder.setSuperClass(superClass);
		
		return viewholder;
	}
	
}
