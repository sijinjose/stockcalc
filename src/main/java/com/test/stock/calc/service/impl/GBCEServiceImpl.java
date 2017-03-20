package com.test.stock.calc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.stock.calc.datasource.DataSource;
import com.test.stock.calc.model.Trade;
import com.test.stock.calc.service.GBCEService;

public class GBCEServiceImpl implements GBCEService {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public double allShareIndex() {
		double shareIndex = 0l;
		List<Trade> listTrade = new ArrayList<>();
		for(List<Trade> individualTradeList : DataSource.tradeSource.values()){
			listTrade.addAll(individualTradeList);
		}
		int countOfTrade = listTrade.size();
		LOGGER.info("Total number of Trades: " + countOfTrade);
		if(!listTrade.isEmpty()) {
			double[] tradePrices = listTrade.stream().mapToDouble(trade -> trade.getPrice()).toArray();
			double integralPrice = 1l;
			for(double price : tradePrices){
				integralPrice *= price;
			}
			LOGGER.info("Multiplied price: " + integralPrice);
			if(integralPrice > 0){
				shareIndex = Math.pow(countOfTrade, 1.0/integralPrice);
			}
		}
		return shareIndex;
	}

}
