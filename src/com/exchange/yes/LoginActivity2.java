package com.exchange.yes;

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
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity2 extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
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
//				alterDlg.setTitle("��¼��ע��"); //���ñ���
		        alterDlg.setView(selfView);//����һ��ʵ���˺���Դ�ļ��е�dialog.xml�Ĺ���
		        alterDlg.setPositiveButton("��¼", new DialogInterface.OnClickListener() {//���ð�ť���Լ���ť���¼�
		              
		              @Override
		              public void onClick(DialogInterface arg0, int arg1) {
		                  // TODO Auto-generated method stub
		            	  Toast.makeText(LoginActivity2.this, "��½�ɹ�", 1).show();
		            	  Intent HomeIntent = new Intent(LoginActivity2.this,HomeActivity.class);
		      			  startActivity(HomeIntent);
		                }
		          });
		          alterDlg.setNeutralButton("ע��", new DialogInterface.OnClickListener(){ //���ð�ť���Լ���ť���¼�
		              @Override
		              public void onClick(DialogInterface arg0, int arg1) {
		                  // TODO Auto-generated method stub
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

}
