package com.springboot.hadoop.numDemo;

import org.apache.hadoop.io.WritableComparator;

/**
 * @author liumingxin
 * @create 2018 23 15:30
 * @desc
 **/
public class NumGroupingComparator extends WritableComparator {

    protected NumGroupingComparator(){
        super(NumBean.class,true);
    }

    @Override
    public int compare(Object a, Object b) {
        NumBean nb1= (NumBean) a;
        NumBean nb2 = (NumBean) b;
        return nb1.getLeft_num()==nb2.getLeft_num()?
                0: (nb1.getLeft_num()<nb2.getLeft_num()?
                    -1:1);

    }
}
