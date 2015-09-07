package com.exchange.yes.app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

import java.util.List;
import java.util.Map;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import com.exchange.yes.R;
import com.exchange.yes.R.id;
import com.exchange.yes.R.layout;
import com.exchange.yes.adapter.SpinnerAdapter;

public class SelfTrade extends Activity {

	private Spinner spinner;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_self_trade);
		
		//获取spinner
		spinner=(Spinner)findViewById(R.id.spinner);
		//获取数据
		
		List<Map<String, Object>> currencyspinner=SpinnerAdapter.getspinner3data();
		//设置adapter
	
		SimpleAdapter currecyspin=new SimpleAdapter(this, currencyspinner, R.layout.spinner3_item, new String[]{"log","listname"}, new int[]{R.id.image,R.id.text});
		//给spinner添加adapter
		spinner.setAdapter(currecyspin);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				String nameString=((Map<String,Object>)spinner.getItemAtPosition(position)).get("listname").toString();
				
				
				setTitle(nameString);
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
	});
	
	}

}









