package ml.vandenheuvel.ti1216.data;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The User class contains all information of a user, except for it's password.
 * 
 * @author Bram van den Heuvel
 *
 */
public class User {
	private String username;
	private String postalCode;
	private String description;
	private Grade[] gradeList;

	/**
	 * Constructor method for User object
	 * 
	 * @param username
	 *            String representing User's username
	 * @param postalCode
	 *            String representing User's postal code
	 * @param description
	 *            String representing the description of the user
	 * @param gradelist
	 *            Array of Grade objects to represent the grades for the user's
	 *            courses
	 */
	public User(String username, String postalCode, String description,
			Grade[] gradelist) {
		this.username = username;
		this.postalCode = postalCode;
		this.description = description;
		this.gradeList = gradelist;
	}

	/**
	 * Constructor method for User object
	 * 
	 * @param username
	 *            String representing User's username
	 */
	public User(String username) {
		this.username = username;
		this.postalCode = "";
		this.description = "";
		this.gradeList = new Grade[0];
	}

	/**
	 * Accessor method for username variable
	 * 
	 * @return String representing User's username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Accessor method for username string
	 * 
	 * @return String representing User's username
	 */
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * Accessor method for description string
	 * 
	 * @return the user's description in form of a string
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Accessor method to return the array of grades for this user
	 * 
	 * @return this user's list of grades in an array
	 */
	public Grade[] getGradeList() {
		return this.gradeList;
	}

	/**
	 * Mutator method to set the user's username
	 * 
	 * @param username
	 *            String to change it to
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Mutator method to set the user's postal code
	 * 
	 * @param postalCode
	 *            String to change it to
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Mutator method to set the user's description
	 * 
	 * @param description
	 *            String to change it to
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Mutator method to set the user's gradeList
	 * 
	 * @param gradeList
	 *            array of grades to change it to
	 */
	public void setGradeList(Grade[] gradeList) {
		this.gradeList = gradeList;
	}

	/**
	 * Creates a JSON representation of this User object.
	 * 
	 * @return a JSON representation of this User object
	 */
	public JSONObject toJSON() {
		JSONArray jsonGradeList = new JSONArray();
		for (int i = 0; i < this.gradeList.length; i++) {
			if(this.gradeList[i]!=null)
			{
				jsonGradeList.put(this.gradeList[i].toJSON());
			}
		}
		return new JSONObject().put("username", this.username)
				.put("postalCode", this.postalCode)
				.put("description", this.description)
				.put("gradeList", jsonGradeList);
	}

	/**
	 * Creates a User object out of a JSON file.
	 * 
	 * @return a User object out of a JSON file
	 */
	public static User fromJSON(JSONObject jsonObject) {
		JSONArray tempJSONGradeList = jsonObject.getJSONArray("gradeList");
		Grade[] tempGradeList = new Grade[tempJSONGradeList.length()];
		//if (tempJSONGradeList != null) {
			for (int i = 0; i < tempJSONGradeList.length(); i++) {
				if (!tempJSONGradeList.get(i).toString().equals("null")) {
					tempGradeList[i] = Grade
							.fromJSON((JSONObject) tempJSONGradeList.get(i));
				} else
					tempGradeList[i] = null;
			}
		//}
		return new User(jsonObject.getString("username"),
				jsonObject.getString("postalCode"),
				jsonObject.getString("description"), tempGradeList);
	}

	/**
	 * checks whether two Users are equal to each other
	 * 
	 * @param other
	 *            the Object to which the User is compared
	 * @return true if two Users have the same username, otherwise false
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof User) {
			User that = (User) other;
			return this.username.equals(that.username);
		}

		return false;
	}
}
