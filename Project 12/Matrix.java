package matrix;

import java.io.*;
import java.util.Arrays;

public class Matrix {

    public int[][] matrix;
    int[][] trans;
    public int x, y;
    private boolean transposed;
    public Matrix print;
 	public Matrix newMatrix;

    public Matrix(int x, int y){
        matrix = new int[x][y];
        this.x = x;
        this.y = y;
    }
    public class innerClass implements Runnable{
    	public int row;
		public int column;
		public innerClass(int row, int column) {	
			this.row = row;
			this.column = column;
		}		
		public void run() {
			print.matrix[row][column] = 0;
			for (int count = 0; count < matrix.length; count++)
				print.matrix[row][column] += matrix[row][count] * newMatrix.matrix[count][column];
		}
    }
    /*
     * This method takes in a 2d matrix array and returns the transposed matrix
     * https://en.wikipedia.org/wiki/Transpose
     */
    private int[][] transpose(int[][] arr){
		if (arr == null || arr.length == 0) {
			return null;
		}		
		int[][] answer = new int[arr[0].length][arr.length];
		for (int row = 0; row < arr.length; row++) {
			for (int column = 0; column < arr[0].length; column++) {
				answer[column][row] = arr[row][column];
			}
		}
		return answer;
	}

    public void set(int[][] in){
        this.matrix = in;
    }
    
    //Do NOT modify this method
    public void load(String path) throws IOException{
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(path));
        }catch(FileNotFoundException e){
            System.err.println("file not found: " + path);
        }
        int row = 0;
        while(true){
            String line = br.readLine();
            if(line == null){
                break;
            }
            String arr[] = line.split(" ");
            for(int i = 0; i < arr.length; i ++) {
                matrix[row][i] = Integer.parseInt(arr[i]);
            }
            row++;
        }
        trans = transpose(matrix);
        transposed = true;
    }

    //Do NOT modify this method
    public String toString(){
        String aString = "";
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                aString += " " + matrix[row][col];
            }
            aString += "\r\n";
        }
        return aString;
    }

    //This is a Single Threaded matrix multiply.
    //Takes in a matrix and multiplies itself by it so (this x b)
    public Matrix multiply(Matrix b){  
   
    	if(b==null||b.x==0||b.y==0||this.y!=b.x) {
    		return null;
    	}
    	int rows = x;
		int cols = b.y;
		Matrix result = new Matrix(rows, cols);
		if (y != b.x) {
			return null;
		}
		for (int c = 0; c < y; c++) {
			for (int r = 0; r < b.y; r++) {
				result.matrix[c][r] = 0;
				for (int n = 0; n < y; n++)
					result.matrix[c][r] += matrix[c][n] * b.matrix[n][r];
			}
		}
		return result;
    }

    //This method takes in a Matrix, and a number of threads and uses that number of threads to 
    //multiply the two matrices together. It should be in the order (this x m) 
    public Matrix multiply(Matrix m, int threads) {
    	if(threads==1) {
    		return multiply(m);
    	}
    	if(threads==0) {
    		return null;
    	}
    	if(m==null||m.x==0||m.y==0||this.y!=m.x) {
    		return null;
    	}
    	Thread[] answer = new Thread[threads];		
    	print = new Matrix(y, m.y);
    	newMatrix = m;
		int numberOfThread = 0;
		for (int row = 0; row < y; row++) {			
			for (int columns = 0; columns < m.y; columns++) {
				if (answer[numberOfThread] == null || !answer[numberOfThread].isAlive()) {
					answer[numberOfThread] = new Thread(new innerClass(row, columns));
					answer[numberOfThread].start();
				}
				numberOfThread++;
				if (numberOfThread == threads) {
					for (int count = 0; count < threads; count++) {
						if (answer[count] != null) {
							try {
								answer[count].join();
							} catch (InterruptedException t) {						
								t.printStackTrace();
							}
						}
					}
					numberOfThread = 0;
				}
			}
		}
		return print;
	}
    
    //a method that should take in a matrix and determine if it is equal to this matrix
    @Override
    public boolean equals(Object in) {
    	return Arrays.deepEquals(matrix, ((Matrix) in).matrix);
	}

    //this is given as potentially useful starting point for testing
    public static void main(String[] args){
        Matrix a = new Matrix(3,4);
        Matrix b = new Matrix(4,4);
        int[][] ain = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4}//,
            //   {1, 2, 3, 4}
        };
        int[][] bin = {
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4},
            {1, 2, 3, 4}
        };
        
        System.out.println();
        a.set(ain);
        b.set(bin);
        //Matrix rem = a.multiply(a, b, 3);

		/*
        System.out.println(a);
        System.out.println(b);
        System.out.println(rem);
        */

    }

}

