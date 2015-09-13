package com.exchange.yes.app;



import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import android.app.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
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
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.MotionEvent;
import com.exchange.yes.R;
import com.gc.materialdesign.views.Button;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.LimitLine.*;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.exchange.yes.dep.MyMarkerView;


import com.exchange.yes.adapter.SpinnerAdapter;
import com.exchange.yes.db.TimeSerises;
import com.exchange.yes.db.TradeItem;
import com.exchange.yes.dep.FloatingActionButton;
import com.exchange.yes.dep.FloatingActionsMenu;
import com.exchange.yes.service.MessageService;
import com.exchange.yes.service.OnGetMessageListener;



public class HomepageActivity extends FragmentActivity implements OnClickListener,OnSeekBarChangeListener,
OnChartGestureListener, OnChartValueSelectedListener{

	public static int flag = 0;
	public static Boolean islistok= false;
	public static ArrayList<TradeItem> mylist = new ArrayList<TradeItem>(); 
	public static ArrayList<TradeItem> newlist = new ArrayList<TradeItem>(); 
	public static Home1Fra homefragment=null;
	private Spinner spinner;
	private LineChart mChart;
	public int position=1;
	
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
		
		
		setContentView(R.layout.activity_test);
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
	        Toast.makeText(HomepageActivity.this, "个人中心", Toast.LENGTH_SHORT).show();
	      }
	    });
//市场
	    action_mall.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	 Intent quickIntent = new Intent(HomepageActivity.this,MallActivity.class);
	 		 startActivityForResult(quickIntent, 0);
	        Toast.makeText(HomepageActivity.this, "市场", Toast.LENGTH_SHORT).show();
	      }
	    });
//快速交易
	    action_qexchange.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	 Intent quickIntent = new Intent(HomepageActivity.this,QuickTradeActivity.class);
	    	 quickIntent.putExtra("position", position);
//	 		 startActivity(quickIntent);
	 		startActivityForResult(quickIntent, 0);
	        Toast.makeText(HomepageActivity.this, "快速交易", Toast.LENGTH_SHORT).show();
	      }
	    });
//自定义交易	  
	    action_cexchange.setOnClickListener(new OnClickListener() {
	      @Override
	      public void onClick(View view) {
	    	  Bundle mBundle = new Bundle(); 
	    	 Intent customIntent = new Intent(HomepageActivity.this,EditblRatTradActivity.class);
	    	 mBundle.putInt("position", position);
	    	 customIntent.putExtras(mBundle);
//	 		 startActivity(customIntent);
	 		startActivityForResult(customIntent, 0);
	        Toast.makeText(HomepageActivity.this, "自定义交易", Toast.LENGTH_SHORT).show();
	      }
	    });
	    
	    //spinner
	    spinner=(Spinner)findViewById(R.id.cur_spinner);
	    List<Map<String, Object>> currencyspinner=SpinnerAdapter.getspinner3data();
	    SimpleAdapter currecyspin=new SimpleAdapter(this, currencyspinner, R.layout.spinner3_item, new String[]{"log","listname"}, new int[]{R.id.image,R.id.text});
		//给spinner添加adapter
		spinner.setAdapter(currecyspin);
		position=spinner.getSelectedItemPosition();
	    
	    
//button刷新
		Button btn_fresh = (Button)findViewById(R.id.btn_fresh);
		btn_fresh.setOnClickListener(this);
	    
//图表
		  mChart = (LineChart) findViewById(R.id.chart1);
	      mChart.setOnChartGestureListener(this);
	      mChart.setOnChartValueSelectedListener(this);
	      mChart.setDrawGridBackground(false);
	      //初始化
	      mChart.setNoDataTextDescription("You need to provide data for the chart.");
	      //设置部分属性
	      mChart.setDescription("");// 数据描述  
	      mChart.setHighlightEnabled(true);
	      mChart.setTouchEnabled(true);
	      mChart.setDragEnabled(false);
	      mChart.setScaleEnabled(true);
	      mChart.setPinchZoom(true);
	      mChart.setBackgroundColor(Color.WHITE);
