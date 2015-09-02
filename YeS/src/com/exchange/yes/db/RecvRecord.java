package com.exchange.yes.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "T_A_RECV_RECORD")
public class RecvRecord extends Model{

	@Column(name = "pk_a_recv_deal_id")
	public String pk_a_recv_deal_id;
	@Column(name = "a_recv_per_amount")
	public String a_recv_per_amount;
	@Column(name = "a_recv_per_price")
	public String a_recv_per_price;
	@Column(name = "a_recv_end_time")
	public String a_recv_end_time;
	@Column(name = "fk_a_recv_traf_id")
	public String fk_a_recv_traf_id;
	@Column(name = "fk_a_recv_user_id")
	public String fk_a_recv_user_id;

	public RecvRecord() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public static RecvRecord getNameId(String cookie) {
		return new Select().from(RecvRecord.class).where("Cookie = ?", cookie).executeSingle();
	}
}