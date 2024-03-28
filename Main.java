import java.util.ArrayList;
import java.util.Scanner;

/*
 * This main method allows for the printing and execution of the user options represented in the switch case
 * 
 * @author Joey Sanuk CSE 301 A
 */
public class Main
{

	/*
	 * Starting information in files: 
	 * Pets.txt: 
	 * Bug 
	 * 1 
	 * Dog 
	 * 2023 
	 * Olivia
	 * 
	 * Owners.txt: 
	 * Olivia 
	 * 123 Main 
	 * 1234567890
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		// Creates the two array lists that will be responsible for holding temporary
		// data
		ArrayList<Pet> pets = new ArrayList<>();
		ArrayList<Owner> owners = new ArrayList<>();

		Scanner scan = new Scanner(System.in);

		// Reads the files into the array lists which keeps current run data
		PetList.loadPets();
		PetList.loadOwners();

		boolean active = true;

		// Prints the menu for the different user options
		while (active)
		{
			System.out.println("Pet Client Options");
			System.out.println("1. View Pets");
			System.out.println("2. Add a Pet");
			System.out.println("3. Delete a Pet");
			System.out.println("4. Save Changes and Exit");
			System.out.println("5. Exit Without Saving");

			// Reads in the input int
			int option = scan.nextInt();

			// Switch case that allows the different options to run
			// Calls the respective methods in the PetList class
			switch (option)
			{
			case 1:
				// Prints the list of pets
				PetList.printPets();
				break;
			case 2:
				// Adds a pet
				PetList.addPet(scan);
				break;
			case 3:
				// Deletes a pet
				PetList.deletePet(scan);
				break;
			case 4:
				// Writes the array info to files which "saves changes"
				System.out.println("Saving changes and quitting");
				PetList.savePetList();
				PetList.saveOwnerList();
				active = false;
				scan.close();
				break;
			case 5:
				// Does not write info which will allow the array to revert to its prior state
				System.out.println("Exiting program wihtout saving");
				active = false;
				scan.close();
				break;
			default:
				System.out.println("Invalid Input!");
				break;
			}
		}
	}

}
