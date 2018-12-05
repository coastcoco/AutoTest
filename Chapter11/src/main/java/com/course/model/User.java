package com.course.model;


import lombok.Data;

@Data        //注解在类上，为类提供数据读写属性
public class User {
    private  int id;
    private  String name;
    private  int age;
    private  String sex;
}
