package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	@Test
	public void testGetRating() {
		DatabaseCommunicator communicator = new DatabaseCommunicator("tempestasludi.com", "TI1216-test", "TI1216", "3t.uGmL365j2f7B");
		Faculty f = new Faculty("EWI", "Elektrotechniek, Wiskunde en Informatica", new ArrayList<Program>());
		Program p = new Program("TI", "Technische Informatica", f, new ArrayList<Course>());
		new Course("C1", "Course 1", p);
		new Course("C2", "Course 2", p);
		new Course("C3", "Course 3", p);
		new Course("C4", "Course 4", p);
		new Course("C5", "Course 5", p);
		new Course("C6", "Course 6", p);
		new Course("C7", "Course 7", p);
		Grade g11 = new Grade("C1", 9);
		Grade g12 = new Grade("C2", 8);
		Grade g13 = new Grade("C3", 9);
		Grade g14 = new Grade("C4", 10);
		Grade g15 = new Grade("C5", 9);
		Grade g16 = new Grade("C6", 9);
		Grade g17 = new Grade("C7", 8);
		Grade g21 = new Grade("C1", 5);
		Grade g22 = new Grade("C2", 4);
		Grade g23 = new Grade("C3", 5);
		Grade g24 = new Grade("C4", 3);
		Grade g25 = new Grade("C5", 3);
		Grade g26 = new Grade("C6", 6);
		Grade g27 = new Grade("C7", 5);
		Grade g31 = new Grade("C1", 7);
		Grade g32 = new Grade("C2", 8);
		Grade g33 = new Grade("C3", 7);
		Grade g34 = new Grade("C4", 6);
		Grade g35 = new Grade("C5", 3);
		Grade g36 = new Grade("C6", 2);
		Grade g37 = new Grade("C7", 3);
		Grade g41 = new Grade("C1", 3);
		Grade g42 = new Grade("C2", 3);
		Grade g43 = new Grade("C3", 5);
		Grade g44 = new Grade("C4", 5);
		Grade g45 = new Grade("C5", 7);
		Grade g46 = new Grade("C6", 9);
		Grade g47 = new Grade("C7", 6);
		Grade[] l1 = new Grade[] {g11,g12,g13,g14,g15,g16,g17};
		Grade[] l2 = new Grade[] {g21,g22,g23,g24,g25,g26,g27};
		Grade[] l3 = new Grade[] {g31,g32,g33,g34,g35,g36,g37};
		Grade[] l4 = new Grade[] {g41,g42,g43,g44,g45,g46,g47};
		User u1 = new User("U1","","",l1, false);
		User u2 = new User("U2","","",l2, false);
		User u3 = new User("U3","","",l3, false);
		User u4 = new User("U4","","",l4, false);
		Credentials cr1 = new Credentials("U1","P1");
		Credentials cr2 = new Credentials("U2","P2");
		Credentials cr3 = new Credentials("U3","P3");
		Credentials cr4 = new Credentials("U4","P4");
		communicator.save(f);
		communicator.save(u1,cr1);
		communicator.save(u2,cr2);
		communicator.save(u3,cr3);
		communicator.save(u4,cr4);
		Match match12 = new Match(1,"U1","U2",false,false);
		Match match13 = new Match(2,"U1","U3",false,false);
		Match match14 = new Match(3,"U1","U4",false,false);
		Match match21 = new Match(4,"U2","U1",false,false);
		Match match23 = new Match(5,"U2","U3",false,false);
		Match match24 = new Match(6,"U2","U4",false,false);
		Match match31 = new Match(7,"U3","U1",false,false);
		Match match32 = new Match(8,"U3","U2",false,false);
		Match match34 = new Match(9,"U3","U4",false,false);
		Match match41 = new Match(10,"U4","U1",false,false);
		Match match42 = new Match(11,"U4","U2",false,false);
		Match match43 = new Match(12,"U4","U3",false,false);
		assertTrue(7==match12.getRating());
		assertTrue(14==match13.getRating());
		assertTrue(12==match14.getRating());
		assertTrue(match12.getRating()==match21.getRating());
		assertTrue(15==match23.getRating());
		assertTrue(12==match24.getRating());
		assertTrue(match13.getRating()==match31.getRating());
		assertTrue(match23.getRating()==match32.getRating());
		assertTrue(25==match34.getRating());
		assertTrue(match14.getRating()==match41.getRating());
		assertTrue(match24.getRating()==match42.getRating());
		assertTrue(match34.getRating()==match43.getRating());
		communicator.delete(f);
	}

}
