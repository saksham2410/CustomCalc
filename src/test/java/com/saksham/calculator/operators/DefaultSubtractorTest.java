package com.saksham.calculator.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class DefaultSubtractorTest {
	
    DefaultSubtractor defaultSubtractor;

    @Before
    public void init() {
    	defaultSubtractor = new DefaultSubtractor();
    }

    @Test
    public void basicTest() {
        int a=1, b=2;
        Integer sub = defaultSubtractor.subtract(a,b);
        assertEquals("The sub should be -1", -1, (int)sub);
    }

    @Test
    public void nullIntegerShouldThrowExceptionTest() {
        int a=1;
        try {
        	defaultSubtractor.subtract(a,null);
            fail("Exception");
        } catch (Exception e) {
            assertTrue("Null", true);
        }
    }
}
