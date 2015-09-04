package com.exchange.yes.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


/**
 * 
 * @类名 UserInfo.java

 * @作者 lyk  
 * @时间 2015年9月1日 

 * @版本 V1.0 
 * @功能储蓄用户所有信息
 */
@Table(name = "T_A_USER_INFO")
public class UserInfo extends Model{

	@Column(name = "pk_a__user_id")
	public String pk_a__user_id;
	@Column(name = "a_user_name")
	public String a_user_name;
	@Column(name = "a_user_psd")
	public String a_user_psd;
	@Column(name = "a_user_phone")
	public String a_user_phone;
	@Column(name = "a_user_emai")
	public String a_user_emai;
	@Column(name = "a_user _open_time")
	public String a_user_open_time;
	@Column(name = "a_user_update_time")
	public String a_user_update_time;

	public UserInfo() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public UserInfo(String pk_a__user_id, String a_user_name,String a_user_psd,String a_user_phone,
			 String a_user_emai, String a_user_open_time, String a_user_update_time) {
		this.pk_a__user_id = pk_a__user_id;
		this.a_user_name = a_user_name;
		this.a_user_psd = a_user_psd;
		this.a_user_phone = a_user_phone;
		this.a_user_emai = a_user_emai;
		this.a_user_open_time = a_user_open_time;
		this.a_user_update_time = a_user_update_time;
	}

	
	public static UserInfo getNameId(String pk_a__user_id) {
		return new Select().from(UserInfo.class).where("pk_a__user_id = ?", pk_a__user_id).executeSingle();
	}
}