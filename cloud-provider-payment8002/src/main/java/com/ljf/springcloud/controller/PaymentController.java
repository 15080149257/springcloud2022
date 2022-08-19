package com.ljf.springcloud.controller;

import com.ljf.springcloud.entities.CommonResult;
import com.ljf.springcloud.entities.Payment;
import com.ljf.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult creat(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        //打日志
        log.info("*****插入结果*****"+result);

        if (result>0){
            return new CommonResult(200,"插入成功",result);
        }else {
            return new CommonResult(404,"插入失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment=paymentService.getPaymentById(id);
        //打日志
        log.info("*****检索结果*****"+payment+"656565656");
        if (payment!=null){
            return new CommonResult(200,"查询成功"+"serverPort:"+serverPort,payment);
        }else {
            return new CommonResult(404,"没有对应的记录，查询ID："+id,null);
        }
    }
}
