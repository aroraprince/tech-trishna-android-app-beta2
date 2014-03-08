package in.silive.techtrishnabeta2;

import in.silive.techtrishnabeta2.adapter.ScheduleGridAdapter;
import in.silive.techtrishnabeta2.control.Control;
import in.silive.techtrishnabeta2.model.ListItemClass;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ScheduleSlideFragment extends Fragment {

	private int mPageNumber;
	// private String txt;
	// private TextView txtView;

	private ArrayList<ListItemClass> scheduleGridItems;
	private ScheduleGridAdapter sAdapter;
	private GridView mGridView;

	public static ScheduleSlideFragment create(int position) {
		ScheduleSlideFragment frag = new ScheduleSlideFragment();
		Bundle args = new Bundle();
		args.putInt("DAY", position);
		frag.setArguments(args);
		return frag;
	}

	public ScheduleSlideFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// mPageNumber = getArguments().getInt("DAY");
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.schedule_day_layout, container, false);
		Control control = new Control(getActivity());
		scheduleGridItems = new ArrayList<ListItemClass>();
		scheduleGridItems = control.getList(R.array.schedule_events,
				R.array.timings, R.array.eventImages);
		// if (mPageNumber == 0) {
		// txt = getResources().getString(R.string.random1);
		// } else {
		// txt = getResources().getString(R.string.random2);
		// }
		// txtView = (TextView) rootView.findViewById(R.id.dayTxt);
		// txtView.setText(txt);
		mGridView = (GridView) rootView.findViewById(R.id.grid_view);
		sAdapter = new ScheduleGridAdapter(scheduleGridItems, getActivity());
		mGridView.setAdapter(sAdapter);
		return rootView;
	}

	public int getPageNumber() {
		return mPageNumber;
	}

}
