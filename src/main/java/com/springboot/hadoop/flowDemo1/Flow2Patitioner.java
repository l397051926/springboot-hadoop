package com.springboot.hadoop.flowDemo1;

import com.springboot.hadoop.demo3.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liumingxin
 * @create 2018 23 11:18
 * @desc
 **/
public class Flow2Patitioner extends Partitioner<Text,FlowBean> {
    private static Map<String,Integer> map;

    static {
        map=new HashMap<>();
        map.put("bj",0);
        map.put("sh",1);
        map.put("sz",2);
    }

    @Override
    public int getPartition(Text text, FlowBean flowBean, int i) {
        String addr=flowBean.getAddr();
        Integer num=map.get(addr);
        if(num==null){
            num=3;
        }
        return num;
    }
}
