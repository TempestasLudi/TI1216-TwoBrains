package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;
import org.junit.Test;

import ml.vandenheuvel.ti1216.data.*;

public class MatchTest 
{

	@Test
	public void testConstructorMatch() 
	{
		Match test = new Match(-1,"username1", "username2");
		assertNotNull(test);
	}

	@Test
	public void testGetId() 
	{
		Match match = new Match(5,"username1", "username2");
		assertEquals(5, match.getId());
	}
	
	@Test
	public void testGet() 
	{
		Match test = new Match(-1,"username1", "username2");
		assertEquals("username1", test.getUsername1());
	}

	@Test
	public void testGetUser2() 
	{
		Match test = new Match(-1,"username1", "username2");
		assertEquals("username2", test.getUsername2());
	}

	@Test
	public void testEqualsPositive1() 
	{
		Match test1 = new Match(-1, "username1", "username2");
		Match test2 = new Match(-1, "username1", "username2");
		assertTrue(test1.equals(test2));
	}
	
	@Test
	public void testEqualsPositive2() 
	{
		Match test1 = new Match(-1, "username2", "username1");
		Match test2 = new Match(-1, "username1", "username2");
		assertTrue(test1.equals(test2));
	}
	
	@Test
	public void testEqualsNegative1() 
	{
		Match test1 = new Match(-1, "username1", "username2");
		Match test2 = new Match(-1, "username1", "username1");
		assertFalse(test1.equals(test2));
	}
	
	@Test
	public void testEqualsNegative2() 
	{
		Match test1 = new Match(-1, "username1", "username2");
		assertFalse(test1.equals(21));
	}
	
	@Test
	public void testEqualsNegative3() 
	{
		Match test1 = new Match(-1, "username2", "username2");
		Match test2 = new Match(-1, "username1", "username1");
		assertFalse(test1.equals(test2));
	}
	
	@Test
	public void testFromToJSON()
	{
		Match match = new Match(3, "azaidman", "bzaidman");
		assertEquals(match,Match.fromJSON(match.toJSON()));
	}
}
