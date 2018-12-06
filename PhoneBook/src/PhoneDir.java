import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/*
 * This program simulates a phone directory to store first names, last names, 
 * and phone numbers. It allows the user to add, delete, and change records
 * within the directory. A user can change a first name, a last name, or a 
 * phone number. The directory maintains sorting the records in alphabetical 
 * order by last name. If last names are the same, then it is sorted by first 
 * name. If both last names and first names are the same, then the directory 
 * is sorted by phone number. Each time an addition or modification is done, 
 * the directory is re-sorted.
 * 
 * Initially, no current record is selected. When a user adds a new entry to 
 * the directory, the new record becomes the current record. With the current 
 * record, the user can change the first name, last name, or phone number. If 
 * a record is deleted, no current record is selected, and the user must select
 * a new record to become the current record. The program runs until the user 
 * presses 'q', in which case, the program terminates. If the user enters a 
 * command not in the menu, he or she will be prompted for a new command.
 */

public class PhoneDir {
	// PhoneBook where all entries will be contained
	static Vector<PhoneRecord> phoneBook = new Vector<PhoneRecord>();
	static Scanner sc = new Scanner(System.in); // Scanner to read user input
	static PhoneRecord currRecord = null; // Current record is null
	
	// Menu of options
	public static void menu() {
		System.out.println("a - Show all records\n"
				+ "d - Delete the current record\n"
				+ "f - Change the first name in the current record\n"
				+ "l - Change the last name in the current record\n"
				+ "n - Add a new record\n"
				+ "p - Change the phone number in the current record\n"
				+ "q - Quit\n"
				+ "s - Select a record from the record list to become the current"
				+ " record");
	}
	
	// Sort the directory accordingly
	public static void sortDirectory() {
		Collections.sort(phoneBook);
	}
	
	// Display current record
	public static void displayCurrentRecord() {
		if (currRecord == null) { // No current record is selected
			System.out.println("No current record selected");
		}
		else { // Display current record with first and last name and phone number
			System.out.println("Current record: " + currRecord.toString());
		}
	}
	
	// Display all records with first name, last name, and phone number
	public static void displayAllRecords() {
		if (phoneBook.isEmpty()) { // Directory is empty
			System.out.println("Directory is empty!");
		}
		else {
			for (int i = 0; i < phoneBook.size(); i++) {
				System.out.println(phoneBook.get(i).toString());
			}
			sortDirectory(); // Maintain sorting
			displayCurrentRecord(); // Display current record
		}
	}
	
	// Add a new record to the directory
	public static void addRecord() {
		// Prompt user for first name
		System.out.print("Please enter the first name for the record: ");
		String fName = sc.next();
		
		// Prompt user for last name
		System.out.print("Please enter the last name for the record: ");
		String lName = sc.next();
		
		// Prompt user for phone number
		System.out.print("Please enter the phone number for the record: ");
		String phoneNum = sc.next();
		
		// Create new record
		PhoneRecord newRecord = new PhoneRecord(fName, lName, phoneNum);
		
		phoneBook.add(newRecord); // Add record to directory
		sortDirectory(); // Maintain sorting
		currRecord = newRecord; // Set the current record to the new record
		displayCurrentRecord(); // Display current record
	}
	
	// Method to delete the current record
	public static boolean deleteRecord() {
		try {
			phoneBook.remove(currRecord); // Delete the current record
			System.out.println("Deleted: " + currRecord.toString());
			currRecord = null; // Set current record to null
			sortDirectory(); // Maintain sorting
			displayCurrentRecord();
		}
		catch (NullPointerException e) { // No records in directory
			System.out.println("Directory is empty!");
			return false;
		}
		
		return true; // Successful deletion
	}
	
	// Method to change the first name of the current record
	public static void changeFirstName() {
		if (currRecord == null) { // No current record is selected
			System.out.println("No current record; please select record first");
		}
		else { // Current record is selected; prompt user for new first name
			System.out.print("Please enter the new first name for the record: ");
			String firstName = sc.next(); // Read first name
			currRecord.setFirstName(firstName); // Set first name in current record
			sortDirectory(); // Maintain sorting
			displayCurrentRecord();
		}
	}
	
	// Method to change the last name of the current record
	public static void changeLastName() {
		if (currRecord == null) { // No current record is selected
			System.out.println("No current record; please select record first");
		}
		else { // Current record is selected; prompt user for new last name
			System.out.print("Please enter the new last name for the record: ");
			String lastName = sc.next(); // Read last name
			currRecord.setLastName(lastName); // Set last name in current record
			sortDirectory(); // Maintain sorting
			displayCurrentRecord();
		}
	}
	
	// Method to change the phone number of the current record
	public static void changePhoneNumber() {
		if (currRecord == null) { // No current record is selected
			System.out.println("No current record; please select record first");
		}
		else { // Current record is selected; prompt user for new phone number
			System.out.print("Please enter the new phone number for the record: ");
			String phoneNo = sc.next(); // Read phone number
			currRecord.setPhoneNumber(phoneNo); // Set phone number in current record
			sortDirectory(); // Maintain sorting
			displayCurrentRecord();
		}
	}
	
	// Select a new record to become the current record
	public static void selectRecord() {
		if (phoneBook.isEmpty()) { // Directory is empty
			System.out.println("Directory is empty!");
		}
		else { // Prompt user for first name and last name
			System.out.print("Enter first name: ");
			String firstName = sc.next();
			System.out.print("Enter last name: ");
			String lastName = sc.next();
			
			boolean found = false; // Initialize found to false
			
			// Iterate through the directory for the first name and last name
			for (int i = 0; i < phoneBook.size(); i++) {
				if (firstName.equalsIgnoreCase(phoneBook.get(i).getFirstName()) 
					&& lastName.equalsIgnoreCase(phoneBook.get(i).getLastName())) {
					found = true; // Record is found in directory
					currRecord = phoneBook.get(i); // Set current record				
				}
			}
			
			if (!found) { // User-given record not found in directory
				System.out.println("No matching record found!");
			}
			
			displayCurrentRecord();
		}
	}
	
	// Terminate program
	public static void terminate() {
		System.exit(0);
	}
	
	// Main method, where the user will interact with the program
	public static void main(String[] args) {	
		while (true) {
			menu(); // Print menu of options
			
			// Prompt user for option from the menu
			System.out.print("Please select an option from the menu: ");
			char choice = sc.next().charAt(0); // Read option
			
			// Execute the option given by the user
			switch (choice) {
			case 'a': case 'A': // Display all records in directory
				displayAllRecords();
				break;
			case 'd': case 'D': // Delete the current record
				deleteRecord();
				break;
			case 'f': case 'F': // Change the first name in the current record
				changeFirstName();
				break;
			case 'l': case 'L': // Change the last name in the current record
				changeLastName();
				break;
			case 'n': case 'N': // Add a new record to the directory
				addRecord();
				break;
			case 'p': case 'P': // Change the phone number in the current record
				changePhoneNumber();
				break;
			case 'q': case 'Q': // Terminate program
				terminate();
				break;
			case 's': case 'S': // Select new record to become current record
				selectRecord();
				break;
			default: // User entered option not in menu; prompt for re-entry
				System.out.println("Invalid option; please try again");
			}
		}

	}

}
