package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class InternalException extends AbstractException {

	/***  */
	private static final long serialVersionUID = 1L;
	static {
		httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}
	public InternalException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InternalException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	public InternalException(String message, Throwable arg1) {
		super(message, arg1);
		// TODO Auto-generated constructor stub
	}
	public InternalException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public InternalException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
}
