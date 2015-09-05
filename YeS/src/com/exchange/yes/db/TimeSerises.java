package com.exchange.yes.db;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import android.text.format.Time;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
@Table(name = "TimeSerises")
public class TimeSerises extends Model implements Serializable{
	/**
	 * @timeserises数据,用于展示
	 */
	private static final long serialVersionUID = -1155336704583963964L;

	@Column(name = "a_dateformat")
	public String a_dateformat;
	@Column(name = "a_time_price")
	public double a_time_price;
	@Column(name = "a_time_currency_codenum")
	public int a_time_currency_codenum;
	
	public TimeSerises(){};
	
	public List<TimeSerises>  getTimeSerises(int a_time_currency_codenum)
	{
		return new Select()
				.from( TimeSerises.class)
				.where("a_time_currency_codenum= ?" ,a_time_currency_codenum)
				.execute();		
	}
	public List<TimeSerises> getalltimeSerises()
	{return new Select().from(TimeSerises.class).where("0 = 0").execute();}
	
	public void save(TimeSerises Item)
	{
		Item.save();
	}
	public void setTimeSeries(List<TimeSerises> list)
	{
		ActiveAndroid.beginTransaction();
		TimeSerises item=new TimeSerises();
		try{
			for(int i=0;i<list.size();i++){
				list.get(i).save();
			}
			 ActiveAndroid.setTransactionSuccessful();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			ActiveAndroid.endTransaction();
		}
	}
	
	
}
