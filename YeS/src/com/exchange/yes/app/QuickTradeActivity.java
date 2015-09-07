package com.exchange.yes.app;

import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.gc.materialdesign.widgets.Dialog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class QuickTradeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_quick_trade);
		
//		dialog of confirm your deal 
		findViewById(R.id.btn_submitorder_quick).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View flatButton) {
				Dialog dialog = new Dialog(QuickTradeActivity.this, "提示", "点击确定将直接交易！");
				dialog.setOnAcceptButtonClickListener(new OnClickListener() {
					@Override
					public void onClick(View v){
						Intent adkEditIntent=new Intent(QuickTradeActivity.this,AskQuickActivity.class);
						startActivity(adkEditIntent);
						Toast.makeText(QuickTradeActivity.this, "直接交易", 1).show();
					}
				});
				dialog.setOnCancelButtonClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quick_trade, menu);
		return true;
	}

}
