package infixAndPostfix;

import java.util.Stack;

public class InfixToPostfix {
	public static void main(String[] args) {
		String infix = "(3+4)*(5-2)^2*(4/2)";
		System.out.println(postfixEvaluation(infixToPostfixEval(infix)));
	}
	
	public static int rank(String infix) {
		int rank = 0;
		for(int i = 0; i < infix.length(); i++) {
			if(Character.isDigit(infix.charAt(i))) {
				rank++;
			}else if(infix.charAt(i) == '(' || infix.charAt(i) == ')') {
				continue;
			}else {
				rank--;
			}
		}
		System.out.println();
		System.out.println(rank);
		System.out.println();
		return rank;
	}
	
	public static String infixToPostfixEval(String infix) {
		if(rank(infix) != 1) {
			return null;
		}
		String postFix = "";
		Stack<Character> s = new Stack<>();
		for(int i = 0; i < infix.length(); i++) {
			if(Character.isDigit(infix.charAt(i))) {
				postFix += infix.charAt(i);
			}else if(infix.charAt(i) == ')') {
				while(!s.isEmpty() && s.peek() != '(') {
					postFix += s.pop();
				}
				s.pop();//remove the (
			}else {
				while(!s.isEmpty() && prec(infix.charAt(i), true) <= prec(s.peek(), false)) {
					postFix += s.pop();
				}
				s.add(infix.charAt(i));
			}
		}
		
		while(!s.isEmpty()) {
			postFix += s.pop();
		}
		return postFix;
	}
	public static String convertInfixToPostfix(String infix) {
		String postFix = "";
		Stack<Character> operatorStack = new Stack<>();
		for(int i = 0; i < infix.length(); i++) {
			char op = infix.charAt(i);
			if(Character.isDigit(op)) {
				postFix += op;
			}else if(op == ')') {
				while(!operatorStack.isEmpty() && operatorStack.peek() != '(') {
					postFix += operatorStack.pop();
				}
				operatorStack.pop();
			}else {
				while(!operatorStack.isEmpty() && precedence(op, true) <= precedence(operatorStack.peek(), false)) {
					postFix += operatorStack.pop();
				}
				operatorStack.push(op);
			}
		}
		
		while(!operatorStack.isEmpty()) {
			postFix += operatorStack.pop();
		}
		return postFix;
	}
	
	public static int postfixEvaluation(String postFix) {
		Stack<Integer> s = new Stack<>();
		for(int i = 0; i < postFix.length(); i++) {
			if(Character.isDigit(postFix.charAt(i))) {
				s.push(postFix.charAt(i) - '0');
			}else {
				s.add(calculateResult(s.pop(), s.pop(), postFix.charAt(i)));
			}
		}
		return s.pop();	
	}
	
	public static int calculateResult(int rhs, int lhs, char operator) {
		int result;
		switch(operator) {
			case '+':
				result = lhs + rhs;
				break;
			case '-':
				result = lhs - rhs;
				break;
			case '*':
				result = lhs * rhs;
				break;
			case '/':
				result = lhs/rhs;
				break;
			case '^':
				result = (int)Math.pow(lhs, rhs);
				break;
			case '%':
				result = lhs % rhs;
				break;
			default:
				throw new IllegalArgumentException();
		}
		return result;
	}
	
	public int evaluatePostFix(int rhs, int lhs, char op) {
		int result;
		switch(op) {
		case '+':
			result = rhs + lhs;
			break;
		case '-':
			result = lhs - rhs;
			break;
		case '*':
			result = lhs * rhs;
			break;
		case '/':
			result = lhs / rhs;
			break;
		case '%':
			result = lhs % rhs;
			break;
		case '^':
			result = (int)Math.pow(lhs,rhs);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return result;
	}
	
	public static int prec(char op, boolean fromInput) {
		switch(op) {
		case '+':
		case '-':
			return 1;
		case '/':
		case '*':
		case '%':
			return 2;
		case '^':
			if(fromInput) {
				return 4;
			}
			return 3;
		case '(':
			if(fromInput) {
				return 5;
			}
			return -1;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	
	
	
	public static int precedence(char op, boolean fromInput) {
		int result;
		switch(op) {
			case '+':
			case '-':
				result = 1;
				break;
			case '*':
			case '/':
			case '%':
				result = 2;
				break;
			case '^':
				if(fromInput) {
					result = 4;
				}else {
					result = 3;
				}
				break;
			case '(':
				if(fromInput) {
					result = 5;
				}else {
					result = -1;
				}
				break;
			default:
				throw new IllegalArgumentException();
		}
		return result;
	}
}
