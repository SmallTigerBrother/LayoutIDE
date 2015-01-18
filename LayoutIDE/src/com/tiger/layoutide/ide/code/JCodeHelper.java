package com.tiger.layoutide.ide.code;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.mn.tiger.annonation.ViewById;
import com.tiger.code.constant.JActionScope;
import com.tiger.code.model.JAnnonation;
import com.tiger.code.model.JAnnonation.ParamKeyValue;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JField;
import com.tiger.code.model.JGeneric;
import com.tiger.code.model.JGeneric.WildCardType;
import com.tiger.code.model.JInterface;
import com.tiger.code.model.JMethod;
import com.tiger.code.operator.JSwitch;
import com.tiger.layoutide.ide.code.library.AndroidClass;
import com.tiger.layoutide.ide.code.library.AndroidInterface;
import com.tiger.layoutide.ide.code.library.ClassFactory;
import com.tiger.layoutide.ide.code.library.CustomAdapters;
import com.tiger.layoutide.ide.code.library.InterfaceFactory;
import com.tiger.layoutide.ide.code.library.JActivity;
import com.tiger.layoutide.widget.AdapterViewHelper;
import com.tiger.layoutide.widget.IView;

public class JCodeHelper
{
	public static String outputInjectViewById(ViewGroup viewGroup)
	{
		JClass jClass = new JClass(null, "ViewById");
		getInjectViewFields(jClass, viewGroup);
		return jClass.toString();
	}
	
	private static void getInjectViewFields(JClass jClass, View mianView)
	{
		List<IView> views = getAllViews(mianView);
		
		IView view;
		JSwitch jSwitch = new JSwitch("view.getId()");
		for(int i = 0;i < views.size(); i++)
		{
			view = views.get(i);
			if(!TextUtils.isEmpty(view.getViewHelper().getIdName()))
			{
				JClass valueType = new JClass(view.getPackageName(), 
						view.getSimpleClassName());
				JField jField = new JField(JActionScope.PRIVATE, valueType, getViewName(
						view.getViewHelper().getIdName()));
				JAnnonation jAnnonation = new JAnnonation(ViewById.class.getSimpleName());
				jAnnonation.addKeyValue(new ParamKeyValue("id", "R.id." + 
						view.getViewHelper().getIdName()));
				jField.addAnnonation(jAnnonation);
				jClass.addField(jField);
				
				if(view.getViewHelper().isSetOnClickListener())
				{
					jSwitch.addCase("R.id." + view.getViewHelper().getIdName());
				}
				
				if(view.getViewHelper() instanceof AdapterViewHelper && 
						((AdapterViewHelper)view.getViewHelper()).isSetOnItemClickListener())
				{
					
				}
			}
			
			//TODO �����������Զ����¼�
		}
		
		if(jSwitch.getCaseValues().size() > 0)
		{
			JInterface jInterface = InterfaceFactory.createInterface(
					AndroidInterface.OnClickListener4View);
			
			JMethod onClickMethod = jInterface.getMethods().get(0);
			JCodeBlock codeBlock = new JCodeBlock();
			codeBlock.addCode(jSwitch);
			onClickMethod.setCodeBlock(codeBlock);
			
			jClass.implementInterface(jInterface);
		}
	}
	
	private static List<IView> getAllViews(View view)
	{
		List<IView> views = new ArrayList<IView>();
		
		if(view instanceof IView)
		{
			views.add((IView) view);
		}
		
		if(view instanceof ViewGroup)
		{
			for (int i = 0; i < ((ViewGroup)view).getChildCount(); i++)
			{
				View childView = ((ViewGroup)view).getChildAt(i);
				views.addAll(getAllViews(childView));
			}
		}
		
		return views;
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
		
		if(params.isRegisterEventBus())
		{
		}
				jActivity.implementInterfaces(params.getInterfaces());
				
//				jActivity.addFields(getInjectViewFields(viewGroup));
		
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
		
		JClass viewholder = new JClass(packageName, simpleClazzName);
		viewholder.setSuperClass(superClass);
		
		return viewholder;
	}
	
}
