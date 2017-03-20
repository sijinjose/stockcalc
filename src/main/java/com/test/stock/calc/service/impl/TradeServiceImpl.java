package com.test.stock.calc.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.stock.calc.datasource.DataSource;
import com.test.stock.calc.model.Trade;
import com.test.stock.calc.service.TradeService;

public class TradeServiceImpl implements TradeService {
	
	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean trade(String stockSymbol, int quantity, String buySellIndicator, double price, Date tradeDate) {
		Trade trade = validateCreateTrade(stockSymbol, quantity, buySellIndicator, price, tradeDate);
		if(trade != null){
			LOGGER.info("Adding the trade to data source");
			List<Trade> listTrade = DataSource.tradeSource.get(stockSymbol);
			if(listTrade == null || listTrade.isEmpty()){
				listTrade = new ArrayList<>();
			}
			listTrade.add(trade);
			DataSource.tradeSource.put(stockSymbol, listTrade);
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}

	}

	@Override
	public double calculateVWSP(String stockSymbol) {
		List<Trade> listTrade = DataSource.tradeSource.get(stockSymbol);
		if(listTrade != null && !listTrade.isEmpty()){
			LOGGER.info("Got all Trades for stock " + stockSymbol);
			LOGGER.info("Total Trades: " + listTrade.size());
			List<Trade> validlistTrade = listTrade.stream().
					filter((trade) -> LocalDateTime.now().minusMinutes(15l).isBefore(
					LocalDateTime.from(LocalDateTime.ofInstant(
							trade.getTradeTime().toInstant(), ZoneId.systemDefault())))).collect(Collectors.toList());
			LOGGER.info("Valid Trades: " + validlistTrade.size());
			double sumOfPrice = validlistTrade.stream().mapToDouble(trade -> trade.getPrice()).sum();
			double sumOfQuantityPrice = validlistTrade.stream().
					mapToDouble(trade -> (trade.getPrice() * trade.getQuantity())).sum();
			if(sumOfQuantityPrice == 0 || sumOfPrice == 0){
				return 0;
			} else {
				return sumOfQuantityPrice / sumOfPrice;
			}
		} else {
			return 0;
		}
	}
	
	private Trade validateCreateTrade(String stockSymbol, int quantity, String buySellIndicator, double price, Date tradeDate){
		LOGGER.info("Validating Trade");
		boolean valid = Boolean.TRUE;
		if(stockSymbol == null || stockSymbol.isEmpty()){
			valid = Boolean.FALSE;
			LOGGER.info("Stock Symbol is invalid");
		}
		if(quantity == 0 || quantity < 0) {
			valid = Boolean.FALSE;
			LOGGER.info("Quantity is invalid");
		}
		if(buySellIndicator == null || buySellIndicator.isEmpty() || !(buySellIndicator.equalsIgnoreCase("buy") || buySellIndicator.equalsIgnoreCase("sell"))) {
			valid = Boolean.FALSE;
			LOGGER.info("Buy Sell Indicator is invalid");
		}
		if(price == 0 || price < 0) {
			valid = Boolean.FALSE;
			LOGGER.info("Price is invalid");
		}
		if(valid){
			if(tradeDate == null){
				tradeDate = new Date();
				LOGGER.info("Trade Date is empty. Updating with current date");
			}
			return new Trade(stockSymbol, buySellIndicator.toUpperCase(), quantity, price, tradeDate);
		} else {
			return null;
		}
	}
}
