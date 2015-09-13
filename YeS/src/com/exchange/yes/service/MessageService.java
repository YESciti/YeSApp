package com.exchange.yes.service;


/** 
 * @author pc
 * @email lianlupeng@gmail.com
 * @创建日期 2013-11-4 
 * @版本 V 1.0 
 * setrvice完全作为路由层，作为消息的起始站
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
	//�ֶ���
	public static String userid;
	public static String userphone;
	
	
	//������
	public static OnGetMessageListener onGetMessageListener;
	public boolean isCancel=false;
	public boolean isActivityPause=false;
	public boolean ispoll=false;
	public static final String ACTION = "com.exchange.yes.service.PollingService";
//	 private final IBinder mBinder = new LocalBinder();  
	private String currencyIdString="1";
	private NotificationManager mManager;

	
	
//	private OnServiceUIChangeListener ServiceUIChangeListener;
//	//�ص��ӿ�
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

		// ����ʱ��������Service
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
		 * ��ȡ��ǰService��ʵ��
		 * 
		 * @return
		 */
		public MessageService getService() {
			return MessageService.this;
		}
	}
	/**
	 *  ���½��ȵĻص��ӿ� ������Ϣ�Ĳ���������ͨ���ص���ɵ� such as updateChatFragment
	 * updateConsultFragment
	 */



	/**
	 * ��ȡ��ǰService��ʵ��
	 * 
	 * @return
	 */


	/*
	 *���������� isExistThenUpdate �������30���Ự
	 */
	

	// 处理新上线用�?
	//���³���
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
		// �����������
		Random rd=new Random();
		// ���50~250ms��һ�������
		int delaytime = (int)(rd.nextDouble() * 200 + 50);
		
		System.out.println("test423 MessageService repoll");
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				// ���·�poll
				
				poll();
			}
		}, delaytime);
		
	}
	
	
	/**
	 * ģ����������ÿ���Ӹ���һ��
	 */
	public void poll() {
		
		if (!isCancel) {		
			// ���ڵ�ˢ���б�
			if (onGetMessageListener != null)
				onGetMessageListener.onRefreshState();
			MobileManagerClient.get(getApplicationContext(),  "userver/poll", getRequestParams()
					,  new JsonHttpResponseHandler() {
				@Override
				 public void onSuccess(int statusCode, org.apache.http.Header[] headers
						 , org.json.JSONObject jsonobj){
					try {//����Ƿ�ɹ�
						boolean flag = jsonobj.getBoolean("success");
						//��ȡcurrencyid����
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
								
								//��ȡ��ͨ����
								if (PollObject.getString(
												"toId").equals(userid)){
									trade=new TradeItem(
											PollObject
											.getString("askbid")
											, PollObject
											.getString("price")
											, PollObject
											.getString("number"));
									HomepageActivity.mylist.add(trade);//���ݴ���
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
					
					//2��¼��ʱ
					
					
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
	 * @类名 MessageService.java
	 * @包名 com.lege.mobilechat.service
	 * @作�?? ChunTian
	 * @时间 2013�?12�?27�? 下午12:39:47
	 * @Email ChunTian1314@vip.qq.com
	 * @版本 V1.0
	 * @功能 创建新用户欢迎消�?
	 */


	
	//������ ����requestparams Ҫ��ȷ�������ֵ
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
	// 构�?�RequestParams


	// 消息的id.0

	// 初始化�?�知栏配�?
	private void initNotifiManager() {
		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
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
