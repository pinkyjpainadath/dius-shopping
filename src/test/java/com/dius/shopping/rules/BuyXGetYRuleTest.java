package com.dius.shopping.rules;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dius.shopping.model.Item;
import com.dius.shopping.service.CheckOutService;

class BuyXGetYRuleTest {
	
	BuyXGetYRule buyXGetYRule;
	
	@Test
	@DisplayName("Test the number of items on which discount is applied")
	void checkCalculateDiscount() {
		buyXGetYRule = new BuyXGetYRule("atv",3,1);
		Map<Item, Integer> items = new HashMap();
		items.put(new Item("atv", "Apple TV", new BigDecimal("109.50")),3);
		items.put(new Item("vga", "VGA adapter", new BigDecimal("30.00")),1);
		assertEquals(1,buyXGetYRule.calculateDiscount(items).size());
	}
	

	@Test
	@DisplayName("Test if discount is valid for invalid quantity")
	void checkCalculateDiscountInvalid() {
		buyXGetYRule = new BuyXGetYRule("atv",3,1);
		Map<Item, Integer> items = new HashMap();
		items.put(new Item("atv", "Apple TV", new BigDecimal("109.50")),2);
		items.put(new Item("vga", "VGA adapter", new BigDecimal("30.00")),1);
		assertEquals(0,buyXGetYRule.calculateDiscount(items).size());
	}
	

}
