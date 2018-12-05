package com.course.model;

import lombok.Data;

public class User {

    @Data
    // 除了id是int型，其他都是字符型。
    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isActive;

    @Override
    public String toString(){
        return (

                )
    }
}
