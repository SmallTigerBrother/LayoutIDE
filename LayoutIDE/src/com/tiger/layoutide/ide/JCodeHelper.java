package com.tiger.layoutide.ide;

import com.mn.tiger.annonation.ViewById;
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
		StringBuilder jcodeStr = new StringBuilder();
		View view = null;
		for (int i = 0; i < viewGroup.getChildCount(); i++)
		{
			view = viewGroup.getChildAt(i);
			if(!TextUtils.isEmpty(((IView)view).getIdName()))
			{
				//添加当前View的声明
				jcodeStr.append(getInjectViewByIdJCode(((IView)view).getClassSimpleName() , ((IView)view).getIdName()));
			}
			
			if(view instanceof ViewGroup)
			{
				jcodeStr.append(outputInjectViewById((ViewGroup)view));
			}
		}
		
		return jcodeStr.toString();
	}
	
	private static String getInjectViewByIdJCode(String clazzName, String idName)
	{
		StringBuilder jcodeStr = new StringBuilder();
		jcodeStr.append(String.format(Constant.JCODE_INJECT_VIEW_BY_ID_ANNONATION, idName) + "\n");
		jcodeStr.append(String.format(Constant.JCODE_INJECT_VIEW_BY_ID_DECLARE, clazzName, idName) + "\n\n");
		return jcodeStr.toString();
	}
}
