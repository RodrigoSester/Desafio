package Desafio;

import java.io.*;

public class main {
	final static String FILE_PATH = "challengeTwo/input.txt";

	public static void main(String[] args) {
	    Labirinto labirinto = new Labirinto();
	    
	    try {
	      labirinto.criaLabirinto(FILE_PATH);
	
	      labirinto.getLabirinto();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
}
