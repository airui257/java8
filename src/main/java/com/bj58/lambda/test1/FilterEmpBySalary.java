package com.bj58.lambda.test1;

/**
 * 函数式接口实现类
 * @author liruifeng01
 */
public class FilterEmpBySalary implements MyPredicate<Emp> {

	@Override
	public boolean test(Emp t) {
		return t.getSalary() >= 5000;
	}

}