//	      mChart.centerViewPort(1, 1);
	    
	      //markview
	      MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
	      mChart.setMarkerView(mv);
	      
	    //坐标轴设置
	      //LimitLine llXAxis = new LimitLine(10f, "Index 10");
	       // llXAxis.setLineWidth(4f);
	        //llXAxis.enableDashedLine(10f, 10f, 0f);
	        //llXAxis.setLabelPosition(LimitLabelPosition.POS_RIGHT);
	       // llXAxis.setTextSize(10f);

	        XAxis xAxis = mChart.getXAxis();
	        xAxis.setDrawGridLines(false);
	        xAxis.setDrawAxisLine(false);
	        xAxis.setPosition(XAxisPosition.BOTTOM); 
	        xAxis.setTextSize(BIND_AUTO_CREATE);
	   //     xAxis.addLimitLine(llXAxis);
	        
	        LimitLine ll1 = new LimitLine(7.5f, "高位警戒值");
	        ll1.setLineWidth(4f);
	        ll1.enableDashedLine(10f, 10f, 0f);
	        ll1.setLabelPosition(LimitLabelPosition.POS_LEFT);
	        ll1.setTextSize(10f);

	        LimitLine ll2 = new LimitLine(6.5f, "低位警戒值");
	        ll2.setLineWidth(4f);
	        ll2.enableDashedLine(10f, 10f, 0f);
	        ll2.setLabelPosition(LimitLabelPosition.POS_RIGHT);
	        ll2.setTextSize(10f);

	        YAxis leftAxis = mChart.getAxisLeft();
	        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
	        leftAxis.addLimitLine(ll1);
	        leftAxis.addLimitLine(ll2);
	        leftAxis.setAxisMaxValue(8.0f);
	        leftAxis.setAxisMinValue(6.0f);
	        leftAxis.setStartAtZero(false);
	        //leftAxis.setYOffset(20f);
	        leftAxis.enableGridDashedLine(10f, 10f, 0f);
	        
	        // limit lines are drawn behind data (and not on top)
	        leftAxis.setDrawLimitLinesBehindData(true);

	        mChart.getAxisRight().setEnabled(false);
	        
	        //初值设置
	        setData(50, 1);
	        
	        mChart.animateY(1000, Easing.EasingOption.EaseInOutQuart);
	        Legend l = mChart.getLegend();
	        l.setForm(LegendForm.LINE);
  
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
		
		 //freshTradeList();
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
	
	
	//用于刷新数据
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
		else if(serviceBound == true  ){
			mylist.clear();
			mylist=newlist;
		};
	}
	//用于设置数据
	private void setTradeList(){
		newlist.clear();
		for (int i=0;i<10;i++)
		{  TradeItem item=new TradeItem();
			
			if(i<5)
			{
				item=new TradeItem("卖",6.5-0.1*i+"",i+10+"");
				newlist.add(item);
			}else if(i>=5)
			{
				item=new TradeItem("买",6.5-0.1*i+"",i+12+"");
				newlist.add(item);
			}				
		}
	}

	//时间序列数据的存取
	//生成并储存
	public void testtimeserise()
	{
		
		List<TimeSerises> list =new ArrayList<TimeSerises>() ;
		TimeSerises item1=new TimeSerises();
        for (int i = 0; i < 40; i++) {
        	TimeSerises item=new TimeSerises();
            item.a_dateformat="9:30"+":"+i;
            item.a_time_currency_codenum=1;
            item.a_time_price=(float) (Math.random() * 0.5+ 6.5);
          // item.save(); 
            list.add(item);
        }
        
        item1.setTimeSeries(list);
        List<TimeSerises> test=item1.getTimeSerises(1);
        Log.i("shujuku",item1.getalltimeSerises().get(2).a_time_currency_codenum+"");
        Log.i("shujuku",item1.getalltimeSerises().get(2).a_time_price+"");
	}



	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		setTradeList();
		freshTradeList();
		homefragment.freshadapter(newlist);
	}


//图表数据设置
	private void setData(int count, float range) {
		TimeSerises item=new TimeSerises();
		testtimeserise();
		DateFormat time = DateFormat.getTimeInstance();//只显示出时分秒
		
		List<TimeSerises> list=new ArrayList<TimeSerises>();
		list=item.getTimeSerises(1);
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(list.get(i).a_dateformat);
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {

            float mult = range ;
//            float val = (float) (Math.random() * mult+ 6.5);//+ (float)
                                                           // ((mult *
                                                           // 0.1) / 10);
            
            float val=(float) list.get(i).a_time_price;
            yVals.add(new Entry(val, i));
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(yVals, "汇率时序数据");
        // set1.setFillAlpha(110);
        // set1.setFillColor(Color.RED);

        // set the line to be drawn like this "- - - - - -"
 
        set1.setAxisDependency(AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setCircleColor(Color.WHITE);
        set1.setLineWidth(2f);
        set1.setCircleSize(3f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);
        set1.setDrawValues(false);
        set1.setDrawFilled(true);
        set1.setDrawCircles(false);
//        set1.setDrawFilled(true);
        // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
        // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1); // add the datasets

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);
    }
//图表接口重写
	@Override
	public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onNothingSelected() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onChartLongPressed(MotionEvent me) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onChartDoubleTapped(MotionEvent me) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onChartSingleTapped(MotionEvent me) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onChartTranslate(MotionEvent me, float dX, float dY) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
 	
