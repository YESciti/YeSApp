package com.exchange.yes.service;


/** 
 * @author pc
 * @email lianlupeng@gmail.com
 * @创建日期 2013-11-4 
 * @版本 V 1.0 
 * setrvice完全作为路由层，作为消息的起始站
 */

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.widget.RemoteViews;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MessageService extends Service {
	public OnGetMessageListener onGetMessageListener;
	public boolean isCancel=false;
	public boolean isActivityPause=false;
	public static final String ACTION = "com.exchange.yes.service.PollingService";
//	 private final IBinder mBinder = new LocalBinder();  


	
	
//	private OnServiceUIChangeListener ServiceUIChangeListener;
//	//回调接口
//	public void SetServiceUIChangeListener(OnServiceUIChangeListener onServiceUIChangeListener) {  
//    this.ServiceUIChangeListener = onServiceUIChangeListener;  
//}  
	//private PowerManager pm;
	//private WakeLock wakeLock = null;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.d("MessageService", "MessageService onCreate() executed");

		
		initNotifiManager();
		super.onCreate();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("MessageService", "test416 MessageServiceonDestroy() executed");

		// �?毁时重新启动Service
//		PollingUtils.startPollingService(MessageService.this,
//				getRandomTime(), MessageService.class,
//				MessageService.ACTION);
		super.onDestroy(); 
		
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.d("MessageService", "test423 MessageServiceonStartCommand() executed");
		
		
	
		
		flags = START_STICKY;
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.d("MessageService", "test423 MessageServic onStart() executed");
	}
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.d("MessageService", "test423 MessageService onBind() executed polling");
		
//		}
		return new MsgBinder();
	}

	public class MsgBinder extends Binder {
		/**
		 * 获取当前Service的实�?
		 * 
		 * @return
		 */
		public MessageService getService() {
			return MessageService.this;
		}
	}
	/**
	 * 更新进度的回调接�? �?有消息的操作，都是�?�过回掉完成�? such as updateChatFragment
	 * updateConsultFragment
	 */



	/**
	 * 获取当前Service的实�?
	 * 
	 * @return
	 */


	/*
	 * 辅助方法�? isExistThenUpdate 保持�?�?30条会�?
	 */
	

	// 处理新上线用�?
	

	
	public void repoll() { 
		// 随机数生成器
		Random rd=new Random();
		// 获得50~250ms的一个随机数
		int delaytime = (int)(rd.nextDouble() * 200 + 50);
		
		System.out.println("test423 MessageService repoll");
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				// 重新发poll
				
				poll();
			}
		}, delaytime);
		
	}
	
	
	/**
	 * 模拟下载任务，每秒钟更新�?�?
	 */
	public void poll() {}

	public void cancelGetMessage() {}

	public void startGetMessage() {}

	/**
	 * 
	 * @param chatMessageGet
	 * @param msgPollObject
	 * @类名 MessageService.java
	 * @包名 com.lege.mobilechat.service
	 * @作�?? ChunTian
	 * @时间 2013�?12�?27�? 下午12:39:47
	 * @Email ChunTian1314@vip.qq.com
	 * @版本 V1.0
	 * @功能 创建新用户欢迎消�?
	 */


	// 构�?�RequestParams


	// 消息的id.0

	// 初始化�?�知栏配�?
	private void initNotifiManager() {
//		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//pm = (PowerManager)getSystemService(POWER_SERVICE);
	}

	// 弹出Notification
//	private void showNotification(ChatMessage messageInMap) {
//		System.out.println("showNotification");
//		Notification mNotification = new Notification(R.drawable.ic_launcher,
//				"有新消息", System.currentTimeMillis());
//		;
//		Intent intent = new Intent(this, MainTabActivity.class);
//		// toconsultlist="toconsultlist";
//		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		mNotification.contentView = new RemoteViews(getPackageName(),
//				R.layout.notification_item_layout);
//		PendingIntent pendingIntent = PendingIntent.getActivity(
//				getApplicationContext(), 0, intent, 0);
//		mNotification.contentView.setTextViewText(R.id.notification_content,
//				messageInMap.content);
//		mNotification.contentView.setTextViewText(R.id.notification_name,
//				messageInMap.fromName + "发来新消�?");
//		mNotification.number = 1;
//		mNotification.contentView.setImageViewResource(R.id.notification_image,
//				R.drawable.ic_launcher);
//		// mNotification.contentView.setTextViewText(R.id.notifcation_time,"2013.11.15");
//		mNotification.defaults |= Notification.DEFAULT_LIGHTS;
//		mNotification.ledARGB = 0xff00ff00;
//		mNotification.ledOnMS = 300;
//		mNotification.ledOffMS = 1000;
//		mNotification.flags |= Notification.FLAG_SHOW_LIGHTS;
//		mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
//		mNotification.contentIntent = pendingIntent;
//		mManager.notify(Constant.MESSAGE_SERVICE_NOTIFICATION_ID, mNotification);
//	}

	

	// 显示声音


	// 发�?�一个空的请求，消除客户端登陆，给服务器带来的bug

//	private void acquireWakeLock() {
//		if (null == wakeLock) {
//			
//			wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK
//					| PowerManager.ON_AFTER_RELEASE, "PostLocationService");
//			if (null != wakeLock) {
//				wakeLock.acquire();
//			}
//		}
//	}
//
//	// 释放设备电源�?
//	private void releaseWakeLock() {
//		if (null != wakeLock) {
//			wakeLock.release();
//			wakeLock = null;
//		}
//	}
	
	// 获取1-10的随机数
	private int getRandomTime() {
		Random rd=new Random();//随机数生成器
		int j=(int)(rd.nextDouble() * 10);
		return j;
	}


}
