package com.bj58.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description 使用Java8来解决时间日期线程问题
 * @Title TestJava8DateTimeFormatter
 * @Company www.airui257.com
 * @author airui257
 * @version 1.0
 */
public class Java8DateTimeFormatterTest {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		Callable<LocalDate> task = () -> LocalDate.parse("20181213", dtf);

		ExecutorService pool = Executors.newFixedThreadPool(10);
		
		List<Future<LocalDate>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Future<LocalDate> submit = pool.submit(task);
			list.add(submit);
		}
		
		for (Future<LocalDate> future : list) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
	}

}
