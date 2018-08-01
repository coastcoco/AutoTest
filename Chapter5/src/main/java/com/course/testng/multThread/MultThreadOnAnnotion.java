package com.course.testng.multThread;

import org.testng.annotations.Test;

//Annotion：注解；MultThread：多线程
public class MultThreadOnAnnotion {

    @Test(invocationCount = 10,threadPoolSize = 3)//线程池中有3个线程
    public void test(){
        System.out.println(1);
        System.out.printf("ThreadID：%s%n",Thread.currentThread().getId());// 打印线程ID
    }
}
//多线程程序，线程执行顺序我们控制不了，多线程难调试也在这里，执行顺序我们控制不了。