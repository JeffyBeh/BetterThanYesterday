package pers.jeffy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ConcurrentModificationException;

/**
 * @Author: BIJF
 * @Date: Create in 12:20 06/12/2020
 * @ModifiedBy:
 * @Description:
 **/


public class HDFSIO {

    // 把本地E盘中的testFile.txt上传到HDFS根目录
    @Test
    public void putFileToHDFS() throws URISyntaxException, IOException, InterruptedException {

        // 1.获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.获取输入流
        FileInputStream fis = new FileInputStream(new File("e:/testFile.txt"));

        // 3.获取输出流
        FSDataOutputStream fos = fs.create(new Path("/xiaoxxx.txt"));

        // 4.流的对拷
        IOUtils.copyBytes(fis,fos,conf);

        // 5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    // 从HDFS上下载xiaoxxx.txt文件到本地的e盘
    @Test
    public void getFileFromHDFS() throws IOException, URISyntaxException, InterruptedException {

        // 1.获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.获取输入流
        FSDataInputStream fis = fs.open(new Path("/xiaoxxx.txt"));

        // 3.获取输出流
        FileOutputStream fos = new FileOutputStream(new File("e:/xiaoxx.txt"));

        // 4.流的对拷
        IOUtils.copyBytes(fis,fos,conf);

        // 5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    // 下载第一块
    @Test
    public void readFileSeed1() throws URISyntaxException, IOException, InterruptedException {

        // 1.获取对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.获取输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop-3.2.1.tar.gz"));

        // 3.获取输出流
        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-3.2.1.tar.gz.part1"));

        // 4.流的对拷 -- 只考128MB
        byte[] buf = new byte[1024];
        for (int i = 0; i < 1024 * 128; i++) {

            fis.read(buf);
            fos.write(buf);
        }

        // 5.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

    // 下载第二块
    @Test
    public void feadFileSeek2() throws IOException, URISyntaxException, InterruptedException {

        // 1.获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.获取输入流
        FSDataInputStream fis = fs.open(new Path("/hadoop-3.2.1.tar.gz"));

        // 3.设置读取的起点
        fis.seek(1024*1024*128);

        // 4.获取输出流
        FileOutputStream fos = new FileOutputStream(new File("e:/hadoop-3.2.1.tar.gz.part2"));

        // 5.流的对拷
        IOUtils.copyBytes(fis,fos, conf);

        // 6.关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs.close();
    }

}

