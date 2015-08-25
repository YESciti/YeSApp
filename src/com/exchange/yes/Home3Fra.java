package com.exchange.yes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.exchange.yes.R;
/**
 * home3
 * @author andye
 *
 */
public class Home3Fra extends Fragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.account, null);

		init(v);

		return v;
	}

	private void init(View v) {
		// main_bg = (LinearLayout)v.findViewById(R.id.message_layout_bg);
		// title_bg = (RelativeLayout)v.findViewById(R.id.title_message);
		LayoutRipple ye=(LayoutRipple)v. findViewById(R.id.wbye); 
		LayoutRipple zh=(LayoutRipple)v. findViewById(R.id.bdzh); 
		LayoutRipple jl=(LayoutRipple)v. findViewById(R.id.jyjl); 
		
		ye.setOnClickListener(this);
		zh.setOnClickListener(this);
		jl.setOnClickListener(this);

	}

	/**
	 * 按钮点击事件处理
	 */
	@Override
	public void onClick(View v) {
		// switch (v.getId()) {
		// case R.id.message_delete:
		//
		// break;
		//
		// default:
		// break;
		// }
	}
}
