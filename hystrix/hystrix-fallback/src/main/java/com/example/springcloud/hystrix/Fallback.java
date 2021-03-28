package com.example.springcloud.hystrix;

import com.example.springcloud.MyService;
import com.example.springcloud.entity.Friend;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class Fallback implements MyService {

    @Override
    @HystrixCommand(fallbackMethod = "fallback2")
    public String error() {
        log.info("Fallback: I'm not a black sheep any more");
        throw new RuntimeException("first fallback");
    }

    public String fallback2(){
        log.info("fallback again");
        return "success";
    }

    @Override
    public String sayHi() {
        return null;
    }

    @Override
    public Friend sayHi(Friend friend) {
        return null;
    }

    @Override
    public String retry(int timeout) {
        return "You are late!";
    }

}
