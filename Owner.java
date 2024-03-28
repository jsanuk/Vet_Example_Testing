/*
 * This class contains the methods that allow for the creation and returning of data and owner class objects
 * 
 * @author Joey Sanuk CSE 301 A
 */
public class Owner
{

	private String name;
	private String address;
	private String phoneNum;

	/*
	 * This constructor creates a pet object and initializes the name, id, type,
	 * yearBorn, and owner data elements
	 * 
	 * @param String newName, String newAddress, String newPhoneNum
	 */
	public Owner(String newName, String newAddress, String newPhoneNum)
	{
		name = newName;
		address = newAddress;
		phoneNum = newPhoneNum;
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
	 * This method returns the address data element
	 * 
	 * @return String address
	 */
	public String getAddress()
	{
		return address;
	}

	/*
	 * This method returns the phoneNum
	 * 
	 * @return String phoneNum
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}
}
