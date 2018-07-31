package com.course.testng.groups;
import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass1 {

    public void stu1(){
        System.out.println("GroupsOnClass1中stu1运行1");
    }

    public void stu2(){
        System.out.println("GroupsOnClass1中stu2运行2");
    }
}
