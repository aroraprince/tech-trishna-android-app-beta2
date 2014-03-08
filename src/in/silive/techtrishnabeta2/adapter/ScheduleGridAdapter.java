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

public class ScheduleGridAdapter extends BaseAdapter {

	ArrayList<ListItemClass> scheduleGridItems;
	Context mContext;

	public ScheduleGridAdapter(ArrayList<ListItemClass> scheduleGridItems,
			Context mContext) {
		this.mContext = mContext;
		this.scheduleGridItems = scheduleGridItems;
	}

	@Override
	public int getCount() {
		return scheduleGridItems.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// this method checks if the gridview is null or not. If the view is
		// null then the view is inflated
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.schedule_grid_item, null);
		}
		// get a reference to all the components in the layout
		TextView timeTxt = (TextView) convertView.findViewById(R.id.timeTxt);
		TextView evntName = (TextView) convertView.findViewById(R.id.evntName);
		ImageView eIcon = (ImageView) convertView.findViewById(R.id.evntIcon);

		// set the values of all the components in the layout
		timeTxt.setText(scheduleGridItems.get(position).getNum());
		evntName.setText(scheduleGridItems.get(position).getName());
		eIcon.setImageResource(scheduleGridItems.get(position).getIcon());

		return convertView;
	}

}
