package com.exchange.yes.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.exchange.yes.R;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.LayoutRipple;
import com.gc.materialdesign.widgets.Dialog;

public class EditblRatTradActivity extends Activity implements OnClickListener{
Button deal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ask_editbl_rat);
		
		LayoutRipple back=(LayoutRipple) findViewById(R.id.item_back_edit);
		back.setOnClickListener(this);
		
		
		
		
//		dialog of confirm your deal 
		findViewById(R.id.btn_submitorder_edit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View flatButton) {
				Dialog dialog = new Dialog(EditblRatTradActivity.this, "提示", "确认提交您目前填写的订单");
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=null;
		Log.i("mine","gone1");
		
		switch(arg0.getId()){
		case R.id.item_back_edit:
		
			EditblRatTradActivity.this.finish();
			break;
		}
	}

}
