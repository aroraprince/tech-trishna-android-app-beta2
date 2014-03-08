package in.silive.techtrishnabeta2;

import in.silive.techtrishnabeta2.adapter.FaqListAdapter;
import in.silive.techtrishnabeta2.control.Control;
import in.silive.techtrishnabeta2.model.ListItemClass;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.tjerkw.slideexpandable.library.SlideExpandableListAdapter;

public class FaqFragment extends Fragment {
	private ListView mFaqList;

	private ArrayList<ListItemClass> faqListItems;
	private FaqListAdapter adapter;
	private Control control;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.faq_layout, container, false);
		control = new Control(getActivity());
		faqListItems = control.getList(R.array.questions, R.array.answers,
				R.array.answers);
		// get a reference to the faq list
		mFaqList = (ListView) rootView.findViewById(R.id.faqList);
		// call the list adapter
		adapter = new FaqListAdapter(faqListItems, getActivity());
		mFaqList.setAdapter(new SlideExpandableListAdapter(adapter,
				R.id.quesId, R.id.expandable));
		return rootView;
	}
}
