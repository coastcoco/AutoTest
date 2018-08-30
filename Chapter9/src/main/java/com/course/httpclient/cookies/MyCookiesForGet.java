package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;


import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;


    @BeforeTest
    public  void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);    //application resources下的配置文件
        url = bundle.getString("test.url");
    }

    @Test
    public  void testGetCookies() throws IOException {


        //从配置文件中拼接测试url
        String uri= bundle.getString("getCookies.url");
        String testUrl = this.url+uri;

        //get方法-测试逻辑实现
        HttpGet get = new HttpGet(testUrl);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        //转换成字符串型
        String result;
        result= EntityUtils.toString(response.getEntity());
        System.out.print(result);
    }
}
