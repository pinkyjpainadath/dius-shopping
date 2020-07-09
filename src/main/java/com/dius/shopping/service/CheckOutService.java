package com.dius.shopping.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.dius.shopping.exception.InvalidSKUException;
import com.dius.shopping.model.Item;
import com.dius.shopping.rules.IPricingRule;

public class CheckOutService {

	//Map of Item sku and the quantity added during scan
	protected Map<Item, Integer> items = new HashMap<Item,Integer>();
	//Default Pricing rules
	private final Set<IPricingRule> pricingRules;
	//Default Catalogue- SKU mapped to Item
	private Map<String, Item> catalogue = new HashMap<String,Item>();

	/**
	 * Checkout Service initialized with default pricing rule and default catalogue.
	 * 
	 */
	public static CheckOutService getInstance() {

		return new CheckOutService(PricingRuleMapping.getPriceRuleMapping(),Catalogue.getDefaultCatalogue());

	}

	private CheckOutService(Set<IPricingRule> pricingRules, Map<String, Item> catalogue) {
		this.pricingRules = pricingRules;
		this.catalogue=catalogue;

	}

	public void scan( String sku ) { 
		Item newItem= catalogue.get(sku);
		if(newItem!=null) {
			items.merge(newItem, 1, (a,b) -> a+b);
		}else {
			throw new InvalidSKUException(
			          "Invalid SKU value : " + sku);
		}

	}


	public BigDecimal total() {
		BigDecimal total =  BigDecimal.ZERO;
		for (IPricingRule rule : pricingRules) {
			Map<Item, BigDecimal> appliedItems=rule.calculateDiscount(items);
			if(appliedItems!=null) {
				for (Item item : appliedItems.keySet()) {
					total = total.add(appliedItems.get(item));
					items.remove(item);
				}
			}
		}

		for (Item item : items.keySet()) {
			total = total.add(item.getPrice().multiply(new BigDecimal(items.get(item))));
		}

		return total;
	}




}
