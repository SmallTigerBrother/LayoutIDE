package com.tiger.layoutide.tree;


public interface IView
{
	String getClassSimpleName();
	
	void setIdName(String idName);
	
	String getIdName();
	
	void setText(CharSequence text);
	
	CharSequence getText();
	
	void setTextSize(float textSize);
	
	float getTextSize();
	
	void setTextColor(int textColor);
	
	int getTextColor();
	
	void setLayoutWidth(String layoutWidth);
	
	String getLayoutWidth();
	
	void setLayoutHeight(String layoutHeight);
	
	String getLayoutHeight();
	
	void setLayoutWeight(float weight);
	
	float getLayoutWeight();
	
	void setLayoutMarginLeft(int marginLeft);
	
	int getLayoutMarginLeft();
	
	int getLayoutMarginRight();
	
	int getLayoutMarginTop();
	
	int getLayoutMarginBottom();
	
	int getBackgroundColor();
	
	String getGravityStringValue();
	
	String getLayoutGravityStringValue();
}
