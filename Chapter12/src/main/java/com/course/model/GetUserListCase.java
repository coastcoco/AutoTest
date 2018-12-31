package com.course.model;


import lombok.Data;

@Data
public class GetUserListCase {
    private int id;
    private String userName;
    private String age;
    private String sex;
    private String expected;

    @Override
    public String toString(){

        return (
                "{id:"+id+","+
                        "username:"+userName+","+
                        "age:"+age+","+
                        "sex:"+sex+","+
                        "expected:"+expected+"}"
                );
    }
}
