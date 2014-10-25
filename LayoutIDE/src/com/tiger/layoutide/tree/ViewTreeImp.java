package com.tiger.layoutide.tree;

import java.util.LinkedList;
import java.util.List;

import com.tiger.layoutide.widget.IViewGroup;

public class ViewTreeImp extends ViewTreeNodeImp implements IViewTree
{
	private LinkedList<IViewTreeNode> viewTreeNodes;
	
	private IViewGroup viewGroup;
	
	public ViewTreeImp(IViewGroup viewGroup)
	{
		super(viewGroup);
		
		viewTreeNodes = new LinkedList<IViewTreeNode>();
		
		this.viewGroup = viewGroup;
	}
	
	@Override
	public List<IViewTreeNode> getViewTreeNodes()
	{
		return getViewTreeNodes();
	}

	@Override
	public String getXMLString()
	{
		StringBuilder xmlOutPutStr = new StringBuilder("<" + viewGroup.getClassSimpleName() + "\n");
		
		if(viewGroup.isRootViewGroup())
		{
			//���namespace�ֶ�
			xmlOutPutStr.append("xmlns:android=\"http://schemas.android.com/apk/res/android\"" + "\n");
			xmlOutPutStr.append("xmlns:tools=\"http://schemas.android.com/tools\"" + "\n");
		}
		
		//���layout���������ֶ�
		xmlOutPutStr.append(getPropertiesString());
		
		xmlOutPutStr.append(">\n");
		
		//��������ͼ��XML
		for (int i = 0; i < viewTreeNodes.size(); i++)
		{
			xmlOutPutStr.append(viewTreeNodes.get(i).getXMLString());
		}
		
		xmlOutPutStr.append("</" + viewGroup.getClassSimpleName() + ">");
		
		return xmlOutPutStr.toString();
	}

	@Override
	public void dump()
	{
		
	}

	@Override
	public void addViewTreeNode(IViewTreeNode viewTreeNode)
	{
		viewTreeNodes.addLast(viewTreeNode);
	}
	
	public void removeViewTreeNode(IViewTreeNode viewTreeNode)
	{
		viewTreeNodes.remove(viewTreeNode);
	}

	@Override
	public void clearViewTreeNode()
	{
		viewTreeNodes.clear();
	}

}
