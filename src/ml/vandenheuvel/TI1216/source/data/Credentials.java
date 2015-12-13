package ml.vandenheuvel.TI1216.source.data;

import org.json.JSONObject;

/**
 * Credentials is a username-password pair, used to sign in to the application.
 * 
 * @author Arnoud van der Leer
 */
public class Credentials {
	private String username;
	private String password;
	private boolean signUp;

	/**
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param signUp
	 *            is true if the user wants to sign up, false otherwise
	 */
	public Credentials(String username, String password, boolean signUp) {
		this.username = username;
		this.password = password;
		this.signUp = signUp;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * The set method for the signUp boolean.
	 * 
	 * @param signUp
	 *            the boolean to set
	 */
	public void setSignUp(boolean signUp) {
		this.signUp = signUp;
	}

	/**
	 * The get method for the signUp boolean.
	 * 
	 * @return the signUp boolean
	 */
	public boolean getSignUp() {
		return this.signUp;
	}

	/**
	 * Turns this Credentials object into a JSON object
	 * 
	 * @return a JSON object that represents this Credentials object
	 */
	public JSONObject toJSON() {
		return new JSONObject().put("username", this.username)
				.put("password", this.password).put("signUp", this.signUp);
	}

	/**
	 * Creates a Credentials object out of a JSON object
	 * 
	 * @param jsonObject
	 *            the JSONObject to create a Credentials object from
	 * @return the Credentials object that is represented in the JSON object
	 */
	public static Credentials fromJSON(JSONObject jsonObject) {
		return new Credentials(jsonObject.getString("username"),
				jsonObject.getString("password"),
				jsonObject.getBoolean("signUp"));
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Credentials && object != null) {
			Credentials that = (Credentials) object;
			if (this.getUsername().equals(that.getUsername())
					&& this.getPassword().equals(that.getPassword())
					&& this.getSignUp() == that.getSignUp()) {
				return true;
			}
		}
		return false;
	}
}
