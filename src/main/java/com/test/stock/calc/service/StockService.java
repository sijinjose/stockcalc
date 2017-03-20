package com.test.stock.calc.service;

public interface StockService {

	/**
	 * Get the Divident Yield for the stock and the price given
	 * @param stockSymbol
	 * @param price
	 * @return Double
	 */
	public double getDividentYield(String stockSymbol, double price);
	
	/**
	 * Get the PE Ration for the stock and price given
	 * @param stockSymbol
	 * @param price
	 * @return Double
	 */
	public double getPERatio(String stockSymbol, double price);
	
}
