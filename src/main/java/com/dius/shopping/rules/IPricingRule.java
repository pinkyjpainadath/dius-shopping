package com.dius.shopping.rules;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import com.dius.shopping.model.Item;


public interface IPricingRule {

	public Map<Item, BigDecimal>  calculateDiscount(Map<Item, Integer> items);

}
