package boggle;
import java.util.Stack;
import utils.LetterGrid;
public class BoggleGame {
	public class Word {
		public int row;
		public int col;
		public char character;

		public Word(int row, int col, char character) {
			this.row = row;
			this.col = col;
			this.character = character;
		}
		public String toString() {
			return "(" + row + "," + col + ")";
		}
	}
	/**
	 * The grid that contains all the letters. @see boggle.LetterGrid
	 */
	LetterGrid grid;
	
	/**
	 * The stack that stores the path when you search the word path
	 */
	Stack<String> path;
	
	/**
	 * A boolean array to mark any location (row,col) as visited
	 */
	boolean[][] visited;
	
	public BoggleGame(LetterGrid g) {
		grid = g;
		path = new Stack<String>();
		visited = new boolean[grid.getNumRows()][grid.getNumCols()];
	}	
	/**
	 * implement your method here (you may write helper method)
	 * @param word
	 * @return true if "word" can be found in grid, false otherwise
	 */
	public boolean findWord(String word) {
		visitedInitializtion();
		Stack<Word> WordStack = new Stack<Word>();
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int column = 0; column < grid.getNumCols(); column++) {
				if (grid.getLetter(row, column) == word.toUpperCase().charAt(0)) {
					WordStack.add(new Word(row, column, word.toUpperCase().charAt(0)));
				}
			}
		}
		return findWordHelper(WordStack, word.substring(1));
	}	
	/**
	 * @param word
	 * @return the path (cell index) of each letter
	 */
	public String findWordPath(String word) {
		if (!findWord(word)) {
			return "";
		}
		visitedInitializtion();
		Stack<Word> WordPath = new Stack<Word>();
		Stack<Word> stack = new Stack<Word>();
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int column = 0; column < grid.getNumCols(); column++) {
				if (grid.getLetter(row, column) == word.toUpperCase().charAt(0)) {
					stack.add(new Word(row, column, word.toUpperCase().charAt(0)));
				}
			}
		}
		return FindWordPathHelper(stack, WordPath, word.toUpperCase().substring(1));
	}	
	/**
	 * Determines the frequency of a word on the Boggle board. For simplicity,
	 * assume palindromes count twice.
	 * @param word
	 * @return the number of occurrences of the "word" in the grid
	 */
	public int frequency(String word) {
		visitedInitializtion();
		int occurrences = 0;
		Stack<Word> stack = new Stack<Word>();
		for (int row = 0; row < grid.getNumRows(); row++) {
			for (int column = 0; column < grid.getNumCols(); column++) {
				if (grid.getLetter(row, column) == word.toUpperCase().charAt(0)) {
					stack.add(new Word(row, column, word.toUpperCase().charAt(0)));
					visitedInitializtion();
					if (findWordHelper(stack, word.toUpperCase().substring(1))) {
						occurrences++;
						stack.clear();
					}
				}
			}
		}
		return occurrences;
	}
	private void visitedInitializtion() {
		for (int row = 0; row < visited.length; row++) {
			for (int column = 0; column < visited[row].length; column++) {
				visited[row][column] = false;
			}
		}
	}
	private boolean helperMethod(char character, int row, int column) {
		if(character==grid.getLetter(row, column)&& !visited[row][column]) {
			return true;
		}
		return false;
	}
	private boolean findWordHelper(Stack<Word> stack, String word) {
		if (word.length() == 0) {
			return true;
		}
		if (stack.size() == 0) {
			return false;
		}
		Word point = stack.pop();
		visited[point.row][point.col] = true;
		boolean wordFounder = false;	
		if (point.row - 1 >= 0 && helperMethod(word.charAt(0),point.row-1,point.col)) {			
			stack.add(new Word(point.row - 1, point.col, word.charAt(0)));
			wordFounder = true;
		}
		if (point.row - 1 >= 0 && point.col + 1 < grid.getNumCols()&& helperMethod(word.charAt(0),point.row-1,point.col+1)) {
			stack.add(new Word(point.row - 1, point.col + 1, word.charAt(0)));
			wordFounder = true;
		}
		if (point.col + 1 < grid.getNumCols()&& helperMethod(word.charAt(0),point.row,point.col+1)) {
			stack.add(new Word(point.row, point.col + 1, word.charAt(0)));
			wordFounder = true;
		}
		if (point.row + 1 < grid.getNumRows() && point.col + 1 < grid.getNumCols()&&helperMethod(word.charAt(0),point.row+1,point.col+1)) {
			stack.add(new Word(point.row + 1, point.col + 1, word.charAt(0)));
			wordFounder = true;
		}
		if (point.row + 1 < grid.getNumRows()&&helperMethod(word.charAt(0),point.row+1,point.col)) {
			stack.add(new Word(point.row + 1, point.col, word.charAt(0)));
			wordFounder = true;
		}
		if (point.row + 1 < grid.getNumRows() && point.col - 1 >= 0 &&helperMethod(word.charAt(0),point.row+1,point.col-1)) {
			stack.add(new Word(point.row + 1, point.col - 1, word.charAt(0)));
			wordFounder = true;
		}
		if (point.col - 1 >= 0 && helperMethod(word.charAt(0),point.row,point.col-1)) {
			stack.add(new Word(point.row, point.col - 1, word.charAt(0)));
			wordFounder = true;
		}
		if (point.row - 1 >= 0 && point.col - 1 >= 0 && helperMethod(word.charAt(0),point.row-1,point.col-1)) {
			stack.add(new Word(point.row - 1, point.col - 1, word.charAt(0)));
			wordFounder = true;
		}
		if (!wordFounder) {
			return false;
		}
		return findWordHelper(stack, word.substring(1));
	}
	private String FindWordPathHelper(Stack<Word> stack, Stack<Word> way, String word) {
		if (stack.size() == 0) {
			return "";
		}		
		if (word.length() == 0) {
			String answer="";
			Word u = stack.pop();
			way.add(u);
			for(int count=0;count<way.size();count++) {
				answer+=way.get(count).toString();
			}
			return answer;
		}		
		char point = word.charAt(0);
		word = word.substring(1);
		Word character = stack.pop();
		way.add(character);		
		visited[character.row][character.col] = true;
		boolean wordFounder = false;
		if (character.row - 1 >= 0 && helperMethod(point,character.row-1,character.col)) {
			stack.add(new Word(character.row - 1, character.col, point));
			wordFounder = true;
		}
		if (character.row - 1 >= 0 && character.col + 1 < grid.getNumCols()) {
			if(helperMethod(point,character.row-1,character.col+1)) {
				stack.add(new Word(character.row - 1, character.col + 1, point));
				wordFounder = true;
			}			
		}
		if (character.col + 1 < grid.getNumCols() && helperMethod(point,character.row,character.col+1)) {
			stack.add(new Word(character.row, character.col + 1, point));
			wordFounder = true;			
		}
		if (character.row + 1 < grid.getNumRows() && character.col + 1 < grid.getNumCols()) {
			if(helperMethod(point,character.row+1,character.col+1)) {
				stack.add(new Word(character.row + 1, character.col + 1, point));
				wordFounder = true;
			}
		}
		if (character.row + 1 < grid.getNumRows() && helperMethod(point,character.row+1,character.col)) {
			stack.add(new Word(character.row + 1, character.col, point));
			wordFounder = true;
		}
		if (character.row + 1 < grid.getNumRows() && character.col - 1 >= 0) {
			if(helperMethod(point,character.row+1,character.col-1)) {
				stack.add(new Word(character.row + 1, character.col - 1, point));
				wordFounder = true;
			}
		}
		if (character.col - 1 >= 0 && helperMethod(point,character.row,character.col-1)) {
			stack.add(new Word(character.row, character.col - 1, point));
			wordFounder = true;
		}
		if (character.row - 1 >= 0 && character.col - 1 >= 0) {
			if(helperMethod(point,character.row-1,character.col-1)) {
				stack.add(new Word(character.row - 1, character.col - 1, point));
				wordFounder = true;
			}
		}
		if (!wordFounder) {
			return "";
		}
		return FindWordPathHelper(stack, way, word);
	}
}