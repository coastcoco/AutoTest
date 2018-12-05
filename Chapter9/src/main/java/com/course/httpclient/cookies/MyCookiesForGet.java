package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;


import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;

    //用来存储cookies信息的变量
    private CookieStore store ;
    @BeforeTest
    public  void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);    //application resources下的配置文件
        url = bundle.getString("test.url");
    }

    //获取cookies信息的方法
    @Test
    public  void testGetCookies() throws IOException {

        //从配置文件中拼接测试url
        String uri= bundle.getString("testGetCookies.uri");
        String testUrl = this.url+uri;

        //get方法-测试逻辑实现
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response =client.execute(get);
        //转换成字符串型
        String result;
        result= EntityUtils.toString(response.getEntity());
        System.out.print(result);


        //获取cookies信息
        this.store=client.getCookieStore();
        List<Cookie> cookieList= store.getCookies();

        for (Cookie cookie: cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println(";cookieName= "+name+"   cookieValue="+value);
        }
    }


    //依赖携带cookies信息的方法
     @Test(dependsOnMethods = {"testGetCookies"})
     public  void  testGetWithCookies() throws IOException {

        //从配置文件读取url
         String uri= bundle.getString("testGetWithCookies.uri");
         String testUrl = this.url+uri;

         //声明一个客户端
         HttpGet get = new HttpGet(testUrl);
         DefaultHttpClient client =new DefaultHttpClient();

         //设置cookies信息
         client.setCookieStore(this.store);

         //client.execute(get)访问接口， HttpResponse  response接收响应报文
         HttpResponse  response  = client.execute(get);

         //获取接口响应状态码
         int statusCode = response.getStatusLine().getStatusCode();
         System.out.println("响应状态码："+statusCode);

         //如果状态码返回200，打印返回结果
         if(statusCode == 200)
         {
             String result;
             result= EntityUtils.toString(response.getEntity());
             System.out.print(result);
         }

         else{
             System.out.println("测试失败");
         }

    }
}
