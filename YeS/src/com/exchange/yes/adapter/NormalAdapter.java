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




public class NormalAdapter  extends BaseAdapter{

	//public  List<>
	public ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
	private Context context;
	private LayoutInflater layoutInflater;
	private FragmentManager fragmentManager;
	
	
	 public NormalAdapter(Context context, ArrayList<HashMap<String, String>> mylist,
				FragmentManager fragmentManager2)
	 {
		 this.context = context;
		 this.mylist=mylist;
		 layoutInflater = LayoutInflater.from(context);
		 this.fragmentManager = fragmentManager2;
	 }
	 
	 public void addMessage(HashMap<String, String> list) {
			// list.add(0, msg);
			mylist.add(list);
			notifyDataSetChanged();
		}

	public void freshData(ArrayList<HashMap<String, String>> mylist) {
			this.mylist = mylist;
			notifyDataSetChanged();
		}
	
	public void addMessages(ArrayList<HashMap<String, String>> mylist) {
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
			viewHolder.price=(TextView) convertView
					.findViewById(R.id.listview_2);
			viewHolder.number=(TextView) convertView
					.findViewById(R.id.listview_1);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	
	}
	class ViewHolder{
		TextView price;
		TextView number;}

