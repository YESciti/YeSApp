package com.exchange.yes.app;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.exchange.yes.R;
import com.exchange.yes.net.MobileHttpClient;
import com.exchange.yes.pre.WidgetActivity;
import com.gc.materialdesign.widgets.Dialog;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AskEditblRatActivity extends Activity {
	 
	//处理交易的操作
			private Handler customTradeHandler = null;
			//当前上下文
			private Context context;
			private static String currencycode="1";
			private static String buysell="sell.action?";
			private static String amount=0+"";
			private static String Rate=6.2+"";
			private static boolean flag=false;
			TextView amountText;
			TextView rateText;
			//返回数据
			JSONObject returnobject;
			double returnRate;
			double returnAmount;
			
			RelativeLayout layout1;
			RelativeLayout layout2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_editbl_rat_trad);
		
		
		Intent intent=getIntent();
		 int position=intent.getIntExtra("position", 1);
		 	currencycode=intent.getStringExtra("currencycode");
		 	buysell=intent.getStringExtra("buysell");
		 	amount=intent.getStringExtra("amount");
		 	Rate=intent.getStringExtra("customrate");
		 	amountText=(TextView) findViewById(R.id.txt_camount); 
			rateText=(TextView) findViewById(R.id.textlay3_1);
			
			layout1=(RelativeLayout) findViewById(R.id.clay2);
			layout2=(RelativeLayout) findViewById(R.id.clay3);
		
//		对于剩余未交易成功部分，用户点击更改汇率按钮
//		REWRITE RATE AND OFFER A NEW ORDER
		findViewById(R.id.btn_submitcontin).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AskEditblRatActivity.this.finish();
			}
		});
//		对于剩余未交易成功部分，用户不改汇率，挂单等待
//		THE RATE UNCHAGED AND WAIT
		findViewById(R.id.btn_submitresult).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent adkEditIntent=new Intent(AskEditblRatActivity.this,HomepageActivity.class);
				AskEditblRatActivity.this.setResult(0);
				AskEditblRatActivity.this.finish();
			}
		});
		
		customTradeHandler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				int flag=msg.what;
				switch(flag){
				case 1:
					Toast.makeText(AskEditblRatActivity.this, "提交成功", Toast.LENGTH_SHORT).show();					
					break;
				case 2:
					Toast.makeText(AskEditblRatActivity.this, "提交失败", Toast.LENGTH_SHORT).show();					
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
				postServer();
				
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	 }

	//数据传送post
		private void postServer() 
				throws JSONException,UnsupportedEncodingException{
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("transactiontype", "1");
			paramMap.put("currencycode",currencycode);
			paramMap.put("amount", amount);
			paramMap.put("exchangerate", Rate);
			RequestParams params = new RequestParams(paramMap);
			MobileHttpClient.get(buysell, params, new JsonHttpResponseHandler(){
				@Override
				public void onFailure(int statusCode, org.apache.http.Header[] headers, java.lang.Throwable throwable, org.json.JSONObject errorResponse){
					// TODO Auto-generated method stub
					Message msg = new Message();
					//2提交超时
					msg.what = 2;
					customTradeHandler.sendMessage(msg);
					
				}
				@Override
				 public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONObject traderesult){
					try{//获取交易数据
						returnobject=traderesult.getJSONObject("success");
						flag=true;										
						if(flag){
							Message msg = new Message();
							//1提交成功
							msg.what = 1;
							customTradeHandler.sendMessage(msg);
//							JSONObject trade =  traderesult.getJSONObject("quickresult");
							
							returnRate = returnobject.getDouble("exchangerate");
							returnAmount=returnobject.getDouble("amount");
							setTexTView();
							Log.i("test914",returnAmount+"");
						}
						
					}catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
			});
		}
		
		public void setTexTView(){
			if(flag){
				layout1.setVisibility(View.VISIBLE);
				amountText.setText(returnAmount+"");
				
				layout2.setVisibility(View.VISIBLE);
				rateText.setText("共计获得"+returnRate+"美元");
			}
		}

}
