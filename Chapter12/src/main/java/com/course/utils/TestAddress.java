package com.course.utils;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestAddress {

    //首先要有一个变量来加载配置文件
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    //接下来是拼接URL，在这里就用到刚才设计的枚举类，它传进来必须是InterfaceName，不让传其他。
    public  static String getUrl(InterfaceName name){

        String address = bundle.getString("test.url");
        String uri="";

        //最终的测试地址
        String testUrl;
        if (name == InterfaceName.LOGIN){
            uri = bundle.getString("login.uri");
        }
        else if (name == InterfaceName.ADDUSER){
            uri = bundle.getString("addUser.uri");
        }
        else if(name == InterfaceName.GETUSERLIST){
            uri = bundle.getString("getUserList.uri");
        }
        else if (name == InterfaceName.GETUSERINFO){
            uri = bundle.getString("getUserInfo.uri");
        }
        else if (name == InterfaceName.UPDATEUSERINFO){
            uri = bundle.getString("updateUserInfo.uri");
        }

        testUrl=address+uri;
        return testUrl;
    }

}
