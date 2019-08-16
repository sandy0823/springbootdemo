package com.example.demo.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.util.PathInjectUtil;

public abstract class AbstractDownload implements Idownload{
	
	@Autowired
	protected PathInjectUtil pathInjectUtil;
	
	/**
	 * 从项目路径中获取文件
	 * @param filePath
	 * @return
	 */
    protected InputStream download(String filePath) {
    	return pathInjectUtil.getFileInputStreamByClassPathResource(filePath);
    }
}
