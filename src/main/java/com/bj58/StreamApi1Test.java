package com.bj58;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 的创建方式
 *
 * @author liruifeng01
 */
public class StreamApi1Test {

    //创建 Stream
    @Test
    public void test01() {

        //1.可以通过Collection 系列集合提供的Stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays 中的静态方法Stream() 获取数组流
        Employee[] emps = new Employee[5];
        Stream<Employee> stream2 = Arrays.stream(emps);

        //3.通过Stream 类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc", "dd");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        Stream<Double> stream5 = Stream.generate(Math::random);
        Stream<Double> limit = stream5.limit(5);
        limit.forEach(System.out::println);

    }
}
