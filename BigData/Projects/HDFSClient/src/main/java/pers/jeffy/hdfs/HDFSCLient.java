package pers.jeffy.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: BIJF
 * @Date: Create in 11:36 PM 05/28/2020
 * @ModifiedBy:
 * @Description:
 **/


public class HDFSCLient {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();

        conf.set("fs.defaultFS", "hdfs://hadoop-101:9000");

        // 1.获取HDFS客户端对象
        FileSystem fs = FileSystem.get(conf);
        // 2.在HDFS上创建路径
        fs.mkdirs(new Path("/0611/bijf"));
        // 3.关闭资源
        fs.close();

        System.out.println("over");
    }

    // 文件上传
    @Test
    public void testCopyFromLocalFile() throws URISyntaxException, IOException, InterruptedException {

        // 1.获取fs对象
        Configuration conf = new Configuration();

        conf.set("dfs.replication", "2");
        FileSystem fs =  FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.上传文件
        fs.copyFromLocalFile(new Path("E:\\testFile.txt"), new Path("/testFile_2.txt"));

        // 3.关闭资源
        fs.close();
    }

    // 文件下载
    @Test
    public void copyToLocalFile() throws URISyntaxException, InterruptedException, IOException {

        // 1.获取fs对象
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.下载文件

        fs.copyToLocalFile(false, new Path("/testFile_2.txt"), new Path("C:\\Users\\Administrator\\Desktop"), true);

        // 3. 关闭资源
        fs.close();
    }

    // 文件删除
    @Test
    public void testDelete() throws IOException, URISyntaxException, InterruptedException {

        // 1获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.删除文件
        boolean result = fs.deleteOnExit(new Path("/0611"));

        System.out.println(result);
        // 3.关闭资源
        fs.close();
    }

    // 文件更名
    @Test
    public void testRename() throws URISyntaxException, IOException, InterruptedException {

        // 1.获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.删除文件
        fs.rename(new Path("/testFile_1.txt"), new Path("/testFile_1.1.txt"));

        // 3.关闭资源
        fs.close();
    }

    // 查看文件详细信息 -- 文件名称、权限、长度、块信息
    @Test
    public void testListFile() throws IOException, URISyntaxException, InterruptedException {

        // 1.获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.获取文件详细信息
        /*
         * @param pathString
         * @param recursive
         */
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while(listFiles.hasNext()){
            LocatedFileStatus fileStatus = listFiles.next();

            // 文件名
            System.out.println(fileStatus.getPath().getName());
            // 文件权限
            System.out.println(fileStatus.getPermission());
            // 文件长度
            System.out.println(fileStatus.getLen());
            // 文件块信息 -- 根据位置信息获取副本存在哪台主机上
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for(BlockLocation blockLocation:blockLocations){

                String[] hosts = blockLocation.getHosts();

                for(String host:hosts){
                    System.out.println(host);
                }
            }
            System.out.println("-----------------分割线----------------");
        }

        // 3.关闭资源
        fs.close();
    }

    // 判断是文件还是文件夹
    @Test
    public void testListStatus() throws IOException, URISyntaxException, InterruptedException {

        // 1.获取fs对象
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop-101:9000"), conf, "jeffy");

        // 2.判断操作
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));

        for(FileStatus fileStatus:fileStatuses){

            if(fileStatus.isFile()){
                // 是否为文件
                System.out.println("f: " + fileStatus.getPath().getName());
            } else if(fileStatus.isDirectory()){
                //是否为文件夹
                System.out.println("d: " + fileStatus.getPath().getName());
            }
        }

        // 3.关闭资源
        fs.close();
    }
}
