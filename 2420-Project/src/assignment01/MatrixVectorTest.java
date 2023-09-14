package assignment01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tester for the Matrix and Vector classes
 * 
 * @author Everett Oglesby	
 * @version May 17, 2023
 */
class MatrixVectorTest {
	
	//Create Matrices to be tested	
	Matrix standardMatrix;
	double[][] standardData = {{1,2,3},{4,5,6},{7,8,9}};
	
	Matrix simpleMatrix;
	double[][] simpleData = {{1,2},{3,4}};
	
	Matrix longMatrix;
	double[][] longData = {{1},{2},{3}};
	
	//Create Vectors to be tested
	Vector simpleVector;
	double[] simpleVectorData = {1,2,3};
	
	Vector vectorMag;
	double[] vectorMagData = {0,3,4};
	
	Vector vectorLarge;
	double[] vectorLargeData = {1,2,3,4,5};

	/**
	 * Setup the matrices and vectors
	 */
	void setup() {
		standardMatrix = new Matrix(standardData);
		simpleMatrix = new Matrix(simpleData);
		longMatrix = new Matrix(longData);
		
		simpleVector = new Vector(simpleVectorData);
		vectorMag = new Vector(vectorMagData);
		vectorLarge = new Vector(vectorLargeData);
	}

	@Test
	void testMatrixCreation() {
		setup();
		Matrix identicalMatrix = new Matrix(standardData);
		
		//Loop through each row and column
		for(int i = 0; i < standardMatrix.getRows(); i++) {
			for(int j = 0; j < standardMatrix.getColumns(); j++) {
				//If the number is equal to the expect number, continue
				boolean equal = standardMatrix.equals(identicalMatrix);
				assertEquals(equal,true);
			}
		}
	}
	
	@Test
	void testEquals() {
		
		setup();
		
		Matrix identicalMatrix = new Matrix(standardData);
		
		//Loop through each row and column
		for(int i = 0; i < standardMatrix.getRows(); i++) {
			for(int j = 0; j < standardMatrix.getColumns(); j++) {
				//If the number is the the same as the corresponding number int
				//the identical matrix, continue
				boolean equal = standardMatrix.equals(identicalMatrix);
				assertEquals(equal,true);
			}
		}
	}
	
	@Test
	void testToString() {
		setup();
		
		//The string of what the given string should look like
		String expectedString = "1.0 2.0 3.0\n"
				+ "4.0 5.0 6.0\n7.0 8.0 9.0\n";
		assertEquals(standardMatrix.toString(),expectedString);
	}

	@Test
	void testAddWithBasicMatrix() {
		setup();
		
		//Create an identical matrix and add it to the current simple matrix
		Matrix identicalMatrix = new Matrix(simpleData);
		
		Matrix sumMatrix = simpleMatrix.add(identicalMatrix);
		double[][] expectedData = {{2,4},{6,8}};
		assertArrayEquals(sumMatrix.getData(),expectedData);
	}
	
	@Test
	void testAdditionWithLargerMatrix() {
		setup();
		
		//Create an identical matrix and add it to the current simple matrix
		Matrix identicalMatrix = new Matrix(standardData);
		
		Matrix sumMatrix = standardMatrix.add(identicalMatrix);
		double[][] expectedData = {{2,4,6},{8,10,12},{14,16,18}};
		assertArrayEquals(expectedData,sumMatrix.getData());
	}
	
	@Test
	void testMultiplicationWithBasicMatrix() {
		setup();
		
		Matrix identicalMatrix = new Matrix(simpleData);
		
		Matrix productMatrix = simpleMatrix.multiply(identicalMatrix);
		double[][] expectedData = {{7,10},{15,22}};
		assertArrayEquals(expectedData,productMatrix.getData());
	}
	
	@Test
	void testMultiplicationWithLargerMatrix() {
		setup();
		
		Matrix identicalMatrix = new Matrix(standardData);
		
		Matrix productMatrix = standardMatrix.multiply(identicalMatrix);
		double[][] expectedData = {{30,36,42},{66,81,96},{102,126,150}};
		assertArrayEquals(expectedData,productMatrix.getData());
	}
	
	@Test
	void testMultiplicationWithUncompatibleMatrices() {
		setup();
		
		assertThrows(IllegalArgumentException.class, () -> standardMatrix.multiply(simpleMatrix));
	}
	
	@Test
	void testMultiplicationWithUnevenMatrices() {
		setup();
		
		Matrix productMatrix = standardMatrix.multiply(longMatrix);
		double[][] expectedData = {{14},{32},{50}};
		assertArrayEquals(expectedData,productMatrix.getData());
	}
	
	@Test
	void testSimpleVectorSetup() {
		setup();
		
		for(int i = 0; i < simpleVector.getRows(); i++) {
			assertEquals(simpleVector.getData()[i][0],i+1);
		}
	}
	
	@Test
	void testVectorMagnitudeWithEvenNumber() {
		setup();
		
		double vectorMagnitude = vectorMag.magnitude();
		assertEquals(5.0,vectorMagnitude);
	}
	
	@Test
	void  testVectorMagnitudeWithUnevenNumber() {
		setup();
		
		double vectorMagnitude = simpleVector.magnitude();
		double expectedMagnitude = Math.sqrt((1*1)+(2*2)+(3*3));
		assertEquals(expectedMagnitude,vectorMagnitude);
	}
	
	@Test
	void testDotProductWithSimpleVector() {
		setup();
		
		double dotProduct = simpleVector.dotProduct(simpleVector);
		assertEquals(14,dotProduct);
	}
	
	@Test
	void testDotProductWithSeperateVectors() {
		setup();
		
		double dotProduct = simpleVector.dotProduct(vectorMag);
		assertEquals(18,dotProduct);		
	}
	
	@Test
	void testDotProductOnDifferentVectorSizes() {
		setup();
		
		assertThrows(IllegalArgumentException.class, () -> simpleVector.dotProduct(vectorLarge));
	}
}
