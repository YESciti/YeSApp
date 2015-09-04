package com.exchange.yes.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exchange.yes.R;
import com.exchange.yes.db.TradeItem;




public class NormalAdapter  extends BaseAdapter{

	//public  List<>
	public ArrayList<TradeItem> mylist = new ArrayList<TradeItem>();
	private Context context;
	private LayoutInflater layoutInflater;
	private FragmentManager fragmentManager;
	
	
	 public NormalAdapter(Context context,ArrayList<TradeItem> mylist,
				FragmentManager fragmentManager2)
	 {
		 this.context = context;
		 this.mylist=mylist;
		 layoutInflater = LayoutInflater.from(context);
		 this.fragmentManager = fragmentManager2;
	 }
	 
	 public void addMessage(TradeItem list) {
			// list.add(0, msg);
			mylist.add(list);
			notifyDataSetChanged();
		}

	public void freshData(ArrayList<TradeItem> mylist) {
			this.mylist = mylist;
			notifyDataSetChanged();
		}
	
	public void addMessages(ArrayList<TradeItem> mylist) {
		// list.add(0, msg);
		mylist.addAll(mylist);
		notifyDataSetChanged();
	}
	
	public void clear() {
		mylist.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mylist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mylist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		final int pos = position;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.listview_trade,
					null);
			viewHolder.askbid=(TextView) convertView
					.findViewById(R.id.listview_1);
			viewHolder.askbid.setText("Âò");
			viewHolder.price=(TextView) convertView
					.findViewById(R.id.listview_2);
			viewHolder.price.setText("3.168");
			viewHolder.number=(TextView) convertView
					.findViewById(R.id.listview_3);
			viewHolder.number.setText("12345");
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	
	}
	class ViewHolder{
		TextView askbid;
		TextView price;
		TextView number;}

