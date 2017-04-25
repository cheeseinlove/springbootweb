package com.example.model;

/**
 * Created by 讯 on 2017/4/7.
 */
public class Student {
    private int id;
    private String name;

    public Student(String name, int id) {
        this.name=name;
        this.id=id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}