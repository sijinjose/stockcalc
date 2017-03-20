package com.test.stock.calc.service;

import java.util.Date;

public interface TradeService {

	/**
	 * Capture trade for the details given
	 * @param stockSymbol
	 * @param quantity
	 * @param buySellIndicator
	 * @param price
	 * @param tradeDate
	 * @return Boolean
	 */
	public boolean trade(String stockSymbol, int quantity, String buySellIndicator, double price, Date tradeDate);
	
	/**
	 * Calculate Volume Weighted Stock Price based on trades in past 15 minutes
	 * @param stockSymbol
	 * @return Double
	 */
	public double calculateVWSP(String stockSymbol);
	
}
