package com.bj58.lambda.test2;

import org.junit.Test;

public class MyFunTest {

	@Test
	public void test() {
		Integer result = operation(100, (n)->n-1);
		System.out.println(result);
		Integer result2 = operation(101, (n)->n+1);
		System.out.println(result2);
	}
	
	private Integer operation(Integer num, MyFun mf) {
		return mf.operation(num);
	}

}
