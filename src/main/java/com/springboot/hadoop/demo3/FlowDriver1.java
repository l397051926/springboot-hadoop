package com.springboot.hadoop.demo3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job= Job.getInstance(configuration,"FLOWJOB2");

        job.setJarByClass(FlowDriver1.class);
        job.setMapperClass(FlowMap1.class);
        job.setReducerClass(FlowReuce1.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.187.21:9000/data/flow"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.187.21:9000/data/flow/result"));

        job.waitForCompletion(true);

    }
}
