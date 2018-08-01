package com.course.testng;

import org.testng.annotations.Test;

/*
什么事依赖测试，方法1先执行方法2后执行，方法1失败了方法2就不执行了。
依赖测试应用场景：比如说要买什么东西必须先登录一下，登录失败了还买什么东西，那就别买了呗- -。
*/
public class DependTest {

    @Test
    public  void test1(){
        System.out.println("运行test1");
        // throw new RuntimeException();
    }

    @Test(dependsOnMethods = {"test1"})
    public  void test2(){
        System.out.println("运行test2");
    }

}
