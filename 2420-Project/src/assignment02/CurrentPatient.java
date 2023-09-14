package assignment02;

import java.util.GregorianCalendar;

/**
 * This class represents a current patient that extends the patient class and has a physician and date of last visit.
 * 
 * @author Everett Oglesby and Parker Catten
 * @version CS-2420_020 FA-2023
 */
public class CurrentPatient extends Patient {
	
	// Fields
	private String firstName;
	private String lastName;
	private int physician; 
	private GregorianCalendar lastVisit;
	
	
	/**
	 * @Constructor that creates the current patient with their name, physician and date of last visit.
	 * 
	 * @param firstName: First name of patient
	 * @param lastName: Last name of patient
	 * @param UHealthID: Specific ID code for each patient represented by unique object
	 * @param physician: Physician's number to track doctor
	 * @param lastVisit: Date of last visit tracked by a GregorianCalendar object
	 */
	public CurrentPatient (String firstName, String lastName, UHealthID uHealthID, int physician, GregorianCalendar lastVisit) {
		
		// Call the parent class's constructor for relevant fields
		super(firstName, lastName, uHealthID);
		
		this.physician = physician;
		this.lastVisit = lastVisit;
	}
	
	 
	
	/**
	 * @return physician integer
	 */
	public int getPhysician () { return physician; }
	
	
	
	/**
	 * @return lastVisit GregorianCalendar object
	 */
	public GregorianCalendar getLastVisit () { return lastVisit; }
	
	
	
	/**
	 * Update the physician to the given physician
	 * 
	 * @param newPhysician: new physician code
	 */
	public void updatePhysician (int newPhysician) {
		physician = newPhysician;
	}
	
	
	
	/**
	 * Update the last visit to the given date
	 * 
	 * @param date: new GregorianCalendar object
	 */
	public void updateLastVisit(GregorianCalendar date) {
		lastVisit = date;
	}
	
}