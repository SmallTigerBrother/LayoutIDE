package com.tiger.layoutide.ide.code.activity;

import com.tiger.code.constant.JConstant;
import com.tiger.code.model.JClass;
import com.tiger.code.model.JCodeBlock;
import com.tiger.code.model.JField;
import com.tiger.code.model.JMethod;
import com.tiger.layoutide.ide.code.library.AndroidInterface;
import com.tiger.layoutide.ide.code.library.CustomInterfaces;
import com.tiger.layoutide.ide.code.library.CustomViews;
import com.tiger.layoutide.ide.code.library.InterfaceFactory;
import com.tiger.layoutide.ide.code.library.JActivity;

public class XListViewActivityBuilder extends JActivityBuilder
{
	private boolean enablePullRefresh = true;
	
	private boolean enablePullLoad = false;
	
	public XListViewActivityBuilder(String packageName, String simpleClazzName)
	{
		super(packageName, simpleClazzName);
	}
	
	public void setPullRefreshEnable(boolean enable) 
	{
		enablePullRefresh = enable;
	}
	
	public void setPullLoadEnable(boolean enable)
	{
		enablePullLoad = enable;
	}
	@Override
	public void buildOnCreate(JActivity activity, JCodeBlock onCreateBlock, String layoutName)
	{
		super.buildOnCreate(activity, onCreateBlock, layoutName);
		
		activity.implementInterface(CustomInterfaces.newIXListViewListener());
		
		activity.implementInterface(InterfaceFactory.createInterface(AndroidInterface.OnItemClickListener));
	}
	
	@Override
	public void buildSetupViews(JActivity activity, JCodeBlock setupViewsBlock)
	{
		super.buildSetupViews(activity, setupViewsBlock);
		
		JField xListViewField = activity.findFieldsByType(CustomViews.newXListView()).get(0);
		if(enablePullRefresh)
		{
			setupViewsBlock.addCode(xListViewField.getFiledName() + JConstant.POINT + 
					"setPullRefreshEnable(true);");
		}
		else
		{
			setupViewsBlock.addCode(xListViewField.getFiledName() + JConstant.POINT + 
					"setPullRefreshEnable(false);");
		}
		
		if(enablePullLoad)
		{
			setupViewsBlock.addCode(xListViewField.getFiledName() + JConstant.POINT + 
					"setPullLoadEnable(true);");
		}
		else
		{
			setupViewsBlock.addCode(xListViewField.getFiledName() + JConstant.POINT + 
					"setPullLoadEnable(fasle);");
		}
		
		setupViewsBlock.addCode(xListViewField.getFiledName() + JConstant.POINT + 
				"setXListViewListener(this);");
		
		setupViewsBlock.addCode(xListViewField.getFiledName() + JConstant.POINT + 
				"setOnItemClickListener(this);");
		//TODO …Ë÷√Adapter
	}
	
	@Override
	public JClass buildSuperClass()
	{
		return null;
	}

	@Override
	public JMethod buildOnRequestSuccess(JActivity activity)
	{
		JMethod requestListData = new JMethod("requestListData");
		activity.addMethod(requestListData);
		
		return null;
	}
	
	
}
