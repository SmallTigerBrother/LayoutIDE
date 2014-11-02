package com.tiger.layoutide.widget;

import android.view.View;


public interface IView extends ISelectable
{
	String getPackageName();
	
	String getSimpleClassName();
	
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
	
	String getLayoutGravityValue();
	
	void setLayoutGravityValue(String gravity);
	
	/************************** Background *********************************/
	
	String getBackgroundColor();
	
	void setBackgroundColor(String color);
	
	/************************** Content *********************************/
	
	String getGravityValue();
	
	void setGravityValue(String gravity);
	
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
