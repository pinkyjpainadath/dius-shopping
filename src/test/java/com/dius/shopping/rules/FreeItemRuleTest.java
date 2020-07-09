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

class FreeItemRuleTest {
	
	FreeItemRule freeItemRule;
	@Test
	@DisplayName("Test calculateDiscount Negative Scenario")
	void checkCalculateDiscountInvalid() {
		freeItemRule = new FreeItemRule("mbp","vga");
		Map<Item, Integer> items = new HashMap();
		items.put(new Item("atv", "Apple TV", new BigDecimal("109.50")),2);
		items.put(new Item("ipd", "Super iPad", new BigDecimal("549.99")),5);
		assertNotEquals(1,freeItemRule.calculateDiscount(items).size());
	}
	
	
	@Test
	@DisplayName("Test calculateDiscount Negative Scenario")
	void checkCalculateDiscountNoPrerequisite() {
		freeItemRule = new FreeItemRule("mbp","vga");
		Map<Item, Integer> items = new HashMap();
		items.put(new Item("vga", "VGA adapter", new BigDecimal("30.00")),1);
		assertEquals(0,freeItemRule.calculateDiscount(items).size());
	}

	@Test
	@DisplayName("Test calculateDiscount Positive Scenario")
	void checkCalculateDiscountValid() {
		freeItemRule = new FreeItemRule("mbp","vga");
		Map<Item, Integer> items = new HashMap();
		items.put(new Item("vga", "VGA adapter", new BigDecimal("30.00")),1);
		items.put(new Item("mbp", "MacBook Pro", new BigDecimal("1399.99")),1);

		assertEquals(1,freeItemRule.calculateDiscount(items).size());
	}


}
