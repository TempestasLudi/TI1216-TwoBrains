package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.ti1216.data.Grade;
import ml.vandenheuvel.ti1216.data.User;

public class UserTest {
	@Test
	public void testConstructorUser1() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		assertNotNull(u1);
	}

	@Test
	public void testConstructorUser2() {
		User u1 = new User("User");
		assertNotNull(u1);
	}

	@Test
	public void testGetUsername() {

		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		assertEquals(u1.getUsername(), "User");
	}

	@Test
	public void testGetPostalCode() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		assertEquals(u1.getPostalCode(), "PC");
	}

	@Test
	public void testGetDescription() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		assertEquals(u1.getDescription(), "descrip");
	}

	@Test
	public void testGetGradeList() {
		Grade[] g = new Grade[2];
		g[0] = new Grade("TI1216", 5);
		User u1 = new User("User", "PC", "descrip", g, false);
		assertEquals(u1.getGradeList()[0], g[0]);
	}

	@Test
	public void testSetUsername() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		u1.setUsername("User2");
		assertEquals(u1.getUsername(), "User2");
	}

	@Test
	public void testSetPostalCode() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		u1.setPostalCode("PC2");
		assertEquals(u1.getPostalCode(), "PC2");
	}

	@Test
	public void testSetDescription() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		u1.setDescription("descrip2");
		assertEquals(u1.getDescription(), "descrip2");
	}

	@Test
	public void testSetGradeList() {
		Grade[] g = new Grade[2];
		g[0] = new Grade("TI1216", 7);
		Grade[] g2 = new Grade[3];
		g2[0] = new Grade("TI1206", 7);
		User u1 = new User("User", "PC", "descrip", g, false);
		u1.setGradeList(g2);
		assertEquals(u1.getGradeList()[0], g2[0]);
	}

	@Test
	public void testEquals1() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		User u2 = new User("User", "PC", null, g, false);
		assertTrue(u1.equals(u2));
	}

	@Test
	public void testEquals2() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		User u2 = new User("User2", "PC", "descrip", g, false);
		assertFalse(u1.equals(u2));
	}

	@Test
	public void testEquals3() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g, false);
		assertFalse(u1.equals(5));
	}

	@Test
	public void testFromToJSON1() {
		Grade[] gradelist = new Grade[0];
		User user = new User("azaidman", "postcode", "slimme man", gradelist, false);
		assertEquals(user, User.fromJSON(user.toJSON()));
	}

	@Test
	public void testFromToJSON2() {
		Grade[] gradelist = new Grade[3];
		User user = new User("azaidman", "postcode", "slimme man", gradelist, false);
		assertEquals(user.getDescription(), User.fromJSON(user.toJSON()).getDescription());
	}

	@Test
	public void testFromToJSON3() {
		Grade[] gradelist = new Grade[3];
		User user = new User("azaidman", "AndyAntwerpen", "slimme man", gradelist, false);
		assertEquals(user.getPostalCode(), User.fromJSON(user.toJSON()).getPostalCode());
	}

	@Test
	public void testFromToJSON4() {
		Grade grade1 = new Grade("TI1216", 9);
		Grade grade2 = new Grade("TI1206", 10);
		Grade[] gradelist = new Grade[3];
		gradelist[0] = grade1;
		gradelist[1] = grade2;
		User user = new User("azaidman", "AndyAntwerpen", "slimme man", gradelist, false);
		assertEquals(gradelist[0], User.fromJSON(user.toJSON()).getGradeList()[0]);
		assertEquals(gradelist[1], User.fromJSON(user.toJSON()).getGradeList()[1]);
	}
	 
}
