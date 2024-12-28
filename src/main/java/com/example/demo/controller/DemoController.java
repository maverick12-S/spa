package com.example.demo.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DemoService;


@RestController
@RequestMapping("/api")
public class DemoController {
    private final DemoService demoService;
    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
    public DemoController(DemoService demoService){
        this.demoService = demoService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "RestApiを用いたspringとreact間の操作:基礎";
    }

    @GetMapping("/show")
    public String showHello(){
        return "ボタンが押されました。";
    }

    @GetMapping("/name")
    public String addWord(){
        demoService.settingName();
        String answer = demoService.callName();
        return answer;
    }

    @PostMapping("/add")
    public Map<String,String>receiveData(@RequestBody Map<String,Object>requestData){
        String name = (String) requestData.get("name");
        String message = demoService.changeName(name);
        logger.debug("DEBUG: Preparing data for processing"+ message);
        Map<String,String> response = new HashMap<>();
        response.put("message",message);
        return response;
    }
    
}
