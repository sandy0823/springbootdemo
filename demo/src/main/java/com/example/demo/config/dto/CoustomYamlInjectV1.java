package com.example.demo.config.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CoustomYamlInjectV1 {
	private String usernameV2;
	@Value("${yml.test.usernameV2}")
	private String testUsernameV2;
	
	/***与application.properties 文件存在同名key配置项***/
	private String username;
	@Value("${hello.test.username}")
	private String testUsername;
	@Value("${hello.test.usernameV2}")
	private String helloTestUsernameV2;
	
	public String getHelloTestUsernameV2() {
		return helloTestUsernameV2;
	}
	public void setHelloTestUsernameV2(String helloTestUsernameV2) {
		this.helloTestUsernameV2 = helloTestUsernameV2;
	}
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
	public String getUsernameV2() {
		return usernameV2;
	}
	public void setUsernameV2(String usernameV2) {
		this.usernameV2 = usernameV2;
	}
	public String getTestUsernameV2() {
		return testUsernameV2;
	}
	public void setTestUsernameV2(String testUsernameV2) {
		this.testUsernameV2 = testUsernameV2;
	}
}
