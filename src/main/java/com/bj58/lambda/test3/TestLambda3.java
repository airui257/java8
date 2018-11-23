package com.bj58.lambda.test3;

import com.bj58.lambda.test1.Emp;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestLambda3 {

	// 创建员工集合
	List<Emp> empList = Arrays.asList(
			new Emp("zhang3", 33, 3333.33),
			new Emp("li4", 44, 4444.44),
			new Emp("wang5", 55, 5555.55),
			new Emp("zhao6", 66, 6666.66),
			new Emp("tian7", 77, 7777.77)
	);
	
	/**
	 * 	调用 Collections.sort()方法，通过定制排序比较两个 Emp(先按年龄比，年龄相同按姓名比)，使用
	 * lambda作为参数传递。
	 */
	@Test
	public void test01() {
		Collections.sort(empList, (e1, e2)-> {
			if (e1.getAge() == e2.getAge()) {
				return e1.getName().compareTo(e2.getName());
			}else {
				return -Integer.compare(e1.getAge(), e2.getAge());
			}
		});
		for (Emp emp : empList) {
			System.out.println(emp);
		}
	}
	
	/**
	 * ①声明函数式接口，接口中声明抽象方法，public String getValue(String str);
	 * ②声明类 TestLambda，类中编写方法使用接口作为参数，讲一个字符串转换成大写，并作为方法的返回值
	 * ③再将一个字符串的第2个和第4个索引位置进行截取子串。
	 */
	@Test
	public void test02() {
		String strTrim = strHandler("\t\tHello   ", (str)-> str.trim());
		System.out.println(strTrim);
		
		String upperCaseString = strHandler("abcdefg", (str)-> str.toUpperCase());
		System.out.println(upperCaseString);
		
		String substring = strHandler("abcdefghijklmnopqrstuvwxyz", (str)-> str.substring(2, 5));
		System.out.println(substring);
	}
	// 用于处理字符串
	public String strHandler(String str, MyFunction mf) {
		return mf.getValue(str);
	}
	
	/**
	 * ①声明一个带两个泛型的函数式接口，泛型类型为<T, R> T为参数，R为返回值
	 * ②接口中声明对应抽象方法
	 * ③在 TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和。
	 * ④再计算两个long型参数的乘积。
	 */
	@Test
	public void test03() {
		long operation = operation(10L, 20L, (e1, e2) -> e1 * e2);
		System.out.println(operation);
	}
	// 对于两个 Long 型数据进行处理
	public long operation(long l1, long l2, MyFunction2<Long, Long> mf) {
		return mf.operation(l1, l2);
	}
}
