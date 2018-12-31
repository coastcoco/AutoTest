package com.course.controller;
import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value="v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)

    //下面方法参数的意思是根据入参返回接口处理结果


    public  Boolean login(HttpServletResponse response, @RequestBody User user){

        //查询数据库中是不是有这个用户，只要查得出一条数据就证明数据库有这个用户
        int i = template.selectOne("login",user);
        //返回cookie信息
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);

        log.info("查询到的结果是"+i);

        if(i == 1){
            log.info("登录的用户是:"+user.getUserName());
            return true;
        }
      return false;
    }


    @ApiOperation(value = "添加用户接口",httpMethod = "POST") //这个注解意思是api操作Operation，操作
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    //验证上面返回的cookie信息
    public  boolean addUser(HttpServletRequest request,@RequestBody User user){
            Boolean x = verifyCookies(request);
            int result = 0;
            if (x != null){
                result = template.insert("addUser",user);
            }
            if(result > 0){
                log.info("添加用户数是："+result);
                return true;
            }
         return false;
    }


    @ApiOperation(value = "获取用户信息（列表）接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    //获取用户信息，因为用户不止一个所以用列表
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        //cookies验证通过就去数据库查用户信息
        if (x == true){
            List<User> users = template.selectList("getUserInfo",user);
            log.info("getUserInfo获取到的用户数量"+users.size());
            return  users;
        }
        else {
        return null;
        }
    }


    @ApiOperation(value = "更新/删除用户接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public  int updateUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verifyCookies(request);
        int i = 0;
        if(x == true){
            i = template.update("updateUserInfo",user);
        }
        log.info("更新用户数据条目为："+i);
        return i;
    }


    //抽出验证cookies信息的方法
    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie [] cookies = request.getCookies();
        if (Objects.isNull(cookies)){
            log.info("cookies为空");
            return false;
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login") &&
                cookie.getValue().equals("true")) {
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
