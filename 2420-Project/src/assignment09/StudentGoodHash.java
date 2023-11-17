package assignment09;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object, but one that does a good job of distributing students in a hash
 * table.
 * 
 * @author Everett Oglesby and Parker Catten
 * @version 07/09/2023
 */

public class StudentGoodHash {

	// Fields
	private int uid;
	private String firstName;
	private String lastName;
	
	
	/**
	 * @Constructor for a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid: ID number for student
	 * @param firstName: First name of student
	 * @param lastName: Last name of student
	 */
	public StudentGoodHash(int uid, String firstName, String lastName) {
		
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	

	/**
	 * @return the UID for this student object
	 */
	public int getUid() { return this.uid; }

	
	
	/**
	 * @return the first name for this student object
	 */
	public String getFirstName() { return this.firstName; }
 
	
	
	/**
	 * @return the last name for this student object
	 */
	public String getLastName() { return this.lastName; }

	
	
	/**
	 * @return true if this student and 'other' have the same UID, first name, and last name; false otherwise
	 */
	public boolean equals(Object other) {

		if (!(other instanceof StudentGoodHash)) {
			return false;
		}

		StudentGoodHash rhs = (StudentGoodHash) other;

		return this.uid == rhs.uid && this.firstName.equals(rhs.firstName) && this.lastName.equals(rhs.lastName);
	}
	
	
	
	/**
	 * @return a textual representation of this student
	 */
	public String toString() {
		
		DecimalFormat formatter = new DecimalFormat("0000000");
		return firstName + " " + lastName + " (u" + formatter.format(uid) + ")";
	}
	
	
	
	/**
	 * This method will give unique hashCodes for objects that will reduce the number of collisions a great deal.
	 * 
	 * @return: The combination of each letter in ASCII value, raised to the power of its position in the String,
	 * 			multiplied by the uid
	 */
	public int hashCode() {
		
		int code = 0;
		//First set the code equal to the student's UID.
		code = uid;
		
		//For the first name, add the value of the code by each characters ASCII value
		//to the position it is in the String.
		for(int i = 0; i < firstName.length();i++) {
			code += Math.pow(Character.getNumericValue( firstName.charAt(i) ),i);
		}
		
		//For the last name, multiple the value of the code by each characters position
		//in the String.
		for(int i = 0; i < lastName.length();i++) {
			code += Math.pow(Character.getNumericValue( lastName.charAt(i) ),i);
		}
		
		//Return the created hash code
		return code;
	}
}