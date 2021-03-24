package com.example.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "eureka-client", configuration = com.netflix.loadbalancer.RandomRule.class)
public class LBruleConfig {

//    @Bean
//    public IRule defaultLBStrategy() {
//        return new RandomRule();
//    }

}
