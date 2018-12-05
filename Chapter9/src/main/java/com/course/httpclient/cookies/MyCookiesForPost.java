package com.course.httpclient.cookies;

import com.sun.security.ntlm.Client;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

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

    public void testPostMethod() throws IOException {

        //获取uri 并且拼接测试地址
        String uri = bundle.getString("testPostWithCookies.uri");
        String testUrl = this.url + uri;

        //声明一个Client对象，用来进行方法的执行
         DefaultHttpClient client = new DefaultHttpClient();

        //声明一个方法，这个方法就是post方法
        HttpPost  post = new HttpPost(testUrl);

        //添加参数,mock写的json格式，先引进json插件,json都是key-value形式
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        //设置请求头信息，设置header,无论多少头信息都是key-value形式添加
        post.setHeader("Content-Type","application/json");

        //将参数信息添加到方法中,实体整个对象都在StringEntry中,把异常抛出去
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);  /*把参数和post方法绑在一块*/

        //声明一个对象来进行响应结果的存储
         String result;

        //设置cookies信息,client是执行，post都是自定义参数
        client.setCookieStore(this.store);

        //执行post方法
         HttpResponse response = client.execute(post);

        //获取响应结果
        result= EntityUtils.toString(response.getEntity());
        System.out.print(result);

        //将返回的字符串响应结果转化成json对象
        JSONObject resultJson = new JSONObject(result);

        //获取到实际结果值和期望结果进行比较 success为期望结果，actualResult为实际结果
        String actualResult = (String) resultJson.get("huhansan");
        String status = String.valueOf(resultJson.get("status"));

        Assert.assertEquals("success",actualResult);   //实际结果值和期望结果进行比较
        Assert.assertEquals("1",status);
    }


}
