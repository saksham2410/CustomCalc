package com.saksham.calculator.operators;

import java.util.List;

public class DefaultAdder implements Adder{
    @Override
    public Integer add(List<Integer> numbers) {
    	if(numbers.isEmpty())
    		throw new IllegalStateException();
			int total = 0;
			for (int number : numbers) {
				total+= number;
			}
			return total;
    }
}
