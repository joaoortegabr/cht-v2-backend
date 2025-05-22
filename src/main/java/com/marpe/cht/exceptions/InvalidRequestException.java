package com.marpe.cht.exceptions;

public class InvalidRequestException extends RuntimeException {
	private static final long serialVersionUID = -1596178670559153828L;

	public InvalidRequestException(String msg) {
		super(msg);
	}

}
