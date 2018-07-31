package com.course.testng;

/*异常测试什么时候会用到：
在我们期望结果为某一个异常的时候
比如：我们传入了某个不合法的参数，程序抛出了异常
也就是我们预期结果就是这个异常
*/

import org.testng.annotations.Test;

public class ExpectedException {

    // 这是一个测试结果会失败的异常测试

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败的异常测试");
    }

    // 这是一个成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功的异常测试111");
        throw new RuntimeException(); //java抛异常的写法
    }
}
