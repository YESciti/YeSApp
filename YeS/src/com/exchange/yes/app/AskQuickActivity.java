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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AskQuickActivity extends Activity {
	  //�����׵Ĳ���
		private Handler quickTradeHandler = null;
		//��ǰ������
		private Context context;
		private static String currencycode="1";
		private static String buysell="sell.action?";
		private static String amount=0+"";
		private static boolean flag=false;
		TextView amountText;
		TextView rateText;
		//��������
		JSONObject returnobject;
		double returnRate;
		double returnAmount;
		
		//layout
		RelativeLayout layout1;
		RelativeLayout layout2;
		
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ask_quick);
		
		
		Intent intent=getIntent();
		currencycode=intent.getStringExtra("currencycode");
		buysell=intent.getStringExtra("buysell");
		amount=intent.getStringExtra("amount");
		
		
		
		amountText=(TextView) findViewById(R.id.txt_amount); 
		rateText=(TextView) findViewById(R.id.textlay3_1_rate);
		
		layout1=(RelativeLayout) findViewById(R.id.qlay2);
		layout2=(RelativeLayout) findViewById(R.id.qlay3);
		
//		
//		����ʣ��δ���׳ɹ����֣��û�������Ļ��ʰ�ť
//		REWRITE RATE AND OFFER A NEW ORDER
		findViewById(R.id.btn_qsubmitcontin).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AskQuickActivity.this.finish();
			}
		});
//		����ʣ��δ���׳ɹ����֣��û����Ļ��ʣ��ҵ��ȴ�
//		THE RATE UNCHAGED AND WAIT
		findViewById(R.id.btn_qsubmitresult).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent adkEditIntent=new Intent(AskQuickActivity.this,HomepageActivity.class);
				AskQuickActivity.this.setResult(0);
				AskQuickActivity.this.finish();				
			}			
		});
		//�������߳�
		quickTradeHandler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				int flag=msg.what;
				switch(flag){
				case 1:
					Toast.makeText(AskQuickActivity.this, "�ύ�ɹ�", Toast.LENGTH_SHORT).show();					
					break;
				case 2:
					Toast.makeText(AskQuickActivity.this, "�ύʧ��", Toast.LENGTH_SHORT).show();					
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

	//���ݴ���post
		private void postServer() 
				throws JSONException,UnsupportedEncodingException{
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("transactiontype", "0");
			paramMap.put("currencycode",currencycode);
			paramMap.put("amount", amount);
			RequestParams params = new RequestParams(paramMap);
			MobileHttpClient.get(buysell, params, new JsonHttpResponseHandler(){
				@Override
				public void onFailure(int statusCode, org.apache.http.Header[] headers, java.lang.Throwable throwable, org.json.JSONObject errorResponse){
					// TODO Auto-generated method stub
					Message msg = new Message();
					//2�ύ��ʱ
					msg.what = 2;
					quickTradeHandler.sendMessage(msg);
					
				}
				@Override
				 public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONObject traderesult){
					try{//��ȡ��������
						returnobject=traderesult.getJSONObject("success");
						flag=true;										
						if(flag){
							Message msg = new Message();
							//1�ύ�ɹ�
							msg.what = 1;
							quickTradeHandler.sendMessage(msg);
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
				rateText.setText("���ƻ��"+returnRate+"��Ԫ");
			}
		}
}
