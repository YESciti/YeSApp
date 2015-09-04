package com.exchange.yes.db;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "T_A_RECV_RECORD")
public class OfferRecord extends Model{

	@Column(name = "pk_a_offer_deal_id")
	public String pk_a_offer_deal_id;
	@Column(name = "a_offer_per_amount")
	public String a_offer_per_amount;
	@Column(name = "a_offer_per_price")
	public String a_offer_per_price;
	@Column(name = "a_offer_start_time")
	public String a_offer_start_time;
	@Column(name = "a_offer_end_time")
	public String a_offer_end_time;
	@Column(name = "fk_a_offer_match_id")
	public String fk_a_offer_match_id;
	@Column(name = "fk_a_offer_user_id")
	public String fk_a_offer_user_id;
	public OfferRecord() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public  OfferRecord(String pk_a_offer_deal_id, String a_offer_per_amount,String a_offer_per_price,String a_offer_start_time,
			 String a_offer_end_time, String fk_a_recv_user_id) {
		this.pk_a_offer_deal_id= pk_a_offer_deal_id;
		this.a_offer_per_amount =a_offer_per_amount;
		this.a_offer_per_price = a_offer_per_price;
		this.a_offer_start_time = a_offer_start_time;
		this.a_offer_end_time=a_offer_end_time;
		this.fk_a_offer_match_id = fk_a_offer_match_id;
		this.fk_a_offer_user_id= fk_a_offer_user_id;
	
	}
	
	public  List< OfferRecord> getRecvRecord(String pk_a_offer_deal_id
			) {
		return new Select()
				.from( OfferRecord.class)
				.where("pk_a_offer_deal_id= ?" ,pk_a_offer_deal_id).orderBy(" fk_a_offer_user_id")
				.execute();
	}
	
	
}