package com.saksham.calculator.operators;

public class DefaultDivider implements Divider {
    @Override
    public Integer divide(Integer a, Integer b) {
    	if(a==null || b==null || b==0)
    		throw new IllegalStateException();
		return a/b;
    }
}
