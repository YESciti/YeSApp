package com.exchange.yes.pre;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import com.exchange.yes.R;
import com.exchange.yes.R.id;
import com.exchange.yes.R.layout;




public class SwitchActivity extends Activity {
	
	int backgroundColor = Color.parseColor("#1E88E5");

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switchs);
        int color = getIntent().getIntExtra("BACKGROUND", Color.BLACK);
        findViewById(R.id.checkBox).setBackgroundColor(color);
        findViewById(R.id.switchView).setBackgroundColor(color);
    }  
    

}
