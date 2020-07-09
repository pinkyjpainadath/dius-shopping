package com.dius.shopping;

import com.dius.shopping.service.CheckOutService;

public class Application {

	public static void main(String[] args) {

		CheckOutService checkout = CheckOutService.getInstance();
		
		checkout.scan("mbwep");
		checkout.scan("vga");
		checkout.scan("ipd");
		
		System.out.println("Total Amount: " + checkout.total());
	}

}
