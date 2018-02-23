package com.springboot.hadoop.profit;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 23 14:15
 * @desc
 **/
public class ProfitDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job = Job.getInstance(configuration,"Profit");

        job.setJarByClass(ProfitDriver.class);
        job.setMapperClass(ProfitMapper.class);
        job.setReducerClass(ProfitReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ProfitBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.187.21:9000/data/profit"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.187.21:9000/data/profit/result"));

        job.waitForCompletion(true);

    }
}
