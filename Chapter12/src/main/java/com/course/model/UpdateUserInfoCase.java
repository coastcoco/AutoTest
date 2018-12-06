package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {

    private int id;
    private int userId;
    private String userName;
    private String sex;
    private String age;
    private String permisson;
    private String isActive;
    private String expected;

    public  String toString(){
        return (
                "{id"+id+""+
                "userid"+userId+""+
                "username"+userName+""+
                "sex"+sex+""+
                "age"+age+""+
                "permisson"+permisson+""+
                "isActive"+isActive+""+
                "expected"+expected+"}"

                );
    }
}
