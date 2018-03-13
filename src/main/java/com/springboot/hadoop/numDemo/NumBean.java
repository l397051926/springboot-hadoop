package com.springboot.hadoop.numDemo;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 23 15:23
 * @desc
 **/
public class NumBean implements WritableComparable<NumBean> {

    private long left_num;
    private long right_num;

    @Override
    public int compareTo(NumBean o) {
        if(left_num>o.getLeft_num()){
            return 1;
        }else if(left_num<o.getLeft_num()){
            return -1;
        }else{
            return (int) (right_num-o.getRight_num());
        }

    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(left_num);
        dataOutput.writeLong(right_num);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.left_num=dataInput.readLong();
        this.right_num=dataInput.readLong();
    }

    public long getLeft_num() {
        return left_num;
    }

    public void setLeft_num(long left_num) {
        this.left_num = left_num;
    }

    public long getRight_num() {
        return right_num;
    }

    public void setRight_num(long right_num) {
        this.right_num = right_num;
    }

    @Override
    public String toString() {
        return "NumBean{" +
                "left_num=" + left_num +
                ", right_num=" + right_num +
                '}';
    }
}
