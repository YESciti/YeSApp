package com.exchange.yes.pre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.exchange.yes.R;
import com.exchange.yes.R.id;
import com.exchange.yes.R.layout;
import com.gc.materialdesign.views.LayoutRipple;
import com.gc.materialdesign.widgets.Dialog;
/**
 * home2
 * @author andye
 *
 */
public class Home2Fra extends Fragment implements OnClickListener {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_quick_trade, null);

		return v;
		
	}

	private void init(View v) {
		// main_bg = (LinearLayout)v.findViewById(R.id.message_layout_bg);
		// title_bg = (RelativeLayout)v.findViewById(R.id.title_message);
		LayoutRipple quickT=(LayoutRipple)v. findViewById(R.id.item_quicktrade); 
		LayoutRipple customT=(LayoutRipple)v. findViewById(R.id.item_customtrade); 
		
		quickT.setOnClickListener(this);
		customT.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.item_quicktrade:
			 Intent quickIntent;
			 quickIntent=new Intent(getActivity(),QuickTradeActivity.class);
			  startActivity(quickIntent);
		
			break;
		case R.id.item_customtrade:
			Dialog dialog = new Dialog(getActivity(), "«Î—°‘Ò", "");
			dialog.setOnAcceptButtonClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Click accept button", 1).show();
				}
			});
			dialog.setOnCancelButtonClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Click cancel button", 1).show();
				}
			});
			dialog.show();
			break;
	}

}
}
