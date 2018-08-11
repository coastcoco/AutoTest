package com.tester.extend.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestMethodsDemo {

    //多元判断
    @Test
    public void  test1(){
        Assert.assertEquals(1,2);//直接判断执行错误
    }

    @Test
    public void  test2(){
        Assert.assertEquals(1,1);//直接判断1执行正确
    }

    @Test
    public void  test3(){
        Assert.assertEquals("aaa","aaa");//直接判断执行正确
    }
    //演示一个log怎么弄
    @Test
    public void logDemo(){
        Reporter.log("这是我们自己写的日志");
        throw  new RuntimeException("这是我自己运行时的错误");
    }
}
