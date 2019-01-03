import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

/**
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
	static PhoneRecord currRecord = null;
	
	/** Menu of options */
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
	
	/** Sort the directory in ascending alphabetical order. */
	public static void sortDirectory() {
		Collections.sort(phoneBook);
	}
	
	/** Display current record if one is selected. */
	public static void displayCurrentRecord() {
		if (currRecord == null) { // No current record is selected
			System.out.println("No current record selected");
		}
		else {
			System.out.println("Current record: " + currRecord.toString());
		}
	}
	
	/** Display all records with first name, last name, and phone number. */
	public static void displayAllRecords() {
		if (phoneBook.isEmpty()) {
			System.out.println("Directory is empty!");
		}
		else {
			for (int i = 0; i < phoneBook.size(); i++) {
				System.out.println(phoneBook.get(i).toString());
			}
			sortDirectory(); 
			displayCurrentRecord(); 
		}
	}
	
	/** Add a new record to the directory. When a new record is added, it becomes
	 *  the current record, and sorting is maintained.
	 */
	public static void addRecord() {
		System.out.print("Please enter the first name for the record: ");
		String fName = sc.next();

		System.out.print("Please enter the last name for the record: ");
		String lName = sc.next();

		System.out.print("Please enter the phone number for the record: ");
		String phoneNum = sc.next();

		PhoneRecord newRecord = new PhoneRecord(fName, lName, phoneNum);
		
		phoneBook.add(newRecord);
		sortDirectory();
		currRecord = newRecord;
		displayCurrentRecord();
	}
	
	/** Delete the current record. When a record is deleted, no current record is
	 *  selected.
	 *  @return false if directory is empty
	 *  @return true otherwise
	 */
	public static boolean deleteRecord() {
		try { // Attempt to remove the current record
			phoneBook.remove(currRecord);
			System.out.println("Deleted: " + currRecord.toString());
			currRecord = null;
			sortDirectory();
			displayCurrentRecord();
		}
		catch (NullPointerException e) { // No records in directory
			System.out.println("Directory is empty!");
			return false;
		}
		
		return true; // Successful deletion
	}
	
	/** Method to change the first name in the current record. A current record 
	 *  must be selected in order for this to happen. If no current record is 
	 *  selected, then a message is printed, telling the user to select a 
	 *  current record before the first name can be changed. Otherwise, the 
	 *  user is prompted for the new first name of the record. The record is 
	 *  then modified and sorted accordingly.
	 */
	public static void changeFirstName() {
		if (currRecord == null) {
			System.out.println("No current record; please select record first");
		}
		else {
			System.out.print("Please enter the new first name for the record: ");
			String firstName = sc.next();
			currRecord.setFirstName(firstName);
			sortDirectory();
			displayCurrentRecord();
		}
	}
	
	/** Method to change the last name in the current record. A current record 
	 *  must be selected in order for this to happen. If no current record is 
	 *  selected, then a message is printed, telling the user to select a 
	 *  current record before the last name can be changed. Otherwise, the 
	 *  user is prompted for the new last name of the record. The record is 
	 *  then modified and sorted accordingly.
	 */
	public static void changeLastName() {
		if (currRecord == null) {
			System.out.println("No current record; please select record first");
		}
		else { 
			System.out.print("Please enter the new last name for the record: ");
			String lastName = sc.next();
			currRecord.setLastName(lastName);
			sortDirectory();
			displayCurrentRecord();
		}
	}
	
	/** Method to change the phone number in the current record. A current record 
	 *  must be selected in order for this to happen. If no current record is 
	 *  selected, then a message is printed, telling the user to select a 
	 *  current record before the phone number can be changed. Otherwise, the 
	 *  user is prompted for the new phone number of the record. The record is 
	 *  then modified and sorted accordingly.
	 */
	public static void changePhoneNumber() {
		if (currRecord == null) {
			System.out.println("No current record; please select record first");
		}
		else {
			System.out.print("Please enter the new phone number for the record: ");
			String phoneNo = sc.next();
			currRecord.setPhoneNumber(phoneNo);
			sortDirectory();
			displayCurrentRecord();
		}
	}
	
	/** Select a new record to become the current record. The user is prompted for 
	 *  the first and last name of the record that they want to find to become 
	 *  the new current record. The method searches the directory for the record, 
	 *  and if there is a matching record, that record becomes the new current 
	 *  record.
	 */
	public static void selectRecord() {
		if (phoneBook.isEmpty()) {
			System.out.println("Directory is empty!");
		}
		else {
			System.out.print("Enter first name: ");
			String firstName = sc.next();
			System.out.print("Enter last name: ");
			String lastName = sc.next();
			
			boolean found = false;
			
			for (int i = 0; i < phoneBook.size(); i++) {
				if (firstName.equalsIgnoreCase(phoneBook.get(i).getFirstName()) 
					&& lastName.equalsIgnoreCase(phoneBook.get(i).getLastName())) {
					found = true;
					currRecord = phoneBook.get(i);			
				}
			}
			
			if (!found) {
				System.out.println("No matching record found!");
			}
			
			displayCurrentRecord();
		}
	}
	
	/** Terminate program. */
	public static void terminate() {
		System.exit(0);
	}
	
	/** Main method, where the user will interact with the program. */
	public static void main(String[] args) {	
		while (true) {
			menu(); // Print menu of options
			
			// Prompt user for option from the menu
			System.out.print("Please select an option from the menu: ");
			char choice = sc.next().charAt(0);
			
			// Execute the option given by the user
			switch (choice) {
			case 'a': case 'A':
				displayAllRecords();
				break;
			case 'd': case 'D':
				deleteRecord();
				break;
			case 'f': case 'F':
				changeFirstName();
				break;
			case 'l': case 'L':
				changeLastName();
				break;
			case 'n': case 'N':
				addRecord();
				break;
			case 'p': case 'P':
				changePhoneNumber();
				break;
			case 'q': case 'Q': 
				terminate();
				break;
			case 's': case 'S': 
				selectRecord();
				break;
			default:
				System.out.println("Invalid option; please try again");
			}
		}

	}

}
