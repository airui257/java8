package com.bj58.lambda.test1;

import java.util.*;

import org.junit.Test;

/**
 * @author liruifeng01
 */
public class TestLambda {

    /**
     * 原来的匿名内部类
     */
    @Test
    public void test01() {
        /**定义排序规则*/
        Comparator<Integer> com = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                int compare = -Integer.compare(o1, o2);
                return compare;
            }
        };
        /**让TreeSet按照指定规则排序*/
        TreeSet<Integer> ts = new TreeSet<>(com); // 默认是自然顺序：升序
//		Set<Integer> ts = new HashSet<>(); // 无序
        ts.add(1);
        ts.add(18);
        ts.add(3);
        ts.add(3345);
        ts.add(2);
        ts.add(16878);
        ts.add(9);
        ts.add(0);
        ts.add(5);

        System.out.println("ts = " + ts);

    }

    /**
     * Lambda表达式
     *
     * @Function：
     * @Description：
     */
    @Test
    public void test11() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    // 创建员工集合
    List<Emp> empList = Arrays.asList(
            new Emp("zhang3", 33, 3333.33),
            new Emp("li4", 44, 4444.44),
            new Emp("wang5", 55, 5555.55),
            new Emp("zhao6", 66, 6666.66),
            new Emp("tian7", 77, 7777.77)
    );

    // 需求：获取当前公司中员工年龄大于35的员工信息
    public List<Emp> filterEmps(List<Emp> list) {
        List<Emp> emps = new ArrayList<>();
        for (Emp emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // 需求：获取当前公司员工中工资大于5000的员工信息
    public List<Emp> filterEmpSalary(List<Emp> list) {
        List<Emp> emps = new ArrayList<>();
        for (Emp emp : list) {
            if (emp.getSalary() >= 5000) {
                emps.add(emp);
            }
        }
        return emps;
    }

    //  测试
    @Test
    public void Test00() {
//		List<Emp> filterEmps = filterEmps(empList);
        List<Emp> filterEmpSalary = filterEmpSalary(empList);
        for (Emp emp : filterEmpSalary) {
            System.out.println(emp);
        }
    }

    // 优化方式一：使用设计模式来测试——策略设计模式
    public List<Emp> filterEmp(List<Emp> list, MyPredicate<Emp> mp) {
        List<Emp> emps = new ArrayList<>();

        for (Emp emp : empList) {
            if (mp.test(emp)) {
                emps.add(emp);
            }
        }
        return emps;
    }

    // 优化后 测试
    @Test
    public void test99() {
        List<Emp> filterEmp = filterEmp(empList, new FilterEmpByAge());
        for (Emp emp : filterEmp) {
            System.out.println(emp);
        }
        System.out.println("------------------------------------");
        List<Emp> filterEmp2 = filterEmp(empList, new FilterEmpBySalary());
        for (Emp emp : filterEmp2) {
            System.out.println(emp);
        }
    }


    // 优化方式二：匿名内部类
    @Test
    public void test03() {
        List<Emp> emps = filterEmp(empList, new MyPredicate<Emp>() {

            @Override
            public boolean test(Emp t) {
//				return t.getAge()>35;
                return t.getSalary() >= 5000;
            }
        });
        for (Emp emp : emps) {
            System.out.println(emp);
        }
    }

    // 优化方式三：使用lambda表达式
    @Test
    public void test04() {
        List<Emp> emps = filterEmp(empList, (e) -> e.getAge() > 35);
        emps.forEach(System.out::println);
    }

    // 优化方式四：使用 Stream API
    @Test
    public void test05() {
        empList.stream().filter((e) -> e.getSalary() >= 5000).forEach(System.out::println);
        empList.stream().filter((e) -> e.getSalary() >= 5000).limit(2).forEach(System.out::println);
        empList.stream()
                .map(Emp::getName)
                .forEach(System.out::println);
    }


    private void test02() {
        Comparable<Integer> com = new Comparable<Integer>() {

            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        };
    }
}
