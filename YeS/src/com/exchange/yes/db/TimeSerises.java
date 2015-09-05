package com.exchange.yes.db;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
@Table(name = "T_A_TIMESERISES_RECORD")
public class TimeSerises extends Model implements Serializable{
	/**
	 * @timeserises数据,用于展示
	 */
	private static final long serialVersionUID = -1155336704583963964L;
	@Column(name = "a_pk_sortid")
	public String a_pk_sortid;
	@Column(name = "a_dateformat")
	public SimpleDateFormat a_dateformat;
	@Column(name = "a_time_price")
	public double a_time_price;
	@Column(name = "a_time_currency_codenum")
	public int a_time_currency_codenum;
	
	public TimeSerises(){};
	
	public List<TimeSerises>  getTimeSerises(int a_time_currency_codenum)
	{
		return new Select()
				.from( TimeSerises.class)
				.where("a_time_currency_codenum= ?" ,a_time_currency_codenum).orderBy("a_pk_sortid")
				.execute();		
	}
	
}
