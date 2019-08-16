package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.FileDTO;

@Service
public class DownloadCSV extends AbstractDownload {
    private static final String csvTemplate = "/data/csv/downloadTemplate.csv";
    
	@Override
	public FileDTO download() {
		return new FileDTO(download(csvTemplate),"downloadTemplate.csv");
	}
}
