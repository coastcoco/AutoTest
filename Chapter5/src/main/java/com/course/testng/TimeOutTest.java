package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000)//期望在3000毫秒内执行成功
    public void testSuccess() throws InterruptedException{
        Thread.sleep(2000);
    }

    @Test(timeOut = 2000)//执行失败
    public void testFailed() throws InterruptedException{
        Thread.sleep(3000);
    }
}
