package assignment01;

/**
 * This class represents a matrix of numbers.
 * It has a set number of rows and columns.
 * A vector is a special case in which the number of columns or rows in one.
 * (A column vector has one column. A row vector has one row).
 * 
 * @author Eric Heisler & Everett Oglesby	
 * @version May 1, 2023
 */
public class Matrix {

	// 2D array to hold the numbers of the matrix
	private double[][] data;
	// The number of columns and rows
	private int columns;
	private int rows;
	
	/**
	 * Creates a new matrix by copying in a 2D array of numbers.
	 * Input data is a row-major 2D array. That means the top left
	 * corner is data[0][0], and the number to its right is data[0][1],
	 * while the number below it is data[1][0].
	 * 
	 * @param data - a 2D array holding the numbers of the matrix
	 * @throws IllegalArgumentException if the number of rows or columns
	 * 			is zero, or if not all rows have the same length.
	 */
	public Matrix(double[][] data) {
		// Check number of rows
		if (data.length == 0) {
			throw new IllegalArgumentException("Number of rows must be positive.");
		}
		rows = data.length;
		
		// Check number of columns
		columns = data[0].length;
		for(int i = 0; i < rows; i++) {
			if (data[i].length == 0) {
				throw new IllegalArgumentException("Number of columns must be positive.");
			}
			if (data[i].length != columns) {
				throw new IllegalArgumentException("Number of columns must be equal for all rows.");
			}
		}
		
		// Create the array and copy data.
		this.data = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	/**
	 * Determines whether this matrix is "equal to" another matrix, 
	 * where equality is defined as both having the same number of rows,
	 * same number of columns, and containing the same numbers in the 
	 * same positions.
	 * 
	 * @param other - another Matrix to compare
	 */
	public boolean equals(Object other) {
		
		//Check if other object is a Matrix, if not return false
		if (!(other instanceof Matrix)) {			
			return false;
		}
		//Cast other object to Matrix named otherMat
		Matrix otherMat = (Matrix)other;
		
		//For every row and column check that the data is the same
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
		
				double otherData = otherMat.data[i][j];
				//If the data is the same continue
				if(data[i][j] == otherData) {
					continue;
				}
				//If the data is not the same, return false
				else {
					return false;
				}
			}
		}
		//If every position matches return true
		return true;
	}
	
	/**
	 * Returns a new matrix that is the sum of this and another matrix.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if this and the other matrix do not have the
	 * 			same number of rows or columns.
	 */
	public Matrix add(Matrix other) {
		
		//If the Matrices are not identical in size, throw IllegalArgumentException
		if(other.rows != rows || other.columns != columns) {
			throw new IllegalArgumentException("The given and current matrix"
					+ "are not compatible for addition.");
		}
		
		//Creates a new matrix with the same number of rows and columns 
		//as the current matrix
		Matrix newMatrix = new Matrix(data);
		
		//Loop through each row and column
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				//Create a new double by adding the doubles of the the current and 
				//given matrices to the corresponding position of the new matrix
				double newNum = data[i][j] + other.data[i][j];
				newMatrix.data[i][j] = newNum;
				
			}
		}
		
		return newMatrix;
	}
	
	/**
	 * Returns the product of this and another matrix.
	 * The order of multiplication is (this * other).
	 * 
	 * @param other - another matrix to multiply this one
	 * @throws IllegalArgumentException if the number of rows in the other matrix 
	 * 			does not equal the number of columns in this.
	 */	
	public Matrix multiply(Matrix other) {
		
		//If the number of rows in the given matrix are not the same as the number 
		//of columns in the current matrix, throw IllegalArgumentException
		if(other.rows != columns) {
			throw new IllegalArgumentException("The given and current matrix"
					+ "are not compatible for multiplication.");
		}
		
		//Creates a new matrix with the same number of rows and columns 
		//as the current matrix
		double[][] newMatrixData = new double[rows][other.columns];
		Matrix newMatrix = new Matrix(newMatrixData);

		
		//Loop through each row and column
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < other.columns; j++) {
				
				//Multiply matrices to get the double at the current position
				for(int n = 0; n < other.rows; n++) {
					newMatrix.data[i][j] += data[i][n] * other.data[n][j];
				}
			}
		}
		//Return the newly created matrix		
		return newMatrix;
	}
	

	/**
	 * Generates and returns a text representation of this matrix.
	 * For example, this is a 3-row, 2-column matrix:
	 * "1.0 2.0
	 *  2.0 4.0
	 *  3.0 6.0"
	 *  
	 *  Each line ends with a newline "\n" and no space after the last number.
	 *  Notice there is no newline or space at the end of the string.
	 */
	public String toString() {
		//Create a new empty string to create the new toString around
		String printString = "";
		
		//Loop through each row and column
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				//If variable has another number in the row after it,
				//add the value to the printString plus a space
				if(j + 1 < columns) {
					printString = printString + data[i][j] + " ";
				}
				//Else, just add the value
				else {
					printString = printString + data[i][j];
				}
			}
			//Start a new line with "\n" after each row but the last
			if(i+1 < rows) {
				printString = printString + "\n";
			}	
		}
		//Return the printString for the toString method
		return printString;
	}
	
	/**
	 * 
	 * @return int - rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * 
	 * @return int - columns
	 */
	public int getColumns() {
		return columns;	
	}
	
	/**
	 * 
	 * @return double[][] - data
	 */
	public double[][] getData() {
		return data;
	}
}
