package com.springboot.hadoop.demo3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMap1 extends Mapper<LongWritable,Text,Text,FlowBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line =value.toString();

        String[] datas =line.split(" ");

        FlowBean flowBean=new FlowBean(datas[0],datas[1],datas[2],Long.valueOf(datas[3]));

        context.write(new Text(datas[0]),flowBean);
    }
}
