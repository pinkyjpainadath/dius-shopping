

package com.dius.shopping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dius.shopping.service.CheckOutService;

class ApplicationTests {
	CheckOutService checkout;

	@BeforeEach
	void init() {
		 checkout = CheckOutService.getInstance();
	}
	
	@Test
	@DisplayName("Test FreeBundle Offer")
	void checkFreeBundleOffer() {

		checkout.scan("mbp");
		checkout.scan("vga");
		checkout.scan("ipd");
		assertEquals(1949.98, checkout.total().doubleValue());
	}
	
	@Test
	@DisplayName("Test BulkDiscount Offer")
	void checkBulkDiscountOffer() {
		checkout.scan("atv");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("atv");
		checkout.scan("ipd");
		checkout.scan("ipd");
		checkout.scan("ipd");
		assertEquals(2718.95, checkout.total().doubleValue());
	}
	
	@Test
	@DisplayName("Test BuyXGetY Offer")
	void checkBuyXGetYOffer() {
		checkout.scan("atv");
		checkout.scan("atv");
		checkout.scan("atv");
		checkout.scan("vga");
		assertEquals(249.00, checkout.total().doubleValue());
	}
}
