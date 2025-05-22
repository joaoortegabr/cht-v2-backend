package com.marpe.cht.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 8898781985280694351L;

	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
