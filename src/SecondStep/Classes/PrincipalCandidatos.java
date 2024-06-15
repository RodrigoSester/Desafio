package SecondStep.Classes;

import java.util.*;
import SecondStep.Classes.Comparator;

public class PrincipalCandidatos {
	private static Random random = new Random();
	private static Candidato[] candidatos;
	private final static Scanner INPUT = new Scanner(System.in);

	private static InsertionSort<Candidato> insertionSort = new InsertionSort<>();

	public static void main(String args[]) {
		int numCandidates = random.nextInt(1, 100);
		
		candidatos = new Candidato[numCandidates];
		
		generateCandidates(numCandidates);
		
		ordenaCandidatosPorPartido();
		ordenaCandidatosPorVotos();
		ordenaCandidatosPorNome();

		for (Candidato candidate : candidatos) {
			System.out.println(candidate.toString());
		}

		System.out.println("Insira o nome que deseja fazer a busca: ");
		String search = INPUT.nextLine();
		System.out.println("Buscando por: " + search);
		
		int positionSearch = pesquisaBinariaCandidatos(candidatos, search);
		
		if (positionSearch == -1) {
			System.out.println("Desculpe, não foi possível encontrar este candidato!");
		} else {			
			System.out.println("Legal!! Encontramos seu candidato!!");
			System.out.println("Aqui estão os dados do candidato: ");
			System.out.println(candidatos[positionSearch].toString());
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
	
	public static int pesquisaBinariaCandidatos(Candidato[] candidates, String nome) {
		int inf = 0;
		int sup = candidates.length;
		
		while (inf <= sup) {
			int med = (inf + sup) / 2;

			if (nome.equals(candidates[med].getNome())) {
				return med;
			} else if (nome.charAt(0) < candidates[med].getNome().charAt(0)) {
				sup = med - 1;
			} else {
				inf = med + 1;
			}
		}

		return -1;
	}
}
