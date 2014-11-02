package com.tiger.layoutide.ide.tool;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mn.tiger.annonation.ViewById;
import com.mn.tiger.utility.ViewInjector;
import com.tiger.layoutide.R;
import com.tiger.layoutide.utils.XmlOutputConstant;
import com.tiger.layoutide.utils.RelativeValues;
import com.tiger.layoutide.widget.IView;
import com.tiger.layoutide.widget.TGRelativeLayout;

public class RelativiePositionToolBar extends FrameLayout
{
	@ViewById(id = R.id.align_parent_selector)
	private Spinner alignParentSelector;
	
	@ViewById(id = R.id.align_left_selector)
	private Spinner alignLeftSelector;
	
	@ViewById(id = R.id.align_right_selector)
	private Spinner alignRightSelector;
	
	@ViewById(id = R.id.align_top_selector)
	private Spinner alignTopSelector;
	
	@ViewById(id = R.id.align_bottom_selector)
	private Spinner alignBottomSelector;
	
	@ViewById(id = R.id.below_selector)
	private Spinner belowSelector;
	
	@ViewById(id = R.id.above_selector)
	private Spinner aboveSelector;
	
	@ViewById(id = R.id.toleftof_selector)
	private Spinner toLeftOfSelector;
	
	@ViewById(id = R.id.torightof_selector)
	private Spinner toRightOfSelector;
	
	@ViewById(id = R.id.center_where_selector)
	private Spinner centerWhereSelector;
	
	private IView selectedView = null;
	
