
public class PhoneRecord implements Comparable<PhoneRecord> {

	private String fName; // First name in record
	private String lName; // Last name in record
	private String phoneNum; // Phone number in record
	
	// Create new record with first name, last name, and phone number
	public PhoneRecord(String fName, String lName, String phoneNum) {
		this.fName = fName;
		this.lName = lName;
		this.phoneNum = phoneNum;
	}
	
	// Return first name in record
	public String getFirstName() {
		return fName;
	}
	
	// Return last name in record
	public String getLastName() {
		return lName;
	}
	
	// Return phone number in record
	public String getPhoneNumber() {
		return phoneNum;
	}

	// Change the first name in the record
	public void setFirstName(String fName) {
		this.fName = fName;
	}
	
	// Change the last name in the record
	public void setLastName(String lName) {
		this.lName = lName;
	}
	
	// Change the phone number in the record
	public void setPhoneNumber(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	// Print record as string, with first name, last name, and phone number
	public String toString() {
		return fName + " " + lName + " " + phoneNum;
	}
	
	// Comparable Object to maintain sorting in data structure
	public int compareTo(PhoneRecord o) {
		if (lName == o.lName) { // Last names are equal
			if (fName == o.fName) { // First names are equal
				return phoneNum.compareTo(o.phoneNum); // Sort by phone number
			}
			return fName.compareTo(o.fName); // Sort by first name
		}
		return lName.compareTo(o.lName); // Sort by last name
	}
}
