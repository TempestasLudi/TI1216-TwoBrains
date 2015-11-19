package ml.vandenheuvel.TI1216.data;

public class Match 
{
	private User user1;
	private User user2;
	
	public Match(User user1, User user2)
	{
		this.user1 = user1;
		this.user2 = user2;
	}
	
	public User getUser1()
	{
		return this.user1;
	}
	
	public User getUser2()
	{
		return this.user2;
	}
	
	public boolean equals(Object other)
	{
		if(other instanceof Match)
		{
			Match that = (Match) other;
			return ((this.user1.equals(that.user1)) && this.user2.equals(that.user2));
		}
		return false;
	}
}
