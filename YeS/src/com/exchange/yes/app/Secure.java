package com.exchange.yes.app;

import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;


public class Secure extends Activity {

	ButtonRectangle btn_save_secure;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secure);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		btn_save_secure=(ButtonRectangle)findViewById(R.id.btn_save_secure);
		btn_save_secure.setOnClickListener(new sureListener());
	}
class sureListener implements OnClickListener{
    	public void onClick(View v){
    		Intent intent=new Intent(Secure.this,MineActivity.class);
    		startActivity(intent);
    		Secure.this.finish();
    	}
    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secure, menu);
		return true;
	}

}

