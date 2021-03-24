package com.example.springcloud.controller;

import com.example.springcloud.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerController {

    @Autowired
    private IService service;

    @GetMapping("/sayHi")
    public String sayHi(){
        return service.sayHi();
    }

}
