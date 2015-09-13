
package com.exchange.yes.util;


/** 
 * @作�?? newbie 
 * @email lianlupeng@gmail.com
 * @创建日期 2013-7-30 
 * @版本 V 1.0 
 * 
 */
public class Constant {

	//fragment 的tag
	public static final String CONSULT_FRAGMENT_TAG="consult_fragment_tag";
	
	public static final String CHAT_FRAGMENT_TAG="chat_fragment_tag";
	
	//os的类型，iphone android pc
	
	public static final String FACE_ANDROID="";
	public static final String FACE_IPHONE="";
	
	//message
	public static final int SUCCESS_POLL = 1;
	public static final int Failure_POLL = 2;
	public static final int handleFailureMessage_POLL = 3;
	public static final int sendFailureMessage_POLL = 4;
	public static final int POLL_ERROR_CODE = 5;
	
	//手机的类�?
	public static final String ANDROID_ICON = "android";
	public static final String IOS_ICON = "ios";
	public static final String WINDOWS_ICON = "window";
	public static final String WEIXIN_ICON = "weixin";
	
	//从本地数据库中加载数据的条数默认�?20
	public static final int STEP_LOAD_FROM_DB = 20;
	//从本地数据库中加载数据的条数默认�?20
	public static final int STEP_LOAD_FROM_NETWORK = 20;
	/**
	 * 每次刷新�?获得的数据量*/
	public static final int Single_STEP_LOAD = 20;
	/**
	 * 会话列表�?能允许的�?大数�?*/
	public static final int MAX_CONSULTMSGCOUNT = 30;
	//数据库存储数据的数据，默认每个客户存�?50条数�?================chatFragment
	public static final int COUNT_PERSISTENT_MAX = 500;
	//数据库存储的数据，默认每个客服存储对应的50个用户的信息=========ConsultFragment
	public static final int  COUNT_PERSISTENT_USERS = 100;
	//离线客服留言的数据是没有存放在本地数据库�?====================LeaveMsgFragment
	
	//客户端认定顾客下线的时间设置
	public static final int OFFLINE_TIME_COUNT=90;
	
	//消息的发送状�?
	public static final int MESSAGE_IS_SENDED = 0;
	public static final int MESSAGE_IS_SENDING=1;
	public static final int MESSAGE_IS_FAILED = 2;
	
	//消息的发送�?�知
	public static final int VIBRATOR_FROM_BACK =0;
	public static final int VIBRATOR_FROM_WINDOW =1;
	
	//后台的两种消�?
	public static final int MESSAGE_POLLED = 0;
	public static final int MESSAGE_NOT_POLLED = 1;
	
	//客户列表是否删除的状态位�?
	public static final int CONSULT_ITEM_DELETE = 1;
	public static final int CONSULT_ITEM_NOT_DELETE = 0;
	//MessageService notification id 
	public static final int MESSAGE_SERVICE_NOTIFICATION_ID = 2013;
	//appinfo的默认id�?1
	public static final int APPINFO_DEFAULT_ID = 1;
	//消息的类�?
	public static final String POLL_NORMAL_MESSAGE="mb_normal";
	public static final String POLL_ONLINE_MESSAGE="mobile_online";
	//设置全局的超时时�?30s
	public static final int POLL_TIMEOUT = 30000;
	

}
