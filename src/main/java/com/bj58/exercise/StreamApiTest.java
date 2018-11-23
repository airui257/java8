package com.bj58.exercise;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author liruifeng01
 */
public class StreamApiTest {

    /**
     * 1.给定一个数字列表,如何返回一个由每个数的平方构成的列表呢？,给定【1,2,3,4,5】, 应该返回【1,4,9,16,25】。
     */
    @Test
    public void test01() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums)
                .map((e) -> e * e)
                .forEach(System.out::println);
        System.out.println("---------------------");
        Integer reduce = Arrays.stream(nums)
                .reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
    }

    /**
     * 2.怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
     */
    List<Employee2> emps = Arrays.asList(
            new Employee2(102, "李四", 59, 6666.66, Employee2.Status.BUSY),
            new Employee2(101, "张三", 18, 9999.99, Employee2.Status.FREE),
            new Employee2(103, "王五", 28, 3333.33, Employee2.Status.VOCATION),
            new Employee2(104, "赵六", 8, 7777.77, Employee2.Status.BUSY),
            new Employee2(104, "赵六", 8, 7777.77, Employee2.Status.FREE),
            new Employee2(104, "赵六", 8, 7777.77, Employee2.Status.FREE),
            new Employee2(105, "田七", 38, 5555.55, Employee2.Status.BUSY)
    );

    @Test
    public void test2() {
        Optional <Integer> count = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());

        System.out.println("-------------------------------");

        Stream<Integer> map = emps.stream()
                .map((e) -> 1);
        System.out.println(map.getClass().getName());

        Optional<Integer> reduce = map.reduce(Integer::sum);

        System.out.println(reduce);
        System.out.println(reduce.get());

    }
}
