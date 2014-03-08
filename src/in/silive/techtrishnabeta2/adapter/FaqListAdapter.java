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
import android.widget.TextView;

public class FaqListAdapter extends BaseAdapter {
	private ArrayList<ListItemClass> faqListItems;
	private Context context;

	public FaqListAdapter(ArrayList<ListItemClass> faqListItems, Context context) {
		this.faqListItems = faqListItems;
		this.context = context;
	}

	@Override
	public int getCount() {
		// This function returns the size of the arraylist
		return faqListItems.size();
	}

	@Override
	public Object getItem(int position) {
		// This function also seems to be useless
		return faqListItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// This function seems to be useless but alas the class is abstract so
		// it must be overridden
		return position;
	}

	// This is the view method and does some real serious work
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// the following lines of code seem to check if convertView is null or
		// not i.e. was getView called before or not if no then the list item
		// layout is inflated or so it seems
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.faq_list_item, null);
		}
		// get the references to the user interface elements present inside the
		// faq list
		TextView quesTxt = (TextView) convertView.findViewById(R.id.quesId);
		TextView ansTxt = (TextView) convertView.findViewById(R.id.ansId);
		// once you have the references to the ui elements then their values
		// must be set which is exactly what I am doing here or think I am doing
		// here
		quesTxt.setText(faqListItems.get(position).getName());
		ansTxt.setText(faqListItems.get(position).getNum());
		return convertView;
	}

}
