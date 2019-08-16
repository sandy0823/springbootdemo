package com.example.demo.model.dto;

import java.io.InputStream;

public class FileDTO {

	/**文件流*****/
    private InputStream fileInputStream;
    /**文件名称****/
    private String fileName;
    
	public FileDTO(InputStream fileInputStream, String fileName) {
		super();
		this.fileInputStream = fileInputStream;
		this.fileName = fileName;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
