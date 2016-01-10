package ml.vandenheuvel.ti1216.data;

import org.json.JSONObject;

/**
 * Credentials is a username-password pair, used to sign in to the application.
 */
public class Credentials {
	private String username;
	private String password;

	/**
	 * @param username the username
	 * @param password the password
	 * @param signUp
	 *            is true if the user wants to sign up, false otherwise
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
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the password
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
	 * Turns this Credentials object into a JSON object
	 * 
	 * @return a JSON object that represents this Credentials object
	 */
	public JSONObject toJSON() {
		return new JSONObject().put("username", this.username)
				.put("password", this.password);
	}

	/**
	 * Creates a Credentials object out of a JSON object
	 * 
	 * @param jsonObject the JSONObject to create a Credentials object from
	 * @return the Credentials object that is represented in the JSON object
	 */
	public static Credentials fromJSON(JSONObject jsonObject) {
		return new Credentials(jsonObject.getString("username"),
				jsonObject.getString("password"));
	}

	/**
	 * The equals method for this class
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof Credentials) {
			Credentials that = (Credentials) object;
			if (this.getUsername().equals(that.getUsername())
					&& this.getPassword().equals(that.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
