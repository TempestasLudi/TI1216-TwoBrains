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

	/**
	 * Creates a Match object out of a JSONObject.
	 * @param jsonObject the JSONObject out of which the Match has to be created
	 * @return the created Match Object
	 */
	public static Match fromJSON(JSONObject jsonObject) {
		return new Match(jsonObject.getInt("id"), jsonObject.getString("username"), jsonObject.getString("matchUsername"), jsonObject.getBoolean("seen"), jsonObject.getBoolean("approved"));
	}
	
	/**
	 * Calculates the rating of a given Match.
	 * @return the rating of the Match
	 */
	public int getRating()
	{
		DatabaseCommunicator communicator = new DatabaseCommunicator("tempestasludi.com", "TI1216-test", "TI1216", "3t.uGmL365j2f7B");
		Grade[] gradelist = communicator.getUser(username).getGradeList();
		Grade[] matchGradelist = communicator.getUser(matchUsername).getGradeList();
		int[] partialRatings = new int[Math.max(gradelist.length, matchGradelist.length)];
		for(int i=0;i<gradelist.length;i++)
		{
			for(int j=0;j<matchGradelist.length;j++)
			{
				if(gradelist[i].getCourseId().equals(gradelist[j].getCourseId()))
				{
					partialRatings[i] = gradelist[i].getGrade() - gradelist[j].getGrade();
				}
				else
				{
					partialRatings[i]=-100000;
				}
			}
		}
		int max = partialRatings[0];
		int min = partialRatings[0];
		int rating = 0;
		for(int j=0;j<partialRatings.length/2+1;j++)
		{
			for(int i=0;i<partialRatings.length;i++)
			{
				if(partialRatings[i]>max)
				{
					max = partialRatings[i];
				}
				if(partialRatings[i]!=-100000 && partialRatings[i]<min)
				{
					min = partialRatings[i];
				}
			}
			rating = rating + max-min;
		}
		return rating;
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
