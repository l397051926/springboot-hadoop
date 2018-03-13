package com.springboot.hadoop.numDemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 23 15:38
 * @desc
 **/
public class NumDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job =Job.getInstance(configuration,"Numbean1");

        job.setJarByClass(NumDriver.class);
        job.setMapperClass(NumMapper.class);
        job.setReducerClass(NumReduce.class);

        job.setMapOutputKeyClass(NumBean.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(LongWritable.class);

//        job.setPartitionerClass(NumPartitioner.class);
//        job.setNumReduceTasks(2);

        job.setGroupingComparatorClass(NumGroupingComparator.class);

        FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.187.21:9000/data/num"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.187.21:9000/data/num/result"));

        if(!job.waitForCompletion(true)) return;

    }
}
