package com.bj58.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liruifeng01
 */
public class SimpleDateFormatTest {

    /**
     * 存在线程问题
     */
    public static void main(String[] args) throws Exception {
        // 按照自定义格式显示日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        // 使用多线程同时进行解析字符串为时间格式类型
        // 创建一个线程池,线程数是10
        ExecutorService pool = Executors.newFixedThreadPool(10);

        // 线程需要做的事情是根据字符串解析成时间日期格式类型
        Callable<Date> task = () -> sdf.parse("20180708");

        // 创建集合，把执行结果放入集合中
        List<Future<Date>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Date> submit = pool.submit(task);
            list.add(submit);
        }

        // 遍历结果
        for (Future<Date> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }

}
