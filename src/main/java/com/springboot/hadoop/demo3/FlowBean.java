package com.springboot.hadoop.demo3;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {

    private String phone;
    private String addr;
    private String name;
    private Long flow;

    public FlowBean(String phone,String addr,String name,Long flow){
        this.phone=phone;
        this.addr=addr;
        this.name=name;
        this.flow=flow;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(phone);
        dataOutput.writeUTF(addr);
        dataOutput.writeUTF(name);
        dataOutput.writeLong(flow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        phone=dataInput.readUTF();
        addr=dataInput.readUTF();
        name=dataInput.readUTF();
        flow=dataInput.readLong();
    }

    @Override
    public String toString() {
        return "FlowBean{" +
                "phone='" + phone + '\'' +
                ", addr='" + addr + '\'' +
                ", name='" + name + '\'' +
                ", flow=" + flow +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFlow() {
        return flow;
    }

    public void setFlow(Long flow) {
        this.flow = flow;
    }
}
