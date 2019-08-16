package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * 使用spring的方法查询路径
 * @author sandy0823
 *
 */
@Component
public class PathInjectUtil {
	private static final Logger log = LoggerFactory.getLogger(PathInjectUtil.class);
	
	/**
	  *  <p>根据路径pattern查找文件<p>
	      *  文件格式为：
	  *  <code>classpath*:/config/application.yml</code> 或<code>classpath*:/config/application.yml</code>
	 * @param pathPattern
	 * @return
	 */
    public List<InputStream> getFileInputStreamByPathMatchingResourcePatternResolver(String pathPattern) {
    	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(new PathMatchingResourcePatternResolver());
		try {
			Resource[] resources = resolver.getResources(pathPattern);
			return Stream.of(resources).map(resource->{
				try {
					return resource.getInputStream();
				} catch (IOException e) {
					log.error("cannot get inputStream[{}]",resource.getFilename());
				}
				return null;
			}).filter((inputStream)->!Objects.isNull(inputStream)).collect(Collectors.toList());
		} catch (IOException e) {
			log.error("There is error when find resource of file:{}",pathPattern,e);
		}
    	return Collections.emptyList();
    }
    
    /**
               * 查找文件（这种方式也可以读取到jar包中的文件）
     * @param file  如：xx.xml
     * @return
     */
    public InputStream getFileInputStreamByClassPathResource(String file) {
    	ClassPathResource classPathResource = new ClassPathResource(file);
    	try {
    		//classPathResource.getFile()  ---- 仅能读取本项目中的文件，不能读取jar包的文件
			return classPathResource.getInputStream();
		} catch (IOException e) {
			log.warn("There is error when find resource of file:{}",file,e);
		}
    	return null;
    }
}
