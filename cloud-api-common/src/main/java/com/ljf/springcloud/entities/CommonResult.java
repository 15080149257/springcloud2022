package com.ljf.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok注解
@Data
//lombok全参
@AllArgsConstructor
//lombok空参
@NoArgsConstructor
public class CommonResult<T> {     //JSON封装体

    //404   not_found
    private Integer code;
    private String message;
    private T data;
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }


}
