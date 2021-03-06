package com.gruponzn.navigationdrawerlibrary;

import android.app.Activity;
import android.content.res.Configuration;
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
	private boolean mEnableMenu = true;

	OnDrawerOpenedListener mOnDrawerOpenedListener;

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

	public ListView getDrawerList() {
		return mDrawerList;
	}

	public void setOnItemClickListener(ListView.OnItemClickListener listener) {
		mDrawerList.setOnItemClickListener(listener);

	}

	public void setAdapter(ListAdapter adapter) {
		this.mDrawerList.setAdapter(adapter);

		if (null != this.mDrawerToggle)
			mDrawerToggle.syncState();
	}

	public void setItemChecked(int position, boolean value) {
		this.mDrawerList.setItemChecked(position, value);
	}

	public void syncState() {
		this.mDrawerToggle.syncState();
	}

	public void onConfigurationChanged(Configuration newConfig) {
		this.mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void closeDrawer() {
		this.mDrawerLayout.closeDrawer(this.mDrawerList);
	}

	public boolean isOpen() {
		return this.mDrawerLayout.isDrawerOpen(this.mDrawerList);
	}

	public void enableSwypeOpenMenu(boolean enable) {

		this.mEnableMenu = enable;

		if (mEnableMenu)
			mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
		else
			mDrawerLayout
					.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

	}

	public boolean onOptionsItemSelected(MenuItem menu) {
		if (mDrawerToggle != null && mEnableMenu == true)
			return this.mDrawerToggle.onOptionsItemSelected(menu);
		else
			return false;
	}

	public void setActionBarDrawerToggle(int drawerImageRes,
			final int openDrawerContentDescRes,
			final int closeDrawerContentDescRes,
			final boolean changeTitleWhenOpen, OnDrawerOpenedListener listener) {

		mOnDrawerOpenedListener = listener;

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

				mOnDrawerOpenedListener.drawerIsOpened(false);
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

				mOnDrawerOpenedListener.drawerIsOpened(true);

			}

		};

		this.mDrawerLayout.setDrawerListener(mDrawerToggle);

	}
}
