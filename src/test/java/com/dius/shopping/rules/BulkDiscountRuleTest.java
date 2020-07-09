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

class BulkDiscountRuleTest {
	
	BulkDiscountRule bulkDiscountRule;
	@Test
	@DisplayName("Test the number of items on which discount is applied")
	void checkCalculateDiscount() {
		bulkDiscountRule = new BulkDiscountRule("ipd",4,new BigDecimal(499.99));
		Map<Item, Integer> items = new HashMap();
		items.put(new Item("atv", "Apple TV", new BigDecimal("109.50")),2);
		items.put(new Item("ipd", "Super iPad", new BigDecimal("549.99")),5);
		assertEquals(1,bulkDiscountRule.calculateDiscount(items).size());
	}
	
	@Test
	@DisplayName("Test if bulk discount is valid for invalid quantity")
	void checkCalculateDiscountInvalid() {
		bulkDiscountRule = new BulkDiscountRule("ipd",4,new BigDecimal(499.99));
		Map<Item, Integer> items = new HashMap();
		items.put(new Item("atv", "Apple TV", new BigDecimal("109.50")),1);
		items.put(new Item("ipd", "Super iPad", new BigDecimal("549.99")),1);
		assertEquals(0,bulkDiscountRule.calculateDiscount(items).size());
	}
	


}
