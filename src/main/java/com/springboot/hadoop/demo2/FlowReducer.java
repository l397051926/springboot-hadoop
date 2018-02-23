package com.springboot.hadoop.demo2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class FlowReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<LongWritable> vals =values.iterator();
        Long count =0L;
        while(vals.hasNext()){
            Long val =vals.next().get();
            count+=val;
        }
        context.write(key,new LongWritable(count));
    }
}
