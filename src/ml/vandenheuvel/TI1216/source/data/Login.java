package ml.vandenheuvel.TI1216.source.data;

import java.io.Serializable;

/**
 * Class "login".
 * 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft
 *         2015-2016.
 */
public class Login implements Serializable {
	private static final long serialVersionUID = 8860938388939089155L;
	/**
	 * Class-instances/variables. signUp is true when signing up, false when
	 * logging in.
	 */
	private String username;
	private String password;
	private boolean signUp;

	// BEGIN CONSTRUCTORS

	public Login() {
		// The default contructor
	}

	/**
	 * General constructor. All instances given.
	 * 
	 * @param username The username
	 * @param password The password
	 * @param singUp Is true when signing up a new account, false if logging in.
	 */
	public Login(String username, String password, boolean signUp) {
		this.username = username;
		this.password = password;
		this.signUp = signUp;
	}

	/**
	 * Get method of the signUp boolean
	 * 
	 * @return True if the person wants to sign up, false if just logging in.
	 */
	public boolean getSignUp() {
		return this.signUp;
	}

	/**
	 * Set method of the signUp boolean
	 * 
	 * @param bool The wanted value
	 */
	public void setSignUp(Boolean bool) {
		this.signUp = bool;
	}

	/**
	 * Get method for the username string
	 * 
	 * @return The username of the login object
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Get method for the password string
	 * 
	 * @return The password of the login object
	 */
	public String getPassword() {
		return this.password;
	}

}
