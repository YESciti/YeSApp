package com.exchange.yes.app;

import com.exchange.yes.R;
import com.exchange.yes.R.id;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.exchange.yes.dep.FloatingActionButton;
import com.exchange.yes.dep.FloatingActionsMenu;
import com.gc.materialdesign.widgets.Dialog;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.LayoutRipple;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AskQuickActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_quick_trade);
		
		
		LayoutRipple back=(LayoutRipple) findViewById(R.id.item_back_quick);
		back.setOnClickListener(this);
		
//		
		findViewById(R.id.btn_submitorder_quick).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View flatButton) {
				Dialog dialog = new Dialog(AskQuickActivity.this, "Title", "detail");
				dialog.setOnAcceptButtonClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v){
						Intent adkQuickIntent=new Intent(AskQuickActivity.this,HomepageActivity.class);
						startActivity(adkQuickIntent);
						Toast.makeText(AskQuickActivity.this, "Click accept button", 1).show();
					}
				});
				dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ask_editbl_rat, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=null;
		Log.i("mine","gone1");
		
		switch(arg0.getId()){
		case R.id.item_back_quick:

			AskQuickActivity.this.finish();
			break;
	}}

}
