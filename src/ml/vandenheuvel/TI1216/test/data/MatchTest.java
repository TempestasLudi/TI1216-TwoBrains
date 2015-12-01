package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.data.*;

public class MatchTest 
{

	@Test
	public void testConstructorMatch() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test = new Match(user1,user2);
		assertNotNull(test);
	}

	@Test
	public void testGetUser1() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test = new Match(user1,user2);
		assertEquals(user1, test.getUser1());
	}

	@Test
	public void testGetUser2() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test = new Match(user1,user2);
		assertEquals(user2, test.getUser2());
	}

	@Test
	public void testEqualsPositive1() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test1 = new Match(user1, user2);
		Match test2 = new Match(user1, user2);
		assertTrue(test1.equals(test2));
	}
	
	@Test
	public void testEqualsPositive2() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test1 = new Match(user2, user1);
		Match test2 = new Match(user1, user2);
		assertTrue(test1.equals(test2));
	}
	
	@Test
	public void testEqualsNegative1() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test1 = new Match(user1, user2);
		Match test2 = new Match(user1, user1);
		assertFalse(test1.equals(test2));
	}
	
	@Test
	public void testEqualsNegative2() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test1 = new Match(user1, user2);
		assertFalse(test1.equals(21));
	}
	
	@Test
	public void testEqualsNegative3() 
	{
		Grade[] gradelist = new Grade[2];
		User user1 = new User("azaidman", "2585KH", "Docent OOP Project", gradelist);
		User user2 = new User("mveraar", "6433WR", "Docent Reële Analyse", gradelist);
		Match test1 = new Match(user2, user2);
		Match test2 = new Match(user1, user1);
		assertFalse(test1.equals(test2));
	}
}
