package com.bj58;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream 终止操作
 * @author liruifeng01
 */
public class StreamApi3Test {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 60, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    //3. 终止操作

    /**
     * allMatch——检查是否匹配所有元素
     * anyMatch——检查是否至少匹配一个元素
     * noneMatch——检查是否没有匹配的元素
     * findFirst——返回第一个元素
     * findAny——返回当前流中的任意元素
     * count——返回流中元素的总个数
     * max——返回流中最大值
     * min——返回流中最小值
     */
    @Test
    public void test1() {
        // 全部匹配
        boolean bl = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(bl);

        // 任意一个匹配
        boolean bl1 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(bl1);

        // 没有匹配
        boolean bl2 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));

        System.out.println(bl2);
    }

    @Test
    public void test2() {
        Optional<Employee> op = emps.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .findFirst();
        // 以下方式可以代替上面形式，取出最小的第一条
//        op = emps.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(op.get());
        System.out.println("--------------------------------");

        // 取出任意一条数据状态为 FREE 的
        Optional<Employee> op2 = emps.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(op2.get());
    }

    @Test
    public void test3() {
        // 查出状态为 FREE 的数据共有多少条
        long count = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .count();
        System.out.println(count);

        // 查询最高工资
        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .max(Double::compare);
        System.out.println(op.get());

        // 查出工资最少的那个人
        Optional<Employee> op2 = emps.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        // 以下代码可以替换掉上面的代码
//        op2 = emps.stream().min(Comparator.comparingDouble(Employee::getSalary))
        System.out.println(op2.get());
    }

    //注意：流进行了终止操作后，不能再次使用
    @Test
    public void test4() {
        Stream<Employee> stream = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE));
//        stream.forEach(System.out::println); // 这里进行了终止操作，所以下面执行 count() 会报错 java.lang.IllegalStateException: stream has already been operated upon or closed
//        long count = stream.count(); // 这里也是终止操作
//        System.out.println("count = " + count);
        Optional<Double> max = stream.map(Employee::getSalary)
                .max(Double::compare);
        System.out.println("max = " + max.get());
    }

    //3. 终止操作
	/**
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
    @Test
    public void test11() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        list.stream()
                .map((e) -> e * e)
                .forEach(System.out::print);
        System.out.println();
        System.out.println("----------------------------------------");
        // 累加操作，设定一个初始值 0，从0加到10
        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("----------------------------------------");

        Optional<Double> op = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
        System.out.println("----------------------------------------");
        Stream<Double> doubleStream = emps.stream().map(Employee::getSalary);
        Double reduce = doubleStream.reduce(0.0, (x, y) -> x + y);
        System.out.println("reduce = " + reduce);
    }


    //需求：搜索名字中 “六” 出现的次数
    @Test
    public void test22() {
        Optional<Integer> sum = emps.stream()
                .map(Employee::getName)
                .flatMap(StreamApi2Test::filterCharacter)
                .map((ch) -> {
                    if (ch.equals('六'))
                        return 1;
                    else
                        return 0;
                }).reduce(Integer::sum);

        System.out.println(sum.get());
    }

    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test33() {
        List<String> list = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("----------------------------------");

        Set<String> set = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("----------------------------------");

        HashSet<String> hs = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    @Test
    public void test44() {
        /**找出最高工资是多少*/
        Optional<Double> max = emps.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        // 以下可以代替上面
//        max = emps.stream().map(Employee::getSalary).max(Double::compare);
        System.out.println("max = " + max.get());
        System.out.println("----------------------------------");

        /**找出工资最低的那个人*/
        Optional<Employee> min = emps.stream()
                .collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
//        min = emps.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
//        min = emps.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println("min = " + min.get());
        System.out.println("----------------------------------");

        /**计算出工资总和*/
        Double sum = emps.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        // 以下可以代替上面
//        sum = emps.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println("sum = " + sum);
        System.out.println("----------------------------------");

        /**计算出工资平均值*/
        Double avg = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("avg = " + avg);
        System.out.println("----------------------------------");

        /**计算出有多少个员工*/
        Long count = emps.stream()
                .collect(Collectors.counting());
        // 以下可以代替上面
//        count = emps.stream().count();
        System.out.println("count = " + count);

    }

    //分组
    @Test
    public void test5() {
        /**根据员工状态分组*/
        Map<Employee.Status, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        for (Employee.Status status : map.keySet()) {
            System.out.println(status + " = " + map.get(status));
        }
    }

    //多级分组
    @Test
    public void test6() {
        /**先按状态分组，再按年龄分组；分别找出不同状态中的老年、中年、成年*/
        Map<Employee.Status, Map<String, List<Employee>>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() >= 60)
                        return "老年";
                    else if (e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));
        for (Employee.Status status : map.keySet()) {
            System.out.println(status + " = " + map.get(status));
        }
    }

    //分区，满足条件一个区，不满足条件一个区
    @Test
    public void test7() {
        /**把一个集合分成两拨，一拨是满足条件的，一拨是不满足条件的*/
        Map<Boolean, List<Employee>> map = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 5000));

        for (Boolean aBoolean : map.keySet()) {
            if (aBoolean)
                System.out.println("满足条件的为一个区 = " + map.get(aBoolean));
            else
                System.out.println("不满足条件的 = " + map.get(aBoolean));
        }
    }

    @Test
    public void test77() {
        /**计算总和、平均值、最大值、最小值、总数*/
        DoubleSummaryStatistics dss = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("计算总数 = " + dss.getCount());
        System.out.println("计算总和 = " + dss.getSum());
        System.out.println("计算最小值 = " + dss.getMin());
        System.out.println("计算平均值 = " + dss.getAverage());
        System.out.println("计算最大值 = " + dss.getMax());
        System.out.println("DoubleSummaryStatistics的toString()方法 = " + dss.toString());
//        dss.combine();
    }

    // 连接
    @Test
    public void test8() {
        /**按照自己的规则：拼接*/
        String str = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "----", "----"));

        System.out.println("字符串的连接 = " + str);
    }

    @Test
    public void test9() {
        /**计算工资总和*/
        Optional<Double> sum = emps.stream()
                .map(Employee::getSalary).reduce(Double::sum);
//                .collect(Collectors.reducing(Double::sum));

        System.out.println("计算工资总和 = " + sum.get());
    }

}
