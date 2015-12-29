package ml.vandenheuvel.ti1216.data;

import org.json.JSONObject;

/**
 * Match represents a match between two users.
 */
public class Match {
	
	/**
	 * The id of the match.
	 */
	private int id;
	
	/**
	 * The name of the user the match belongs to.
	 */
	private String username;
	
	/**
	 * The name of the user that is matched.
	 */
	private String matchUsername;
	
	/**
	 * Whether the match has already been seen or not by its user.
	 */
	private boolean seen;
	
	/**
	 * Whether the match has been approved or not by its user.
	 */
	private boolean approved;

	/**
	 * Class constructor.
	 * 
	 * @param id the id of the match
	 * @param username the name of the user the match belongs to
	 * @param matchUsername the name of the user that is matched
	 * @param seen whether the match has already been seen or not by its user
	 * @param approved whether the match has been approved or not by its user
	 */
	public Match(int id, String username, String matchUsername, boolean seen, boolean approved) {
		this.id = id;
		this.username = username;
		this.matchUsername = matchUsername;
		this.seen = seen;
		this.approved = approved;
	}
	
	/**
	 * Gets the id of the match.
	 * 
	 * @return the id of the match
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Gets the name of the user the match belongs to.
	 * 
	 * @return the name of the user the match belongs to
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the name of the user that is matched.
	 * 
	 * @return the name of the user that is matched
	 */
	public String getMatchUsername() {
		return matchUsername;
	}

	/**
	 * Checks whether the match has already been seen or not by its user.
	 * 
	 * @return true if the match has been seen, otherwise false
	 */
	public boolean isSeen() {
		return seen;
	}

	/**
	 * Checks whether the match has been approved or not by its user.
	 * 
	 * @return true if the match has been approved, otherwise false
	 */
	public boolean isApproved() {
		return approved;
	}
	
	/**
	 * Sets the name of the user the match belongs to.
	 * 
	 * @param username the name of the user the match belongs to
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Sets the name of the user that is matched.
	 * 
	 * @param matchUsername the name of the user that is matched
	 */
	public void setMatchUsername(String matchUsername) {
		this.matchUsername = matchUsername;
	}

	/**
	 * Sets whether the match has already been seen or not by its user.
	 * 
	 * @param seen true if the match has been seen, otherwise false
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/**
	 * Sets whether the match has been approved or not by its user.
	 * 
	 * @param approved true if the match has been approved, otherwise false
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	/**
	 * Creates a JSONObject out of a Match object.
	 * 
	 * @return a JSONObject that represents a Match object
	 */
	public JSONObject toJSON() {
		return new JSONObject().put("id", this.id).put("username", this.username).put("matchUsername", this.matchUsername).put("seen", this.seen).put("approved", this.approved);
	}

	public static Match fromJSON(JSONObject jsonObject) {
		return new Match(jsonObject.getInt("id"), jsonObject.getString("username"), jsonObject.getString("matchUsername"), jsonObject.getBoolean("seen"), jsonObject.getBoolean("approved"));
	}

	/**
	 * checks whether two Matches are equal to each other
	 * 
	 * @param other
	 *            the Object to which the Match is compared
	 * @return true if the Matches are equal, otherwise false
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof Match) {
			Match that = (Match) other;
			return this.username.equals(that.getUsername()) && this.matchUsername.equals(that.getMatchUsername());
		}
		return false;
	}
}
