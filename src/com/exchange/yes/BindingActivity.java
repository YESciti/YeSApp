package com.exchange.yes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.exchange.yes.R;
public class BindingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_binding);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.binding, menu);
		return true;
	}

}
