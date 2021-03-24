package com.example.springcloud.controller;

import com.example.springcloud.entity.Friend;
import com.example.springcloud.inf.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private IService service;

    @GetMapping("/sayHi")
    public String sayHi(){
        return service.sayHi();
    }

    @PostMapping("/sayHi")
    public Friend sayHi(@RequestBody Friend friend){
        return service.sayHi(friend);
    }

}
