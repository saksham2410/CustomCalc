package com.saksham.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.saksham.calculator.logging.OperationsLogger;

@RunWith(MockitoJUnitRunner.class)
public class ExpressionTest {
	
	@Mock
    OperationsLogger operationsLogger;

    @InjectMocks
    Calculator calculator;

    @Test
    public void basicTestInDefaultMode() {

        Calculator calculator = Calculator.builder().build();   

        String exp = "2*(5+5*2)/3+(6/2+8)";
        // Should return the value 21
        Integer result = calculator.calculate(exp, calculator);
        System.out.print(result);
        assertEquals("The calculator should return usual value", 21, (int)result);
        
    }
    
    @Test
    public void invalidExpressionShouldThrowExceptionTest() {
    	
        Calculator calculator = Calculator.builder().build();
        
    	String exp = "100 * ( 2 + 12 ) / 14 )";
    	
    	// Wrong or Invalid expression should throw exception
        try {
            calculator.calculate(exp, calculator);
            fail("Adding an empty list should throw exception");
        } catch (Exception e) {
            assertTrue("Passing an emptyList to defaultAdder threw exception as expected", true);
        }
    }

}