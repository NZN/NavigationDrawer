package com.gruponzn.navigationdrawerlibrary;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class NavigationDrawer {

	private Activity mActivity;
	private final DrawerLayout mDrawerLayout;
	private final ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;

	public NavigationDrawer(Activity activity, int resDrawerLayout,
			int resDrawerList) {
		this.mActivity = activity;

		this.mDrawerLayout = (DrawerLayout) this.mActivity
				.findViewById(resDrawerLayout);

		this.mDrawerList = (ListView) this.mActivity
				.findViewById(resDrawerList);

	}

	public NavigationDrawer(Activity activity, DrawerLayout DrawerLayout,
			ListView DrawerList) {

		this.mActivity = activity;
		this.mDrawerLayout = DrawerLayout;
		this.mDrawerList = DrawerList;

	}

	public void setOnItemClickListener(ListView.OnItemClickListener listener) {
		mDrawerList.setOnItemClickListener(listener);

	}

	public void setAdapter(ListAdapter adapter) {
		this.mDrawerList.setAdapter(adapter);
	}

	public void setItemChecked(int position, boolean value) {
		this.mDrawerList.setItemChecked(position, value);
	}

	public void closeDrawer() {
		this.mDrawerLayout.closeDrawer(this.mDrawerList);
	}

	public boolean isOpen() {
		return this.mDrawerLayout.isDrawerOpen(this.mDrawerList);
	}

	public boolean onOptionsItemSelected(MenuItem menu) {
		if (mDrawerToggle != null)
			return this.mDrawerToggle.onOptionsItemSelected(menu);
		else
			return false;
	}

	public void setActionBarDrawerToggle(int drawerImageRes,
			final int openDrawerContentDescRes,
			final int closeDrawerContentDescRes,
			final boolean changeTitleWhenOpen) {

		mDrawerToggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout,
				drawerImageRes, openDrawerContentDescRes,
				closeDrawerContentDescRes) {

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);

				if (changeTitleWhenOpen) {
					mActivity.getActionBar().setTitle(mTitle);
				}

				mActivity.invalidateOptionsMenu();

			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);

				if (changeTitleWhenOpen) {
					mTitle = mActivity.getActionBar().getTitle();

					mActivity.getActionBar().setTitle(
							mActivity.getString(openDrawerContentDescRes));
				}
				mActivity.invalidateOptionsMenu();

			}

		};

		this.mDrawerLayout.setDrawerListener(mDrawerToggle);

	}
}
