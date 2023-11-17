package assign09;

import java.text.DecimalFormat;

/**
 * This class provides a simple representation for a University of Utah student.
 * Object's hashCode method is overridden with a correct hash function for this
 * object. This hash method does a good job of distributing students in a hash
 * table.
 * 
 * @author Prof. Parker, Everett Oglesby and Parker Catten
 * @version November 13, 2023
 */
public class StudentGoodHash {

	private int uid;
	private String firstName;
	private String lastName;

	/**
	 * Creates a new student with the specified uid, firstName, and lastName.
	 * 
	 * @param uid
	 * @param firstName
	 * @param lastName
	 */
	public StudentGoodHash(int uid, String firstName, String lastName) {
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return the UID for this student object
	 */
	public int getUid() {
		return this.uid;
	}

	/**
	 * @return the first name for this student object
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
 
	/**
	 * @return the last name for this student object
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * @return true if this student and 'other' have the same UID, first name, and last name; false otherwise
	 */
	public boolean equals(Object other) {
		if(!(other instanceof StudentGoodHash))
			return false;

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

	/*
	 * Creates a hashcode to return to the Hash Table to determine where to put
	 * the current item in the Hash Table.
	 */
	public int hashCode() {
		int code = 0;
		//First set the code equal to the student's UID.
		code = uid;
		
		//For the first name, add the value of the code by each characters ASCII value
		//to the position it is in the String.
		for(int i = 0; i < firstName.length();i++) {
			code += Math.pow(Character.getNumericValue( firstName.charAt(i) ),i+1);
		}
		
		//For the last name, multiple the value of the code by each characters position
		//in the String.
		for(int i = 0; i < lastName.length();i++) {
			code += Math.pow(Character.getNumericValue( lastName.charAt(i) ),i+1);
		}
		
		//Return the created hash code
		return code;
	}
}