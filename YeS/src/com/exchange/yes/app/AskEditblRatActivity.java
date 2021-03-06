package com.exchange.yes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.exchange.yes.R;
import com.exchange.yes.pre.WidgetActivity;
import com.gc.materialdesign.widgets.Dialog;

public class AskEditblRatActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ask_editbl_rat);
		
//		对于剩余未交易成功部分，用户点击更改汇率按钮
//		REWRITE RATE AND OFFER A NEW ORDER
		findViewById(R.id.btn_submitorder_edit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent adkEditIntent=new Intent(AskEditblRatActivity.this,EditblRatTradActivity.class);
				startActivity(adkEditIntent);
			}
		});
//		对于剩余未交易成功部分，用户不改汇率，挂单等待
//		THE RATE UNCHAGED AND WAIT
		findViewById(R.id.btn_submitorder_edit).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent adkEditIntent=new Intent(AskEditblRatActivity.this,HomepageActivity.class);
				startActivity(adkEditIntent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ask_editbl_rat, menu);
		return true;
	}

}
