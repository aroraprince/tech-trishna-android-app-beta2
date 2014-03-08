package in.silive.techtrishnabeta2.control;

import in.silive.techtrishnabeta2.model.ListItemClass;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;

public class Control {
	private ArrayList<ListItemClass> mListItems;
	private String[] name;
	private String[] num;
	private String[][] categoryItems;
	private String[] category;
	private TypedArray icon;
	private Context mContext;

	public Control() {
		// default constructor
	}

	public Control(Context mContext) {
		// get the context to obtain the resources
		this.mContext = mContext;
		// initialise the array list
		mListItems = new ArrayList<ListItemClass>();
	}

	public ArrayList<ListItemClass> getList(int nameId, int numId, int iconId) {
		// obtain the name array
		name = mContext.getResources().getStringArray(nameId);
		// obtain the number array
		num = mContext.getResources().getStringArray(numId);
		// obtain the icon array
		icon = mContext.getResources().obtainTypedArray(iconId);
		for (int i = 0; i < num.length; i++) {
			mListItems.add(new ListItemClass(name[i], num[i], icon
					.getResourceId(i, -1)));
		}
		icon.recycle();
		return mListItems;
	}

	public ArrayList<ListItemClass> getList(int nameId, int iconId) {
		// obtain the name array
		name = mContext.getResources().getStringArray(nameId);
		// obtain the icon array
		icon = mContext.getResources().obtainTypedArray(iconId);
		for (int i = 0; i < name.length; i++) {
			mListItems
					.add(new ListItemClass(name[i], icon.getResourceId(i, -1)));
		}
		icon.recycle();
		return mListItems;
	}

	public String[] getCategory(int id) {
		category = mContext.getResources().getStringArray(id);
		return category;
	}

	public String[][] getCategoryItems(int id1, int id2, int id3, int id4) {
		// get the computer science games
		String[] category1 = mContext.getResources().getStringArray(id1);
		// get the ec games
		String[] category2 = mContext.getResources().getStringArray(id2);
		// get the online games
		String[] category3 = mContext.getResources().getStringArray(id3);
		// get the robotics games
		String[] category4 = mContext.getResources().getStringArray(id4);
		// get all the category items
		categoryItems = new String[][] { category3, category1, category2,
				category4 };
		return categoryItems;
	}
}
