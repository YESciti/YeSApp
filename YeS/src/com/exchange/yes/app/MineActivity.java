package com.exchange.yes.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.exchange.yes.R;
import com.gc.materialdesign.views.LayoutRipple;

	public class MineActivity extends Activity  implements OnClickListener{

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.activity_mine);
			
			
			LayoutRipple rl_geren=(LayoutRipple) findViewById(R.id.rl_geren);
			LayoutRipple rl_anquan=(LayoutRipple) findViewById(R.id.rl_anquan); 
			LayoutRipple rl_exit=(LayoutRipple) findViewById(R.id.rl_exit); 
			LayoutRipple rl_jiaoyi=(LayoutRipple) findViewById(R.id.rl_jiaoyi);
			LayoutRipple rl_zhifu=(LayoutRipple) findViewById(R.id.rl_zhifu);
			LayoutRipple rl_youhuiquan=(LayoutRipple) findViewById(R.id.rl_youhuiquan);
			
			LayoutRipple back=(LayoutRipple) findViewById(R.id.item_back_mine);
			back.setOnClickListener(this);
			
			rl_geren.setOnClickListener(this);
			rl_anquan.setOnClickListener(this);
			rl_exit.setOnClickListener(this);
			rl_jiaoyi.setOnClickListener(this);
			rl_zhifu.setOnClickListener(this);
			rl_youhuiquan.setOnClickListener(this);
			
			Log.i("mine","gone");
		}
		
		 
	

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.mine, menu);
			return true;
		}

		@Override
		public void onClick(View lr) {
			// TODO Auto-generated method stub
			Intent intent=null;
			Log.i("mine","gone1");
			
			switch(lr.getId()){
			case R.id.item_back_mine:
		
				MineActivity.this.finish();
				break;
			
			case R.id.rl_exit:
				
				intent=new Intent(MineActivity.this,Person.class);	
				startActivity(intent);
				Log.i("mine","gone2");
				break;
			
			case R.id.rl_zhifu:
				
//				intent=new Intent(MineActivity.this,Secure.class);
//				startActivity(intent);
				break;
			
	        case R.id.rl_jiaoyi:
				
//				intent=new Intent(MineActivity.this,Person.class);
//				startActivity(intent);
				break;
				
	        case R.id.rl_geren:
				
				intent=new Intent(MineActivity.this,Person.class);	
				startActivity(intent);
				break;
			
			case R.id.rl_anquan:
				
				intent=new Intent(MineActivity.this,Secure.class);
				startActivity(intent);
				break;
			
	        case R.id.rl_youhuiquan:
				
				intent=new Intent(MineActivity.this,MallActivity.class);
				startActivity(intent);
				break;
			}	
		}
	}


