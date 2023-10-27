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

	public static void main(String[] args) {
		
		//Money added per month
		double moneyAdded = 100;
		
		//Yearly interest
		double interest = 8;
		
		//Time in years
		double time = 25;
		
		double currentMoney = 0;
		
		//Run for the each month during the 25 years or 12*25
		for(int i = 0;i < (12*time);i++) {
			
			currentMoney *= (1+(.08/12)); //Calculate and add the interest to the total
			
			currentMoney += moneyAdded;//Add the money each money to the total
		}
		
		System.out.println("After 25 years you will have " + currentMoney + " saved up!");
	}
}
