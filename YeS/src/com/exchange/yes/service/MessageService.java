package com.exchange.yes.service;


/** 
 * @author pc
 * @email lianlupeng@gmail.com
 * @鍒涘缓鏃ユ湡 2013-11-4 
 * @鐗堟湰 V 1.0 
 * setrvice瀹屽叏浣滀负璺敱灞傦紝浣滀负娑堟伅鐨勮捣濮嬬珯
 */

import java.util.HashMap;
import java.util.Random;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.exchange.yes.app.HomepageActivity;
import com.exchange.yes.db.TradeItem;
import com.exchange.yes.util.MobileManagerClient;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MessageService extends Service {
	//字段区
	public static String userid;
	public static String userphone;
	
	
	//参数区
	public static OnGetMessageListener onGetMessageListener;
	public boolean isCancel=false;
	public boolean isActivityPause=false;
	public boolean ispoll=false;
	public static final String ACTION = "com.exchange.yes.service.PollingService";
//	 private final IBinder mBinder = new LocalBinder();  
	private String currencyIdString="1";
	private NotificationManager mManager;

	
	
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

		// 销毁时重新启动Service
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
		 * 获取当前Service的实例
		 * 
		 * @return
		 */
		public MessageService getService() {
			return MessageService.this;
		}
	}
	/**
	 *  更新进度的回调接口 所有消息的操作，都是通过回掉完成的 such as updateChatFragment
	 * updateConsultFragment
	 */



	/**
	 * 获取当前Service的实例
	 * 
	 * @return
	 */


	/*
	 *辅助方法： isExistThenUpdate 保持最多30条会话
	 */
	

	// 澶勭悊鏂颁笂绾跨敤鎴?
	//重新尝试
	public void retry() {

		if (!isCancel) {
			
			repoll();
		} else {
			if (MobileManagerClient.polling) {
				
				
				MobileManagerClient.polling = false;
			}
		}
	}
	
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
	 * 模拟下载任务，每秒钟更新一次
	 */
	public void poll() {
		
		if (!isCancel) {		
			// 定期的刷新列表
			if (onGetMessageListener != null)
				onGetMessageListener.onRefreshState();
			MobileManagerClient.get(getApplicationContext(),  "userver/poll", getRequestParams()
					,  new JsonHttpResponseHandler() {
				@Override
				 public void onSuccess(int statusCode, org.apache.http.Header[] headers
						 , org.json.JSONObject jsonobj){
					try {//检测是否成功
						boolean flag = jsonobj.getBoolean("success");
						//获取currencyid数据
						if (flag) {
							JSONArray tradeArray = jsonobj
									.getJSONArray("price");
							int lastMsg = 0;
							TradeItem trade=null;
							
							for (int i = 0; i < tradeArray.length(); i++) {
								JSONObject PollObject=tradeArray.getJSONObject(i);
								String currencyId = "";
								if (PollObject.has("_id")) {
									currencyId = PollObject
											.getString("_id");
									if( currencyId != null ){
										currencyIdString = String.valueOf(currencyId)
												+ "," + currencyIdString;
									}
								}
								
								//获取普通数据
								if (PollObject.getString(
												"toId").equals(userid)){
									trade=new TradeItem(
											PollObject
											.getString("askbid")
											, PollObject
											.getString("price")
											, PollObject
											.getString("number"));
									HomepageActivity.mylist.add(trade);//数据储存
								}
								
						}}
					}catch (Exception e) {
						//retry();
						System.out.println("pollhandle+ pollsuccess but handle Exception"+e.getMessage());
//						Log.d("pollhandle", "pollsuccess but handle Exception " + e.getMessage());
						e.printStackTrace();
						//isCancel = false;
					}	
					
				}
				@Override
				public void onFailure(int statusCode, org.apache.http.Header[] headers, java.lang.Throwable throwable, org.json.JSONObject errorResponse){
					// TODO Auto-generated method stub
					
					//2登录超时
					
					
					retry();
				}
				
					    

						
			
				
			});
		}
						
	}

	public void cancelGetMessage() {
		isCancel = true;
	}

	public void startGetMessage() {
		isCancel = false;
	}

	/**
	 * 
	 * @param chatMessageGet
	 * @param msgPollObject
	 * @绫诲悕 MessageService.java
	 * @鍖呭悕 com.lege.mobilechat.service
	 * @浣滆?? ChunTian
	 * @鏃堕棿 2013骞?12鏈?27鏃? 涓嬪崍12:39:47
	 * @Email ChunTian1314@vip.qq.com
	 * @鐗堟湰 V1.0
	 * @鍔熻兘 鍒涘缓鏂扮敤鎴锋杩庢秷鎭?
	 */


	
	//兰永坤 构造requestparams 要和确定具体的值
	private RequestParams getRequestParams() {
		RequestParams requestParams = null;
		HashMap<String, String> paramMap = new HashMap<String, String>();
		// sPhelper = new SPhelper(getActivity());
		;// LoginActivity.inloginMonitor.unitid
		paramMap.put("usid", userid);
		paramMap.put("uscookie", userphone);
		paramMap.put("deviceType", "android");
		System.out.println("poll userid" + userid);
	
		requestParams = new RequestParams(paramMap);
		return requestParams;
	}
	// 鏋勯?燫equestParams


	// 娑堟伅鐨刬d.0

	// 鍒濆鍖栭?氱煡鏍忛厤缃?
	private void initNotifiManager() {
		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		//pm = (PowerManager)getSystemService(POWER_SERVICE);
	}

	// 寮瑰嚭Notification
//	private void showNotification(ChatMessage messageInMap) {
//		System.out.println("showNotification");
//		Notification mNotification = new Notification(R.drawable.ic_launcher,
//				"鏈夋柊娑堟伅", System.currentTimeMillis());
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
//				messageInMap.fromName + "鍙戞潵鏂版秷鎭?");
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

	

	// 鏄剧ず澹伴煶


	// 鍙戦?佷竴涓┖鐨勮姹傦紝娑堥櫎瀹㈡埛绔櫥闄嗭紝缁欐湇鍔″櫒甯︽潵鐨刡ug

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
//	// 閲婃斁璁惧鐢垫簮閿?
//	private void releaseWakeLock() {
//		if (null != wakeLock) {
//			wakeLock.release();
//			wakeLock = null;
//		}
//	}
	
	// 鑾峰彇1-10鐨勯殢鏈烘暟
	private int getRandomTime() {
		Random rd=new Random();//闅忔満鏁扮敓鎴愬櫒
		int j=(int)(rd.nextDouble() * 10);
		return j;
	}


}
