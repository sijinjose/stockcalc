package com.test.stock.calc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.stock.calc.datasource.DataSource;
import com.test.stock.calc.model.Stock;
import com.test.stock.calc.service.StockService;

public class StockServiceImpl implements StockService{

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public double getDividentYield(String stockSymbol, double price) {
		LOGGER.info("Checking stock for symbol " + stockSymbol);
		Stock stock = DataSource.stockSource.get(stockSymbol);
		double dividentYield = 0l;
		if(null != stock && Double.valueOf(price) > 0){
			if(("Common").equals(stock.getType())){
				LOGGER.info("Last Divident: " + stock.getLastDivident());
				dividentYield = (double)stock.getLastDivident() / price;
			} else {
				LOGGER.info("Fixed Divident: " + stock.getFixedDivident());
				LOGGER.info("Par Value: " + stock.getParValue());
				dividentYield = (stock.getFixedDivident() * stock.getParValue())/(price * 100);
			}
		}
		return dividentYield;
	}

	@Override
	public double getPERatio(String stockSymbol, double price) {
		LOGGER.info("Checking stock for symbol " + stockSymbol);
		Stock stock = DataSource.stockSource.get(stockSymbol);
		double pERatio = 0l;
		if(null != stock && Double.valueOf(price) > 0 && stock.getLastDivident() > 0){
			LOGGER.info("Last Divident: " + stock.getLastDivident());
			pERatio = price / stock.getLastDivident();
		}
		return pERatio;
	}

}
