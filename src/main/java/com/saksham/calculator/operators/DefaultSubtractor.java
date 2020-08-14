package com.saksham.calculator.operators;

public class DefaultSubtractor implements Subtractor {
    @Override
    public Integer subtract(Integer a, Integer b) {
    	if(a==null || b==null)
    		throw new IllegalStateException() ;
    	return a-b;
    }
}
