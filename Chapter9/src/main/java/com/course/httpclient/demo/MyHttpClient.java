package com.course.httpclient.demo;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    void test1() throws IOException {

        //用来存放我们的结果
        String result;

        HttpGet get = new HttpGet("http://www.baidu.com");
        /* 这个是执行get方法的 */
        HttpClient client = new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        //转换成字符串型
        result= EntityUtils.toString(response.getEntity());
        System.out.println(result);
    }

}
