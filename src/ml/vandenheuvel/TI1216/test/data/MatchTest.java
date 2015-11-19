package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.data.*;

public class MatchTest 
{

	@Test
	public void testMatch() 
	{
		User user1 = null;
		User user2 = null;
		Match test = new Match(user1,user2);
		assertNotNull(test);
	}

	@Test
	public void testGetUser1() 
	{
		User user1 = null;
		User user2 = null;
		Match test = new Match(user1,user2);
		assertEquals(null, test.getUser1());
	}

	@Test
	public void testGetUser2() 
	{
		User user1 = null;
		User user2 = null;
		Match test = new Match(user1,user2);
		assertEquals(null, test.getUser2());
	}

	@Test
	public void testEqualsObject() 
	{
		User user1 = null;
		User user2 = null;
		Match test1 = new Match(user1, user2);
		Match test2 = new Match(user1, user2);
		assertTrue(test1.equals(test2));
	}
}
