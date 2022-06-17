package com.zhm.drug.hutool.io;

import cn.hutool.core.io.BOMInputStream;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;

/**
 * @Description
 * IoUtil 流操作工具类
 * FileUtil 文件读写和操作的工具类。
 * FileTypeUtil 文件类型判断工具类
 * WatchMonitor 目录、文件监听，封装了JDK1.7中的WatchService
 * ClassPathResource针对ClassPath中资源的访问封装
 * FileReader 封装文件读取
 * FileWriter 封装文件写入
 * @Author kknever
 * @Date 2022/6/17
 **/
public class FileTest {
    public static void main(String[] args) {
        BufferedInputStream inputStream = FileUtil.getInputStream("C:\\Users\\kknever\\Desktop\\zjut.sql");
        BufferedOutputStream outputStream = FileUtil.getOutputStream("C:\\Users\\kknever\\Desktop\\new.sql");
        IoUtil.copy(inputStream, outputStream, IoUtil.DEFAULT_BUFFER_SIZE);

    }
}
