package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


/*
这里把方法providerData的数据传到 testDataProvider方法里，让name和age接住
*/
public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name=" + name+";  age" + age);
    }

    @DataProvider  (name = "data")
    public Object[][] providerData(){
          Object[][] o =new Object[][]{
                  {"张三",8},
                  {"李四",9},
                  {"王五",10}
          };
          return o;
    }


    //再来演示演示：DataProvider根据方法来传递参数
    @Test(dataProvider = "methodData")
    public void  test1(String name,int age){
        System.out.println("test111方法name" + name+";  age" + age);
    }

    @Test(dataProvider = "methodData")
    public void  test2(String name,int age){
        System.out.println("test222方法name" + name+";  age" + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result=null;
        if(method.getName().equals("test1")){
          result = new Object[][]{
                  {"张三",20},
                  {"李四",25},

          };
        }else if (method.getName().equals("test2")){
            result = new Object[][]{
                    {"王五",50},
                    {"赵六",60}
            };
        }
        return result;
    }
}
