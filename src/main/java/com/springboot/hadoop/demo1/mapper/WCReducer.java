package com.springboot.hadoop.demo1.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class WCReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<LongWritable> vals =values.iterator();
        long count =0;
        String inval ="";
        while(vals.hasNext()){
            long val =vals.next().get();
            count +=val;
            inval+=inval+" "+val;
        }
        System.out.println("-*---key: "+key.toString()+"value: "+count);
        context.write(key,new LongWritable(count));

    }
}
