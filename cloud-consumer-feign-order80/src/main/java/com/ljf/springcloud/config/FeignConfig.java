package com.ljf.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    /**
     * feignClient配置日志级别
     * NONE:    默认，不显示任何日志
     * BASIC:   仅记录请求方法，URL，响应状态码，执行时间
     * HEADERS: 记录请求方法，URL，响应状态码，执行时间，请求和响应头信息
     * FULL:    记录请求方法，URL，响应状态码，执行时间，请求和响应头信息，请求和响应正文，请求和响应元数据
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        // 请求和响应的头信息,请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}
