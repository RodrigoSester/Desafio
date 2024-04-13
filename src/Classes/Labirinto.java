package Classes;

import java.io.*;
import java.util.stream.*;

public class Labirinto {
  public static char[][] labirinto;
  private static char[][] path;
  
  private static int numberOfLines;
  private static int linesLength;

  public void criaLabirinto(String filename) throws IOException {
	final String firstLine;
	  
    final FileReader fileReader = new FileReader(filename);
    final BufferedReader buffRead = new BufferedReader(fileReader);
    
    final Stream<String> lines = buffRead.lines();
    final Object[] arrLines = lines.toArray();
    
    numberOfLines = arrLines.length;
    linesLength = arrLines[0].toString().length();
    
    labirinto = new char[numberOfLines][linesLength];

    firstLine = arrLines[0].toString();
    labirinto[0] = firstLine.toCharArray(); 
    
	Labirinto.percorreLabirinto(arrLines);

    buffRead.close();
  }

  public static void percorreLabirinto(Object[] lines) {	  
	  for (int i = 0; i < numberOfLines; i++) {
		  Object line = lines[i];

		  for (int j = 0; j < linesLength; j++) {
			  char letter = line.toString().charAt(j);
			  labirinto[i][j] = letter;
		  }
	  }
  }
  
  public void getLabirinto() {
	  for (int i = 0; i < numberOfLines; i++) {
		  for (int j = 0; j < linesLength; j++) {
			  System.out.print(labirinto[i][j]);
		  }
		  
		  System.out.println();
	  }
  }
}