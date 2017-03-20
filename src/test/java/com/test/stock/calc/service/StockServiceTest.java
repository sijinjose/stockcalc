package com.test.stock.calc.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.test.stock.calc.service.impl.StockServiceImpl;

public class StockServiceTest {
	
	private StockService stockService;
	
	@Before
	public void init(){
		stockService = new StockServiceImpl();
	}
	
	@After
	public void tearDown(){
		stockService = null;
	}
	
	@Test
	public void getDividentYieldCommon(){
		double result = stockService.getDividentYield("POP", 120);
		Assert.assertEquals(Double.valueOf("0.06666666666666667"), Double.valueOf(result));
	}
	
	@Test
	public void getDividentYieldCommonZeroDivident(){
		double result = stockService.getDividentYield("TEA", 120);
		Assert.assertEquals(Double.valueOf(0l), Double.valueOf(result));
	}
	
	@Test
	public void getDividentYieldCommonZeroPrice(){
		double result = stockService.getDividentYield("POP", 0);
		Assert.assertEquals(Double.valueOf(0l), Double.valueOf(result));
	}
	
	@Test
	public void getDividentYieldPreferred(){
		double result = stockService.getDividentYield("GIN", 250);
		Assert.assertEquals(Double.valueOf("0.008"), Double.valueOf(result));
		
	}
	
	@Test
	public void getDividentYieldPreferredZeroPrice(){
		double result = stockService.getDividentYield("GIN", 0);
		Assert.assertEquals(Double.valueOf(0l), Double.valueOf(result));
	}

	/**/
	@Test
	public void getPERatio(){
		double result = stockService.getPERatio("POP", 120);
		Assert.assertEquals(Double.valueOf("15"), Double.valueOf(result));
	}
	
	@Test
	public void getPERatioZeroDivident(){
		double result = stockService.getDividentYield("TEA", 120);
		Assert.assertEquals(Double.valueOf(0l), Double.valueOf(result));
	}
	
	@Test
	public void getPERatioZeroPrice(){
		double result = stockService.getDividentYield("POP", 0);
		Assert.assertEquals(Double.valueOf(0l), Double.valueOf(result));
	}
	
}
