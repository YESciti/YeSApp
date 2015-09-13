package com.exchange.yes.app;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.exchange.yes.R;
import com.exchange.yes.R.id;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.exchange.yes.net.MobileHttpClient;
import com.exchange.yes.pre.HomeActivity;
import com.gc.materialdesign.views.Button;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.exchange.yes.util.IsNetWorkAvailable;
import com.exchange.yes.util.MobileManagerClient;

public class LoginActivity2 extends Activity implements OnClickListener {

	//�����¼������صĲ���
	private Handler loginInfoinfoHandler = null;
	//��ǰ������
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login2);
		context = this;
//		SHOW DiALOG
		
        Button btn_log = (Button)findViewById(R.id.btn_log);
        Button btn_log_s = (Button)findViewById(R.id.btn_log_s);
        btn_log.setOnClickListener(this);
        Log.i("yes","start");
        ((Button)findViewById(R.id.btn_log)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_log_s)).setOnClickListener(this);
        loginInfoinfoHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				
				if (msg.what == 0) {
					System.out.println("into msg0000000000000000000000000000");
					
					Toast.makeText(context, "����״�����ѣ����µ�¼", Toast.LENGTH_SHORT)
							.show();
				} else if (msg.what == 1) {
					
					Toast.makeText(context, "��¼�ɹ�", Toast.LENGTH_SHORT).show();
				} else if (msg.what == 2) {
					
					Toast.makeText(context, "��¼��ʱ", Toast.LENGTH_SHORT).show();
					//debug ����ʹ��
					activityRedirect();
				} else if (msg.what == 3) {
					
					Toast.makeText(context, "�������û���", Toast.LENGTH_SHORT).show();
				} else if (msg.what == 4) {
					
					Toast.makeText(context, "����������", Toast.LENGTH_SHORT).show();
				} else if (msg.what == 5) {

					Toast.makeText(context, "�û���������", Toast.LENGTH_SHORT)
							.show();
				}
				else if (msg.what == 6) {
					Toast.makeText(context, "�������", Toast.LENGTH_SHORT)
							.show();
				
			}

		}
        };
	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public void onClick(View btn) {
		// TODO Auto-generated method stub
		LayoutInflater myinflater=LayoutInflater.from(LoginActivity2.this);
		 View selfView = myinflater.inflate(R.layout.dialogin, null);
		
		final EditText username = (EditText) selfView.findViewById(R.id.usename);
		final EditText password = (EditText) selfView.findViewById(R.id.password);
		Builder alterDlg;
		switch(btn.getId()){
		case R.id.btn_log:
			 	
				alterDlg = new AlertDialog.Builder(LoginActivity2.this);
//				alterDlg.setTitle("��¼��ע��"); //���ñ���
		        alterDlg.setView(selfView);//����һ��ʵ���˺���Դ�ļ��е�dialog.xml�Ĺ���
		        alterDlg.setPositiveButton("��¼", new DialogInterface.OnClickListener() {//���ð�ť���Լ���ť���¼�
		              
		              @Override
		              public void onClick(DialogInterface arg0, int arg1) {
		                  // TODO Auto-generated method stub
//		            	  Toast.makeText(LoginActivity2.this, "��½�ɹ�", 1).show();
//		            	  Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
//		      			  startActivity(HomeIntent);
		            	  validate(username,password);
		            	  
		                }
		          });
		          alterDlg.setNeutralButton("ע��", new DialogInterface.OnClickListener(){ //���ð�ť���Լ���ť���¼�
		              @Override
		              public void onClick(DialogInterface arg0, int arg1) {
		                  // TODO Auto-generated method stub
		            	  
		            	  Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
		      			  startActivity(HomeIntent);
		            	  
		            	  
		            	  Toast.makeText(LoginActivity2.this, "ע��ɹ�", 1).show();
		              }
		              
		          });
		          alterDlg.create();//��ɶԻ���Ĵ���
		          alterDlg.show();
//			Log.i("yes","log"+alterDlg.toString());
			break;
		case R.id.btn_log_s:
			
			break;
		}
	}
	
	// ��¼�ж�
		public void validate(EditText usernameText,EditText passwordText) {
			try {

				// ���������ж� 
			
				if (IsNetWorkAvailable.isAvailable(LoginActivity2.this)) {

					
					String username=usernameText.getText().toString();
					String password=passwordText.getText().toString();
					
					if (username.equals("")) {
						System.out.println("111111111111111111");
						Message msg = new Message();
						//3 ������
						msg.what = 3;
						loginInfoinfoHandler.sendMessage(msg);
					} else if (password.equals("")) {
						System.out.println("2222222222222222222");
						Message msg = new Message();
						//4����������
						msg.what = 4;
						loginInfoinfoHandler.sendMessage(msg);
					} else {
						System.out.println("33333333333333333");
						loginServer(username,password);
					}

				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							LoginActivity2.this);
					builder.setIcon(android.R.drawable.ic_dialog_alert);
					builder.setTitle("����״̬");
					builder.setMessage("��ǰ�޿������磬������");
					builder.setPositiveButton("����",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									startActivity(new Intent(
											android.provider.Settings.ACTION_SETTINGS));
								}
							});
					builder.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
					builder.create().show();
				}

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	private void loginServer(String username,String password) throws JSONException,
	UnsupportedEncodingException{
		
		// TODO Auto-generated method stub


		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("email", username);
		paramMap.put("password", password);
		RequestParams params = new RequestParams(paramMap);
		//
		//��������֤�˻�������
		MobileHttpClient.get("login.action?", params, new JsonHttpResponseHandler() {

	          //API http://loopj.com/android-async-http/doc/com/loopj/android/http/JsonHttpResponseHandler.html
				@Override
				protected Object parseResponse(byte[] arg0) throws JSONException {
					// TODO Auto-generated method stub
					//System.out.println("fnregkrtjhb" + arg0);
					return super.parseResponse(arg0);

				}

				@Override
				public void onFailure(int statusCode, org.apache.http.Header[] headers, java.lang.Throwable throwable, org.json.JSONObject errorResponse){
					// TODO Auto-generated method stub
					Message msg = new Message();
					//2��¼��ʱ
					msg.what = 2;
					loginInfoinfoHandler.sendMessage(msg);

				}
				@Override
				 public void onSuccess(int statusCode, org.apache.http.Header[] headers, org.json.JSONObject timeline){
					System.out.println(timeline);
					
					try {
						JSONObject LoginInfo =  timeline.getJSONObject("LoginInfo");
						if (LoginInfo.getString("success").equals("true")) {
						
							{
								//��¼��Ҫ�ѱ���login��¼�ĳ��ѵ�¼
								
							}

								Message msg = new Message();
								//1��¼�ɹ���Handler���첽�� ����ת��������ת�д��� Ҳ������ ����ת��
								msg.what = 1;
								loginInfoinfoHandler.sendMessage(msg);
								Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
								startActivity(HomeIntent);
								

//								overridePendingTransition(R.anim.zoomin,
//										R.anim.zoomout);
//								LoginActivity.this.finish();
							}
						else if(LoginInfo.getString("success").equals("false")){
							if(LoginInfo.getString("fail").equalsIgnoreCase("notexist"))
							{
							System.out.println("false");
							Message msg = new Message();
							//5�û��������������
							msg.what = 5;
							loginInfoinfoHandler.sendMessage(msg);
							}
							else if(LoginInfo.getString("fail").equalsIgnoreCase("psdwrong"))
							{
								System.out.println("false");
								Message msg = new Message();
								//5�û��������������
								msg.what = 6;
								loginInfoinfoHandler.sendMessage(msg);
							}

						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}			
		});

	}
	//ҳ����ת �ض���
	private void  activityRedirect()
	{
		Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
		startActivity(HomeIntent);
		

		overridePendingTransition(R.anim.zoomin,
				R.anim.zoomout);
		LoginActivity2.this.finish();
	}
	

}
