package in.silive.techtrishnabeta2.adapter;

import in.silive.techtrishnabeta2.R;
import in.silive.techtrishnabeta2.model.ListItemClass;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactListAdapter extends BaseAdapter {

	private ArrayList<ListItemClass> contactListItems;
	private Context context;

	public ContactListAdapter(ArrayList<ListItemClass> contactListItems,
			Context context) {
		this.contactListItems = contactListItems;
		this.context = context;
	}

	@Override
	public int getCount() {
		return contactListItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		return contactListItems.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.contact_list_item, null);
		}
		// references to the list items
		TextView numTxt = (TextView) convertView.findViewById(R.id.pNum);
		TextView nameTxt = (TextView) convertView.findViewById(R.id.pName);
		ImageView peopleIcon = (ImageView) convertView.findViewById(R.id.pIcon);
		// set the value of the list items
		peopleIcon.setImageResource(contactListItems.get(position).getIcon());
		nameTxt.setText(contactListItems.get(position).getName());
		numTxt.setText(contactListItems.get(position).getNum());
		return convertView;
	}

}
