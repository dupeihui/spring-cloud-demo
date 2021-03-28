package com.example.springcloud;

import com.example.springcloud.hystrix.Fallback;
import com.example.springcloud.inf.IService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "feign-client", fallback = Fallback.class)
public interface MyService extends IService {
}
