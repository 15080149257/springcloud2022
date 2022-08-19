package com.ljf.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayMian9527 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMian9527.class, args);
    }

}
