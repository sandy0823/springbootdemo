package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.dto.ApplicationPropertiesV1;
import com.example.demo.config.dto.ApplicationPropertiesV2;
import com.example.demo.config.dto.CoustomPropertiesInjectV1;
import com.example.demo.config.dto.CoustomPropertiesInjectV2;
import com.example.demo.config.dto.CoustomYamlInjectV1;
import com.example.demo.util.SpringContextUtil;


/**
 * 关于Spring boot配置方面的学习
 *<p> 配置的方式主要有以下几种：</p>
    <li>来自 <code>applicaiton</code>开头的配置文件的配置 （ <code>properties</code> 或  <code>yml</code> 结尾）；</li>
    <li> <code>@propertySource</code> 配置的引入外部文件的配置 文件（非<code>application</code>开头的文件，文件可以存储在类上下文环境中，也可以是在文件系统下，如：在项目工程根目录下）,可以配置加载多个外部配置文件，后加载的可以覆盖前面覆盖的同名 key；</li>
    <li> <code>@Bean</code> 自定义加载 的  <code>PropertySourcesPlaceholderConfigurer</code>，可以加载多个外部配置文件。后加载的可以覆盖前面覆盖的同名 key;</li>
         当遇上同名 <code>key</code>的时候，以上三种方式 是从 优先级高到低排列的，同名  <code>key</code>以优先级高的配置方式为准。
 * <p>针对 applicaiton.properties 和 application.yml同时存在的问题</p>
 *  <li>文件均存在同个目录下时，同名 key以 前者配置文件中的值为准</li>
 *  <li><pre>SpringBoot配置文件可以放置在多种路径下，不同路径下的配置优先级有所不同。
可放置目录(优先级从高到低)

    file:./config/ (当前项目路径config目录下);
    file:./ (当前项目路径下);
    classpath:/config/ (类路径config目录下);
    classpath:/ (类路径config下).

优先级由高到底，高优先级的配置会覆盖低优先级的配置；出现同名 key 的时候，以优先级高的目录中的配置文件中的值为准；
SpringBoot会从这四个位置全部加载配置文件并互补配置；  </pre></li>
 * @author sandy
 *
 */
@RestController
@RequestMapping("/pro")
public class PropertiesController {
	@Autowired
	private CoustomPropertiesInjectV1 propertiesInjectV1;
	@Autowired
	private CoustomPropertiesInjectV2 propertiesInjectV2;
	@Autowired
	private CoustomYamlInjectV1 coustomYamlInjectV1;
	@Autowired
	private ApplicationPropertiesV1 applicationPropertiesV1;
	@Autowired
	private ApplicationPropertiesV2 applicationPropertiesV2;
   
	@GetMapping("/configurationProperties")
	public CoustomPropertiesInjectV1 findPropertiesInjectV1(){
		return propertiesInjectV1;
	}
	
	@GetMapping("/configurationProperties/V2")
	public CoustomPropertiesInjectV2 findPropertiesInjectV2(){
		return propertiesInjectV2;
	}
	
	@GetMapping("/yml")
	public CoustomYamlInjectV1 findYamlInject(){
		return coustomYamlInjectV1;
	}
	@GetMapping("/application")
	public ApplicationPropertiesV1 findApplicationInject(){
		return applicationPropertiesV1;
	}
    
	/**
	 * 配置项存在依赖包里面的 application.properties中<br>
	 * 将本工程中的 src/main/resoures 下的 application.properties 修改成另一个名字，可以看到是默认直接记载了依赖包里面的application.properties<br>
	 * 在本工程中也存在 application.properties 中，则使用本工程中的该配置文件
	 * @return
	 */
	@GetMapping("/application/V2")
	public ApplicationPropertiesV2 findApplicationInjectV2(){
		return applicationPropertiesV2;
	}
	
	@RequestMapping("refresh")
	public ApplicationPropertiesV2 refresh(){
		((AbstractApplicationContext) SpringContextUtil.getApplicationContext()).refresh();;
		return applicationPropertiesV2;
	}
}
