package in.silive.techtrishnabeta2;

import in.silive.techtrishnabeta2.adapter.ExpListAdapter;
import in.silive.techtrishnabeta2.control.Control;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class EventFragment extends Fragment {
	private String[] categories;
	private String[][] categoryItems;
	private ExpandableListView expandableListView;
	private ExpandableListAdapter listAdpater;
	private int lastExpandedGroup;

	public EventFragment() {
		this.lastExpandedGroup = -1;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//set the title of the fragment
		getActivity().setTitle("EVENT");
		Control control = new Control(getActivity());
		categories = control.getCategory(R.array.eventCategory);
		categoryItems = control.getCategoryItems(R.array.csEvents,
				R.array.ecEvents, R.array.onEvents, R.array.rbEvents);
		View rootView = inflater.inflate(R.layout.event_layout1, container,
				false);
		// get the expandable list view
		expandableListView = (ExpandableListView) rootView
				.findViewById(R.id.eventListExp);
		listAdpater = new ExpListAdapter(categories, categoryItems,
				getActivity());
		expandableListView.setAdapter(listAdpater);
		expandableListView.setGroupIndicator(null);
		expandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {

					@Override
					public void onGroupExpand(int groupPosition) {
						if (groupPosition != lastExpandedGroup
								&& lastExpandedGroup != -1) {
							expandableListView.collapseGroup(lastExpandedGroup);
						}
						lastExpandedGroup = groupPosition;
					}
				});
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Fragment fragment = new EventDetails();
				Bundle b = new Bundle();
				b.putString("eventName",
						categoryItems[groupPosition][childPosition]);
				fragment.setArguments(b);
				FragmentManager fragmentManager = getActivity()
						.getSupportFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
				return false;
			}
		});
		return rootView;
	}

}