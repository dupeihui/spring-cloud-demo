package com.example.springcloud;

import com.example.springcloud.entity.Friend;
import com.example.springcloud.hystrix.RequestCacheService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private MyService myService;

    @Autowired
    private RequestCacheService requestCacheService;

    @GetMapping("/fallback")
    public String fallback(){
        return myService.error();
    }

    @GetMapping("/timeout")
    public String timeout(Integer timeout){
        return myService.retry(timeout);
    }

    @GetMapping("/timeout2")
    @HystrixCommand(
            fallbackMethod = "timeoutFallback",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public String timeout2(Integer timeout){
        while (--timeout >= 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        return "No timeout!";
    }

    public String timeoutFallback(Integer timeout){
        return "timeout!";
    }

    @GetMapping("/cache")
    public Friend cache(String name){
        @Cleanup HystrixRequestContext context = HystrixRequestContext.initializeContext();

        Friend friend = requestCacheService.requestCache(name);
        //name += "!";
        friend = requestCacheService.requestCache(name);
        return friend;
    }

}
