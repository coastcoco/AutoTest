package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

//写我们测试的套件之前运行的方法，公用的东西
public class SuiteConfig {

    @BeforeSuite
    public void  beforeSuite(){
        System.out.println("beforeSuite运行啦");
    }

    @AfterSuite
    public void  afterSuite(){
        System.out.println("afterSuite运行啦");
    }

    @BeforeTest
    public  void  beforeTest(){
        System.out.println("beforeTest运行咯~");
    }

    @AfterTest
    public  void  afterTest(){
        System.out.println("afterTest运行咯~");
    }
}
