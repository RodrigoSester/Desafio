package SecondStep.Classes;

public class Candidato {
	private String nome, partido;
	private int intencoesVotos;
	
	public Candidato(String nome, String partido, int intencoesVotos) {
		this.nome = nome;
		this.partido = partido;
		this.intencoesVotos = intencoesVotos;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getPartido() {
		return this.partido;
	}
	
	public int getIntencoesVotos() {
		return this.intencoesVotos;
	}
	
	@Override
	public String toString() {
		return "Candidato do partido: " + getPartido() + "\n"
				+ "Nome: " + getNome() + "\n"
				+ "Intenções de votos: " + getIntencoesVotos() + "\n"
				+ "--------------------------------------";
	}
}
