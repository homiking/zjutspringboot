package com.zhm.drug;

import cn.hutool.Hutool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.netty.util.internal.StringUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * @Description
 * @Author kknever
 * @Date 2022/6/13
 **/
public class Test {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        int value = 18;
        do{
            int temp = value&0x07;
            buffer.append(temp);
        } while((value >>>= 3)!=0);
        System.out.println(buffer.reverse());
    }

}
