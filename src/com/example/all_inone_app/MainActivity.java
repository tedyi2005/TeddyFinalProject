package com.example.all_inone_app;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

	NewsFragment nef = new NewsFragment();
	WeatherFragment wft = new WeatherFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		android.app.FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.main_layout, nef,"News Section");
		transaction.add(R.id.main_layout, wft,"Weather Sectiion");
		transaction.commit();
		//Toast.makeText(this, "the list view is created", Toast.LENGTH_LONG);
	}
	
}
