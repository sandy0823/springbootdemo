package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dao.entity.Student;


@Mapper//加上该注解才能使用@MapperScan扫描到
public interface StudentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}