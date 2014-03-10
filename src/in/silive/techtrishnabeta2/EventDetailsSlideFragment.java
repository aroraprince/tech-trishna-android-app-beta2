package in.silive.techtrishnabeta2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EventDetailsSlideFragment extends Fragment {

	public static EventDetailsSlideFragment create(int pos) {
		EventDetailsSlideFragment frag = new EventDetailsSlideFragment();
		Bundle args = new Bundle();
		args.putInt("DETAIL", pos);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int mPageNumber = getArguments().getInt("DETAIL");
		String title = null;
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.event_details_slide, container, false);
		TextView eventDetailText = (TextView) rootView
				.findViewById(R.id.eventSlideText);
		switch (mPageNumber) {
		case 0:
			title = "EVENT DETAILS";
			break;
		case 1:
			title = "EVENT DATE";
			break;
		case 2:
			title = "EVENT COORDINAOTORS";
			break;
		default:
			break;
		}
		eventDetailText.setText(title);
		return rootView;
	}

}
