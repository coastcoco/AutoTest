package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
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

public class UpdateUserInfoTest {

    @Test(dependsOnGroups = "loginTrue",description = "测试用户信息更新接口")
    public void updateUserInfo() throws IOException, InterruptedException {

        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase",1);

        //SqlSession sqlSession1 = DatabaseUtil.getSqlSession();
        //User user1 = sqlSession1.selectOne("updateUserInfoCase",updateUserInfoCase);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);

        //实际结果
        int result = getResult(updateUserInfoCase);
        //预期结果
        Thread.sleep(6000);
        User user2 = sqlSession.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        Assert.assertNotNull(user2);
        Assert.assertNotNull(result);
    }

    @Test(dependsOnGroups = "loginTrue",description = "测试删除用户")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase",2);
        System.out.println(updateUserInfoCase);
        System.out.println(TestConfig.updateUserInfoUrl);

        //实际结果--第一个线程
        int result = getResult(updateUserInfoCase);
        //预期结果--第二个线程
        Thread.sleep(3000);
        User user = sqlSession.selectOne(updateUserInfoCase.getExpected(),updateUserInfoCase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }

    private int getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {

        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        JSONObject parm = new JSONObject();
        parm.put("id",updateUserInfoCase.getUserId());
        parm.put("userName",updateUserInfoCase.getUserName());
        parm.put("sex",updateUserInfoCase.getSex());
        parm.put("age",updateUserInfoCase.getAge());
        parm.put("isActive",updateUserInfoCase.getIsActive());

        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(parm.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.client.setCookieStore(TestConfig.store);
        String result;
        //携带cookies信息 发起post请求
        HttpResponse response = TestConfig.client.execute(post);

       //下面这段是什么意思 - -
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        //String转int
        return Integer.parseInt(result);
    }
}


