package in.silive.techtrishnabeta2.adapter;

import in.silive.techtrishnabeta2.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpListAdapter extends BaseExpandableListAdapter {

	// private final LayoutInflater mInflater;
	private Context context;
	private String[] groups;
	private String[][] children;
	
	public ExpListAdapter(String[] groups, String[][] children,
			Context context) {
		this.groups = groups;
		this.children = children;
		this.context = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return children[groupPosition][childPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPositon) {
		return childPositon;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastCild, View convertView, ViewGroup parent) {
		final String childTxt = (String) getChild(groupPosition, childPosition);
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.event_list_item, null);
		}
		TextView listItemTxt = (TextView) convertView
				.findViewById(R.id.eventListItem);
		listItemTxt.setText(childTxt);
		return convertView;
	}

	// get the number of children
	@Override
	public int getChildrenCount(int position) {
		return children[position].length;
	}

	@Override
	public Object getGroup(int positon) {
		return groups[positon];
	}

	// get the number of groups
	@Override
	public int getGroupCount() {
		return groups.length;
	}

	@Override
	public long getGroupId(int arg0) {
		return arg0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String eventHeaderTxt = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) this.context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.event_list_group, null);
		}
		TextView eventHeader = (TextView) convertView
				.findViewById(R.id.eventListHeader);
		eventHeader.setText(eventHeaderTxt);
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		return true;
	}
	
}
