package com.tiger.layoutide.widget.tree;

import java.util.List;

public interface IViewTree extends IViewTreeNode
{
	void addViewTreeNode(IViewTreeNode viewTreeNode);
	
	void removeViewTreeNode(IViewTreeNode viewTreeNode);
	
	void clearViewTreeNode();
	
	List<IViewTreeNode> getViewTreeNodes();
	
	String getXMLString();
	
	void dump();
}
