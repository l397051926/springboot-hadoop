package com.springboot;

import com.alibaba.fastjson.JSON;
import com.springboot.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liumingxin
 * @create 2018 11 13:37
 * @desc
 **/
public class util {
    public static void main(String[] args) {
        User user=new User();
        user.setId(1);
        user.setAge(12);
        user.setName("zs");
        user.setSex("nan");

        String json= JSON.toJSONString(user);
        System.out.println(json);
        Map<String,Map<String,Boolean>> map =new HashMap<>();

        for(Map.Entry<String,Map<String,Boolean>> entry :map.entrySet()){
            String key1= entry.getKey();
            Map<String,Boolean> map1 =entry.getValue();
            for (Map.Entry<String,Boolean> entry2 :map1.entrySet()){
                String key2=entry2.getKey();
                Boolean val2=entry2.getValue();
            }
        }

    }
}
