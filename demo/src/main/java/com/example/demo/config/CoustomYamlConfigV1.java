package com.example.demo.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

/**
 * 测试加载自定义 yaml文件
 * @author sandy
 *
 */
@Configuration
public class CoustomYamlConfigV1 {
	@Bean
	public static PropertySourcesPlaceholderConfigurer loadProperties() {
	    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
	    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	    //yaml.setResources(new FileSystemResource("classpath:config/user.yml"));//File路径引入
	    yaml.setResources(new ClassPathResource("coustom.yml"),new FileSystemResource("config/custom-config-dir.yml"));//class路径引入
	    configurer.setProperties(yaml.getObject());
	    return configurer;
	}
}
