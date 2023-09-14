package assignment02;

/**
 * This class represents a UHealth patient who has a unique UHealthID
 * and a first and last name.
 * 
 * @author Everett Oglesby and Parker Catten
 * @version CS-2420_020 FA-2023
 */
public class Patient {
	
	private String firstName;
	private String lastName;
	private UHealthID uHealthID;
	
	/**
	 * Creates a patient with a given name and ID.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param UHealthID
	 */
	public Patient(String firstName, String lastName, UHealthID uHealthID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.uHealthID = uHealthID;
	}
	
	/**
	 * Getter method for the first name field of this patient object.
	 * 
	 * @return this patient's first name
	 */
	public String getFirstName() {
		return this.firstName;
	}


	/**
	 * Getter method for the last name field of this patient object.
	 * 
	 * @return this patient's last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Getter method for the physician field of this patient object.
	 * 
	 * @return this patient's physician
	 */
	public UHealthID getUHealthID() {
		return this.uHealthID;
	}
	
	/**
	 * Two patients are considered equal if they have the same UHealthID.
	 * 
	 * @param other - the object being compared with this patient
	 * @return true if the other object is a Patient type and is equal 
	 * 			to this patient, false otherwise.
	 */
	public boolean equals(Object other) {
		//Cast the other object to a patient
		Patient otherPatient = (Patient) other;
		
		//If the uHealthIDs are equal return true
		if(uHealthID.equals(otherPatient.getUHealthID())) {
			return true;
		}
		//If not equal return false
		return false;
	}
	
	/**
	 * Returns a textual representation of this patient.
	 */
	public String toString() {
		return this.firstName + " " + this.lastName + " (" + this.uHealthID + ")";
	}
}
