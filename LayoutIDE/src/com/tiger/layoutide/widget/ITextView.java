package com.tiger.layoutide.widget;


public interface ITextView extends IView
{
	public String getTextColor();
	
	public void setTextColor(String color);
	
	public void setTextSize(String textSize);
	
	public float getTextSize();
	
	public CharSequence getText();
	
	public void setText(String text);
}
