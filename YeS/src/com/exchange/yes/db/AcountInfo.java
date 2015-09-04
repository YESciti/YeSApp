package com.exchange.yes.db;

import java.util.List;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.Statement;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "T_A_ACCOUNTINFO_SELF")
public class AcountInfo extends Model{

	@Column(name = "pk_a_acc_user_id")
	public String pk_a_acc_user_id;
	@Column(name = "a_acc_realname")
	public String a_acc_realname;
	@Column(name = "a_acc_idcard")
	public String a_acc_idcard;
	@Column(name = "a_acc_bankname")
	public String  a_acc_bankname;
	@Column(name = "a_acc_bankcard_num")
	public String a_acc_bankcard_num;
	@Column(name = "a_acc_balance")
	public String a_acc_balance;
	@Column(name = "fk_a_acc_currency_codenum")
	public String  fk_a_acc_currency_codenum;

	public AcountInfo() {
		// TODO Auto-generated constructor stub
		super();
	}
	public  AcountInfo(String pk_a_acc_user_id, String a_acc_realname,String a_acc_idcard,String a_acc_bankname,
			 String a_offer_end_time, String fk_a_recv_user_id) {
		this.pk_a_acc_user_id= pk_a_acc_user_id;
		this.a_acc_realname =a_acc_realname;
		this.a_acc_idcard = a_acc_idcard ;
		this.a_acc_bankname = a_acc_bankname;
		this.a_acc_bankcard_num=a_acc_bankcard_num;
		this.a_acc_balance = a_acc_balance;
		this.fk_a_acc_currency_codenum= fk_a_acc_currency_codenum;
	
	}
	
	public  List< AcountInfo> getRecvRecord(String pk_a_acc_user_id
			) {
		return new Select()
				.from( OfferRecord.class)
				.where("pk_a_acc_user_id= ?" ,pk_a_acc_user_id).orderBy("fk_a_acc_currency_codenum")
				.execute();
	}
	}
