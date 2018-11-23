package com.bj58.lambda.test1;

/**
 * 函数式接口
 * @param <T>
 */
@FunctionalInterface
public interface MyPredicate<T> {

	boolean test(T t);
}
