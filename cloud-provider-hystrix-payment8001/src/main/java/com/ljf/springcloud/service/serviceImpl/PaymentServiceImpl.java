package com.ljf.springcloud.service.serviceImpl;

import cn.hutool.core.util.IdUtil;
import com.ljf.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${server.port}")
    private String serverPort;
    /**
     *  可以正常访问的
     * */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"    paymentInfo_OK,:id:"+id+"访问成功,端口："+ serverPort;
    }

    /**
     *  超时访问抛异常
     * */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOut_Handler"  //指定服务降级后的方法
                //指定该服务方法自身超时时间
            ,commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public String paymentInfo_TimeOut(Integer id) {
        if (true){
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "线程池："+Thread.currentThread().getName()+"    paymentInfo_TimeOut,:id:"+id+"访问成功,耗时（毫秒）：3000"+"端口："+serverPort;
        }else {
            int age=10/0;       //服务抛异常
            return "测试异常抛出时的降级服务";
        }
      }
    //上方服务降级时会调用该方法【兜底方法】---->总结当正常服务不可用时会替换以下方法作为兜底的服务返回数据
    public String paymentInfo_TimeOut_Handler(Integer id) {
        return "线程池："+Thread.currentThread().getName()+"    paymentInfo_TimeOut_Handler,:id:"+id+"访问失败，服务降级"+"端口："+serverPort;
    }

    //===============服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {     //服务降级
            /*下面4个注解声明：打开一个断路器，规定10秒内请求错误阈值不能超过10次*/
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),       //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),        //最大错误次数,跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id<0){
            throw new RuntimeException("--------ID 不可为负数");
        }
        String serialNumber= IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "ID 不可为负数,请稍后再试，id="+id;
    }
}
