package assign01;

import static org.junit.jupiter.api.Assertions.*; 
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author Prof. Parker & Parker Catten
 * @version August 24, 2023
 */
public class MathVectorTester {
	
	private MathVector rowVec, rowVecTranspose, unitVec, sumVec, colVec, smallVector, largeVector;
	
	
	public void setup() {
		
		smallVector = new MathVector(new double[][] {{5}, {4}, {3}, {2}, {1}});
		largeVector = new MathVector(new double[1][1000]);
		
		for(int i = 0; i < 1000; i++) {
			largeVector.getData()[0][i] = i;
		}
		
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});		
	}
	
	@Test
	public void smallRowVectorEquality() {
		setup();
		
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	public void smallRowVectorInequality() {
		setup();
		
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void createVectorFromBadArray() {
		setup();
		
		double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	public void transposeSmallRowVector() {
		setup();
		
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}
	
	@Test
	public void addRowAndColVectors() {
	  setup();
		
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	public void addSmallRowVectors() {
		setup();
		
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));		
	}

	@Test
	public void dotProductSmallRowVectors() {
		setup();
		
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);		
	}
	
	@Test
	public void smallRowVectorLength() {
		setup();
		
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	
	@Test
	public void smallRowVectorNormalize() {
		setup();
		
		//MathVector normalVec = rowVec.normalize();
		//double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		//assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));		
	}
	
	@Test
	public void smallColVectorToString() {
		setup();
		
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());		
	}
	

/*===================================================================== EQUALS ==============================================================================*/
	
	@Test
	public void emptyEqualsTest () {
		setup();
		
		MathVector empVec = null;
		MathVector othrVec = null;
		
		assertEquals(empVec, othrVec);
	}
	
	
	
	@Test
	public void smallEqualsTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[][] {{5}, {4}, {3}, {2}, {1}});
		MathVector badVector = new MathVector(new double[][] {{7}, {4}, {3}, {2}, {1}});
		
		assertTrue(smallVector.equals(newVector));
		assertFalse(smallVector.equals(badVector));
		assertFalse(newVector.equals(badVector));
	}

	
	
	@Test
	public void largeEqualsTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[1][1000]);
		
		for(int i = 0; i < 1000; i++) {
			newVector.getData()[0][i] = i;
		}
		
		assertTrue(largeVector.equals(newVector));
		
		
		MathVector badVector = new MathVector(new double[1][1000]);
		
		for(int i = 0; i < 1000-1; i++) {
			badVector.getData()[0][i] = i;
		}
		
		assertFalse(largeVector.equals(badVector));
	}
	
	
/*=================================================================== TRANSPOSE =============================================================================*/
	
	@Test
	public void emptyTransposeTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[1][1]);
		MathVector transposed = newVector.transpose();
		
		assertTrue(transposed.equals(newVector));
	}
	
	
	
	@Test
	public void smallTransposeTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[][] {{5, 4, 3, 2, 1}});
		
		assertEquals(newVector, smallVector.transpose());
		assertEquals(smallVector, newVector.transpose());
	}
	
	
	
	@Test
	public void largeTransposeTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[1000][1]);
		
		for(int i = 0; i < 1000; i++) {
			newVector.getData()[i][0] = i;
		}
		
		assertEquals(newVector, largeVector.transpose());
		assertEquals(largeVector, newVector.transpose());
	}
	
	
/*================================================================== DOT_PRODUCT ============================================================================*/
	
	@Test
	public void emptyDotProductTest () {
		setup();
		
		assertEquals(0, new MathVector(new double[1][1]).dotProduct(new MathVector(new double[1][1])));
	}
	
	
	
	@Test
	public void smallDotProductTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[][] {{7}, {4}, {3}, {2}, {1}});
		assertEquals(65, newVector.dotProduct(smallVector));
		
		try {
			
			newVector.dotProduct(largeVector);
			assertTrue(false);
			
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void largeDotProductTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[1][1000]);
		
		for(int i = 999; i >= 0; i--) {
			newVector.getData()[0][i] = i;
		}
		
		assertEquals(3.328335E8, largeVector.dotProduct(newVector));
		
		
		MathVector badVector = new MathVector(new double[1000][1]);
		for(int i = 0; i < 1000; i++) {
			badVector.getData()[i][0] = i;
		}
		
		try{
			
			assertEquals(largeVector.dotProduct(badVector), 0);
			assertTrue(false);
			
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	
/*=================================================================== MAGNITUDE =============================================================================*/
	
	@Test
	public void emptyMagnitudeTest () {
		setup();
		
		assertEquals(0, new MathVector(new double[1][1]).magnitude());
	}
	
	
	
	@Test
	public void smallMagnitudeTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[][] {{5, 4, 3, 2, 1}});
		
		assertEquals(7.416198487095663, smallVector.magnitude());
		assertEquals(7.416198487095663, newVector.magnitude());
		assertEquals(smallVector.magnitude(), newVector.magnitude());
	}

	
	
	@Test
	public void largeMagnitudeTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[1000][1]);
		for(int i = 0; i < 1000; i++) {
			newVector.getData()[i][0] = i;
		}
		
		assertEquals(18243.72494859534, largeVector.magnitude());
		assertEquals(18243.72494859534, newVector.magnitude());
		assertEquals(largeVector.magnitude(), newVector.magnitude());
	}
	
	
/*=================================================================== NORMALIZE =============================================================================*/
	
	@Test
	public void emptyNormalizeTest () {
		setup();
	}
	
	
	
	@Test
	public void smallNormalizeTest () {
		setup();
	}
	
	
	
	@Test
	public void largeNormalizeTest () {
		setup();
	}
	
	
/*=================================================================== TO_STRING =============================================================================*/
	
	@Test
	public void emptyToStringTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[1][1]);
		assertEquals(newVector.toString(), "0.0");
	}
	
	
	
	@Test
	public void smallToStringTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[][] {{5, 4, 3, 2, 1}});
		assertEquals(newVector.toString(), "5.0 4.0 3.0 2.0 1.0 ");
		assertEquals(smallVector.toString(),  "5.0\n4.0\n3.0\n2.0\n1.0");
		assertEquals(newVector.toString(), smallVector.transpose().toString());
	}
	
	
	
	@Test
	public void largeStringTest () {
		setup();
		
		MathVector newVector = new MathVector(new double[1000][1]);
		for(int i = 0; i < 1000; i++) {
			newVector.getData()[i][0] = i;
		}
		
		String newVectorToString = "";
		for(int i = 0; i < 999; i++) {	
			newVectorToString += i + ".0\n";
		}
		newVectorToString += "999.0";
		
		assertEquals(newVector.toString(), newVectorToString);
		
		
		String transposedVector = "";
		for(int i = 0; i < 999; i++) {	
			transposedVector += i + ".0 ";
		}
		transposedVector += "999.0 ";
		
		assertEquals(newVector.transpose().toString(), transposedVector);
	
	}
	
}