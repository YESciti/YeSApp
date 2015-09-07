package com.exchange.yes.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.Menu;

public class Person extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_person);
		

		
		Button btn_edit=(Button)findViewById(R.id.btn_edit);
		btn_edit.setOnClickListener(new editListener());
	}
	
	
	
	 class editListener implements OnClickListener{
	    	public void onClick(View v){
	    		Intent intent=new Intent(Person.this,RePerson.class);
	    		startActivity(intent);
//	    		Person.this.finish();
	    	}
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.person, menu);
		return true;
	}

}
