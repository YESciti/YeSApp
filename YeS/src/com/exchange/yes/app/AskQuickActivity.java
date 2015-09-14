package com.exchange.yes.app;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.exchange.yes.R;
import com.exchange.yes.R.id;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.exchange.yes.dep.FloatingActionButton;
import com.exchange.yes.dep.FloatingActionsMenu;
import com.exchange.yes.net.MobileHttpClient;
import com.gc.materialdesign.widgets.Dialog;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.LayoutRipple;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AskQuickActivity extends Activity {
	  //处理交易的操作
		private Handler quickTradeHandler = null;
		//当前上下文
		private Context context;
		private static String success="1";
		private static String time="1:31";
		private static String tradeid="00001";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ask_quick);
		
		

		
//		
//		对于剩余未交易成功部分，用户点击更改汇率按钮
//		REWRITE RATE AND OFFER A NEW ORDER
		findViewById(R.id.btn_qsubmitcontin).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AskQuickActivity.this.finish();
			}
		});
//		对于剩余未交易成功部分，用户不改汇率，挂单等待
//		THE RATE UNCHAGED AND WAIT
		findViewById(R.id.btn_qsubmitresult).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent adkEditIntent=new Intent(AskQuickActivity.this,HomepageActivity.class);
				AskQuickActivity.this.setResult(0);
				AskQuickActivity.this.finish();				
			}			
		});
		//开启子线程
		quickTradeHandler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				int flag=msg.what;
				switch(flag){
				case 1:
					Toast.makeText(AskQuickActivity.this, "提交成功", Toast.LENGTH_SHORT).show();					
					break;
				case 2:
					Toast.makeText(AskQuickActivity.this, "提交失败", Toast.LENGTH_SHORT).show();					
					break;
				default:
					break;
				}
					
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ask_editbl_rat, menu);
		return true;
	}
	@Override
	protected void onStart(){
		super.onStart();
		 try {
				postServer("" ,"","");
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	 }

	//数据传送post
		private void postServer(String currencyid ,String askbid, String number) 
				throws JSONException,UnsupportedEncodingException{
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("transactiontype", "0");
			paramMap.put("currencycode","1");
			paramMap.put("amount", "123");
			RequestParams params = new RequestParams(paramMap);
			MobileHttpClient.get("buy.action?", params, new JsonHttpResponseHandler(){
				@Override
				public void onFailure(int statusCode, org.apache.http.Header[] headers, java.lang.Throwable throwable, org.json.JSONObject errorResponse){
					// TODO Auto-generated method stub
					Message msg = new Message();
					//2提交超时
					msg.what = 2;
					quickTradeHandler.sendMessage(msg);
					
				}
				@Override
				 public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONObject traderesult){
					try{//获取交易数据
						//boolean flag = traderesult.getBoolean("success");
						if(true){
							Message msg = new Message();
							//1提交成功
							msg.what = 1;
							quickTradeHandler.sendMessage(msg);
//							JSONObject trade =  traderesult.getJSONObject("quickresult");
							JSONObject returnobject=traderesult.getJSONObject("success");
							Double exchangeratef=returnobject.getDouble("exchangerate");
							Log.i("test914",exchangeratef+"");
						}
						
					}catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
			});
		}
		
}
