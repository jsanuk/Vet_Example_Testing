/*
 * This class contains the methods that allow for the creation and returning of data and pet class objects
 * 
 * @author Joey Sanuk CSE 301 A
 */
public class Pet
{

	private int id;
	private String name;
	private String type;
	private int yearBorn;
	private Owner owner;

	/*
	 * This constructor creates a pet object and initializes the name, id, type,
	 * yearBorn, and owner data elements
	 * 
	 * @param String newName, int newID, String newType, int year, Owner own
	 */
	public Pet(String newName, int newID, String newType, int year, Owner own)
	{
		name = newName;
		id = newID;
		type = newType;
		yearBorn = year;
		owner = own;
	}

	/*
	 * This method returns the id data element
	 * 
	 * @return int id
	 */
	public int getID()
	{
		return id;
	}

	/*
	 * This method returns the name data element
	 * 
	 * @return String name
	 */
	public String getName()
	{
		return name;
	}

	/*
	 * This method returns the type data element
	 * 
	 * @return String type
	 */
	public String getType()
	{
		return type;
	}

	/*
	 * This method returns the yearBorn data element
	 * 
	 * @return int yearBorn
	 */
	public int getYear()
	{
		return yearBorn;
	}

	/*
	 * This method returns the owners name data element that is accessed through the
	 * owner object
	 * 
	 * @return String owner.getName()
	 */
	public String getOwner()
	{
		return owner.getName();
	}

	/*
	 * This method will print the pet object that is set as the parameter for the
	 * method call
	 * 
	 * @param Pet pet
	 */
	public void printPet(Pet pet)
	{
		System.out.println(
				"Name: " + name + "\nID: " + id + "\nType: " + type + "\nYear Born: " + yearBorn + "\nOwner: " + owner);
	}
}
