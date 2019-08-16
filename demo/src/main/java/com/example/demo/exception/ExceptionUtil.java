package com.example.demo.exception;

import org.springframework.util.StringUtils;

public class ExceptionUtil {
	
	/**
	   *  查找异常中的message
	 * @param arg0
	 * @return
	 */
	public static String findMsg(Throwable arg0) {
		String msg = arg0.getMessage();
		while(StringUtils.isEmpty(msg) && arg0.getCause() != null) {
			msg = arg0.getMessage();
		}
		return msg;
	}
}
