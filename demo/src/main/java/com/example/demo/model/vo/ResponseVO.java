package com.example.demo.model.vo;

/**
 * 返回固定格式
 * 
 * @author sandy0823
 *
 */
public class ResponseVO<T> {
	private String code = "0";
	private String msg;
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