	public RelativiePositionToolBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}
	
	private void init()
	{
		inflate(getContext(),R.layout.relativie_position_tool_bar , this);
		ViewInjector.initInjectedView(this, this);
		
		alignParentSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					setAlignParentValue(selectedView, parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		centerWhereSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					setRelaviteCenterValue(selectedView, parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		belowSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setBelow(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		aboveSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAbove(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		toLeftOfSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setToLeftOf(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		toRightOfSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setToRightOf(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignLeftSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignLeft(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignRightSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignRight(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignTopSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignTop(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
		
		alignBottomSelector.setOnItemSelectedListener(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(null != selectedView)
				{
					selectedView.setAlignBottom(parent.getAdapter().getItem(position).toString());
				}
				else
				{
					Toast.makeText(getContext(), "Please select one View before edit the property", Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
			}
		});
	}
	
	private void setAlignParentValue(IView selectedView, String curValue)
	{
		if(RelativeValues.ALIGN_PARENT_NONE.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.FALSE);
			selectedView.setAlignParentBottom(XmlOutputConstant.FALSE);
			selectedView.setAlignParentTop(XmlOutputConstant.FALSE);
			selectedView.setAlignParentRight(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.ALIGN_PARENT_LEFT.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.TRUE);
			selectedView.setAlignParentBottom(XmlOutputConstant.FALSE);
			selectedView.setAlignParentTop(XmlOutputConstant.FALSE);
			selectedView.setAlignParentRight(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.ALIGN_PARENT_RIGHT.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.FALSE);
			selectedView.setAlignParentBottom(XmlOutputConstant.FALSE);
			selectedView.setAlignParentTop(XmlOutputConstant.FALSE);
			selectedView.setAlignParentRight(XmlOutputConstant.TRUE);
		}
		else if(RelativeValues.ALIGN_PARENT_TOP.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.FALSE);
			selectedView.setAlignParentBottom(XmlOutputConstant.FALSE);
			selectedView.setAlignParentTop(XmlOutputConstant.TRUE);
			selectedView.setAlignParentRight(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.ALIGN_PARENT_BOTTOM.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.FALSE);
			selectedView.setAlignParentBottom(XmlOutputConstant.TRUE);
			selectedView.setAlignParentTop(XmlOutputConstant.FALSE);
			selectedView.setAlignParentRight(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.ALIGN_PARENT_LEFT_AND_TOP.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.TRUE);
			selectedView.setAlignParentBottom(XmlOutputConstant.FALSE);
			selectedView.setAlignParentTop(XmlOutputConstant.TRUE);
			selectedView.setAlignParentRight(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.ALIGN_PARENT_LEFT_AND_BOTTOM.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.TRUE);
			selectedView.setAlignParentBottom(XmlOutputConstant.TRUE);
			selectedView.setAlignParentTop(XmlOutputConstant.FALSE);
			selectedView.setAlignParentRight(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.ALIGN_PARENT_RIGHT_AND_TOP.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.FALSE);
			selectedView.setAlignParentBottom(XmlOutputConstant.FALSE);
			selectedView.setAlignParentTop(XmlOutputConstant.TRUE);
			selectedView.setAlignParentRight(XmlOutputConstant.TRUE);
		}
		else if(RelativeValues.ALIGN_PARENT_RIGHT_AND_BOTTOM.equals(curValue))
		{
			selectedView.setAlignParentLeft(XmlOutputConstant.FALSE);
			selectedView.setAlignParentBottom(XmlOutputConstant.TRUE);
			selectedView.setAlignParentTop(XmlOutputConstant.FALSE);
			selectedView.setAlignParentRight(XmlOutputConstant.TRUE);
		}
	}
	
	private void setRelaviteCenterValue(IView selectedView, String curValue)
	{
		if(RelativeValues.CENTER_NONE.equals(curValue))
		{
			selectedView.setCenterInParent(XmlOutputConstant.FALSE);
			selectedView.setCenterHorizontal(XmlOutputConstant.FALSE);
			selectedView.setCenterVertical(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.CENTER_IN_PARENT.equals(curValue))
		{
			selectedView.setCenterInParent(XmlOutputConstant.TRUE);
			selectedView.setCenterHorizontal(XmlOutputConstant.FALSE);
			selectedView.setCenterVertical(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.CENTER_HORIZONTAL.equals(curValue))
		{
			selectedView.setCenterInParent(XmlOutputConstant.FALSE);
			selectedView.setCenterHorizontal(XmlOutputConstant.TRUE);
			selectedView.setCenterVertical(XmlOutputConstant.FALSE);
		}
		else if(RelativeValues.CENTER_VERTICAL.equals(curValue))
		{
			selectedView.setCenterInParent(XmlOutputConstant.FALSE);
			selectedView.setCenterHorizontal(XmlOutputConstant.FALSE);
			selectedView.setCenterVertical(XmlOutputConstant.TRUE);
		}
	}
	
	public void resetRelativePosition(final IView selectedView)
	{
		this.selectedView = selectedView;
		
		if(null != selectedView)
		{
			resetAlignParentSelector();
			
			resetCenterWhereSelector();
			
			ArrayList<CharSequence> parentChildIdList = ((TGRelativeLayout)((View)selectedView).getParent()).getChildIdList();
			//ÒÆ³ý×ÔÉíµÄid
			if(null != selectedView.getIdName())
			{
				parentChildIdList.remove(selectedView.getIdName());
			}
			CharSequence[] parentChildIds = new CharSequence[parentChildIdList.size()];
			parentChildIdList.toArray(parentChildIds);
			
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(),
					android.R.layout.simple_spinner_item, parentChildIds);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			
			belowSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getBelow()))
			{
				belowSelector.setSelection(parentChildIdList.indexOf(selectedView.getBelow()));
			}
			else
			{
				belowSelector.setSelection(0);
			}
			
			aboveSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getAbove()))
			{
				aboveSelector.setSelection(parentChildIdList.indexOf(selectedView.getAbove()));
			}
			else
			{
				aboveSelector.setSelection(0);
			}
			
			toLeftOfSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getToLeftOf()))
			{
				toLeftOfSelector.setSelection(parentChildIdList.indexOf(selectedView.getToLeftOf()));
			}
			else
			{
				toLeftOfSelector.setSelection(0);
			}
			
			toRightOfSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getToRightOf()))
			{
				toRightOfSelector.setSelection(parentChildIdList.indexOf(selectedView.getToRightOf()));
			}
			else
			{
				toRightOfSelector.setSelection(0);
			}
			
			alignLeftSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getAlignLeft()))
			{
				alignLeftSelector.setSelection(parentChildIdList.indexOf(selectedView.getAlignLeft()));
			}
			else
			{
				alignLeftSelector.setSelection(0);
			}
			
			alignRightSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getAlignRight()))
			{
				alignRightSelector.setSelection(parentChildIdList.indexOf(selectedView.getAlignRight()));
			}
			else
			{
				alignRightSelector.setSelection(0);
			}
			
			alignTopSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getAlignTop()))
			{
				alignTopSelector.setSelection(parentChildIdList.indexOf(selectedView.getAlignTop()));
			}
			else
			{
				alignTopSelector.setSelection(0);
			}
			
			alignBottomSelector.setAdapter(adapter);
			if(!TextUtils.isEmpty(selectedView.getAlignBottom()))
			{
				alignBottomSelector.setSelection(parentChildIdList.indexOf(selectedView.getAlignBottom()));
			}
			else
			{
				alignBottomSelector.setSelection(0);
			}
		}
	}
	
	private void resetAlignParentSelector()
	{
		if(TextUtils.isEmpty(selectedView.getAlignParentLeft()) && TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				TextUtils.isEmpty(selectedView.getAlignParentTop()) && TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(0);
		}
		else if(!TextUtils.isEmpty(selectedView.getAlignParentLeft()) && TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				TextUtils.isEmpty(selectedView.getAlignParentTop()) && TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(1);
		}
		else if(TextUtils.isEmpty(selectedView.getAlignParentLeft()) && !TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				TextUtils.isEmpty(selectedView.getAlignParentTop()) && TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(2);
		}
		else if(TextUtils.isEmpty(selectedView.getAlignParentLeft()) && TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				!TextUtils.isEmpty(selectedView.getAlignParentTop()) && TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(3);
		}
		else if(TextUtils.isEmpty(selectedView.getAlignParentLeft()) && TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				TextUtils.isEmpty(selectedView.getAlignParentTop()) && !TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(4);
		}
		else if(!TextUtils.isEmpty(selectedView.getAlignParentLeft()) && TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				!TextUtils.isEmpty(selectedView.getAlignParentTop()) && TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(6);
		}
		else if(!TextUtils.isEmpty(selectedView.getAlignParentLeft()) && TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				TextUtils.isEmpty(selectedView.getAlignParentTop()) && !TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(7);
		}
		else if(TextUtils.isEmpty(selectedView.getAlignParentLeft()) && !TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				!TextUtils.isEmpty(selectedView.getAlignParentTop()) && TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(7);
		}
		else if(TextUtils.isEmpty(selectedView.getAlignParentLeft()) && !TextUtils.isEmpty(selectedView.getAlignParentRight()) &&
				TextUtils.isEmpty(selectedView.getAlignParentTop()) && !TextUtils.isEmpty(selectedView.getAlignParentBottom()))
		{
			alignParentSelector.setSelection(8);
		}
	}
	
	private void resetCenterWhereSelector()
	{
		if(TextUtils.isEmpty(selectedView.getCenterInParent()) && TextUtils.isEmpty(selectedView.getCenterHorizontal()) &&
				TextUtils.isEmpty(selectedView.getCenterVertical()))
		{
			centerWhereSelector.setSelection(0);
		}
		else if(!TextUtils.isEmpty(selectedView.getCenterInParent()) && TextUtils.isEmpty(selectedView.getCenterHorizontal()) &&
				TextUtils.isEmpty(selectedView.getCenterVertical()))
		{
			centerWhereSelector.setSelection(1);
		}
		else if(TextUtils.isEmpty(selectedView.getCenterInParent()) && TextUtils.isEmpty(selectedView.getCenterHorizontal()) &&
				!TextUtils.isEmpty(selectedView.getCenterVertical()))
		{
			centerWhereSelector.setSelection(2);
		}
		else if(TextUtils.isEmpty(selectedView.getCenterInParent()) && !TextUtils.isEmpty(selectedView.getCenterHorizontal()) &&
				TextUtils.isEmpty(selectedView.getCenterVertical()))
		{
			centerWhereSelector.setSelection(3);
		}
	}
}
