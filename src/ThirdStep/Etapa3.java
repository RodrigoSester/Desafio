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
			System.out.println(openBrackets);
			int closeBrackets = list.contaElementos(')');
			System.out.println(closeBrackets);
			
			// every open bracket has a close bracket?
			
			return openBrackets == closeBrackets && isBalanced(s1.toString());
		} catch (IndexOutOfBoundsException e) {
			// Handle error
			System.out.println(e);
		}
		
		return false;
	}
	
	public static boolean isBalanced(String s) {
		System.out.println(s);
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
