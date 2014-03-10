package in.silive.techtrishnabeta2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventDetails extends Fragment {
	private String eventName;
	private static int NUM_PAGES = 3;
	private ViewPager viewPager;
	private PagerAdapter mPagerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle bundle) {
		Bundle b = this.getArguments();
		eventName = b.getString("eventName");
		getActivity().setTitle(eventName);
		View rootView = inflater.inflate(R.layout.event_details, container,
				false);
		viewPager = (ViewPager) rootView.findViewById(R.id.pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getChildFragmentManager());
		viewPager.setAdapter(mPagerAdapter);
		viewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int pos) {

					}
				});
		return rootView;
	}

	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return EventDetailsSlideFragment.create(position);
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String title = null;
			switch (position) {
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
			return title;
		}

	}
}
