package com.exchange.yes;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;

import com.exchange.yes.R;


import com.exchange.yes.service.*;
import com.lege.mc.app.SPhelper;
import com.lege.mc.db.Monitor;
import com.lege.mc.service.MessageService;
import com.lege.mc.utils.MobileChatClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnColorSelectedListener{
	private  Context context;
	//后台轮询服务
	private  MessageService localService;
	private boolean serviceBound = false;  
	private ServiceConnection serviceConnection = new ServiceConnection() {  
		  
        @Override  
        public void onServiceConnected(ComponentName className,  
                IBinder service) {  
            // 已经绑定了LocalService，强转IBinder对象，调用方法得到LocalService对象  
            MessageService.MsgBinder binder = ( MessageService.MsgBinder) service;  
            
            localService = binder.getService();  
            System.out.println("test416 onServiceConnected onCreate");
            MessageService.onGetMessageListener = new Listener();
            serviceBound = true;  
        }  
  
        @Override  
        public void onServiceDisconnected(ComponentName arg0) {  
        	serviceBound = false;  
        	System.out.println("test416 onServiceDisconnected onCreate");
        }  
    };  
	int backgroundColor = Color.parseColor("#1E88E5");
	ButtonFloatSmall buttonSelectColor;

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buttonSelectColor = (ButtonFloatSmall) findViewById(R.id.buttonColorSelector);
        buttonSelectColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ColorSelector colorSelector = new ColorSelector(MainActivity.this, backgroundColor, MainActivity.this);
				colorSelector.show();
			}
		});
        
        LayoutRipple layoutRipple = (LayoutRipple) findViewById(R.id.itemButtons);
        
        
        setOriginRiple(layoutRipple);
        
        layoutRipple.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,ButtonsActivity.class);
				intent.putExtra("BACKGROUND", backgroundColor);
				startActivity(intent);
			}
		});
        layoutRipple = (LayoutRipple) findViewById(R.id.itemSwitches);
        
        
        setOriginRiple(layoutRipple);
        
        layoutRipple.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View arg0) {
        		Intent intent = new Intent(MainActivity.this,SwitchActivity.class);
        		intent.putExtra("BACKGROUND", backgroundColor);
        		startActivity(intent);
        	}
        });
        layoutRipple = (LayoutRipple) findViewById(R.id.itemProgress);
        
        
        setOriginRiple(layoutRipple);
        
        layoutRipple.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View arg0) {
        		Intent intent = new Intent(MainActivity.this,ProgressActivity.class);
        		intent.putExtra("BACKGROUND", backgroundColor);
        		startActivity(intent);
        	}
        });
        layoutRipple = (LayoutRipple) findViewById(R.id.itemWidgets);
        
        
        setOriginRiple(layoutRipple);
        
        layoutRipple.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View arg0) {
        		Intent intent = new Intent(MainActivity.this,WidgetActivity.class);
        		intent.putExtra("BACKGROUND", backgroundColor);
        		startActivity(intent);
        	}
        });
        
        	//绑定service
        context = getApplicationContext();
		if(!serviceBound)
		{
			Intent poll_service_intent = new Intent(context, MessageService.class);
			poll_service_intent.setAction(MessageService.ACTION);
		    bindService(poll_service_intent, serviceConnection, Context.BIND_AUTO_CREATE);  
		}
    }
    
	private void setOriginRiple(final LayoutRipple layoutRipple){
    	
    	layoutRipple.post(new Runnable() {
			
			@Override
			public void run() {
				View v = layoutRipple.getChildAt(0);
		    	layoutRipple.setxRippleOrigin(ViewHelper.getX(v)+v.getWidth()/2);
		    	layoutRipple.setyRippleOrigin(ViewHelper.getY(v)+v.getHeight()/2);
		    	
		    	layoutRipple.setRippleColor(Color.parseColor("#1E88E5"));
		    	
		    	layoutRipple.setRippleSpeed(30);
			}
		});
    	
    }

	@Override
	public void onColorSelected(int color) {
		backgroundColor = color;
		buttonSelectColor.setBackgroundColor(color);
	}  
	// 按下home键进行的处理   
		@Override
	protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			//解绑定
					if(serviceBound)
					{
						Intent poll_service_intent = new Intent(context, MessageService.class);
						poll_service_intent.setAction(MessageService.ACTION);
						stopService(poll_service_intent);
						
						unbindService(serviceConnection);
						serviceBound = false;  
						
					}
		
			RequestParams requestParams = null;
			HashMap<String, String> paramMap = new HashMap<String, String>();
			String mDeviceIdStr = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getDeviceId();
			Log.i("uuid", mDeviceIdStr);
			
			//调用JPush API设置Tag
			
//			Set<String> tagSet = new LinkedHashSet<String>();
//			tagSet.add(mDeviceIdStr);
//			JPushInterface.setAliasAndTags(getApplicationContext(), null, tagSet, null);
			
			// 设置向上请求的参数
		

			// 设置这个之后，才能真正把异步的那个循环请求取消掉
			localService.isCancel = true;
			System.out.println("test423 MessageService.isCancel"+localService.isCancel);

			Log.i("service", "test423 onStop"+serviceBound);

			localService.isActivityPause = true;

		}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("test415 MainTabActivity onDestroy");
		super.onDestroy();

		if(serviceBound)
		{
			unbindService(serviceConnection);
//			Intent poll_service_intent = new Intent(context, MessageService.class);
//			poll_service_intent.setAction(MessageService.ACTION);
//			stopService(poll_service_intent);
		}
	
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
//		 启动 poll service 服务
//		Intent poll_service_intent = new Intent(context, MessageService.class);
//		poll_service_intent.setAction(MessageService.ACTION);
//		if(serviceBound)
//		{
//			startService(poll_service_intent);
//		}
		if(!serviceBound)
		{
			Intent poll_service_intent = new Intent(context, MessageService.class);
			poll_service_intent.setAction(MessageService.ACTION);
		    bindService(poll_service_intent, serviceConnection, Context.BIND_AUTO_CREATE);  
		    serviceBound = true;  
		}
		System.out.println("test423 MainTabActivity onRestart MessageService"+serviceBound);
	}
	
	class Listener implements OnGetMessageListener {
		
	}


}
