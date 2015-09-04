package com.exchange.yes.db;

import java.util.List;

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
	public  RecvRecord(String pk_a_recv_deal_id, String a_recv_per_amount,String a_recv_per_price,String a_recv_end_time,
			 String fk_a_recv_traf_id, String fk_a_recv_user_id) {
		this.pk_a_recv_deal_id= pk_a_recv_deal_id;
		this.a_recv_per_amount = a_recv_per_amount;
		this.a_recv_per_price = a_recv_per_price;
		this.a_recv_end_time = a_recv_end_time;
		this.fk_a_recv_traf_id=fk_a_recv_traf_id;
		this.fk_a_recv_user_id = fk_a_recv_user_id;
	
	}
	
	public  List<RecvRecord> getRecvRecord(String pk_a_recv_deal_id
			) {
		return new Select()
				.from(RecvRecord.class)
				.where("pk_a_recv_deal_id= ?" ,pk_a_recv_deal_id).orderBy("fk_a_recv_user_id")
				.execute();
	}
}