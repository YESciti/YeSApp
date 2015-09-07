package com.exchange.yes.app;

import com.exchange.yes.R;
import com.exchange.yes.R.layout;
import com.exchange.yes.R.menu;
import com.exchange.yes.app.Person.editListener;
import android.widget.*;  
import android.widget.RadioGroup.OnCheckedChangeListener;  
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RePerson extends Activity {

	private RadioGroup sex;  
	private RadioButton checkRadioButton; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_re_person);
		
		Button btn_edit=(Button)findViewById(R.id.btn_edit);
		btn_edit.setOnClickListener(new SaveListener());
		
		sex = (RadioGroup) findViewById(R.id.sex);  
	        // 改变默认选项  
		sex.check(R.id.female);  
	  
	        // 获取默认被被选中值  
	  
	        checkRadioButton = (RadioButton) sex.findViewById(sex  
	                .getCheckedRadioButtonId());  
	  
	        
	  
	        // 注册事件  
	        sex .setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {  
	  
	                    @Override  
	                    public void onCheckedChanged(RadioGroup group, int checkedId) {  
	  
	                        // 点击事件获取的选择对象  
	                        checkRadioButton = (RadioButton) sex 
	                                .findViewById(checkedId);  
	                    }  
	                }); 
	     
	    }  
	  
		

	class SaveListener implements OnClickListener{
    	public void onClick(View v){
    		Intent intent=new Intent(RePerson.this,Person.class);
    		startActivity(intent);
    		RePerson.this.finish();
    	}
    }
@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.re_person, menu);
		return true;
	}

}

