package SecondStep.Classes;

import java.util.*;

public class PrincipalCandidatos {
	private static Random random = new Random();
	private static int[] candidatos;

	public static void main(String args[]) {
		int randomNumber = random.nextInt(1, 100);
		System.out.println(randomNumber);
		
		candidatos = new int[randomNumber];
	}
	
	
}
