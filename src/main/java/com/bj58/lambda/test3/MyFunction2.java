package com.bj58.lambda.test3;

@FunctionalInterface
public interface MyFunction2<T, R> {

	R operation(T t1, T t2);
}
