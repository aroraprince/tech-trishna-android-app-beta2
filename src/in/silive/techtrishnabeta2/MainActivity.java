package in.silive.techtrishnabeta2;

import in.silive.techtrishnabeta2.adapter.NavDrawerAdapter;
import in.silive.techtrishnabeta2.control.Control;
import in.silive.techtrishnabeta2.model.ListItemClass;
import in.silive.techtrishnabeta2.network.ConnectionDetector;
import in.silive.techtrishnabeta2.network.DialogBox;
import in.silive.techtrishnabeta2.network.GPSTracker;

import java.util.ArrayList;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class MainActivity extends ActionBarActivity {

	private Control control;
	private ArrayList<ListItemClass> mListItems;
	private DrawerLayout mDrawer;
	private ListView mList;
	private LinearLayout mLinearLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private NavDrawerAdapter adapter;
	private String initial;
	private int status;

	private ConnectionDetector connection;
	private DialogBox dialogBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// set the connection object
		connection = new ConnectionDetector(this);
		// get if google play services are installed or not
		status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());
		// launch the dialog box if the connection is there
		if (!connection.isInternetConnected()) {
			dialogBox = new DialogBox(this, Settings.ACTION_SETTINGS, null, 0);
			dialogBox.setTitle("NO INTERNET CONNECTION");
			dialogBox
					.setBody("It seems that you do not have a net connection. All features of the app will be unavailable if you do not have a net connection.");
			dialogBox.setButtonText("Go to Internet Settings");
			dialogBox.onCreateDialogBox();
		}
		// set the title
		mDrawerTitle = mTitle = getTitle();
		// create an object of the control class
		control = new Control(this);
		// initialise the array list object
		mListItems = new ArrayList<ListItemClass>();
		// get an array list for the navigation drawer list items
		mListItems = control.getList(R.array.listItems, R.array.listIcons);
		// get the reference to the drawer layout
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		// get the reference to the list view
		mList = (ListView) findViewById(R.id.drawer_list);
		// get a reference to the Linear Layout
		mLinearLayout = (LinearLayout) findViewById(R.id.drawer);
		// set the list item click event
		mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				displayView(arg2);// call the function to load the corresponding
									// fragment
			}
		});
		// adpater to populate the list
		adapter = new NavDrawerAdapter(getApplicationContext(), mListItems);
		// create toggle button for the drawer
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				initial = getSupportActionBar().getTitle().toString();
				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu();
			}
		};
		// enable the toggle
		mDrawer.setDrawerListener(mDrawerToggle);

		// enable up navigation
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		mList.setAdapter(adapter);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			initial = " ";
			displayView(0);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		// boolean drawerOpen = mDrawer.isDrawerOpen(linearLayout);
		// menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	private void displayView(int pos) {
		Fragment fragment = null;
		String itemName = null;
		switch (pos) {
		case 0:
			fragment = new EventFragment();
			itemName = "EVENT";
			break;
		case 1:
			fragment = new ScheduleFragment();
			itemName = "SCHEDULE";
			break;
		case 2:
			fragment = new RegisterFragment();
			itemName = "REGISTER";
			break;
		case 3:
			fragment = new ContactFragment();
			itemName = "CONTACT US";
			break;
		case 4:
			if (status != ConnectionResult.SUCCESS) {
				DialogBox dialogBox = new DialogBox(this);
				dialogBox.setTitle("Google Play Services not installed");
				dialogBox
						.setBody("It seems the google play services are not installed on your phone. To access the navigation feature you must install it");
				dialogBox.setButtonText("Cancel");
				dialogBox.onCreateAlertBox();
			} else {
				boolean locStatus = new GPSTracker(this).canGetLocation();
				if (!locStatus) {
					DialogBox dialogBox = new DialogBox(this,
							Settings.ACTION_LOCATION_SOURCE_SETTINGS, null, 0);
					dialogBox.setTitle("GPS not enabled");
					dialogBox
							.setBody("you must enable the gps settings on your phone to use this feature");
					dialogBox.setButtonText("Enable GPS");
					dialogBox.onCreateDialogBox();
				} else {
					fragment = new NavFragment();
					itemName = "NAVIGATION";
				}
			}
			break;
		case 5:
			fragment = new FaqFragment();
			itemName = "FAQ";
			break;
		case 6:
			fragment = new QueryFragment();
			itemName = "Query Us";
			break;
		case 7:
			fragment = new IdFragment();
			itemName = "Get Your TT-ID";
			break;

		}
		if (fragment != null) {
			if (!(initial.equals(itemName))) {
				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragment).commit();
			}
			// update the drawer
			mList.setItemChecked(pos, true);
			mList.setSelection(pos);
			setTitle(itemName);
			mDrawer.closeDrawer(mLinearLayout);
		}
	}

	@Override
	public void onBackPressed() {
		Fragment fragment = new EventFragment();
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		setTitle("EVENT");
	}

}
