package com.course.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DatabaseUtil {

    public static SqlSession getSqlSession() throws IOException {
        //获取数据库配置资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");

        //使用类加载器把数据库配置文件加载出来
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

        //返回SqlSession, SqlSession就是能够执行配置文件中的sql语句
        SqlSession sqlSession = factory.openSession();

        return sqlSession;
    }
}
