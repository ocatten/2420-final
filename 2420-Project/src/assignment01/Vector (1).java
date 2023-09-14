package assignment01;

/**
 * This class represents a vector of numbers.
 * Our column Vector can be considered a more specific kind of Matrix, 
 * so this is a subclass of Matrix. It has a length that is the number 
 * of columns.
 * 
 * @author Eric Heisler & Everett Oglesby
 * @version May 1, 2023
 */
public class Vector extends Matrix{
	
	// array to hold the numbers of the vector
	// Note that this is a duplicate of the data held by the superclass.
	// In practice, it is better to store only one copy, but for
	// simplicity in this assignment, we can keep a duplicate.
	private double[] data;
	// the number of columns in the vector
	private int size;
	
	/**
	 * Creates a new vector by copying in an array of numbers.
	 * The number of columns in the vector matches the length 
	 * of the array.
	 * 
	 * @param data - an array holding the numbers of the vector
	 * @throws IllegalArgumentException if the length of the input
	 * 			array is zero.
	 */
	public Vector(double[] data) {
		// Call the super constructor in Matrix
		super(vectorToColumnMatrix(data));
		
		// The checks for valid size were done in the super constructor.
		size = data.length;
		// Create the array and copy data.
		this.data = new double[size];
		for (int i = 0; i < size; i++) {
			this.data[i] = data[i];
		}
	}
	
	/**
	 * Returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector in the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 * 			the same size.
	 */	
	public double dotProduct(Vector other) {
		
		//If the sizes of the two vectors are not the same, throw IllegalArgumentException
		if(size != other.size) {
			throw new IllegalArgumentException("The given and current vector"
					+ "are not the same size!");
		}
		
		//Create a product double
		double product = 0;
		//Add the product of the data from both of the vectors at the current index
		//to the product variable
		for(int i = 0; i < size; i++) {
			product += data[i] * other.data[i];
		}
		return product;
	}
	
	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length).
	 */
	public double magnitude() {
		//Create an empty double to store the values
		double values = 0;
		
		//For each value in data, add its squared product to the values variable
		for(double value: data) {
			values += value * value;
		}

		//Square root the values added
		double valuesSqrt = Math.sqrt(values);
		return valuesSqrt;
	}
	
	/**
	 * Converts a 1D array of vector data to a 2D array for a 1-column matrix.
	 * This is private because it is only intended for use in the constructor.
	 * It is static because it is not acting on an instance of Vector.
	 * 
	 * @param data - the data array
	 */
	private static double[][] vectorToColumnMatrix(double[] data){
		double[][] matData = new double[data.length][1];
		for (int i = 0; i < data.length; i++) {
			matData[i][0] = data[i];
		}
		return matData;
	}
	
	/**
	 * @return int - size
	 */
	public int getSize() {
		return size;
	}
}
