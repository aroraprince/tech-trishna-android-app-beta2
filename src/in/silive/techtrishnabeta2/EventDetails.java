package in.silive.techtrishnabeta2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EventDetails extends Fragment {
	private String eventName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle bundle) {
		Bundle b = this.getArguments();
		eventName = b.getString("eventName");
		View rootView = inflater.inflate(R.layout.event_details, container,
				false);
		TextView eventText = (TextView) rootView.findViewById(R.id.eventName);
		if (eventName != null) {
			eventText.setText(eventName);
			getActivity().setTitle(eventName);
		}
		return rootView;
	}
}
