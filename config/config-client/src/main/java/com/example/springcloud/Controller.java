package com.example.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {

    @Value("${name}")
    private String name;

    @Value("${myWords}")
    private String words;

    @Value("${food}")
    private String food;


    @GetMapping("/name")
    public String name(){
        return name;
    }

    @GetMapping("/words")
    public String words(){
        return words;
    }

    @GetMapping("/food")
    public String food(){
        return food;
    }
}
