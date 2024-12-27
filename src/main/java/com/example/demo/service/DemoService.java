package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.DemoRepository;

@Service
public class DemoService {
    private final DemoRepository demoRepository;

    public DemoService(DemoRepository demoRepository){
        this.demoRepository = demoRepository;
    }

    public void settingName(){
        String item = "隆";
        demoRepository.setName(item);
    }

    public String callName(){
        return demoRepository.getName();
    }

    public String changeName(String name){
       String message = "Service層での処理:"+name;
       return message;
    }
}
