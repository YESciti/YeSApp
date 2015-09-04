package com.exchange.yes.app;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.MotionEvent;
import com.exchange.yes.R;

import com.exchange.yes.adapter.SpinnerAdapter;
import com.exchange.yes.db.TradeItem;
import com.exchange.yes.dep.FloatingActionButton;
import com.exchange.yes.dep.FloatingActionsMenu;
import com.exchange.yes.service.MessageService;
import com.exchange.yes.service.OnGetMessageListener;



public class HomepageActivity extends FragmentActivity{

	public static int flag = 0;
	public static Boolean islistok= false;
	public static ArrayList<TradeItem> mylist = new ArrayList<TradeItem>(); 
	public static Home1Fra homefragment=null;
	private Spinner spinner;
	
	
	//service
	private boolean serviceBound = false;  
	private  MessageService localService;
	private ServiceConnection serviceConnection = new ServiceConnection() {
		// 已经绑定了LocalService，强转IBinder对象，调用方法得到LocalService对象  
		@Override
		public void onServiceConnected(ComponentName className,  
                IBinder service) {
			// TODO Auto-generated method stub
			 MessageService.MsgBinder binder = ( MessageService.MsgBinder) service;  
	            
	            localService = binder.getService();  
	            System.out.println("test416 onServiceConnected onCreate");
	            MessageService.onGetMessageListener = new Listener();
	            serviceBound = true;  
		}
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			serviceBound = false;  
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Log.i("homepage","right1;");
		
		
		setContentView(R.layout.activity_homepage);
		initFirstFragment();
		
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
	    


	    
//个人中心
	    action_personal.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	 Intent quickIntent = new Intent(HomepageActivity.this,MineActivity.class);
	 		 startActivity(quickIntent);
	        Toast.makeText(HomepageActivity.this, "right", Toast.LENGTH_SHORT).show();
	      }
	    });
//市场
	    action_mall.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	 Intent quickIntent = new Intent(HomepageActivity.this,MallActivity.class);
	 		 startActivity(quickIntent);
	        Toast.makeText(HomepageActivity.this, "right", Toast.LENGTH_SHORT).show();
	      }
	    });
//快速交易
	    action_qexchange.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	 Intent quickIntent = new Intent(HomepageActivity.this,QuickTradeActivity.class);
	 		 startActivity(quickIntent);
	        Toast.makeText(HomepageActivity.this, "right", Toast.LENGTH_SHORT).show();
	      }
	    });
//自定义交易	  
	    action_cexchange.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	 Intent quickIntent = new Intent(HomepageActivity.this,EditblRatTradActivity.class);
	 		 startActivity(quickIntent);
	        Toast.makeText(HomepageActivity.this, "right", Toast.LENGTH_SHORT).show();
	      }
	    });
	    
	    //spinner
	    spinner=(Spinner)findViewById(R.id.cur_spinner);
	    List<Map<String, Object>> currencyspinner=SpinnerAdapter.getspinner3data();
	    SimpleAdapter currecyspin=new SimpleAdapter(this, currencyspinner, R.layout.spinner3_item, new String[]{"log","listname"}, new int[]{R.id.image,R.id.text});
		//给spinner添加adapter
		spinner.setAdapter(currecyspin);
	    
	    
	    
	    
	    
	    
	    
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
		
	
	private void initFirstFragment() {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction ft = manager.beginTransaction();
	    
		 homefragment= new Home1Fra();
		 freshTradeList();
		 Log.i("list","homeinit"+HomepageActivity.mylist.size());

		 try{
		 ft.replace(android.R.id.tabcontent,homefragment,"consult_fragment_tag");
		 ft.commit();
		 }
		 catch(Exception e)
		 {
		 e.printStackTrace();
		}
	}
	
	
	
	class Listener implements OnGetMessageListener {

		@Override
		public void onRefreshState() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onRefreshlistState() {
			// TODO Auto-generated method stub
			
		}	
	}
	
	private void freshTradeList(){
		if(mylist.size()==0)
		{
//	        HashMap<String, String> map = new HashMap<String, String>();  
//	        map.put("ItemTitle", "This is Title.....");  
//	        map.put("ItemText", i+"");  
//	        mylist.add(map);  
			TradeItem item=new TradeItem("买","11","22");
			mylist.add(item);
		}
		else if(true){};
	}
	
	
}
 	
