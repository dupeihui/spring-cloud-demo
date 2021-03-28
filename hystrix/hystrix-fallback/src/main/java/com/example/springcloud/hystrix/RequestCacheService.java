package com.example.springcloud.hystrix;

import com.example.springcloud.MyService;
import com.example.springcloud.entity.Friend;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RequestCacheService {

    @Autowired
    private MyService myService;

    @CacheResult
    @HystrixCommand(commandKey = "cacheKey")
    public Friend requestCache(@CacheKey String name){
        log.info("request cache " + name);
        Friend friend = new Friend();
        friend.setName(name);
        friend = myService.sayHi(friend);
        log.info("after request cache " + name);
        return friend;
    }

}
