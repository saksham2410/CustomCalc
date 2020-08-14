package com.saksham.calculator;

import com.saksham.calculator.logging.OperationsLogger;
import com.saksham.calculator.operators.Adder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorBasicTest {

    @Mock
    OperationsLogger operationsLogger;

    @InjectMocks
    Calculator calculator;

    @Test
    public void basicTestInDefaultMode() {
        LoggingStrategy loggingStrategy = new LoggingStrategy("test@test.com", FileFormat.docx);
        Calculator calculator = Calculator.builder().loggingStrategy(loggingStrategy).build();  

        List<Integer> listOfNumbers = Arrays.asList(1, 2, 5, -9);
        try {
            Integer sum = calculator.add(listOfNumbers);
            assertEquals("The calculator should return usual sum", -1, (int)sum);
            // calculator.sendResults();
            // assertTrue("Sent", true);

        } catch (Exception e) {
            fail("Mail not sent");
        }
        
    }

    @Test
    public void customiseAdditionWhenAdderIsProvidedTest() {

        Adder myAdder = numbers -> numbers.size(); // A lambda which means that myAdder is an instance of Adder with given behavior
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 5, -9);


        Calculator calculator = Calculator.builder()
                .adder(myAdder)
                .calculationMode(CalculationMode.CUSTOM).build();
             
        Integer sum = calculator.add(listOfNumbers);
        assertEquals("The calculator should return size of numbers passed as specified by myAdder", 4, (int)sum);
        Integer product = calculator.multiply(listOfNumbers);
        assertEquals("The multiplication behavior should remain same", -90, (int)product);
        Integer subtraction = calculator.subtract(1,2);
        assertEquals("The subtraction behavior should remain same", -1, (int)subtraction);
        Integer division = calculator.divide(2,1);
        assertEquals("The division behavior should remain same", 2, (int)division);
        try {
        	calculator.add(listOfNumbers);
        	calculator.sendResults();
        	fail("exception");
        } catch (Exception e) {
        	assertTrue("absence of LoggingStrategy",true);
        }
    }
}
