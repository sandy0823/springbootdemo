package com.example.demo.config.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置属性注入(配置项存储在 application.properties 中)
 * 
 * @author sandy
 *
 */
@Component
@ConfigurationProperties(prefix = "hello")
public class CoustomPropertiesInjectV1 {
	private String username;
	@Value("${hello.test.username}")
	private String testUsername;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTestUsername() {
		return testUsername;
	}
	public void setTestUsername(String testUsername) {
		this.testUsername = testUsername;
	}
}
