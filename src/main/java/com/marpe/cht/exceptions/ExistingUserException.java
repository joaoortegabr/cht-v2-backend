package com.marpe.cht.exceptions;

public class ExistingUserException extends RuntimeException {
	private static final long serialVersionUID = 1403579611698318589L;

	public ExistingUserException(String msg) {
		super(msg);
	}
	
}
