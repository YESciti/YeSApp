package com.exchange.yes.db;

public class TradeItem {
	public String askbid;
	public String rate;
	public String amount;	
	
	public void getask(String ask){
		this.askbid=ask;
	}
	public void getprice(String price){
		this.rate=price;
	}
	public void getnumber(String number){
		this.amount=number;
	}
	public TradeItem(String ask,String price,String number){
		this.askbid=ask;
		this.rate=price;
		this.amount=number;
	}
	public TradeItem(){};
}
