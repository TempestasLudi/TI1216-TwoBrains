package ml.vandenheuvel.TI1216.test.data;
import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.Grade;
import ml.vandenheuvel.TI1216.source.data.User;

public class UserTest {

	@Test
	public void testConstructorUser() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		assertNotNull(u1);
	}

	@Test
	public void testGetUsername() {
		
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		
		assertEquals(u1.getUsername(), "User");
	}

	@Test
	public void testGetPostalCode() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		
		assertEquals(u1.getPostalCode(), "PC");
	}

	@Test
	public void testGetDescription() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		
		assertEquals(u1.getDescription(), "descrip");
	}

	@Test
	public void testGetGradelist() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		
		assertEquals(u1.getGradelist(), g);
	}

	@Test
	public void testSetUsername() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		u1.setUsername("User2");
		
		assertEquals(u1.getUsername(), "User2");
	}

	@Test
	public void testSetPostalCode() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		u1.setPostalCode("PC2");
		
		assertEquals(u1.getPostalCode(), "PC2");
	}

	@Test
	public void testSetDescription() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		u1.setDescription("descrip2");
		
		assertEquals(u1.getDescription(), "descrip2");
	}

	@Test
	public void testSetGradeList() {
		Grade[] g = new Grade[2];
		Grade[] g2 = new Grade[3];
		User u1 = new User("User", "PC", "descrip", g);
		u1.setGradeList(g2);
		
		assertEquals(u1.getGradelist(), g2);
	}

	@Test
	public void testEqualsObject1() {
		Grade[] g = new Grade[2];
		//Grade[] g2 = new Grade[3];
		User u1 = new User("User", "PC", "descrip", g);
		User u2 = new User("User", "PC", "descrip", g);
		
		assertTrue(u1.equals(u2));
	}

	@Test
	public void testEqualsObject2() {
		Grade[] g = new Grade[2];
		Grade[] g2 = new Grade[3];
		User u1 = new User("User", "PC", "descrip", g);
		User u2 = new User("User", "PC", "descrip", g2);
		
		assertFalse(u1.equals(u2));
	}
}
