package com.cr.basic.code.pcr.testcode;

import com.cr.basic.code.pcr.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author chengri.piao
 * @Title:
 * @Package
 * @Description:
 * @date 2020/8/18 9:42
 */
public class TestRun {

    public TestRun(){
        System.out.println("init TestRun====");
    }
    private static TestRun testRun = new TestRun();

    public  String toString(){
        return "The instance is:" + this;
    }

    public static void main(String[] args) {
        List<User> userList = new ArrayList();
        userList.add(new User().setId("A").setName("张三"));
        userList.add(new User().setId("A").setName("李四"));
        userList.add(new User().setId("C").setName("王五"));
        Map<String, String> collect = userList.stream().collect(
            Collectors.toMap(User::getId, User::getName, (k, v) -> k, TreeMap::new)
        );

        for (String key : collect.keySet()) {
            System.out.println("key--======" + key);
            System.out.println(collect.get(key));
        }

        System.out.println( testRun);
    }
}
