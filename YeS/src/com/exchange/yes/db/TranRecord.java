package com.exchange.yes.db;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


@Table(name = "T_A_TANSFER_RECORD_SELF")
public class TranRecord extends Model  implements Serializable{
	@Column(name = "pk_a_traf_id")
	public String pk_a_traf_id;
	@Column(name = "fk_a__traf_user_id")
	public String fk_a__traf_user_id;
	@Column(name = "a_traf_balance_add")
	public String a_traf_balance_add;
	@Column(name = "fk_a_traf_currency_codenum")
	public String fk_a_traf_currency_codenum;
	@Column(name = "a_traf_bankname")
	public String a_traf_bankname;
	@Column(name = "a_traf_bankcard_num")
	public String  a_traf_bankcard_num;
	@Column(name = "a_traf_time")
	public String  a_traf_time;
	

	public TranRecord(String pk_a_traf_id, String fk_a__traf_user_id,String a_traf_balance_add,String fk_a_traf_currency_codenum,
			 String a_traf_bankname, String a_traf_bankcard_num, String a_traf_time) {
		this.pk_a_traf_id = pk_a_traf_id;
		this.fk_a__traf_user_id = fk_a__traf_user_id;
		this.a_traf_balance_add = a_traf_balance_add;
		this.fk_a_traf_currency_codenum = fk_a_traf_currency_codenum;
		this.a_traf_bankname = a_traf_bankname;
		this.a_traf_bankcard_num = a_traf_bankcard_num;
		this.a_traf_time = a_traf_time;
	}
	
	public  List<TranRecord> getTranRecord(String pk_a_traf_id
			) {
		return new Select()
				.from(TranRecord.class)
				.where("pk_a_traf_id= ?" ,pk_a_traf_id).orderBy("fk_a__traf_user_id")
				.execute();
	}
	
	

}