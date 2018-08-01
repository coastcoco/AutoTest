package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamterTest {

    @Test
    @Parameters({"name","age"})              //参数标签
    public void paramterTest1(String name,int age){
        System.out.println("naem=" + name + "；  age=" + age);
    }
}
