package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.DatabaseUtil;
import com.course.utils.TestAddress;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue",description = "测试准备工作，获取HttpClient对象等")
    public void beforeTest(){
        TestConfig.loginUrl = TestAddress.getUrl(InterfaceName.LOGIN);
        TestConfig.getUserListUrl = TestAddress.getUrl(InterfaceName.GETUSERLIST);
        TestConfig.getUserInfoUrl = TestAddress.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.updateUserInfoUrl = TestAddress.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.addUserUrl = TestAddress.getUrl(InterfaceName.ADDUSER);

        TestConfig.client = new DefaultHttpClient();
    }

    //获取数据库数据
    @Test(groups = "loginTrue",description = "测试用户登录成功接口")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //第一步发送请求
       String result = getResult(loginCase);
        //第二步验证结果
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    @Test(groups = "loginFalse",description = "用户登录失败接口测试")
    public void loginFalse() throws IOException, InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);

        //第一步发送请求，返回结果
        String result = getResult(loginCase);
        //第二步验证结果

        Thread.sleep(3000);
        Assert.assertEquals(loginCase.getExpected(),result);
    }

    // //发请求的代码LoginCase loginCase为入参，入参不同所以不能抽出来公用？
    private String getResult(LoginCase loginCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        JSONObject parm = new JSONObject();
        parm.put("userName",loginCase.getUserName());
        parm.put("password",loginCase.getPassword());

        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(parm.toString(),"utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        //给cookies store赋值，不然后面的接口都访问不了
        TestConfig.store = TestConfig.client.getCookieStore();
        return result;

    }
}
