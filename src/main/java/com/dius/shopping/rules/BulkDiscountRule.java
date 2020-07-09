package com.dius.shopping.rules;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.dius.shopping.model.Item;
public class BulkDiscountRule implements IPricingRule {

	private final String sku;
	private final int minimumQuantity;
	private final BigDecimal price;

	public BulkDiscountRule(String sku, int minimumQuantity, BigDecimal price) {
		this.sku = sku;
		this.minimumQuantity = minimumQuantity;
		this.price = price;
	}

	@Override
	public  Map<Item, BigDecimal> calculateDiscount(Map<Item, Integer> items) {
		Map<Item, BigDecimal> appliedItems =  new HashMap<Item, BigDecimal>();

		for (Item item : items.keySet()) {
			Integer quantity= items.get(item);
			BigDecimal total=BigDecimal.ZERO;
			if( item.getSku().contentEquals(sku) && quantity>minimumQuantity) {
				total = total.add(price.multiply(new BigDecimal(quantity)));
				appliedItems.put(item, total);
			}
		}
		return appliedItems;
	}

}
