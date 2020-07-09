package com.dius.shopping.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dius.shopping.exception.InvalidSKUException;

class CheckOutServiceTest {

	CheckOutService checkout;

	@BeforeEach
	void init() {
		 checkout = CheckOutService.getInstance();
	}
	
	@Test
	@DisplayName("Invalid SKU Throw Exception")
	void invalidScanSKU() {
		assertThrows(InvalidSKUException.class, () -> {
			checkout.scan("notvalidSKU");
		    });
	}

	@Test
	@DisplayName("Valid SKU Scan Adds Item")
	void validScanSKU() {
		checkout.scan("ipd");
		assertEquals(1,checkout.items.size());
	}
}
