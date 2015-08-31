package com.exchange.yes.app;



import com.exchange.yes.R;
import com.exchange.yes.R.id;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.exchange.yes.dep.FloatingActionButton;
import com.exchange.yes.dep.FloatingActionsMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HomepageActivity extends Activity {

	public static int flag = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_homepage);
		
		
		final View action_personal = findViewById(R.id.action_personal);
		final View action_mall = findViewById(R.id.action_mall);
//		final View action_infor = findViewById(R.id.action_infor);
		final View action_qexchange =findViewById(R.id.action_qexchange);
		final View action_cexchange =findViewById(R.id.action_cexchange);
		
		
	    FloatingActionButton blue_sFAB = new FloatingActionButton(getBaseContext());
	    blue_sFAB.setTitle("½»Ò×");
	    blue_sFAB.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View v) {
	    	  if (flag == 0) {  
	    		  action_personal.setVisibility(action_personal.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
	    		  action_mall.setVisibility(action_mall.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
//	    		  action_infor.setVisibility(action_infor.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
	    		  action_qexchange.setVisibility(action_qexchange.getVisibility() == View.GONE ? View.VISIBLE : View.VISIBLE);
	    		  action_cexchange.setVisibility(action_cexchange.getVisibility() == View.GONE ? View.VISIBLE : View.VISIBLE);
	    		
	    		  flag = 1;
	      } else {
	    	  	action_personal.setVisibility(action_personal.getVisibility() == View.GONE ? View.VISIBLE : View.VISIBLE);
	    	  	action_mall.setVisibility(action_mall.getVisibility() == View.GONE ? View.VISIBLE : View.VISIBLE);
//	    	  	action_infor.setVisibility(action_infor.getVisibility() == View.GONE ? View.VISIBLE : View.VISIBLE);
	    	  	action_qexchange.setVisibility(action_qexchange.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
	    	  	action_cexchange.setVisibility(action_cexchange.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
	    	  	
	    	  	flag = 0;
	      }
	      }
	    });

	    final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
	    menuMultipleActions.addButton(blue_sFAB);
	    


	    final FloatingActionButton action_qex = (FloatingActionButton) findViewById(R.id.action_qexchange);
	    action_qex.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	 Intent quickIntent = new Intent(HomepageActivity.this,AskCusromActivity.class);
	 		 startActivity(quickIntent);
	        Toast.makeText(HomepageActivity.this, "right", Toast.LENGTH_SHORT).show();
	      }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fab_, menu);
		return true;
	};
		
	    
	   
	}


