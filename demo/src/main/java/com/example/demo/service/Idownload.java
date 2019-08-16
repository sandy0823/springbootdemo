package com.example.demo.service;

import com.example.demo.model.dto.FileDTO;

public interface Idownload {
	/**
	   * 下载文件，获取到下载文件流
	 * @return
	 */
	FileDTO download();
}
