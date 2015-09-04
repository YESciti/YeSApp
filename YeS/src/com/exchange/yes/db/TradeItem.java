package com.exchange.yes.db;

public class TradeItem {
	public String askbid;
	public String price;
	public String number;	
	
	public void getask(String ask){
		this.askbid=ask;
	}
	public void getprice(String price){
		this.price=price;
	}
	public void getnumber(String number){
		this.number=number;
	}
	public TradeItem(String ask,String price,String number){
		this.askbid=ask;
		this.price=price;
		this.number=number;
	}
	public TradeItem(){};
}
