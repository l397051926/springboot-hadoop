package com.springboot.hadoop.numDemo;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author liumingxin
 * @create 2018 23 15:27
 * @desc
 **/
public class NumPartitioner extends Partitioner<NumBean,LongWritable> {
    @Override
    public int getPartition(NumBean numBean, LongWritable longWritable, int i) {
        if(numBean.getLeft_num()>100){
            return 0;
        }else{
            return 1;
        }
    }
}
