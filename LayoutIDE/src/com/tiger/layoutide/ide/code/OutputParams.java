package com.tiger.layoutide.ide.code;

import java.util.ArrayList;
import java.util.List;

import com.tiger.code.model.JInterface;
import com.tiger.layoutide.ide.code.library.AndroidInterface;
import com.tiger.layoutide.ide.code.library.InterfaceFactory;

public class OutputParams
{
	private String layoutName;
	
	private List<JInterface> interfaces;
	
	private boolean registerEventBus;

	public OutputParams()
	{
		interfaces = new ArrayList<JInterface>();
	}
	
	public void addInterface(AndroidInterface interface1)
	{
		JInterface realInterface = InterfaceFactory.createInterface(interface1);
		interfaces.add(realInterface);
	}
	
	public List<JInterface> getInterfaces()
	{
		return interfaces;
	}

	public String getLayoutName()
	{
		return layoutName;
	}

	public void setLayoutName(String layoutName)
	{
		this.layoutName = layoutName;
	}

	public boolean isRegisterEventBus()
	{
		return registerEventBus;
	}

	public void setRegisterEventBus(boolean registerEventBus)
	{
		this.registerEventBus = registerEventBus;
	}
}
