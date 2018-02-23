package com.springboot.hadoop.profit;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 23 13:18
 * @desc
 **/
public class ProfitMapper extends Mapper<LongWritable,Text,Text,ProfitBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String lens = value.toString();
        String[] len=lens.split(" ");

        ProfitBean profitBean=new ProfitBean();
        profitBean.setMonth(Integer.valueOf(len[0]));
        profitBean.setName(len[1]);
        profitBean.setProfit(Integer.valueOf(len[2]));
        profitBean.setChengben(Integer.valueOf(len[3]));

        context.write(new Text(profitBean.getName()),profitBean);

    }
}
