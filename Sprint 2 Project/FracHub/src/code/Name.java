package code;

/**
 * Contains the names of members and can be used to return the name in different formats.
 */

public class Name 
{	
	// Instance variables.
	private String firstName;
	private String middleName;
	private String surname;
	
	
	// Default constructor.
	public Name()
	{
		firstName = "";
		middleName = "";
		surname = "";
	}
	
	// Parameterised constructor with full name in one string.
	public Name(String name)
	{
		int first = name.indexOf(' ');
		int second = name.indexOf(' ', first + 1);
		
		if (second > 0)
		{
			firstName = name.substring(0,first);
			middleName = name.substring(first + 1,second);
			surname = name.substring(second + 1,name.length());
		}
		else
		{
			firstName = name.substring(0,first);
			middleName = "";
			surname =  name.substring(first + 1,name.length());
		}
	}
	
	// Parameterised constructor with first and last name in two variables.
	public Name(String fname, String sname)
	{
		firstName = fname;
		middleName = "";
		surname = sname;
	}
	
	// Parameterised constructor with full name in three variables.
	public Name(String fname, String mname, String sname)
	{
		firstName = fname;
		middleName = mname;
		surname = sname;
	}
	
	// Return full name in format first_name middle_name surname.
	public String getFullName()
	{
		String first = getFirstName();
		String middle = getMiddleName();
		String last = getSurname();
		String fullName;
		
		if ((middle == "") || (middle.isEmpty()))
		{
			fullName = first + " " + last;
			
		}
		else
		{
			fullName = first + " " + middle + " " + last;
		}
		
		return fullName;
	}
	
	// Return name in format surname, first name.
	public String getLastCommaFirst()
	{
		String first = getFirstName();
		String last = getSurname();
		String name = last + "," + first;
		
		return name;
	}
	
	// Return name in format first_name surname.
	public String getFirstAndLastName()
	{
		String first = getFirstName();
		String last = getSurname();
		String name = first + " " + last;
		
		return name;
	}
	
	// Get value of first name.
	public String getFirstName() {
		return firstName;
	}
	
	// Set value of first name.
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// Get value of middle name.
	public String getMiddleName() {
		return middleName;
	}
	
	// Set value of middle name.
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	// Get value of surname.
	public String getSurname() {
		return surname;
	}
	
	// Set value of surname.
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}