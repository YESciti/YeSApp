package com.exchange.yes.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.exchange.yes.R;


public class NormalAdapter  extends BaseAdapter{

	//public  List<>
	public ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	private LayoutInflater layoutInflater;
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
			viewHolder.price=(TextView) convertView
					.findViewById(R.id.listview_2);
			viewHolder.number=(TextView) convertView
					.findViewById(R.id.listview_1);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	
	}
	class ViewHolder{
		TextView price;
		TextView number;}

