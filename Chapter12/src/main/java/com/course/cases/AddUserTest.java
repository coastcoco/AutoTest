package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    //依赖登录成功
    @Test(dependsOnGroups = "loginTrue",description = "增加用户接口测试")
    public void addUser () throws IOException, InterruptedException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = sqlSession.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);

        //发请求，并获取结果
        String result = getResult(addUserCase);

        //验证返回结果,我自己在数据库把这用户查出来，看它在不在表里
        Thread.sleep(3000);
        User user = sqlSession.selectOne("addUser",addUserCase); /*addUser需要在mapper中找
                                                                    现在mapper都是之前写的什么Case的sql
                                                                    语句，所以后面还要写出addUser的语句.addUser作为实际结果

                                                                  addUserCase作为预期结果然后进行对比*/

        Assert.assertEquals(addUserCase.getExpected(),result); //addUserCase.getExpected()预期结果，result实际结果
    }

    //发请求的代码AddUserCase addUserCase为入参
    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject parm = new JSONObject();
        //把case里的数据取出来放到对象里
        parm.put("userName",addUserCase.getUserName());
        parm.put("password",addUserCase.getPassword());
        parm.put("sex",addUserCase.getSex());
        parm.put("age",addUserCase.getAge());
        parm.put("permission",addUserCase.getPermission());
        parm.put("isActive",addUserCase.getIsActive());


        //设置头信息
        post.setHeader("content-type","application/json");
        //在把参数添加到对象中
        StringEntity entity = new StringEntity(parm.toString(),"UTF-8");
        post.setEntity(entity);

        //设置cookies
        TestConfig.client.setCookieStore(TestConfig.store);

        //存放返回结果
        String result;
        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("返回的结果:"+result);
        return result;
    }
}
