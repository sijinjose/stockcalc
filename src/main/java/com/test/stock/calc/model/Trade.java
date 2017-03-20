package com.test.stock.calc.model;

import java.util.Date;

public class Trade {
	
	private String stockSymbol;
	private String buySellIndicator;
	private int quantity;
	private double price;
	private Date tradeTime;
	
	public Trade(String stockSymbol, String buySellIndicator, int quantity, double price, Date tradeTime) {
		super();
		this.stockSymbol = stockSymbol;
		this.buySellIndicator = buySellIndicator;
		this.quantity = quantity;
		this.price = price;
		this.tradeTime = tradeTime;
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public String getBuySellIndicator() {
		return buySellIndicator;
	}
	public void setBuySellIndicator(String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

}
