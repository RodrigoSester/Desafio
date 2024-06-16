package ThirdStep;

import java.util.Stack;

public class Etapa3 {
	public boolean checkBrackets(Stack<Character> s1) {
		StaticList<Character> list = new StaticList<Character>(s1.size());
		
		for (int index = 0; index < s1.size(); index++) {
			list.insert(s1.elementAt(index), index);
		}
		
		try {
			int openBrackets = list.contaElementos('(');
			int closeBrackets = list.contaElementos(')');

			return openBrackets == closeBrackets && checkParenthesesBalance(s1.toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	public boolean checkParenthesesBalance(String expression) {
	    Stack<Character> stack = new Stack<>();

	    for (int i = expression.length() - 1; i >= 0; i--) {
	        char c = expression.charAt(i);

	        if (c == '(') {
	            stack.push(c);
	        } else if (c == ')') {
	            if (stack.isEmpty() || stack.pop() != '(') {
	                return false;
	            }
	        }
	    }

	    return stack.isEmpty();
	}

}
