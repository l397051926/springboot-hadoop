package com.springboot.hadoop.profit;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author liumingxin
 * @create 2018 23 13:44
 * @desc
 **/
public class ProfitReducer extends Reducer<Text,ProfitBean,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<ProfitBean> values, Context context) throws IOException, InterruptedException {
        Iterator<ProfitBean> it = values.iterator();
        int cj = 0;
        while (it.hasNext()) {
            ProfitBean profitBean = it.next();
            int pro = profitBean.getProfit() - profitBean.getChengben();
            cj += pro;
        }
        context.write(key,new IntWritable(cj));

    }
}
