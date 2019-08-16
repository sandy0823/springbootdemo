package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.StudentDao;
import com.example.demo.dao.entity.Student;

@RestController
public class DemoController {
	@Autowired
    private StudentDao studentDao;
	
	@RequestMapping("/hello")
	public Student demo(){
		return studentDao.selectByPrimaryKey(1);
	}
}
