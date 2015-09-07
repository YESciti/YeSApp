package com.exchange.yes.app;

	import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;

	import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import com.gc.materialdesign.views.LayoutRipple;
import android.database.SQLException; 
import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;

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
				
			case R.id.rl_exit:
				
				intent=new Intent(MineActivity.this,Person.class);	
				startActivity(intent);
				Log.i("mine","gone2");
				break;
			
			case R.id.rl_zhifu:
				
				intent=new Intent(MineActivity.this,Secure.class);
				startActivity(intent);
				break;
			
	        case R.id.rl_jiaoyi:
				
				intent=new Intent(MineActivity.this,Person.class);
				startActivity(intent);
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


