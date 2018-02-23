package com.springboot.hadoop.profit;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 23 13:20
 * @desc
 **/
public class ProfitBean implements WritableComparable {

    private int month;
    private String name;
    private int profit;
    private int chengben;

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(month);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(profit);
        dataOutput.writeInt(chengben);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.month=dataInput.readInt();
        this.name=dataInput.readUTF();
        this.profit=dataInput.readInt();
        this.chengben=dataInput.readInt();
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getChengben() {
        return chengben;
    }

    public void setChengben(int chengben) {
        this.chengben = chengben;
    }

    @Override
    public String toString() {
        return "ProfitBean{" +
                "month=" + month +
                ", name='" + name + '\'' +
                ", profit=" + profit +
                ", chengben=" + chengben +
                '}';
    }
}
