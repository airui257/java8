package com.bj58.date;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 	解决线程问题
 * @author liruifeng01
 */
public class DateFormatThreadLocalTest {

	// 手动创建线程池
	private static int corePoolSize = 10;
	private static int maximumPoolSize = 100;
	private static long keepAliveTime = 1;
	private static TimeUnit unit = TimeUnit.SECONDS;
	private static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);
	private static ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new ThreadFactoryBuilder().setNameFormat("XX-tast-%d").build());

	// 自动创建线程池,线程数是10
//    ExecutorService pool = Executors.newFixedThreadPool(10);

	private static final ThreadLocal<DateFormat> DF =
			ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));



	private static Date convert(String source) throws ParseException  {
		return DF.get().parse(source);
	}



	public static void main(String[] args) throws Exception {
		// 线程需要做的事情是根据字符串解析成时间日期格式类型
		Callable<Date> task = () -> DateFormatThreadLocalTest.convert("20181111");

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
