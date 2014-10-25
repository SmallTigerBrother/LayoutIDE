package com.tiger.layoutide.tree;

import java.util.List;

public interface IViewTree
{
	void addViewTreeNode(IViewTreeNode viewTreeNode);
	
	void removeViewTreeNode(IViewTreeNode viewTreeNode);
	
	void clearViewTreeNode();
	
	List<IViewTreeNode> getViewTreeNodes();
	
	String getXMLString();
	
	void dump();
}
