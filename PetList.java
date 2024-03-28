
/*
 * This class contains all of the methods for loading, saving, and writing to the pets.txt 
 * and owners.txt files. It also allows the modification to the pets and owners array lists which
 * are used for temporary storage of the data.
 * 
 * @author Joey Sanuk CSE 301 A
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PetList
{

	private static ArrayList<Pet> pets;
	private static ArrayList<Owner> owners;

	/*
	 * This method fills the pets array list for temporary storage during the run
	 * time and reads in from the Pets.txt file.
	 */
	public static void loadPets()
	{
		// Clears the array to allow updating from the file
		if (pets == null)
		{
			pets = new ArrayList<>();
		} else
		{
			pets.clear();
		}

		// Begins to read in the pets file into the array
		File file = new File("Pets.txt");
		try
		{
			Scanner scan = new Scanner(file);

			// Scans the lines with needed information to create pet objects
			while (scan.hasNextLine())
			{
				String name = scan.nextLine();
				int ID = Integer.parseInt(scan.nextLine());
				String type = scan.nextLine();
				int yearBorn = Integer.parseInt(scan.nextLine());
				String ownerName = scan.nextLine();

				// Uses a help method to find the owner based on the name
				Owner owner = readOwnerInfo(ownerName);
				String filler = scan.nextLine();
				pets.add(new Pet(name, ID, type, yearBorn, owner));
			}
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * This method fills the pets array list for temporary storage during the run
	 * time and reads in from the Owners.txt file.
	 */
	public static void loadOwners()
	{
		// Clears the owner array to allow updating from the owner file
		if (owners == null)
		{
			owners = new ArrayList<>();
		} else
		{
			owners.clear();
		}

		try
		{
			// Begins to open and read in the owners text file
			File file = new File("Owners.txt");
			Scanner scan = new Scanner(file);

			// Begins to create owner objects for the array through the file
			while (scan.hasNextLine())
			{
				String name = scan.nextLine();
				String address = scan.nextLine();
				String phoneNumber = scan.nextLine();
				String filler = scan.nextLine();

				// Adds owner objects to the array
				Owner owner = new Owner(name, address, phoneNumber);
				owners.add(owner);
			}
			scan.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found: Owners.txt");
			e.printStackTrace();
		}
	}

	/*
	 * This method is a helper method that allows the reading in of owner data from
	 * the owner text file
	 * 
	 * @param String ownerName
	 * 
	 * @return Owner
	 */
	public static Owner readOwnerInfo(String ownerName)
	{
		try
		{
			// Opens and prepares to read in data for the array list
			File ownersFile = new File("Owners.txt");
			Scanner ownersScanner = new Scanner(ownersFile);

			// Reads in the owner data to populate the array list owners
			while (ownersScanner.hasNextLine())
			{
				String name = ownersScanner.nextLine();
				String address = ownersScanner.nextLine();
				String phoneNumber = ownersScanner.nextLine();
				String filler = ownersScanner.nextLine();

				// Checks to see if the owner already exists to determine if to add
				if (name.equals(ownerName))
				{
					ownersScanner.close();
					return new Owner(name, address, phoneNumber);
				}
			}

			ownersScanner.close();
		} catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * This method allows a pet to be added to the pet array list
	 * 
	 * @param Scanner scan
	 */
	public static void addPet(Scanner scan)
	{
		// Begins to request pet data
		System.out.println("Please enter the required information!");

		System.out.println("Name?");
		scan.nextLine();
		String name = scan.nextLine();

		// Checks to see if the id exists because id will be the primary key for
		// identifying pets
		int id;
		while (true)
		{
			System.out.println("ID?");
			id = Integer.parseInt(scan.nextLine());
			boolean idExists = false;
			for (Pet pet : pets)
			{
				if (pet.getID() == id)
				{
					idExists = true;
					break;
				}
			}
			// Allows the re entry of an id
			if (!idExists)
			{
				break;
			} else
			{
				System.out.println("ID already exists. Please enter a different ID.");
			}
		}

		System.out.println("Type?");
		String type = scan.nextLine();

		System.out.println("Year Born?");
		int yearBorn = Integer.parseInt(scan.nextLine());

		// Checks if owner is on file already
		Owner owner;
		while (true)
		{
			System.out.println("Is the owner already on file? (yes or no)");
			String response = scan.nextLine();

			// Helper methods to check for owner or add owner
			if (response.equalsIgnoreCase("yes"))
			{
				owner = searchForOwner(scan);
				break;
			} else if (response.equalsIgnoreCase("no"))
			{
				owner = addOwner(scan);
				break;
			} else
			{
				System.out.println("invalid input");
			}
		}
		// Creates new pet objects for array
		Pet newPet = new Pet(name, id, type, yearBorn, owner);
		System.out.println("New pet added!");
		pets.add(newPet);

	}

	/*
	 * This method is a helper method to search the file for an existing owner which
	 * will then allow for either finding or adding a new owner
	 * 
	 * @param Scanner scan
	 * 
	 * @return Owner
	 */
	public static Owner searchForOwner(Scanner scan)
	{
		// Begins to request owner info
		while (true)
		{
			System.out.println("Enter Owner's Name");
			String name = scan.nextLine();

			System.out.println("Enter Owner's Address");
			String address = scan.nextLine();

			System.out.println("Enter Owner's Phone Number");
			String phoneNum = scan.nextLine();

			// Runs through the array to check if entered data exists within the array
			for (Owner owner : owners)
			{
				if (owner.getName().equals(name) && owner.getAddress().equals(address)
						&& owner.getPhoneNum().equals(phoneNum))
				{
					return owner;
				}
			}
			System.out.println("Owner not found");
			// Allows for trying new info or to add a new owner
			while (true)
			{
				System.out.println("Would you like to try again or add a new owner?");
				System.out.println("1 for try again");
				System.out.println("2 for add new owner");
				String choice = scan.nextLine();
				if (choice.equals("1"))
				{
					break;
				} else if (choice.equals("2"))
				{
					return addOwner(scan);
				} else
				{
					System.out.println("Invalid input");
				}
			}
		}
	}

	/*
	 * This method adds an owner to the owners array list
	 * 
	 * @param Scanner scan
	 * 
	 * @return Owner
	 */
	public static Owner addOwner(Scanner scan)
	{
		// Requests owner info to add
		System.out.println("Enter owner's name");
		String name = scan.nextLine();
		System.out.println("Enter owner's address");
		String address = scan.nextLine();
		System.out.println("Enter owner's phone number");
		String phoneNum = scan.nextLine();

		// Creates new owner object and adds to the owners array list
		Owner newOwner = new Owner(name, address, phoneNum);
		owners.add(newOwner);
		return newOwner;
	}

	/*
	 * This method updates the pet text file on input 4 of the main method switch
	 * case. It writes the data in the array list to the file.
	 */
	public static void savePetList()
	{
		try
		{
			// Begins to open the pets text file and write in the data from the array list
			FileWriter writer = new FileWriter("Pets.txt");
			for (Pet pet : pets)
			{
				writer.write(pet.getName() + "\n" + pet.getID() + "\n" + pet.getType() + "\n" + pet.getYear() + "\n"
						+ pet.getOwner() + "\n" + "\n");
			}
			// Closes writer and prompts user that is saved correctly
			writer.close();
			System.out.println("Pet details written to file");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("An error has occured while writing pet details to file");
			e.printStackTrace();
		}
	}

	/*
	 * This method updates the pet text file on input 4 of the main method switch
	 * case. It writes the data in the array list to the file.
	 */
	public static void saveOwnerList()
	{
		try
		{
			// Opens a writer and starts writing the Owner data into the owner text file.
			FileWriter writer = new FileWriter("Owners.txt");
			for (Owner owner : owners)
			{
				writer.write(owner.getName() + "\n" + owner.getAddress() + "\n" + owner.getPhoneNum() + "\n" + "\n");
			}
			// Prompts the user that the details were saved correctly
			writer.close();
			System.out.println("Owner details written to file");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			System.out.println("An error has occured while writing owner details to file");
			e.printStackTrace();
		}
	}

	// This method prints all of the pets within the existing array list
	public static void printPets()
	{
		// Prints all of the pets with their respective information
		for (Pet pet : pets)
		{
			System.out.println("Name: " + pet.getName() + "\nID: " + pet.getID() + "\nType: " + pet.getType()
					+ "\nYear Born: " + pet.getYear() + "\nOwner: " + pet.getOwner());
		}
	}

	/*
	 * This method deletes a pet from the pet array list
	 * 
	 * @param Scanner scan
	 */
	public static void deletePet(Scanner scan)
	{
		// Requests the primary key (id) and scans through the pets array list to delete
		// the data
		System.out.println("Enter the ID of the pet you want to delete:");
		int id = Integer.parseInt(scan.next());

		boolean found = false;
		// Goes through array list
		for (int i = 0; i < pets.size(); i++)
		{
			Pet pet = pets.get(i);
			if (pet.getID() == id)
			{
				pets.remove(i);
				System.out.println("Pet with ID " + id + " has been deleted.");
				found = true;
				break;
			}
		}

		// If the id is not found it allows you to re-delete
		if (!found)
		{
			System.out.println("Pet with ID " + id + " not found.");
		}
	}

}
