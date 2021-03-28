package com.example.springcloud.controller;

import com.example.springcloud.entity.Friend;
import com.example.springcloud.inf.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/retry")
    public String retry(@RequestParam(name = "timeout") Integer timeout){
        return service.retry(timeout);
    }

}
