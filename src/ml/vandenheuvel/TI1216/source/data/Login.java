package ml.vandenheuvel.TI1216.source.data;

/**
 * Class "login".
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft 2015-2016. 
 */
public class Login {
	
	/**
	 * Class-instances/variables.
	 */
	private String username;
	private String password;
	public boolean signUp;
	
	
	// BEGIN CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public Login(){}
	
	/**
	 * General constructor. All instances given.
	 * @param username
	 * @param password
	 * @param alreadyExists
	 */
	public Login(String username, String password, boolean signUp){
		this.username = username; this.password = password; 
		this.signUp = signUp;
		}
	
}
