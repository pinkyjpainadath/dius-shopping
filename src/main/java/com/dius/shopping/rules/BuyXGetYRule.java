package com.dius.shopping.rules;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.dius.shopping.model.Item;

public class BuyXGetYRule implements IPricingRule {


	private final String sku;
	private final int quantityPurchased;
	private final int quantityFree;

	public BuyXGetYRule(String sku, int quantityPurchased, int quantityFree) {
		this.sku = sku;
		this.quantityPurchased = quantityPurchased;
		this.quantityFree = quantityFree;
	}

	@Override
	public Map<Item, BigDecimal>  calculateDiscount(Map<Item, Integer> items) {
		Map<Item, BigDecimal> appliedItems= new HashMap<Item, BigDecimal>();
		for (Item item : items.keySet()) {
			Integer quantity= items.get(item);
			BigDecimal total=BigDecimal.ZERO;
			if( item.getSku().contentEquals(sku) && quantity>=quantityPurchased) {
				quantity=quantity-quantityFree ;
				total = total.add((item.getPrice()).multiply(new BigDecimal(quantity))); 
				appliedItems.put(item, total);
			}
		}
		
		
		return appliedItems;
	}

}
