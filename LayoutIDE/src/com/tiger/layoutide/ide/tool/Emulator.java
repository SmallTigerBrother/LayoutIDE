package com.tiger.layoutide.ide.tool;

import android.content.Context;
import android.util.AttributeSet;

import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.JTGLinearLayout;

public class Emulator extends JTGLinearLayout
{
	private static Emulator emulator;
	
	private IView newViewModel;
	
	private IView curDragView;
	
	public Emulator(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		setRootViewGroup(true);
		emulator = this;
	}
	
	public static Emulator getSingleInstance()
	{
		return emulator;
	}

	@Override
	public String getXMLString()
	{
		return super.getXMLString();
	}
	
	public void setNewViewModel(IView view)
	{
		this.newViewModel = view;
	}
	
	public IView getNewViewModel()
	{
		return newViewModel;
	}

	public IView getCurDragView()
	{
		return curDragView;
	}

	public void setCurDragView(IView curDragView)
	{
		this.curDragView = curDragView;
	}
}
