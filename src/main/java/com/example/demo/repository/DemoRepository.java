package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DemoRepository {
    private final List<DemoRepository> dataList = new ArrayList<>();

    public DemoRepository(){

    }

    private String name;
    private int age;
    
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public List<DemoRepository> getAllData(){
        return dataList;
    }

    
}
