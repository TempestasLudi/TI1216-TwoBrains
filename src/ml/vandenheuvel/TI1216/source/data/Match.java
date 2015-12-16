package ml.vandenheuvel.TI1216.source.data;

import org.json.JSONObject;

public class Match {

	private int id;
	private String username1;
	private String username2;

	/**
	 * creates a new Match
	 * 
	 * @param id
	 *            the id of the match
	 * @param username1
	 *            the first User of the Match
	 * @param username2
	 *            the second User of the Match
	 */
	public Match(int id, String username1, String username2) {
		this.username1 = username1;
		this.username2 = username2;
	}
	
	public int getId() {
		return this.id;
	}

	/**
	 * gets the first User of the Match
	 * 
	 * @return the first User of the Match
	 */
	public String getUsername1() {
		return this.username1;
	}

	/**
	 * gets the second User of the Match
	 * 
	 * @return the second User of the Match
	 */
	public String getUsername2() {
		return this.username2;
	}

	/**
	 * Creates a JSONObject out of a Match object.
	 * 
	 * @return a JSONObject that represents a Match object
	 */
	public JSONObject toJSON() {
		return new JSONObject().put("username1", this.getUsername1()).put("username2", this.getUsername2()).put("id", this.id);
	}

	public static Match fromJSON(JSONObject jsonObject) {
		return new Match(jsonObject.getInt("id"), jsonObject.getString("username1"), jsonObject.getString("username2"));
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
			return ((this.username1.equals(that.username1) && this.username2.equals(that.username2))
					|| (this.username1.equals(that.username2) && this.username2.equals(that.username1)));
		}
		return false;
	}
}
