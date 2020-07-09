package com.dius.shopping.service;

import java.math.BigDecimal;
import java.util.Map;

import com.dius.shopping.model.Item;

public class Catalogue {
	
    /**
     * Default Catalog with all items
     * 
     */
    private static Map<String, Item> items = Map.of(
            "ipd", new Item("ipd", "Super iPad", new BigDecimal("549.99")),
            "mbp", new Item("mbp", "MacBook Pro", new BigDecimal("1399.99")),
            "atv", new Item("atv", "Apple TV", new BigDecimal("109.50")),
            "vga", new Item("vga", "VGA adapter", new BigDecimal("30.00")));

    public static Map<String, Item> getDefaultCatalogue() {
        return items;
    }

}
