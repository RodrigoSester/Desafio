package Classes;

import java.io.*;
import java.util.stream.*;

public class Labirinto {
  public static char[][] maze;
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
    
    maze = new char[numberOfLines][linesLength];

    firstLine = arrLines[0].toString();
    maze[0] = firstLine.toCharArray(); 
    
	Labirinto.setMaze(arrLines);

    buffRead.close();
  }

  public static void setMaze(Object[] lines) {
	  boolean isStucked = false;
	  

	  for (int i = 0; i < numberOfLines; i++) {
		  Object line = lines[i];

		  for (int j = 0; j < linesLength; j++) {
			  char letter = line.toString().charAt(j);
			  maze[i][j] = letter;
			  
			  isStucked = checkIsStucked(i, j);
			  
			  if (isStucked) {
				  System.out.println("Fiquei travado na posição:");

				  String lineStucked = String.format("Linha: %s", i);
				  String columnStucked = String.format("Coluna: %s", j);
				  
				  System.out.println(lineStucked);
				  System.out.println(columnStucked);
				  
				  break;
			  }
		  
		  }

		  if (isStucked) break;
	  }
  }
  
  private static boolean checkIsStucked(int lineIndex, int columnIndex) {
	  return true;
  }
  
  public static boolean percorreLabirinto() {
	  return runInMaze();
  }
  
  private static boolean runInMaze() {
	  for (int i = 0; i < numberOfLines; i++) {
		  for (int j = 0; j < linesLength; j++) {
			  System.out.print(maze[i][j]);
			  
			  if (maze[i][j] == 'D') {
				  return true;
			  }
		  }
		  
		  System.out.println();
	  }
	  
	  return true;
  }
  
  public void getLabirinto() {
	  for (int i = 0; i < numberOfLines; i++) {
		  for (int j = 0; j < linesLength; j++) {
			  System.out.print(maze[i][j]);
		  }
		  
		  System.out.println();
	  }
  }
  
}