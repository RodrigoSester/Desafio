package FirstStep.Classes;

import java.io.*;

public class Main {
	final static String FILE_PATH = "src/Assets/input.txt";

	public static void main(String[] args) {
	    Labirinto labirinto = new Labirinto();
	    
	    try {
	      labirinto.criaLabirinto(FILE_PATH);
	      
	      if (Labirinto.percorreLabirinto()) {
	    	  System.out.println("Legal!! Consegui resolver o labirinto");
	    	  return;
	      }
	      
	      System.out.println("Que pena, não consegui resolver");
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
}
