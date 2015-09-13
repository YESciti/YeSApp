package com.exchange.yes.app;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.json.JSONException;

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
import android.widget.Spinner;
import android.widget.Toast;

import com.exchange.yes.R;
import com.exchange.yes.net.MobileHttpClient;
import com.gc.materialdesign.views.Button;
import com.gc.materialdesign.views.LayoutRipple;
import com.gc.materialdesign.widgets.Dialog;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class EditblRatTradActivity extends Activity implements OnClickListener{
	//参数传递
	
	Button deal;
	
	//处理交易的操作
		private Handler customTradeHandler = null;
		//当前上下文
		private Context context;
		private static String success="1";
		private static String time="1:31";
		private static String tradeid="00001";
		private static String customrate="6.2";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ask_editbl_rat);
		
		Bundle b=getIntent().getExtras();
		int position=b.getInt("position", 1);
		
		context = this;
		LayoutRipple back=(LayoutRipple) findViewById(R.id.item_back_edit);		
		back.setOnClickListener(this);
		
		Spinner spinner=(Spinner) findViewById(R.id.spinner__edit);
		spinner.setSelection(position);
		
		customTradeHandler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				int flag=msg.what;
				switch(flag){
				case 1:
					Toast.makeText(EditblRatTradActivity.this, "提交成功", Toast.LENGTH_SHORT).show();					
					break;
				case 2:
					Toast.makeText(EditblRatTradActivity.this, "提交失败", Toast.LENGTH_SHORT).show();					
					break;
				default:
					break;
				}
					
			}
		};
		
		
//		dialog of confirm your deal 
		findViewById(R.id.btn_submitorder_edit).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View flatButton) {
				Dialog dialog = new Dialog(EditblRatTradActivity.this, "提示", "确认提交您目前填写的订单");
				dialog.setOnAcceptButtonClickListener(new OnClickListener() {
					@Override
					public void onClick(View v){
						Intent adkEditIntent=new Intent(EditblRatTradActivity.this,AskEditblRatActivity.class);
						adkEditIntent.putExtra("success", success);
					    adkEditIntent.putExtra("time", time);
					    adkEditIntent.putExtra("tradeid", tradeid);
					    adkEditIntent.putExtra("rate",customrate);
						startActivity(adkEditIntent);
						Toast.makeText(EditblRatTradActivity.this, "Click accept button", 1).show();
					}
				});
				dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editbl_rat_trad, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent=null;
		Log.i("mine","gone1");
		
		switch(arg0.getId()){
		case R.id.item_back_edit:
		
			EditblRatTradActivity.this.finish();
			
			break;
		}
	}
	
	//数据传送post
		private void postServer(String currencyid ,String askbid, String number,String rate) 
				throws JSONException,UnsupportedEncodingException{
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("currencyid", currencyid);
			paramMap.put("askbid", askbid);
			paramMap.put("number", number);
			paramMap.put("rate", rate);
			RequestParams params = new RequestParams(paramMap);
			MobileHttpClient.get("customtrade.action", params, new JsonHttpResponseHandler(){
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
						boolean flag = traderesult.getBoolean("success");
						if(flag){
							Message msg = new Message();
							//1提交成功
							msg.what = 1;
							customTradeHandler.sendMessage(msg);
//							JSONObject trade =  traderesult.getJSONObject("quickresult");
							success=traderesult.getString("success");
							time=traderesult.getString("time");
							tradeid=traderesult.getString("tradeid");	
							customrate=traderesult.getString("rate");
						}
						
					}catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
			});
		}

}
