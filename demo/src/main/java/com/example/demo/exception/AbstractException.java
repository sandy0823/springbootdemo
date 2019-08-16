package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {
	/***  */
	private static final long serialVersionUID = 1L;
	protected static HttpStatus httpStatus;
	protected String message;
	
	public AbstractException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AbstractException(String message, Throwable arg1, boolean arg2, boolean arg3) {
		super(message, arg1, arg2, arg3);
		this.message = message;
	}
	public AbstractException(String message, Throwable arg1) {
		super(message, arg1);
		this.message = message;
	}
	public AbstractException(String message) {
		super(message);
		this.message = message;
	}
	public AbstractException(Throwable arg0) {
		super(arg0);
		this.message = ExceptionUtil.findMsg(arg0);
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		AbstractException.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
