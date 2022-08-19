package com.ljf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients     //Feign注解
@EnableHystrix
public class OrderHyrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHyrixMain80.class, args);
    }
}
