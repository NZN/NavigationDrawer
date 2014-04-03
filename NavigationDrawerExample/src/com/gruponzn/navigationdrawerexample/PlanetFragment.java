package com.gruponzn.navigationdrawerexample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlanetFragment extends Fragment {

	public static final String ARG_PLANET_NUMBER = "planet_number";

	public PlanetFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_planet, container,
				false);

		int i = getArguments().getInt(ARG_PLANET_NUMBER);

		String planet = getResources().getStringArray(R.array.planets_array)[i];

		((TextView) rootView.findViewById(R.id.planet)).setText(planet);

		getActivity().setTitle(planet);

		return rootView;
	}
}