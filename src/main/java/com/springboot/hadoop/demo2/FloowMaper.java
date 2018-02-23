package com.springboot.hadoop.demo2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FloowMaper extends Mapper<LongWritable,Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line =value.toString();
        String[] datas =line.split(" ");
        String phone =datas[0];
        String city =datas[1];
        String name =datas[2];
        long flow =Long.valueOf(datas[3]);

        context.write(new Text(phone),new LongWritable(flow));

    }
}
