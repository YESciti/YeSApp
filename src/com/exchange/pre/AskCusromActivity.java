package com.exchange.pre;

import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AskCusromActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ask_cusrom);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

}
