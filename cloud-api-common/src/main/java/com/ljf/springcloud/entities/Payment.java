package com.ljf.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//lombok注解
@Data
//lombok全参:使用该注解下方实体类默认生成一个全参
@AllArgsConstructor
//lombok空参
@NoArgsConstructor
public class Payment implements Serializable {
    private Long id;
    private String serial;
}
