package Classes;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Labirinto {
  public static char[][] maze;

  public static Map<String, Position> movements = Map.of(
		  "right", new Position(0, 1),
		  "down", new Position(1, 0),
		  "up", new Position(-1, 0),
		  "left", new Position(0, -1)
		  );
  
  public static List<Position> spacePositions = new ArrayList<>();
  
  private static int numberOfLines;
  private static int linesLength;
  public static int indexTwoPaths;
  
  private static final char WALL = 'X';
  private static final char PATH = ' ';
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
	  for (int row = 0; row < numberOfLines; row++) {
		  Object line = lines[row];

		  for (int column = 0; column < linesLength; column++) {
			  char letter = line.toString().charAt(column);
			  maze[row][column] = letter;
			  
			  if (letter == PATH) {
				  spacePositions.add(new Position(row, column));
			  }
		  }
	  }
  }
  
  public static boolean percorreLabirinto() {
	  spacePositions.get(0).setPassed(true);

	  return runInMaze(0);
  }
  
  private static boolean runInMaze(int actualIndexPosition) {
	  Position actualPosition = spacePositions.get(actualIndexPosition);
	  List<Position> availablePaths = new ArrayList<>();
	  
	  if (hasReachedInDestiny(actualIndexPosition)) {
		  return true;
	  }
	  
	  for (Map.Entry<String, Position> movement : movements.entrySet()) {
		Position runForward = movement.getValue();
		
		for (int i = 0; i < spacePositions.size(); i++) {
			int nextLine = actualPosition.line + runForward.line;
			int nextColumn = actualPosition.column + runForward.column;

			Position possibleNextPosition = new Position(nextLine, nextColumn);
			
			boolean hasPath = checkHasPath(possibleNextPosition, spacePositions.get(i));
			
			if (hasPath && !spacePositions.get(i).getPassed()) {
				availablePaths.add(spacePositions.get(i));
			}
		}
	  }

	  if (availablePaths.size() == 0 && indexTwoPaths != -1) {
		  int nextPositionIndex = indexTwoPaths;
		  indexTwoPaths = -1;
		  return runInMaze(nextPositionIndex);
	  }
	  
	  if (checkIsStucked(actualIndexPosition) && actualIndexPosition == indexTwoPaths) {
		  return false;
	  }

	  if (availablePaths.size() > 1) {
		  indexTwoPaths = actualIndexPosition;

		  for (Position path : availablePaths) {
			  path.setPassed(true);
			  
			  int nextPositionIndex = spacePositions.indexOf(path);
			  return runInMaze(nextPositionIndex);
		  }
	  } else if (availablePaths.size() == 1) {		  
		  Position path = availablePaths.get(0);
		  path.setPassed(true);
		  
		  int nextPositionIndex = spacePositions.indexOf(path);
		  return runInMaze(nextPositionIndex);
	  }
	  
	  
	  return false;
  }

  private static boolean checkIsStucked(int indexPosition) {
	  Position position = spacePositions.get(indexPosition);

	  int lineIndex = position.line;
	  int columnIndex = position.column;
	  
	  int movementsAvailable = 0;
	  
	  for (Map.Entry<String, Position> movement : movements.entrySet()) {
		Position movementPosition = movement.getValue();
		int nextLine = lineIndex + movementPosition.line;
		int nextColumn = columnIndex + movementPosition.column;
		
		if (!isValidMove(nextLine, nextColumn)) {
			continue;
		}

		boolean isWall = maze[nextLine][nextColumn] == WALL;
		boolean alreadyPassed = spacePositions.get(indexPosition).getPassed();

		if (isWall || alreadyPassed) {
			movementsAvailable += 1;
		}
	  }
	  
	  if (movementsAvailable == 4) {
		  System.out.println("Não há caminho para a posição:");
		  String lineStucked = String.format("Linha: %s", lineIndex);
		  String columnStucked = String.format("Coluna: %s", columnIndex);
		  
		  System.out.println(lineStucked);
		  System.out.println(columnStucked);
		  return true;
	  }
	  
	  return false;
  }

  private static boolean hasReachedInDestiny(int indexPosition) {
	  Position position = spacePositions.get(indexPosition);

	  int line = position.line;
	  int column = position.column;
	  
	  for (Map.Entry<String, Position> movement : movements.entrySet()) {
		Position movementPosition = movement.getValue();
		int nextLine = line + movementPosition.line;
		int nextColumn = column + movementPosition.column;
		
		if (!isValidMove(nextLine, nextColumn)) {
			continue;
		}

		boolean isDestiny = maze[nextLine][nextColumn] == DESTINY;

		if (isValidMove(nextLine, nextColumn) && isDestiny) {
			return true;
		}
	  }
	  
	  return false;
  }  
  
  private static boolean checkHasPath(Position nextPosition, Position spacePosition) {
	  boolean isPositionsEquals = nextPosition.line == spacePosition.line && nextPosition.column == spacePosition.column;
	  
	  return isPositionsEquals;
  }
  
  public void getLabirinto() {
	  for (int i = 0; i < numberOfLines; i++) {
		  for (int j = 0; j < linesLength; j++) {
			  System.out.print(maze[i][j]);
		  }
		  
		  System.out.println();
	  }
  }

  private static boolean isValidMove(int line, int column) {
	  return line >= 0 && line < maze.length && column >= 0 && column < maze[0].length;
  }
}