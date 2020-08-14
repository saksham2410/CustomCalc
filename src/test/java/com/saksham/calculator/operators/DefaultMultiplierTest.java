package com.saksham.calculator.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DefaultMultiplierTest {
	
	DefaultMultiplier defaultMultiplier;

    @Before
    public void init() {
    	defaultMultiplier = new DefaultMultiplier();
    }

    @Test
    public void basicTest() {
        List<Integer> numbersToBeMultiplied = Arrays.asList(1, 2, 3, 4);
        Integer sum = defaultMultiplier.multiply(numbersToBeMultiplied);
        assertEquals("24",24, (int)sum);
    }

    @Test
    public void notEnoughNumbersExceptionTest() {
    	
        List<Integer> emptyList = new ArrayList<>();
        try {
        	defaultMultiplier.multiply(emptyList);
            fail("Exception");
        } catch (Exception e) {
            assertTrue("Empty", true);
        }
    }

}
