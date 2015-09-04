package com.exchange.yes.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.exchange.yes.R;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.widgets.Dialog;

public class EditblRatTradActivity extends Activity {
Button deal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editbl_rat_trad);
//		dialog of confirm your deal 
		findViewById(R.id.btn_submitorder_edit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View flatButton) {
				Dialog dialog = new Dialog(EditblRatTradActivity.this, "Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam");
				dialog.setOnAcceptButtonClickListener(new OnClickListener() {
					@Override
					public void onClick(View v){
						Intent adkEditIntent=new Intent(EditblRatTradActivity.this,AskEditblRatActivity.class);
						startActivity(adkEditIntent);
						Toast.makeText(EditblRatTradActivity.this, "Click accept button", 1).show();
					}
				});
				dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editbl_rat_trad, menu);
		return true;
	}

}
