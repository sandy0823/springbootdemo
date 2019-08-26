package com.example.demo.config.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationPropertiesV1 {
	@Value("${day.username}")
	private String helloUsername;
	@Value("${day.test.username}")
	private String helloTestUsername;

	public String getHelloUsername() {
		return helloUsername;
	}

	public void setHelloUsername(String helloUsername) {
		this.helloUsername = helloUsername;
	}

	public String getHelloTestUsername() {
		return helloTestUsername;
	}

	public void setHelloTestUsername(String helloTestUsername) {
		this.helloTestUsername = helloTestUsername;
	}

}
