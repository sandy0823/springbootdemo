package com.example.demo.config.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义配置属性注入(配置项存储在cutom.properties 中)
 * <li>虽然配置了 @PropertySource，并指明了加载其他文件的配置项，但是实际上仍会去application.properties中加载配置<br>
 *     当该类的配置项key与applicaiton.properties中一致时，加载了 applicaiton.propeties中的同名key属性值；
 * </li>
 * <li>自定义配置文件在类上下文时，value中可加上 classpath 指明文件在类路径下，或者不加这个，也默认是在类路劲下</li>
 * <li>加载文件系统文件，自定义配置文件不在类路径下，使用file,默认从项目跟路径下加载</li>
 * <li>@PropertySource 的value 属性可以指定多个地方的配置文件，若不同文件中存在同名key，则后者覆盖前者</li>
 * @author sandy
 *
 */
@PropertySource(value={"classpath:custom.properties","file:config/custom-config-dir.properties"})
@Component
@ConfigurationProperties(prefix = "hello")
public class CoustomPropertiesInjectV2 {
	private String usernameV2;
	@Value("${hello.test.usernameV2}")
	private String testUsernameV2;
	
	/***与application.properties 文件存在同名key配置项***/
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
