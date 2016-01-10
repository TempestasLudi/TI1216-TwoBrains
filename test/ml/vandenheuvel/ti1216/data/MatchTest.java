package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;
import org.junit.Test;

public class MatchTest {
	private Match create() {
		return new Match(-1, "username", "matchUsername", false, false);
	}

	@Test
	public void testConstructorId() {
		Match test = create();
		assertEquals(-1, test.getId());
	}

	@Test
	public void testConstructorUsername() {
		Match test = create();
		assertEquals("username", test.getUsername());
	}

	@Test
	public void testConstructorMatchUsername() {
		Match test = create();
		assertEquals("matchUsername", test.getMatchUsername());
	}

	@Test
	public void testConstructorSeen() {
		Match test = create();
		assertEquals(false, test.isSeen());
	}

	@Test
	public void testConstructorApproved() {
		Match test = create();
		assertEquals(false, test.isApproved());
	}

	@Test
	public void testEqualsPositive1() {
		Match test1 = create();
		Match test2 = create();
		assertTrue(test1.equals(test2));
	}

	@Test
	public void testEqualsNegative1() {
		Match test1 = create();
		Match test2 = new Match(-1, "username1", "username1", false, false);
		assertFalse(test1.equals(test2));
	}

	@Test
	public void testEqualsNegative2() {
		Match test1 = create();
		assertFalse(test1.equals(21));
	}

	@Test
	public void testEqualsNegative3() {
		Match test1 = new Match(-1, "username2", "username2", false, false);
		Match test2 = new Match(-1, "username2", "username1", false, false);
		assertFalse(test1.equals(test2));
	}

	@Test
	public void testEqualsNegative4() {
		Match test1 = new Match(-1, "username2", "username2", false, false);
		Match test2 = new Match(-1, "username1", "username2", false, false);
		assertFalse(test1.equals(test2));
	}

	@Test
	public void testFromToJSON() {
		Match match = create();
		assertEquals(match, Match.fromJSON(match.toJSON()));
	}

	@Test
	public void testGetSetUsername() {
		Match match = new Match(5, null, "MgNaam", true, true);
		match.setUsername("gNaam");
		assertEquals("gNaam", match.getUsername());
	}

	@Test
	public void testGetMatchUsername() {
		Match match = new Match(5, "gNaam", null, true, true);
		match.setMatchUsername("MgNaam");
		assertEquals("MgNaam", match.getMatchUsername());
	}

	@Test
	public void testGetSetSeen() {
		Match match = new Match(5, "gNaam", "MgNaam", false, true);
		match.setSeen(true);
		assertTrue(match.isSeen());
	}

	@Test
	public void testGetSetApproved() {
		Match match = new Match(5, "gNaam", "MgNaam", true, false);
		match.setApproved(true);
		assertTrue(match.isApproved());
	}

}
