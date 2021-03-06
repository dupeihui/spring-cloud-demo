package com.example.springcloud.controller;


import com.example.springcloud.entity.Friend;
import com.example.springcloud.inf.IService;
import com.sun.jmx.remote.internal.ArrayQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.transaction.PlatformTransactionManagerCustomizer;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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

    @Override
    public String retry(int timeout) {
        while (--timeout >= 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        log.info("retry " + port);
        return port;
    }

    @Override
    public String error() {
        throw new RuntimeException("black sheep");
    }
}
