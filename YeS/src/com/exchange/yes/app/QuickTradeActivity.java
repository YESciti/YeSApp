package com.exchange.yes.app;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.exchange.yes.net.MobileHttpClient;
import com.gc.materialdesign.views.LayoutRipple;
import com.gc.materialdesign.widgets.Dialog;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.Toast;

public class QuickTradeActivity extends Activity implements OnClickListener{

	
	
	
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
		setContentView(R.layout.activity_quick_trade);
		context = this;
		
		Intent intent=getIntent();
		int position=intent.getIntExtra("position", 1);
		LayoutRipple back=(LayoutRipple) findViewById(R.id.item_back_quick);
		
		Spinner spinner=(Spinner) findViewById(R.id.spinner_bizhong_edit);
		spinner.setSelection(position);
		back.setOnClickListener(this);
		//开启子线程
		quickTradeHandler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				int flag=msg.what;
				switch(flag){
				case 1:
					Toast.makeText(QuickTradeActivity.this, "提交成功", Toast.LENGTH_SHORT).show();					
					break;
				case 2:
					Toast.makeText(QuickTradeActivity.this, "提交失败", Toast.LENGTH_SHORT).show();					
					break;
				default:
					break;
				}
					
			}
		};
		
		
		
		
		
		
		
		
		
//		dialog of confirm your deal 
		findViewById(R.id.btn_submitorder_quick).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View flatButton) {
				Dialog dialog = new Dialog(QuickTradeActivity.this, "提示", "点击确定将直接交易！");
				dialog.setOnAcceptButtonClickListener(new OnClickListener() {
					@Override
					public void onClick(View v){
						Intent adkEditIntent=new Intent(QuickTradeActivity.this,AskEditblRatActivity.class);
					    adkEditIntent.putExtra("success", success);
					    adkEditIntent.putExtra("time", time);
					    adkEditIntent.putExtra("tradeid", tradeid);
						startActivity(adkEditIntent);
						Toast.makeText(QuickTradeActivity.this, "直接交易", 1).show();
					}
				});
				dialog.setOnCancelButtonClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quick_trade, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		QuickTradeActivity.this.finish();
	}
	
	//数据传送post
	private void postServer(String currencyid ,String askbid, String number) 
			throws JSONException,UnsupportedEncodingException{
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("currencyid", currencyid);
		paramMap.put("askbid", askbid);
		paramMap.put("number", number);
		RequestParams params = new RequestParams(paramMap);
		MobileHttpClient.get("quicktrade.action", params, new JsonHttpResponseHandler(){
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
					boolean flag = traderesult.getBoolean("success");
					if(flag){
						Message msg = new Message();
						//1提交成功
						msg.what = 1;
						quickTradeHandler.sendMessage(msg);
//						JSONObject trade =  traderesult.getJSONObject("quickresult");
						success=traderesult.getString("success");
						time=traderesult.getString("time");
						tradeid=traderesult.getString("tradeid");						
					}
					
				}catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		});
	}
	
	
	
	

}
