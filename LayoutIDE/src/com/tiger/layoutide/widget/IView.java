package com.tiger.layoutide.widget;


public interface IView
{
	String getClassSimpleName();
	
	void setIdName(String idName);
	
	String getIdName();
	
	void setText(CharSequence text);
	
	CharSequence getText();
	
	void setTextSize(String textSize);
	
	float getTextSize();
	
	void setTextColor(String textColor);
	
	String getTextColor();
	
	void setLayoutWidth(String layoutWidth);
	
	String getLayoutWidth();
	
	void setLayoutHeight(String layoutHeight);
	
	String getLayoutHeight();
	
	void setLayoutWeight(String weight);
	
	float getLayoutWeight();
	
	void setLayoutMarginLeft(String marginLeft);
	
	void setLayoutMarginRight(String marginRight);

	void setLayoutMarginTop(String marginTop);
	
	void setLayoutMarginBottom(String marginBottom);
	
	int getLayoutMarginLeft();
	
	int getLayoutMarginRight();
	
	int getLayoutMarginTop();
	
	int getLayoutMarginBottom();
	
	String getBackgroundColor();
	
	void setBackgroundColor(String color);
	
	String getGravityStringValue();
	
	String getLayoutGravityStringValue();
}
