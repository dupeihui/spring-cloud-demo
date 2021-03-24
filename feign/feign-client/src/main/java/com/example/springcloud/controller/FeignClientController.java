package com.example.springcloud.controller;


import com.example.springcloud.entity.Friend;
import com.example.springcloud.inf.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FeignClientController implements IService {

    @Value("${server.port}")
    private String port;

    @Override
    public String sayHi() {
        return "this is " + port;
    }

    @Override
    public Friend sayHi(Friend friend) {
        log.info("you are " + friend.getName());
        friend.setPort(port);
        return friend;
    }
}
