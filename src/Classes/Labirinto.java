package Classes;

import java.io.*;
import java.util.stream.*;

public class Labirinto {
  public static char[][] maze;
  
  private static int numberOfLines;
  private static int linesLength;
  
  private static final char WALL = 'X';
  private static final char PATH = ' ';
  private static final char PASSED = '-';
  private static final char DESTINY = 'D';

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
	  for (int i = 0; i < numberOfLines; i++) {
		  Object line = lines[i];

		  for (int j = 0; j < linesLength; j++) {
			  char letter = line.toString().charAt(j);
			  maze[i][j] = letter;
		  }
	  }
  }
  
  public static boolean percorreLabirinto() {
	  return runInMaze(0, 0);
  }
  
  private static boolean runInMaze(int line, int column) {
	  Position nextPosition = getPath(line, column);
	  
	  if (hasReachedInDestiny(line, column)) {
		  return true;
	  }
	  
	  return runInMaze(nextPosition.line, nextPosition.column);
  }
  
  private static Position getPath(int line, int column) {
	  try {
		  boolean isStucked = false;
		  isStucked = checkIsStucked(line, column);
		  
		  if (!isStucked) {
			  Position nextPosition = getNextPosition(line, column);
			  
			  maze[line][column] = PASSED;

			  return nextPosition;
		  }
		  
		  return null;

	  } catch (Error err){
		  System.out.println(err);
		  throw err;
	  }
  }
  
  private static boolean checkIsStucked(int lineIndex, int columnIndex) throws ArrayIndexOutOfBoundsException {
	  try {		  
		  if (maze[lineIndex+1][columnIndex] == WALL
				  && maze[lineIndex-1][columnIndex] == WALL
				  && maze[lineIndex][columnIndex+1] == WALL
				  && maze[lineIndex][columnIndex-1] == WALL
				  ) {
			  System.out.println("Não há caminho para a posição:");
			  
			  String lineStucked = String.format("Linha: %s", lineIndex);
			  String columnStucked = String.format("Coluna: %s", columnIndex);
			  
			  System.out.println(lineStucked);
			  System.out.println(columnStucked);
			  return true;
		  }
	  } catch (ArrayIndexOutOfBoundsException e) {
		  System.out.println("Deu algum erro em uma posição");
	  }
	  
	  return false;
  }
  
  private static Position getNextPosition(int line, int column) {
	  Position nextPosition = null;

	  if (isValidMove(line, column + 1)) {
		  if (maze[line][column + 1] == PATH) {
			  nextPosition = new Position(line, column + 1);
			  return nextPosition;
		  }
	  }
	  
	  if (isValidMove(line + 1, column)) {
		  if (maze[line + 1][column] == PATH) {
			  nextPosition = new Position(line + 1, column);
			  return nextPosition;
		  }
	  }
	  
	  if (isValidMove(line, column - 1)) {
		  if (maze[line][column - 1] == PASSED) {
			  nextPosition = new Position(line, column - 1);
			  return nextPosition;
		  }
	  }
	  
	  if (isValidMove(line - 1, column)) {
		  if (maze[line - 1][column] == PASSED) {
			  nextPosition = new Position(line - 1, column);
			  return nextPosition;
		  }
	  }

	  return nextPosition;
  }
  
  private static boolean isValidMove(int line, int column) {
	  return line >= 0 && line < maze.length && column >= 0 && column < maze[0].length;
  }
  
  private static boolean hasReachedInDestiny(int line, int column) {
	  if (isValidMove(line + 1, column)) {
		  return maze[line + 1][column] == DESTINY;
	  }
	  
	  if (isValidMove(line, column + 1)) {
		  return maze[line][column + 1] == DESTINY;
	  } 
	  
	  if (isValidMove(line, column - 1)) {
		  return maze[line][column - 1] == DESTINY;
	  }
	  
	  if (isValidMove(line - 1, column)) {
		  return maze[line - 1][column] == DESTINY;
	  }
	  
	  return false;
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