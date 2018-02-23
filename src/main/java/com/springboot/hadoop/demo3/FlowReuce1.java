package com.springboot.hadoop.demo3;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class FlowReuce1 extends Reducer<Text,FlowBean,Text,NullWritable> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        Iterator<FlowBean> vals =values.iterator();
        long count=0;
        String phone="";
        while(vals.hasNext()){
            FlowBean flowBean=vals.next();
            phone=flowBean.getPhone();
            count+=flowBean.getFlow();

        }
        context.write(new Text(phone+" "+count),NullWritable.get());
    }
}
