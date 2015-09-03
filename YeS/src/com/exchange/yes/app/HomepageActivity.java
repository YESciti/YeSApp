package com.exchange.yes.app;



import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.view.MotionEvent;
import com.exchange.yes.R;

import com.exchange.yes.dep.FloatingActionButton;
import com.exchange.yes.dep.FloatingActionsMenu;




public class HomepageActivity extends Activity{

	public static int flag = 0;
	public static Boolean islistok= false;
	public static ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>(); 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Log.i("homepage","right1;");
		
		try{
		setContentView(R.layout.activity_homepage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		Log.i("homepage","right2;");
		
		final View action_personal = findViewById(R.id.action_personal);
		final View action_mall = findViewById(R.id.action_mall);
//		final View action_infor = findViewById(R.id.action_infor);
		final View action_qexchange =findViewById(R.id.action_qexchange);
		final View action_cexchange =findViewById(R.id.action_cexchange);
		
		android.widget.ScrollView scroll=(android.widget.ScrollView)findViewById(R.id.myscroll);
		
	    FloatingActionButton blue_sFAB = new FloatingActionButton(getBaseContext());
	    blue_sFAB.setTitle("交易");
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
	    
	    
	    
	    
	    
	    
	    
	    
	    
//	    ListView list = (ListView) findViewById(R.id.tradelistview); 
//	    
//	    //test
//	     
//	    for(int i=0;i<30;i++)  
//	    {  
//	        HashMap<String, String> map = new HashMap<String, String>();  
//	        map.put("ItemTitle", "This is Title.....");  
//	        map.put("ItemText", i+"");  
//	        mylist.add(map);  
//	    }  
	
	
//	//Êý¾Ýadapter
//	 SimpleAdapter mSchedule = new SimpleAdapter(this, 
//             mylist,//Êý¾ÝÀ´Ô´   
//             R.layout.listview_trade,//ListItemµÄXMLÊµÏÖ  
//               
//             //¶¯Ì¬Êý×éÓëListItem¶ÔÓ¦µÄ×ÓÏî          
//             new String[] {"ItemTitle", "ItemText"},   
//               
//             //ListItemµÄXMLÎÄ¼þÀïÃæµÄÁ½¸öTextView ID  
//             new int[] {R.id.listview_1,R.id.listview_2});  
//	 		//Ìí¼Ó²¢ÇÒÏÔÊ¾  
//	 		list.setAdapter(mSchedule); 	    
	}
	

  
  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fab_, menu);
		return true;
	};
		
	    
	   
	}


