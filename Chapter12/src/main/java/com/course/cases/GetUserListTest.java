package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserListCase;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetUserListTest {

    @Test(dependsOnGroups = "loginTrue",description = "测试获取用户列表接口测试")
    public void getUserList() throws IOException, InterruptedException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserListCase getUserListCase =sqlSession.selectOne("getUserListCase",1);
        System.out.println(getUserListCase.toString());
        System.out.println(TestConfig.getUserListUrl);

        //发请求并获取结果,getUserInfo返回的是json
        JSONArray resultJson = getJsonResult(getUserListCase);

        //断言-验证结果,分3步
        List<User> userList = sqlSession.selectList(getUserListCase.getExpected(),getUserListCase);
        for (User u:userList){
            System.out.println("获取到的user是："+u.toString());
        }

        //第1步：把userList转成json格式
        JSONArray userListJson = new JSONArray(userList);
        //第2步：首先断言userListjson和resultJson长度即用户个数是否一样
        Thread.sleep(3000);
        Assert.assertEquals(userListJson.length(),resultJson.length());
        //第2步：然后断言比较里面用户是否一样。
        for (int i =0;i<resultJson.length();i++) {
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());
        }
    }

    //添加参数发起请求，并返回结果
    private JSONArray getJsonResult(GetUserListCase getUserListCase) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject parm = new JSONObject();
        parm.put("userName",getUserListCase.getUserName());
        parm.put("age",getUserListCase.getAge());
        parm.put("sex",getUserListCase.getSex());
        parm.put("expected",getUserListCase.getExpected());

        post.setHeader("content-type","application/josn");
        StringEntity entity = new StringEntity(parm.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.client.setCookieStore(TestConfig.store);
        String result;

        HttpResponse response = TestConfig.client.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");


        List resultList = Arrays.asList(result);
        JSONArray jsonArray = new JSONArray(resultList);
        return jsonArray;
    }
}
