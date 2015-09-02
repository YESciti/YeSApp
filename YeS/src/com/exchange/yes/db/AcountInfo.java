package com.exchange.yes.db;

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
	@Column(name = "a _acc_idcard")
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
	
	public static AcountInfo getNameId(String cookie) {
		return new Select().from(AcountInfo.class).where("Cookie = ?", cookie).executeSingle();
	}
}