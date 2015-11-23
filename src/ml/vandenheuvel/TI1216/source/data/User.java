package ml.vandenheuvel.TI1216.source.data;

/**
 * Author Callum Holland
 * User class in OOP Project:Two Brains
 */
public class User {

	private String username;
	private String postalCode;
	private String description;
	private Grade[] gradeList;
	
	/**
	 * Constructor method for User object
	 * @param username - String representing User's username
	 * @param postalCode - String representing User's postal code
	 * @param description - String representing the description of the user
	 * @param gradelist - Array of grade objects to represent the grades for the user's courses
	 */
	public User(String username, String postalCode, String description, Grade[] gradelist){
		this.username = username;
		this.postalCode = postalCode;
		this.description = description;
		this.gradeList = gradelist;
		
	}
	
	/**
	 * Accessor method for username variable
	 * @return username - String representing User's username 
	 */
	public String getUsername()
	{
		return this.username;
	}
	
	/**
	 * Accessor method for username string
	 * @return username - String representing User's username 
	 */
	public String getPostalCode()
	{
		return this.postalCode;
	}
	
	/**
	 * Accessor method for description string
	 * @return the user's description in form of a string
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Accessor method to return the array of grades for this user
	 * @return this user's list of grades in an array
	 */
	public Grade[] getGradeList()
	{
		return this.gradeList;
	}
	
	/**
	 * Mutator method to set the user's username
	 * @param username - String to change it to
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Mutator method to set the user's postal code
	 * @param postalCode - String to change it to
	 */
	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}
	
	/**
	 * Mutator method to set the user's description
	 * @param description - String to change it to
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * Mutator method to set the user's gradeList
	 * @param gradeList - array of grades to change it to
	 */
	public void setGradeList(Grade[] gradeList)
	{
		this.gradeList = gradeList;
	}
	
	/**
	 * To check if the username is taken or not.
	 * @param other to run by the database if it is taken or not
	 * @return boolean if it is taken.
	 */
	public boolean isTaken(Object other){
		if(other instanceof User){
			User that = (User) other;
			
			return this.username.equals(that.username);
		}
		return false;
	}
	
	/**
	 * Equals method for this object (mainly for tests)
	 * @param other - Object to compare this to
	 */
	public boolean equals(Object other)
	{
		if(other instanceof User){
			User that = (User) other;
			
			return (this.username.equals(that.username) && this.postalCode.equals(that.postalCode) && this.description.equals(that.description) && this.gradeList.equals(that.gradeList));
		}
		
		return false;
	}
}
