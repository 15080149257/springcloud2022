package com.ljf.springcloud.service;

public interface PaymentService {
    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    //服务熔断
    String paymentCircuitBreaker(Integer id);
}
