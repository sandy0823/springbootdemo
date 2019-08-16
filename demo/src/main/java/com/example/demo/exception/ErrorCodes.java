package com.example.demo.exception;

/**
   *  错误码集合
 * @author sandy0823
 *
 */
public enum ErrorCodes {
  ;
	private String errorCode;
	private ErrorCodes(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorCode() {
		return errorCode;
	}
}
