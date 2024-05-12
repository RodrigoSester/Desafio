package FirstStep.Classes;

import java.util.*;

public class Position {
	public int line, column;
	
	private boolean passed = false;
	
	public Position(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public void setPassed(boolean passed) {
		this.passed = passed;
	}
	
	public boolean getPassed() {
		return this.passed;
	}
	
	public Map<String, Integer> getProperties() {
		Map<String, Integer> properties = Map.of("line", this.line, "column", this.column);
		
		return properties;
	}
}
