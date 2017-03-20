package com.test.stock.calc.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.stock.calc.model.Stock;
import com.test.stock.calc.model.Trade;

public class DataSource {
	
	public static Map<String, Stock> stockSource = new HashMap<>();
	public static Map<String, List<Trade>> tradeSource = new HashMap<>();
	
	static{
		stockSource.put("TEA", new Stock("TEA", "Common", 0, 0, 100));
		stockSource.put("POP", new Stock("POP", "Common", 8, 0, 100));
		stockSource.put("ELE", new Stock("ELE", "Common", 23, 0, 60));
		stockSource.put("GIN", new Stock("GIN", "Preferred", 8, 2, 100));
		stockSource.put("JOE", new Stock("JOE", "Common", 13, 0, 250));
	}

}
