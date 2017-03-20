package com.test.stock.calc.service;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.test.stock.calc.datasource.DataSource;
import com.test.stock.calc.service.impl.TradeServiceImpl;

public class TradeServiceTest {
	
	private TradeService tradeService;
	
	@Before
	public void init(){
		tradeService = new TradeServiceImpl();
	}
	
	@After
	public void tearDown() {
		tradeService = null;
	}
	
	@Test
	public void addTrade(){
		Boolean result = tradeService.trade("TEA", 5, "Buy", 98, new Date());
		Assert.assertEquals(Boolean.TRUE, result);
		Assert.assertTrue(DataSource.tradeSource.get("TEA").size() >= 1);
	}
	
	@Test
	public void addTradeNullDate(){
		Boolean result = tradeService.trade("TEA", 1, "Sell", 102, null);
		Assert.assertEquals(Boolean.TRUE, result);
		Assert.assertTrue(DataSource.tradeSource.get("TEA").size() >= 1);
	}
	
	@Test
	public void addTradePreferred(){
		Boolean result = tradeService.trade("GIN", 3, "Buy", 99, new Date());
		Assert.assertEquals(Boolean.TRUE, result);
		Assert.assertTrue(DataSource.tradeSource.get("TEA").size() >= 1);
	}

	@Test
	public void addTradeNullSymbol(){
		Boolean result = tradeService.trade(null, 1, "Buy", 98, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@Test
	public void addTradeNullBuySeillIndicator(){
		Boolean result = tradeService.trade("TEA", 1, null, 98, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@Test
	public void addTradeEmptySymbol(){
		Boolean result = tradeService.trade("", 1, "Buy", 98, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@Test
	public void addTradeEmptyBuySellIndicator(){
		Boolean result = tradeService.trade("TEA", 1, "", 98, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@Test
	public void addTradeNegativeQuantity(){
		Boolean result = tradeService.trade("TEA", -1, "Buy", 98, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@Test
	public void addTradeZeroQuantity(){
		Boolean result = tradeService.trade("TEA", 0, "Buy", 98, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@Test
	public void addTradeNegativePrice(){
		Boolean result = tradeService.trade("TEA", 1, "Buy", -98, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@Test
	public void addTradeZeroPrice(){
		Boolean result = tradeService.trade("TEA", 1, "Buy", 0, new Date());
		Assert.assertEquals(Boolean.FALSE, result);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void calculateVWSP(){
		tradeService.trade("ALE", 5, "Buy", 98, new Date());
		tradeService.trade("ALE", 3, "Sell", 102, new Date());
		Date oldDate = new Date();
		oldDate.setMinutes(new Date().getMinutes() - 16);
		tradeService.trade("ALE", 2, "Buy", 98, oldDate);
		
		Assert.assertTrue(DataSource.tradeSource.get("ALE").size() >= 3);
		double result = tradeService.calculateVWSP("ALE");
		Assert.assertEquals(Double.valueOf("3.98"), Double.valueOf(result));
	}
	
	@Test
	public void calculateVWSPZeroTrade(){
		double result = tradeService.calculateVWSP("POP");
		Assert.assertEquals(Double.valueOf(0l), Double.valueOf(result));
	}
	
}
