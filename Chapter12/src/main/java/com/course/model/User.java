package com.course.model;


import lombok.Data;

@Data
public class User {

    // 除了id是int型，其他都是字符型。
    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isActive;

    //覆写toString()，以json串形式输出
    @Override
    public String toString(){
        return (
                "id:" + id + "," +
                        "{userName:" + userName + "," +
                        "passworld:" + password + "," +
                        "age:" + age + "," +
                        "sex:" + sex + "," +
                        "permission:" + permission + "," +
                        "isActive:" + isActive + "}"
                );
    }
}
