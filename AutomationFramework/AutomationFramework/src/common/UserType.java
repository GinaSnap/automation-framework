package common;

public class UserType {
	
	String username;
	String password;
	String firstName;
	String lastName;
	String email;
	String zipCode;
	
	public UserType(String username, String password, String firstName, String lastName, String email, String zipCode)
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.zipCode = zipCode;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getZipCode()
	{
		return zipCode;
	}

}
