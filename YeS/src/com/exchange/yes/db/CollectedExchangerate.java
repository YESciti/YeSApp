package com.exchange.yes.db;

import java.io.Serializable;
import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

/*
 * 集合汇率的model
 * author:pc
 * time :2015.9.01
 * 
 * version:0.0
 * changelog:
 * 
 * version:0.0

 * */
@Table(name = "collectedrate")
public class CollectedExchangerate extends Model implements Serializable{

	public CollectedExchangerate() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -8060503040772021877L;

	@Column(name ="currencyleftcode")
	public String currencyleftcode;
	@Column(name = "currencyleft")
	public int currencyleft;
	@Column(name = "currencyrightcode")
	public String currencyrightcode;
	@Column(name = "currencyright")
	public int currencyright;
	@Column(name = "coId")
	public int coId;
	@Column(name = "rate")
	public double rate;
	@Column(name = "amount")
	public int amount;
	
	@Column(name = "timestamp")
	public String timestamp;

	
	public  CollectedExchangerate(String currencyleftcode, int currencyleft, String currencyrightcode,int currencyright,int coId,double rate,int amount) {
		this.currencyleftcode= currencyleftcode;
		this.currencyleft= currencyleft;
		this.currencyrightcode=  currencyrightcode;
		this.currencyright = currencyright;
		this.coId = coId;
		this.rate=rate;
		this.amount=amount;
	}
	
	public List< CollectedExchangerate> getRate(String currencyleftcode
			) {
		return new Select()
				.from(CollectedExchangerate.class)
				.where("currencyleftcode= ?" ,currencyleftcode).orderBy("coId")
				.execute();
	}
	


	


}
