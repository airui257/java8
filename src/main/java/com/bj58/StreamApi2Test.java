package com.bj58;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream 的中间操作
 *
 * @author liruifeng01
 */
public class StreamApi2Test {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    /**
     * 筛选与切片
     * filter——接收 Lambda ， 从流中排除某些元素。
     * limit——截断流，使其元素不超过给定数量。
     * skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void test2() {
        //所有的中间操作不会做任何的处理
        Stream<Employee> stream = emps.stream()
                .filter((e) -> {
                    System.out.println("测试中间操作");
                    return e.getAge() <= 35;
                });

        //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
        stream.forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test3() {
        Iterator<Employee> it = emps.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * 分页 limit
     */
    @Test
    public void test4() {
        emps.stream()
                .filter((e) -> {
                    System.out.println("短路！"); // &&  ||
                    return e.getSalary() >= 5000;
                }).limit(3)
                .forEach(System.out::println);
    }

    /**
     * 跳过 skip
     */
    @Test
    public void test5() {
        emps.parallelStream()
                .filter((e) -> e.getSalary() >= 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * 去重 distinct
     */
    @Test
    public void test6() {
        emps.stream()
                .distinct()
                .forEach(System.out::println);
    }

    //2. 中间操作

    /**
     * 映射
     * map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test11() {
        // 提取 Employee 的 name 属性
        Stream<String> str = emps.stream()
                .map(Employee::getName);
        str.forEach(System.out::print);
        System.out.println();
        System.out.println("-------------------------------------------");

        // 把每个元素转换为大写字母
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        Stream<String> stream = strList.stream()
                .map(String::toUpperCase); // {{a,a,a}, {b,b,b}, {c,c,c}}
        stream.forEach(System.out::print);
        System.out.println();
        System.out.println("-------------------------------------------");

        /**
         * map 和 flatMap 的区别
         */
        Stream<Stream<Character>> stream2 = strList.stream()
                .map(StreamApi2Test::filterCharacter);
        stream2.forEach((sm) -> sm.forEach(System.out::print));

        System.out.println();
        System.out.println("---------------------------------------------");

        Stream<Character> stream3 = strList.stream()
                .flatMap(StreamApi2Test::filterCharacter); // {a,a,a,b,b,b,c,c,c}
        stream3.forEach(System.out::print);
    }

    /**
     * 把字符串拆分成字节数组，放入list中返回
     *
     * @param str 字符串
     * @return list集合
     */
    static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (Character ch : chars) {
            list.add(ch);
        }
        return list.stream();
    }

    /*
        sorted()——自然排序
        sorted(Comparator com)——定制排序
     */
    @Test
    public void test22() {
        // 自然排序
        emps.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::print);
        System.out.println();
        System.out.println("------------------------------------");

        // 定制排序
        emps.stream()
                .sorted((x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);
    }
}
