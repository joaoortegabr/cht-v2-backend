package com.marpe.cht.exceptions;

public class UnprocessableRequestException extends RuntimeException {
	private static final long serialVersionUID = 3497003573724038647L;

	public UnprocessableRequestException(String msg) {
		super(msg);
	}
	
}
