package com.exchange.yes.app;

import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.gc.materialdesign.views.LayoutRipple;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MallActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mall);
		
		
		
		LayoutRipple backe=(LayoutRipple) findViewById(R.id.item_back_mall);
		backe.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mall, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		Log.i("mine","gone1");
		
		switch(arg0.getId()){
		case R.id.item_back_mall:			
			MallActivity.this.finish();
			break;
		}
	}

}
