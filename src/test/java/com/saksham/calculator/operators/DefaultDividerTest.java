package com.saksham.calculator.operators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class DefaultDividerTest {
	
	DefaultDivider defaultDivider;
	
	@Before
    public void init() {
		defaultDivider = new DefaultDivider();
    }

    @Test
    public void basicTest() {
        int a=9, b=3;
        Integer div = defaultDivider.divide(a,b);
        assertEquals("The division should be 3", 3, (int)div);
    }

    @Test
    public void nullIntegerExceptionTest() {
        int a=3;
        try {
        	defaultDivider.divide(a,null);
            fail("Exception");
        } catch (Exception e) {
            assertTrue("Null integer", true);
        }
    }
    
    @Test
    public void zeroDivisorExceptionTest() {
        int a=3,b=0;
        try {
        	defaultDivider.divide(a,b);
            fail("Zero");
        } catch (Exception e) {
            assertTrue("Expected", true);
        }
    }

}
