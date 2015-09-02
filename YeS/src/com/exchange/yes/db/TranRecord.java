package com.exchange.yes.db;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
@Table(name = "T_A_TANSFER_RECORD_SELF")
public class TranRecord {
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
	

	public TranRecord() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	
}
