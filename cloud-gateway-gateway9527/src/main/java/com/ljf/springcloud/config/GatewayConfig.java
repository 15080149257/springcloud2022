package com.ljf.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

@Configuration
public class GatewayConfig {
    /**
     * 配置一个id为route-name的路由规则
     * 当访问【http://127.0.0.1:9527/guonei】地址的时候
     * 网关会主动转发链接到【http://news.baidu.com/guonei】
     * */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_ljf1"  //该命名相当于一个分组，每个分组下可以有多个网关映射
                //访问映射名字
                ,r->r.path("/guonei")
                        //网关转发到下面的地址
                        .uri("http://news.baidu.com/guonei")).build();

        //创建分组下多个网关映射
        routes.route(r->r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }

    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_ljf2"  //该命名相当于一个分组，每个分组下可以有多个网关映射
                //访问映射名字
                ,r->r.path("/yinyue")
                        //网关转发到下面的地址
                        .uri("http://music.91q.com/")).build();
        return routes.build();
    }

    //获取当前时区时间
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.err.println(now);
        //2022-08-18T16:07:13.824+08:00[Asia/Shanghai]
    }

}
