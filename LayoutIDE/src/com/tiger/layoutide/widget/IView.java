package com.tiger.layoutide.widget;

import android.view.View;


public interface IView extends ISelectable
{
	String getClassSimpleName();
	
	void setIdName(String idName);
	
	String getIdName();
	
	/**************************** Common Position ***************************************/
	
	void setLayoutWidth(String layoutWidth);
	
	String getLayoutWidth();
	
	void setLayoutHeight(String layoutHeight);
	
	String getLayoutHeight();
	
	void setLayoutMarginLeft(String marginLeft);
	
	void setLayoutMarginRight(String marginRight);

	void setLayoutMarginTop(String marginTop);
	
	void setLayoutMarginBottom(String marginBottom);
	
	int getLayoutMarginLeft();
	
	int getLayoutMarginRight();
	
	int getLayoutMarginTop();
	
	int getLayoutMarginBottom();
	
	/************************** LinearLayout *********************************/
	
	void setLayoutWeight(String weight);
	
	float getLayoutWeight();
	
	void setOrientationValue(String orientation);
	
	String getOrientationValue();
	
	/************************** Background *********************************/
	
	String getBackgroundColor();
	
	void setBackgroundColor(String color);
	
	/************************** Content *********************************/
	
	void setText(CharSequence text);
	
	CharSequence getText();
	
	void setTextSize(String textSize);
	
	float getTextSize();
	
	void setTextColor(String textColor);
	
	String getTextColor();
	
	String getGravityValue();
	
	void setGravityValue(String gravity);
	
	String getLayoutGravityValue();
	
	void setLayoutGravityValue(String gravity);
	
	
	/*****************   RelativieLayout  ************************/
	void setAlignParentLeft(String value);
	
	String getAlignParentLeft();
	
	void setAlignParentRight(String value);
	
	String getAlignParentRight();
	
	void setAlignParentTop(String value);
	
	String getAlignParentTop();
	
	void setAlignParentBottom(String value);
	
	String getAlignParentBottom();
	
	void setToLeftOf(String anchorIdName);
	
	String getToLeftOf();
	
	void setToRightOf(String anchorIdName);
	
	String getToRightOf();
	
	void setBelow(String anchorIdName);
	
	String getBelow();
	
	void setAbove(String anchorIdName);
	
	String getAbove();
	
	void setAlignLeft(String anchorIdName);
	
	String getAlignLeft();
	
	void setAlignRight(String anchorIdName);
	
	String getAlignRight();
	
	void setAlignTop(String anchorIdName);
	
	String getAlignTop();
	
	void setAlignBottom(String anchorIdName);
	
	String getAlignBottom();
	
	void setCenterInParent(String value);
	
	String getCenterInParent();
	
	void setCenterVertical(String value);
	
	String getCenterVertical();
	
	void setCenterHorizontal(String value);
	
	String getCenterHorizontal();
	
	/***********************  Other ****************************/
	View newInstance();
}
