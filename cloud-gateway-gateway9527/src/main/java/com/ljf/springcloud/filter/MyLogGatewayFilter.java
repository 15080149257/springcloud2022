package com.ljf.springcloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
//使用自定义全局过滤器必须实现【GlobalFilter】【Ordered】两个接口
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    /**
     * 模板化，使用时修改相关逻辑即可
     * */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.err.println("---------------全局过滤器："+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname==null){
            System.err.println("-----非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
