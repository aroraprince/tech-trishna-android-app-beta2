package in.silive.techtrishnabeta2;

import in.silive.techtrishnabeta2.adapter.ContactListAdapter;
import in.silive.techtrishnabeta2.control.Control;
import in.silive.techtrishnabeta2.model.ListItemClass;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ContactFragment extends Fragment {
	private GridView mContactList;

	private ArrayList<ListItemClass> contactListItems;
	private ContactListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.contact_us_layout, container,
				false);
		Control control = new Control(getActivity());
		contactListItems = control.getList(R.array.pNames, R.array.pNumbers,
				R.array.pIcons);
		// get a reference to the contact list
		mContactList = (GridView) rootView.findViewById(R.id.contact_list);
		adapter = new ContactListAdapter(contactListItems, getActivity());
		mContactList.setAdapter(adapter);
		return rootView;
	}

}
