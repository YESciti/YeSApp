package com.exchange.yes.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.exchange.yes.pre.HomeActivity;
import com.gc.materialdesign.views.Button;

public class LoginActivity2 extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login2);
//		SHOW DiALOG
		
        Button btn_log = (Button)findViewById(R.id.btn_log);
        Button btn_log_s = (Button)findViewById(R.id.btn_log_s);
        btn_log.setOnClickListener(this);
        Log.i("yes","start");
        ((Button)findViewById(R.id.btn_log)).setOnClickListener(this);
        ((Button)findViewById(R.id.btn_log_s)).setOnClickListener(this);}


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
		EditText username = (EditText) selfView.findViewById(R.id.usename);
		EditText password = (EditText) selfView.findViewById(R.id.password);
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
		            	  Toast.makeText(LoginActivity2.this, "登陆成功", 1).show();
		            	  Intent HomeIntent = new Intent(LoginActivity2.this,HomepageActivity.class);
		      			  startActivity(HomeIntent);
		                }
		          });
		          alterDlg.setNeutralButton("注册", new DialogInterface.OnClickListener(){ //设置按钮，以及按钮的事件
		              @Override
		              public void onClick(DialogInterface arg0, int arg1) {
		                  // TODO Auto-generated method stub
		            	  
		            	  
		            	  
		            	  
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

}
