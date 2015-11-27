package ml.vandenheuvel.TI1216.source.data;

public class Match 
{
	
	private User user1;
	private User user2;
	
	/**
	 * creates a new Match
	 * @param user1 the first User of the Match
	 * @param user2 the second User of the Match
	 */
	public Match(User user1, User user2)
	{
		this.user1 = user1;
		this.user2 = user2;
	}
	
	/**
	 * gets the first User of the Match
	 * @return the first User of the Match
	 */
	public User getUser1()
	{
		return this.user1;
	}
	
	/**
	 * gets the second User of the Match
	 * @return the second User of the Match
	 */
	public User getUser2()
	{
		return this.user2;
	}
	
	/**
	 * checks whether two Matches are equal to each other
	 * @param other the Object to which the Match is compared
	 * @return true if the Matches are equal, otherwise false
	 */
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof Match)
		{
			Match that = (Match) other;
			return ((this.user1.equals(that.user1) && this.user2.equals(that.user2)) || (this.user1.equals(that.user2) && this.user2.equals(that.user1)));
		}
		return false;
	}
}
