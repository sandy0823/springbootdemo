package com.example.demo.dao.entity;

public class Student {
    private Integer id;

    private String name;

    private Integer timaeable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTimaeable() {
        return timaeable;
    }

    public void setTimaeable(Integer timaeable) {
        this.timaeable = timaeable;
    }
}