package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.data.*;

public class MatchTest {

	@Test
	public void testConstructorMatch() {
		Match test = new Match("user1", "user2");
		assertNotNull(test);
	}

	@Test
	public void testGetUser1() {
		Match test = new Match("username1", "username2");
		assertEquals("username1", test.getUsername1());
	}

	@Test
	public void testGetUser2() {
		Match test = new Match("user1", "user2");
		assertEquals("user2", test.getUsername2());
	}
	
	@Test
	public void testJSON(){
		Match test = new Match("username1", "username2");
		assertTrue(test.equals(Match.fromJSON(test.toJSON())));
	}

	@Test
	public void testEqualsPositive1() {
		Match test1 = new Match("user1", "user2");
		Match test2 = new Match("user1", "user2");
		assertTrue(test1.equals(test2));
	}

	@Test
	public void testEqualsPositive2() {
		Match test1 = new Match("user1", "user2");
		Match test2 = new Match("user2", "user1");
		assertTrue(test1.equals(test2));
	}

	@Test
	public void testEqualsNegative2() {
		Match test1 = new Match("user1", "user2");
		assertFalse(test1.equals(21));
	}

	@Test
	public void testEqualsNegative3() {
		Match test1 = new Match("user2", "user");
		Match test2 = new Match("user1", "user1");
		assertFalse(test1.equals(test2));
	}
}
