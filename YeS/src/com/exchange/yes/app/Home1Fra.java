package com.exchange.yes.app;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;

import android.content.ClipData.Item;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;

import com.exchange.yes.R;
import com.exchange.yes.adapter.NormalAdapter;
import com.exchange.yes.db.TradeItem;

/**
 * home1
 * 
 * @author andye
 * 
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") 
public class Home1Fra extends ListFragment implements OnClickListener {
	
	public NormalAdapter normalAdapter;
	View view=null;
	public  ListView listview;
	private Activity activity=getActivity();
	private Context content;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	 view = inflater.inflate(R.layout.homefra1,container, false);
		
		
	
			try {
				add(view);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		activity = getActivity();
		content = getActivity();
		}
	
	private void add(View view)throws Exception {

		// headerIV = (ImageView) v.findViewById(R.id.person2_header_iv);
		 listview =(ListView)view.findViewById(android.R.id.list);
		 if(HomepageActivity.mylist.size()==0){
			 TradeItem item=new TradeItem();
			 item.getask("��");
			 item.getprice("0");
			 item.getnumber("0");
			 Log.i("list","runright"+HomepageActivity.mylist.size());
			 HomepageActivity.mylist.add(item);
			 Log.i("list","runright"+HomepageActivity.mylist.size());
		 }else {}		 
		 Log.i("list","runright");
		 initHAdapter();
		 Log.i("list","runinit"+HomepageActivity.mylist.size());
	}

	private void initHAdapter() {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = getFragmentManager();
		normalAdapter =new NormalAdapter(activity, HomepageActivity.mylist, fragmentManager);
		
		listview.setAdapter(normalAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// case R.id.person2_shopping: // ��ť����
		// break;

		default:
			break;
		}
	}

	public void freshadapter(ArrayList<TradeItem> mylist){
		normalAdapter.mylist=mylist;
		normalAdapter.freshData(normalAdapter.mylist);
	}
}
