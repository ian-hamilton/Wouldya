package com.example.whowouldya;

import org.apache.cordova.DroidGap;

import android.os.Bundle;
import android.view.Menu;

public class WhoWouldYaActivity extends DroidGap {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		super.loadUrl("file:///android_asset/www/index.html");		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
