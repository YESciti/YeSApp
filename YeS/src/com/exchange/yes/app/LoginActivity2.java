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

	//处理登录界面相关的操作
	private Handler loginInfoinfoHandler = null;
	//当前上下文
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
					
					Toast.makeText(context, "网络状况不佳，重新登录", Toast.LENGTH_SHORT)
							.show();
				} else if (msg.what == 1) {
					
					Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
				} else if (msg.what == 2) {
					
					Toast.makeText(context, "登录超时", Toast.LENGTH_SHORT).show();
					//debug 调试使用
					activityRedirect();
				} else if (msg.what == 3) {
					
					Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT).show();
				} else if (msg.what == 4) {
					
					Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
				} else if (msg.what == 5) {

					Toast.makeText(context, "用户名不存在", Toast.LENGTH_SHORT)
							.show();
				}
				else if (msg.what == 6) {
					Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT)
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
//				alterDlg.setTitle("登录或注册"); //设置标题
		        alterDlg.setView(selfView);//在这一步实现了和资源文件中的dialog.xml的关联
		        alterDlg.setPositiveButton("登录", new DialogInterface.OnClickListener() {//设置按钮，以及按钮的事件
		              
		              @Override
		              public void onClick(DialogInterface arg0, int arg1) {
		                  // TODO Auto-generated method stub
//		            	  Toast.makeText(LoginActivity2.this, "登陆成功", 1).show();
//		            	  Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
//		      			  startActivity(HomeIntent);
		            	  validate(username,password);
		            	  
		                }
		          });
		          alterDlg.setNeutralButton("注册", new DialogInterface.OnClickListener(){ //设置按钮，以及按钮的事件
		              @Override
		              public void onClick(DialogInterface arg0, int arg1) {
		                  // TODO Auto-generated method stub
		            	  
		            	  Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
		      			  startActivity(HomeIntent);
		            	  
		            	  
		            	  Toast.makeText(LoginActivity2.this, "注册成功", 1).show();
		              }
		              
		          });
		          alterDlg.create();//完成对话框的创建
		          alterDlg.show();
//			Log.i("yes","log"+alterDlg.toString());
			break;
		case R.id.btn_log_s:
			
			break;
		}
	}
	
	// 登录判断
		public void validate(EditText usernameText,EditText passwordText) {
			try {

				// 网络连接判断 
			
				if (IsNetWorkAvailable.isAvailable(LoginActivity2.this)) {

					
					String username=usernameText.getText().toString();
					String password=passwordText.getText().toString();
					
					if (username.equals("")) {
						System.out.println("111111111111111111");
						Message msg = new Message();
						//3 请输入
						msg.what = 3;
						loginInfoinfoHandler.sendMessage(msg);
					} else if (password.equals("")) {
						System.out.println("2222222222222222222");
						Message msg = new Message();
						//4请输入密码
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
					builder.setTitle("网络状态");
					builder.setMessage("当前无可用网络，请设置");
					builder.setPositiveButton("设置",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									startActivity(new Intent(
											android.provider.Settings.ACTION_SETTINGS));
								}
							});
					builder.setNegativeButton("取消",
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
		//服务器验证账户名密码
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
					//2登录超时
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
								//登录需要把本地login记录改成已登录
								
							}

								Message msg = new Message();
								//1登录成功，Handler是异步的 ，跳转可以在跳转中处理 也可以在 此跳转；
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
							//5用户名或者密码错误
							msg.what = 5;
							loginInfoinfoHandler.sendMessage(msg);
							}
							else if(LoginInfo.getString("fail").equalsIgnoreCase("psdwrong"))
							{
								System.out.println("false");
								Message msg = new Message();
								//5用户名或者密码错误
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
	//页面跳转 重定向
	private void  activityRedirect()
	{
		Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
		startActivity(HomeIntent);
		

		overridePendingTransition(R.anim.zoomin,
				R.anim.zoomout);
		LoginActivity2.this.finish();
	}
	

}
