package com.marpe.cht.exceptions;

public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = 3641711418158470251L;

	public ServiceException(String msg) {
		super(msg);
	}
	
}
