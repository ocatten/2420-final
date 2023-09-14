package assign01;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (i.e., along the columns).
 * In a column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Prof. Parker & Parker Catten
 * @version August 24, 2023
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;      
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;
	
	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not 
	 *         compatible with a row or column vector
	 */
	public MathVector (double[][] data) {
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");
		
		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");
		
		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i=0; i < this.data.length; i++) { 
			for(int j=0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	
	
	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same 
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals (Object other) {
		if(!(other instanceof MathVector))
			return false;
		
		@SuppressWarnings("unused")
		MathVector otherVec = (MathVector)other;
		
		// For each row and column
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
						
				// Check each item for an unequal value
				if(otherVec.getData()[i][j] != data[i][j]) {
					
					return false; // The two vectors aren't equal
				}
							
			}
		}
		
		return true; // No discrepancies found
	}
	
	
	
	/**
	 * Generates and returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose () {

		// Make a new array and swap the lengths
		double[][] newVector = new double[ data[0].length ][ data.length ];
		
		// Move through each column
		for (int j = 0; j < newVector[0].length; j++) {
			// Make each new row equal to each old column
			for (int i = 0; i < newVector.length; i++) {
			
				newVector[i][j] = data[j][i];
			}
		}
		
		// Return the completed vector
		return new MathVector(newVector);
	}
	
	
	
	/**
	 * @return the 2D backing array
	 */
	public double[][] getData () { return data; }
	
	
	
	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public MathVector add (MathVector other) {
		
		//If the backing 2D arrays are not identical in size, throw IllegalArgumentException
		if ( (other.getData().length != data.length) || (other.getData()[0].length != data[0].length) ) {
			throw new IllegalArgumentException("The other MathVector provided is a different size and cannot be added.");
		}
				
		// Makes a copy of the current object
		MathVector result = new MathVector(data);
				
		// For each row and column
		for(int i = 0; i < result.getData().length; i++) {
			for(int j = 0; j < result.getData()[0].length; j++) {
				
				// Add the value at that point to the other's value at that point
				result.getData()[i][j] += other.getData()[i][j];
					
			}
		}
				
		return result;
	}
	
	
	
	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */	
	public double dotProduct (MathVector other) {
		
		// Catch case for empty array
		if (vectorSize == 0) {
			return 0;
		}
		
		//If the backing 2D arrays are not identical in size, throw IllegalArgumentException
		if ( (other.getData().length != data.length) || (other.getData()[0].length != data[0].length) ) {
			throw new IllegalArgumentException("The other MathVector provided is a different size and cannot be multiplied.");
		}
		
		double product = 0; // Number to be returned
		
		// Combine the cumulative result to the product of the data from both vectors at the current index
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				product += data[i][j] * other.getData()[i][j];
			}
		}
		
		return product; // Return the sum
	}
	
	
	
	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length).
	 */
	public double magnitude () {
		
		double magnitudeSquared = 0; // Value to be added to and returned
		
		// For each value in the backing array, add its squared product to the cumulative result
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				
				magnitudeSquared += data[i][j] * data[i][j];
			}
		}

		// Return the square root of magnitudeSquared
		return Math.sqrt(magnitudeSquared);
	}
	
	
	
	/** 
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize () {
		return null;
	}
	
	
	
	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and 
	 * "1.0
	 *  2.0
	 *  3.0
	 *  4.0
	 *  5.0" for a sample column vector of length 5. 
	 *  In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		
		String result = ""; // Blank String to add to and return
		
		// For each column and for each row:
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				
				if (i + 1 < data[0].length) {
					
					result += data[i][j] + " ";
					
				} else { // If there isn't another value in the vertex, print the value without a space
					result += data[i][j];
				}
			}
			
			if(i+1 < data.length) { // If the last element of a reached, start a new line
				result += "\n";
			}
		}
		
		return result; // Return completed String
	}
	
}