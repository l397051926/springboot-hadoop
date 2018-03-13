package com.springboot.hadoop.numDemo;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author liumingxin
 * @create 2018 23 15:36
 * @desc
 **/
public class NumReduce extends Reducer<NumBean,LongWritable,LongWritable,LongWritable> {
    @Override
    protected void reduce(NumBean key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        Iterator<LongWritable> it =values.iterator();

        while(it.hasNext()){
            context.write(new LongWritable(key.getLeft_num()), it.next());
        }

    }
}
