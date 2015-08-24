package com.exchange.yes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class QuickTradeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick_trade);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quick_trade, menu);
		return true;
	}

}
