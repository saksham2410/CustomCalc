package com.saksham.calculator;

import com.saksham.calculator.logging.OperationsLogger;
import com.saksham.calculator.operators.Adder;
import com.saksham.calculator.operators.DefaultAdder;
import com.saksham.calculator.operators.DefaultDivider;
import com.saksham.calculator.operators.DefaultMultiplier;
import com.saksham.calculator.operators.DefaultSubtractor;
import com.saksham.calculator.operators.Divider;
import com.saksham.calculator.operators.ExpressionCalculator;
import com.saksham.calculator.operators.Multiplier;
import com.saksham.calculator.operators.Subtractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Calculator {

    private Adder adder;
    private Subtractor subtractor;
    private Multiplier multiplier;
    private Divider divider;
    private LoggingStrategy loggingStrategy;
    private CalculationMode calculationMode;
    private List<Operation> operations;
    private OperationsLogger operationsLogger;

    public static class Builder {

        private Calculator calculator = new Calculator();

        public Calculator build() {   	
            calculator.operations = new ArrayList<>();
            if (calculator.adder == null)
                calculator.adder = new DefaultAdder();
            if (calculator.subtractor == null)
                calculator.subtractor = new DefaultSubtractor();
            if (calculator.multiplier == null)
                calculator.multiplier = new DefaultMultiplier();
            if (calculator.divider == null)
                calculator.divider = new DefaultDivider();
            if (calculator.calculationMode == null)
                calculator.calculationMode = CalculationMode.DEFAULT;
            return calculator;
        }

        public Builder adder(Adder adder) {
        	calculator.adder = adder;
            return this;
        }

        public Builder subtractor(Subtractor subtractor) {
            calculator.subtractor = subtractor;
            return this;
        }

        public Builder multiplier(Multiplier multiplier) {
            calculator.multiplier = multiplier;
            return this;
        }

        public Builder divider(Divider divider) {
            calculator.divider = divider;
            return this;
        }

        public Builder calculationMode(CalculationMode calculationMode) {
            calculator.calculationMode = calculationMode;
            return this;
        }

        public Builder loggingStrategy(LoggingStrategy loggingStrategy) {
            calculator.loggingStrategy = loggingStrategy;
            return this;
        }      
    }

    public Integer add(List<Integer> numbers) 
    {    	
		String typeofop = null;
		if(calculationMode==CalculationMode.CUSTOM && adder!=null)
			typeofop = "CUSTOM ADD";
		Integer sum = adder.add(numbers);
		if(operations==null) {
			List<Operation> operations = new ArrayList<Operation>();
			this.operations = operations;
		}
		for(int i=0;i<numbers.size()-1;i++)	{
			int total = adder.add(numbers.subList(0,i+1));
			Operation newOperation=new Operation(total, adder.add(Arrays.asList(numbers.get(i+1))),typeofop);			
			operations.add(newOperation);
		}
		if(numbers.size()==1) {
			Operation newOperation=new Operation(adder.add(Arrays.asList(numbers.get(0))),0,typeofop);			
			operations.add(newOperation); 
		}
		
		return sum;
    }

    public Integer subtract(Integer a, Integer b) {
    	
		String typeofop = null;if(calculationMode==CalculationMode.CUSTOM && subtractor!=null)
			typeofop = "CUSTOM SUB";
    	Integer difference = subtractor.subtract(a, b);
    	if(operations==null) {
			List<Operation> operations = new ArrayList<Operation>();
			this.operations = operations;
		}
		Operation newOperation=new Operation(a,b,typeofop);			
		operations.add(newOperation); 			
		
    	return difference;
    }

    public Integer multiply(List<Integer> numbers) {
    	
		String typeofop = null;if(calculationMode==CalculationMode.CUSTOM && multiplier!=null)
			typeofop = "CUSTOM MUL";
    	Integer product = multiplier.multiply(numbers);
    	if(operations==null) {
			List<Operation> operations = new ArrayList<Operation>();
			this.operations = operations;
		}
		for(int i=0;i<numbers.size()-1;i++)	{
			int currmul = multiplier.multiply(numbers.subList(0,i+1));
			Operation newOperation=new Operation(currmul, multiplier.multiply(Arrays.asList(numbers.get(i+1))),typeofop);			
			operations.add(newOperation);
		}
		if(numbers.size()==1) {
			Operation newOperation=new Operation(multiplier.multiply(Arrays.asList(numbers.get(0))),1,typeofop);			
			operations.add(newOperation); 
		}	
    	return product;
    }

    public Integer divide(Integer a, Integer b) {
		String typeofop = null;if(calculationMode==CalculationMode.CUSTOM && divider!=null)
			typeofop = "CUSTOM DIV";
    	Integer division = divider.divide(a, b);
    	if(operations==null) {
			List<Operation> operations = new ArrayList<Operation>();
			this.operations = operations;
		}    	
		Operation newOperation=new Operation(a, b, typeofop);
		operations.add(newOperation);
    	
    	return division;
    }

    public void sendResults() {
    	if(loggingStrategy == null)
    		throw new NullPointerException();
    	if(operationsLogger==null) {
    		OperationsLogger operationsLogger = new OperationsLogger();
    		this.operationsLogger = operationsLogger;
    	}    	
        operationsLogger.logOperations(operations, loggingStrategy);
    }


    public Integer calculate(String expression, Calculator calculator) {	
    	ExpressionCalculator expCalculator = new ExpressionCalculator();
    	return expCalculator.calculateExpression(expression, calculator);
    }

	public static Builder builder() {
        return new Builder();
    }     
}