package com.dius.shopping.exception;

public class InvalidSKUException extends RuntimeException {


	private static final long serialVersionUID = 2798763548511750782L;

	public InvalidSKUException(String errorMessage) {
		super(errorMessage);
	}

}