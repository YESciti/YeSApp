package com.exchange.yes.adapter;

import android.widget.ArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.exchange.yes.db.CollectedExchangerate;
import com.exchange.yes.R;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CollectedExchangerateListAdap extends ArrayAdapter<CollectedExchangerate> {


	private List<CollectedExchangerate> list;
	private Context context;
	private LayoutInflater layoutInflater;
	private FragmentManager fragmentManager;
	
	/**
	 * resource 布局文件ID/
	 */
	public CollectedExchangerateListAdap(Context context, int resource,
			List<CollectedExchangerate> objects) {
		super(context, resource, objects);
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		this.list = new LinkedList<CollectedExchangerate>();
		// TODO Auto-generated constructor stub
	}
	public CollectedExchangerateListAdap(Context context, int resource, List<CollectedExchangerate> listCollectedRate,FragmentManager fragmentManager) {
		super(context, resource, listCollectedRate);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = listCollectedRate;
		this.fragmentManager = fragmentManager;
		layoutInflater = LayoutInflater.from(context);
	
	}
	public void addMessage(CollectedExchangerate singlerate) {
		list.add(singlerate);
		notifyDataSetChanged();
	}

	public void addMessages(List<CollectedExchangerate> erateList) {
		list.addAll(erateList);
		notifyDataSetChanged();
	}

	public void clear() {
		list.clear();
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public CollectedExchangerate getItem(int position) {

		// TODO Auto-generated method stub
		return list.get(position);
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
		if (convertView == null) {
			viewHolder = new ViewHolder();
//			convertView = layoutInflater.inflate(R.layout.child_item_layout,
//					null);
			
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
	

	
		


		return convertView;
	}

	class ViewHolder {
		
	}
}
