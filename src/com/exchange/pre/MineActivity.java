package com.exchange.pre;

import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MineActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mine, menu);
		return true;
	}

}
