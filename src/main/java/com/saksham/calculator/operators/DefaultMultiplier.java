package com.saksham.calculator.operators;

import java.util.List;

public class DefaultMultiplier implements Multiplier {
    @Override
    public Integer multiply(List<Integer> numbers) {
    	if(numbers.isEmpty())
    		throw new IllegalStateException() ;
    	Integer ans=1;
    	for(int i=0; i<numbers.size(); i++)
    		ans*=numbers.get(i);
    	return ans;
    }
}
