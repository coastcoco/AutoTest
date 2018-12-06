package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.TestAddress;
import org.apache.http.impl.client.DefaultHttpClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

    @Test
}
