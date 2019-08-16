package com.example.demo.controller;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.InternalException;
import com.example.demo.model.dto.FileDTO;
import com.example.demo.service.DownloadCSV;

@RestController
@RequestMapping("/download")
public class DownloadController {
    private static final Logger log = LoggerFactory.getLogger(DownloadController.class);
    @Autowired
	private DownloadCSV downloadCSV;
    
    @GetMapping("/csv")
	public void downloadCSVFile(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		log.info("start to download file");
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			FileDTO fileDto = downloadCSV.download();
			response.reset();
	        response.setContentType("application/csv");
	    	response.setHeader("Content-Disposition", "attachment;fileName="
					+ new String(fileDto.getFileName().getBytes("gb2312"), "ISO8859-1"));
	    	inputStream = fileDto.getFileInputStream();
		    if(null == inputStream) {
		    	//抛出异常，下载失败
		    	log.error("inputStream is null");
		    	throw new InternalException("cannot get file");
		    }
		    os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			//这句话至关重要，没有这句话，导出来的文件内容不全
			os.flush(); 
		}catch(Throwable e) {
			throw e;
		}finally {
			if(null != inputStream) {
				IOUtils.closeQuietly(inputStream);
			}
		}
	}
}
