package ThirdStep;

import java.util.Stack;

public class Main {
	private static Stack<Character> stack = new Stack<>();
	private static Etapa3 thirdStep = new Etapa3();

	private static String expression = "(A+B)-C";
	
	public static void main(String[] args) {
		char[] characters = expression.toCharArray();

		for (int index = characters.length - 1; index >= 0; index--) {
			stack.add(characters[index]);
		}
		
		if (thirdStep.checkBrackets(stack)) {
			System.out.println("Bom! Sua fórmula matemática está balanceada!");
		} else {
			System.out.println("Não está correto! Verifique a fórmula e tente novamente!");
		}
	}
}
