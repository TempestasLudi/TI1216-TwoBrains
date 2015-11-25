package ml.vandenheuvel.TI1216.source.data;

/**
 * Credentials is a username-password pair, used to sign in to the application.
 * 
 * @author Arnoud van der Leer
 */
public class Credentials {
	/**
	 * The username.
	 */
	private String username;
	
	/**
	 * The password.
	 */
	private String password;

	/**
	 * Default constructor.
	 * 
	 * @param username the username
	 * @param password the password
	 */
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Sets the username.
	 * 
	 * @param username the username
	 */
	public void setUsername(String username){
		this.username = username;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the password
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername(){
		return this.username;
	}
	
	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword(){
		return this.password;
	}

}
