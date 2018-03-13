package com.springboot.hadoop.numDemo;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 23 15:33
 * @desc
 **/
public class NumMapper extends Mapper<LongWritable,Text,NumBean,LongWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String lines=value.toString();
        String [] line= lines.split(" ");
        NumBean numBean=new NumBean();
        numBean.setLeft_num(Long.parseLong(line[0]));
        numBean.setRight_num(Long.parseLong(line[1]));

        context.write(numBean,new LongWritable(numBean.getRight_num()));
    }
}
