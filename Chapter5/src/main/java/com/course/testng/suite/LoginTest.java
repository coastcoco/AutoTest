package com.course.testng.suite;

import org.testng.annotations.Test;

//逻辑控制的类，主要写@Test标签包含的方法
public class LoginTest {

    @Test
    public void  loginTaoBao(){
        System.out.println("淘宝登录成功！");
    }
}
