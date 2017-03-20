package com.test.stock.calc.service;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.test.stock.calc.service.impl.GBCEServiceImpl;
import com.test.stock.calc.service.impl.TradeServiceImpl;

public class GBCEServiceTest {

	private TradeService tradeService;
	private GBCEService gbceService;
	
	@Before
	public void init(){
		tradeService = new TradeServiceImpl();
		gbceService = new GBCEServiceImpl();
	}
	
	@After
	public void tearDown(){
		tradeService = null;
		gbceService = null;
	}
	
	@Test
	public void getAllShareIndex(){
		tradeService.trade("ALE", 5, "Buy", 98, new Date());
		tradeService.trade("ALE", 3, "Sell", 102, new Date());
		tradeService.trade("GIN", 3, "Buy", 99, new Date());
		tradeService.trade("TEA", 5, "Buy", 98, new Date());
		tradeService.trade("TEA", 1, "Sell", 102, null);
		
		double result = gbceService.allShareIndex();
		Assert.assertEquals(Double.valueOf("1.0000000001626996"), Double.valueOf(result));
	}
}
