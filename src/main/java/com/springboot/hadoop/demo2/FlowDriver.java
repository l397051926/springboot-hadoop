package com.springboot.hadoop.demo2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration=new Configuration();
        Job job = Job.getInstance(configuration,"FLOWJOB");

        job.setJarByClass(FlowDriver.class);
        job.setMapperClass(FloowMaper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.187.21:9000/data/flow"));
        FileOutputFormat.setOutputPath(job,new Path("hdfs://192.168.187.21:9000/data/flow/result"));

        job.waitForCompletion(true);
    }
}
