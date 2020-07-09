package com.dius.shopping.rules;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.dius.shopping.model.Item;

public class FreeItemRule implements IPricingRule{


	private final String skuPurchased;
	private final String skuFree;

	public FreeItemRule(String skuPurchased, String skuFree) {
		this.skuPurchased = skuPurchased;
		this.skuFree = skuFree;
	}

	@Override
	public Map<Item, BigDecimal> calculateDiscount(Map<Item, Integer> items) {
		Map<Item, BigDecimal> appliedItems = new HashMap<Item, BigDecimal>();

		for (Item item : items.keySet()) {
			Integer quantity= items.get(item);
			BigDecimal total=BigDecimal.ZERO;

			if( item.getSku().contentEquals(skuFree) &&  (items.keySet().stream().filter(o -> o.getSku().equals(skuPurchased)).findFirst().isPresent())) {
				quantity=quantity-1;
				total = total.add((item.getPrice()).multiply(new BigDecimal(quantity)));
				appliedItems.put(item, total);
			}
		}
		return appliedItems;
	}
}
