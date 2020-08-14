package com.saksham.calculator.operators;

import java.util.Arrays;
import java.util.Stack;

import com.saksham.calculator.Calculator;

public class ExpressionCalculator {
	
	public Integer calculateExpression(String s, Calculator calculator)
	{
		try 
    	{
	    	if (s == null || s.length() == 0) {
				return 0;
			}
			s = s.trim().replaceAll("[ ]+", "");
	 
			if (s == null || s.length() == 0) {
				return 0;
			}
	 
			Stack<Character> opStack = new Stack<>();
			Stack<Integer> numStack = new Stack<>();
	 
			int i = 0;
			while (i < s.length()) {
				if (Character.isDigit(s.charAt(i))) {
					int num = 0;
					while (i < s.length() && Character.isDigit(s.charAt(i))) {
						num = num * 10 + Character.getNumericValue(s.charAt(i));
						i++;
					}
					numStack.push(num);
				} else {
					char op = s.charAt(i);
					if (opStack.isEmpty()) {
						opStack.push(op);
						i++;
					} else if (op == '+' || op == '-') {
						char top = opStack.peek();
						if (top == '(') {
							opStack.push(op);
							i++;
						} else {
							applyOp(numStack, opStack, calculator);
						}
					} else if (op == '*' || op == '/') {
						char top = opStack.peek();
						if (top == '(') {
							opStack.push(op);
							i++;
						} else if (top == '*' || top == '/') {
							applyOp(numStack, opStack, calculator);
						} else if (top == '+' || top == '-') {
							opStack.push(op);
							i++;
						}
					} else if (op == '(') {
						opStack.push(op);
						i++;
					} else if (op == ')') {
						while (opStack.peek() != '(') {
							applyOp(numStack, opStack, calculator);
						}
						opStack.pop();
						i++;
					}
				}
			}
	 
			while (!opStack.isEmpty()) {
				applyOp(numStack, opStack, calculator);
			}
	 
			return numStack.peek();
	       
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.toString());
    		throw new IllegalStateException();
    	}
	}
	
    
    public void applyOp(Stack<Integer> numStack, Stack<Character> opStack, Calculator calculator) 
    { 
		int b = numStack.pop();
        int a = numStack.pop();
		char op = opStack.pop();
		int ans = 0;
        switch (op) 
        { 
        case '+': 
			ans =  calculator.add(Arrays.asList(a,b)); 
			break;
        case '-': 
			ans =  calculator.subtract(a,b); 
			break;
        case '*': 
			ans =  calculator.multiply(Arrays.asList(a,b)); 
			break;
        case '/': 
			ans = calculator.divide(a,b); 
			break;
		} 
		numStack.push(ans);
    } 

}
