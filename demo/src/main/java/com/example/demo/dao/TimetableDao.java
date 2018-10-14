package com.example.demo.dao;

import com.example.demo.dao.entity.Timetable;

public interface TimetableDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Timetable record);

    int insertSelective(Timetable record);

    Timetable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Timetable record);

    int updateByPrimaryKey(Timetable record);
}