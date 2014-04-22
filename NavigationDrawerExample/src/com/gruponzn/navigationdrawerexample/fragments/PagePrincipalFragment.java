package com.gruponzn.navigationdrawerexample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.common.view.SlidingTabLayout;
import com.gruponzn.navigationdrawerexample.R;
import com.gruponzn.navigationdrawerexample.viewpager.MyPagerAdapter;

public class PagePrincipalFragment extends Fragment {

	private ViewPager mViewPager = null;
	MyPagerAdapter adapterViewPager;
	SlidingTabLayout mSlidingTabLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_viewpager,
				container, false);

		mViewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
		adapterViewPager = new MyPagerAdapter(getFragmentManager());
		mViewPager.setAdapter(adapterViewPager);

		mSlidingTabLayout = (SlidingTabLayout) rootView
				.findViewById(R.id.sliding_tabs);

		mSlidingTabLayout.setViewPager(mViewPager);

		return rootView;
	}

}
