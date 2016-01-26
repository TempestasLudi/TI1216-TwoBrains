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
	 * @param id
	 *            the id of the match
	 * @param username
	 *            the name of the user the match belongs to
	 * @param matchUsername
	 *            the name of the user that is matched
	 * @param seen
	 *            whether the match has already been seen or not by its user
	 * @param approved
	 *            whether the match has been approved or not by its user
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
	 * @param username
	 *            the name of the user the match belongs to
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the name of the user that is matched.
	 * 
	 * @param matchUsername
	 *            the name of the user that is matched
	 */
	public void setMatchUsername(String matchUsername) {
		this.matchUsername = matchUsername;
	}

	/**
	 * Sets whether the match has already been seen or not by its user.
	 * 
	 * @param seen
	 *            true if the match has been seen, otherwise false
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/**
	 * Sets whether the match has been approved or not by its user.
	 * 
	 * @param approved
	 *            true if the match has been approved, otherwise false
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
		return new JSONObject().put("id", this.id).put("username", this.username)
				.put("matchUsername", this.matchUsername).put("seen", this.seen).put("approved", this.approved);
	}

	/**
	 * Creates a Match object out of a JSONObject.
	 * 
	 * @param jsonObject
	 *            the JSONObject out of which the Match has to be created
	 * @return the created Match Object
	 */
	public static Match fromJSON(JSONObject jsonObject) {
		return new Match(jsonObject.getInt("id"), jsonObject.getString("username"),
				jsonObject.getString("matchUsername"), jsonObject.getBoolean("seen"),
				jsonObject.getBoolean("approved"));
	}

	/**
	 * Calculates the rating of a given Match.
	 * @param communicator TODO
	 * 
	 * @return the rating of the Match
	 */
	public double getRating(DatabaseCommunicator communicator) {
		User user = communicator.getUser(username);
		Grade[] gradelist = user.getGradeList();
		User matchUser = communicator.getUser(matchUsername);
		Grade[] matchGradelist = matchUser.getGradeList();
		double[] partialRatings = new double[Math.max(gradelist.length, matchGradelist.length)];
		for (int i = 0; i < gradelist.length; i++) {
			for (int j = 0; j < matchGradelist.length; j++) {
				if (gradelist[i].getCourseId().equals(matchGradelist[j].getCourseId())) {
					partialRatings[i] = gradelist[i].getGrade() - matchGradelist[j].getGrade();
					break;
				} else {
					partialRatings[i] = -100000;
				}
			}
		}
		double max = -100000;
		double min = -100000;
		double rating = 0;
		for (int j = 0; j < partialRatings.length / 2 + 1; j++) {
			for (int i = 0; i < partialRatings.length; i++) {
				if (partialRatings[i] > max) {
					max = partialRatings[i];
				}
				if (min==-100000 || (partialRatings[i] != -100000 && partialRatings[i] < min)) { // NOSONAR
					min = partialRatings[i];
				}
			}
			rating = rating + max - min;
			boolean deletedmax = false;
			boolean deletedmin = false;
			for (int i = 0; i < partialRatings.length; i++) {
				if (partialRatings[i]==max && !deletedmax) {
					partialRatings[i]=-100000;
					max=-100000;
					deletedmax=true;
				}
				if (partialRatings[i]==min && !deletedmin) {
					partialRatings[i]=-100000;
					min=-100000;
					deletedmin=true;
				}
			}
		}
		if (user.isUrgent()) {
			rating = rating * 1.2;
		}
		if (matchUser.isUrgent()) {
			rating = rating * 1.2;
		}
		return rating;
	}
	
//	/**
//	 * Returns matches above the rating of 10 and above for the username given
//	 * @param username the user that will get the matches
//	 * @return array of matches to show in the GUI
//	 */
//	public static Match[] showMatches(String username){
//		DatabaseCommunicator communicator = new DatabaseCommunicator("tempestasludi.com", "TI1216-test", "TI1216",
//				"3t.uGmL365j2f7B");
//		Match[] matches = communicator.getMatches(username);
//		int counter = 0;
//		Match[] display = new Match[25];
//		
//		for(int i = 0; i < matches.length; i++){
//			if(matches[i].getRating() >= 10.0){
//				display[counter] = matches[i];
//				counter++;
//			}
//		}
//		
//		return display;
//	}

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
