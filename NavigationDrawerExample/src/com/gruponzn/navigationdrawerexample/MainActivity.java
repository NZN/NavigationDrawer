package com.gruponzn.navigationdrawerexample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.gruponzn.navigationdrawerlibrary.NavigationDrawer;

public class MainActivity extends Activity {

	private String[] mPlanetTitles = null;
	private NavigationDrawer mMenu = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setHomeButtonEnabled(true);

		mPlanetTitles = getResources().getStringArray(R.array.planets_array);

		mMenu = new NavigationDrawer(this, R.id.drawer_layout, R.id.listMenu);

		mMenu.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mPlanetTitles));

		mMenu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				selectItem(position);
			}

		});

		mMenu.setActionBarDrawerToggle(R.drawable.ic_launcher,
				R.string.drawer_open, R.string.drawer_close, false);

		if (savedInstanceState == null) {
			selectItem(0);
		}

	}

	private void selectItem(int position) {

		// Create a new fragment and specify the planet to show based on
		// position

		Fragment fragment = new PlanetFragment();
		Bundle args = new Bundle();

		args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		fragment.setArguments(args);

		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// Highlight the selected item, update the title, and close the drawer

		mMenu.setItemChecked(position, true);
		mMenu.closeDrawer();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		boolean drawerOpen = mMenu.isOpen();
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (mMenu.onOptionsItemSelected(item))
			return true;

		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
