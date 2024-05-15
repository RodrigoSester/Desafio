package SecondStep.Classes;

import java.util.*;

public class PrincipalCandidatos {
	private static Random random = new Random();
	private static Candidato[] candidatos;

	public static void main(String args[]) {
		int numCandidates = random.nextInt(1, 100);
		System.out.println(numCandidates);
		
		candidatos = new Candidato[numCandidates];
	}
	
	private static void generateCandidates(int numCandidates) {
        for (int i = 0; i < numCandidates; i++) {
            String name = generateRandomName();
            String party = generateRandomParty();
            int votes = random.nextInt(100000);
            candidatos[i] = new Candidato(name, party, votes);
        }
    }

    private static String generateRandomName() {
        String[] names = {"Alice", "Bob", "Charlie", "David", "Emily", "Hope", "Maggy", "Homer", "Lau"};
        return names[random.nextInt(names.length)];
    }

    private static String generateRandomParty() {
        String[] parties = {"PT", "PSDB", "DEM", "PP", "PSOL", "PM"};
        return parties[random.nextInt(parties.length)];
    }

	
	
}
