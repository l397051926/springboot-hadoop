package com.springboot.hadoop.inputDemo;

import com.google.common.io.LineReader;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author liumingxin
 * @create 2018 23 15:00
 * @desc
 **/
public class ScoreRecordReader extends RecordReader<Text,Text> {

    private LineReader lineReader;
    private Text key;
    private Text value;
    private boolean hasMore=true;


    //初始化方法
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        FileSplit fs= (FileSplit) inputSplit;
        Path path = fs.getPath();


    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return null;
    }

    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return null;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
