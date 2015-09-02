package com.exchange.yes.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;


/**
 * 
 * @���� UserInfo.java

 * @���� lyk  
 * @ʱ�� 2015��9��1�� 

 * @�汾 V1.0 
 * @���ܴ����û�������Ϣ
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
	
	public static UserInfo getNameId(String cookie) {
		return new Select().from(UserInfo.class).where("Cookie = ?", cookie).executeSingle();
	}
}