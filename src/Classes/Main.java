package Classes;

import java.io.*;

public class Main {
	final static String FILE_PATH = "src/Assets/input.txt";

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
