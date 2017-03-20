package com.test.stock.calc.model;

public class Stock {
	
	private String symbol;
	private String type;
	private int lastDivident;
	private int fixedDivident;
	private int parValue;
	
	public Stock(String symbol, String type, int lastDivident, int fixedDivident, int parValue) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDivident = lastDivident;
		this.fixedDivident = fixedDivident;
		this.parValue = parValue;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLastDivident() {
		return lastDivident;
	}
	public void setLastDivident(int lastDivident) {
		this.lastDivident = lastDivident;
	}
	public int getFixedDivident() {
		return fixedDivident;
	}
	public void setFixedDivident(int fixedDivident) {
		this.fixedDivident = fixedDivident;
	}
	public int getParValue() {
		return parValue;
	}
	public void setParValue(int parValue) {
		this.parValue = parValue;
	}
	

}
