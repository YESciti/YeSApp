package com.exchange.yes.adapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exchange.yes.R;
import com.exchange.yes.R.drawable;

public class SpinnerAdapter {

	public SpinnerAdapter() {

	}
public static List<Map<String, Object>> getspinner3data() {
		List<Map<String, Object>> curencyspinner = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("log", R.drawable.meiyuan);
		map.put("listname", "美元");
		curencyspinner.add(map);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("log", R.drawable.euro);
		map2.put("listname", "欧元");
		curencyspinner.add(map2);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("log", R.drawable.hk);
		map3.put("listname", "港币");
		curencyspinner.add(map3);
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("log", R.drawable.japan);
		map4.put("listname", "日元");
		curencyspinner.add(map4);

		return curencyspinner;		
   }
}
