package SecondStep.Classes;

import java.util.*;
import SecondStep.Classes.Comparator;

public class PrincipalCandidatos {
	private static Random random = new Random();
	private static Candidato[] candidatos;

	private static InsertionSort<Candidato> insertionSort = new InsertionSort<>();

	public static void main(String args[]) {
		int numCandidates = random.nextInt(1, 10);
		
		candidatos = new Candidato[numCandidates];
		
		generateCandidates(numCandidates);
		
		ordenaCandidatosPorPartido();
		ordenaCandidatosPorVotos();
		ordenaCandidatosPorNome();

		for (Candidato candidate : candidatos) {
			System.out.println(candidate.toString());
		}
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
	
	private static void ordenaCandidatosPorNome() {
		insertionSort.sort(candidatos, (Candidato candidate1, Candidato candidate2) -> candidate1.getNome().compareTo(candidate2.getNome()));
	}
	
	private static void ordenaCandidatosPorVotos() {
		Comparator comparator = new SecondStep.Classes.Comparator();
		insertionSort.sort(candidatos, (Candidato candidate1, Candidato candidate2) -> comparator.compare(candidate1.getIntencoesVotos(), candidate2.getIntencoesVotos()));
	}
	
	private static void ordenaCandidatosPorPartido() {
		insertionSort.sort(candidatos, (Candidato candidate1, Candidato candidate2) -> candidate1.getPartido().compareTo(candidate2.getPartido()));
	}
}
