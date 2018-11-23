package com.bj58.lambda.test2;

import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * @Description：
 * Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
 * 	上联：左右遇一括号省
 * 	下联：左侧推断类型省
 * 	 横批：能省则省
 * Lambda 表达式需要“函数式接口”的支持
 *	 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰
 * 	可以检查是否是函数式接口
 * @Title：TestLambda2
 * @Company：www.airui257.com
 * @author airui257
 * @version 1.0
 */
public class TestLambda2 {

	/**
	 * 无参数、无返回值
	 */
	@Test
	public void test01() {
		Runnable r = () -> System.out.println("hello");
		new Thread(r).start();
	}
	
	/**
	 * 有一个参数、无返回值
	 * 只有一个参数的话，小括号可以省略不写
	 */
	@Test
	public void test02() {
		Consumer<String> con = (t) -> System.out.println(t);
		con.accept("hello");
	}
	
	/**
	 * 有两个参数、有返回值
	 * 有多条语句要使用大括号{}
	 */
	@Test
	public void test03() {
		Comparator<Integer> com = (x, y) -> {
			System.out.println("函数式接口");
			return Integer.compare(x, y);
		};
		com.compare(3, 2);
	}
	
	/**
	 * 有两个参数、有返回值
	 * 如果只有一条语句，return和大括号都可以省略
	 */
	@Test
	public void test04() {
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		System.out.println(com.compare(4, 4));;
	}
	
	
	
}
