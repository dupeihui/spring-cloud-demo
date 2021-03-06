package com.example.springcloud.inf;

import com.example.springcloud.entity.Friend;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("feign-client")
public interface IService {

    @GetMapping("/sayHi")
    public String sayHi();

    @PostMapping("/sayHi")
    public Friend sayHi(@RequestBody Friend friend);

    @GetMapping("/retry")
    public String retry(@RequestParam(name = "timeout") int timeout);

    @GetMapping("/error")
    public String error();
}
