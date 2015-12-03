package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.json.JSONObject;
import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.Course;
import ml.vandenheuvel.TI1216.source.data.Faculty;
import ml.vandenheuvel.TI1216.source.data.Grade;
import ml.vandenheuvel.TI1216.source.data.Program;
import ml.vandenheuvel.TI1216.source.data.User;

public class UserTest {
	private Course createCourse(String subject) {
		Faculty faculty = new Faculty("EWI",
				"Elektrotechniek, wis- en informatica",
				new ArrayList<Program>());
		Program program = new Program("TI", "Technische Informatica", faculty,
				new ArrayList<Course>());
		return new Course(subject, "OOP", program);
	}

	@Test
	public void testConstructorUser1() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
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
	public void testGetGradeList() {
		Grade[] g = new Grade[2];
		g[0] = new Grade("TI1216", 5);
		User u1 = new User("User", "PC", "descrip", g);
		assertEquals(u1.getGradeList()[0], g[0]);
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
		g[0] = new Grade("TI1216", 7);
		Grade[] g2 = new Grade[3];
		g2[0] = new Grade("TI1206", 7);
		User u1 = new User("User", "PC", "descrip", g);
		u1.setGradeList(g2);
		assertEquals(u1.getGradeList()[0], g2[0]);
	}

	@Test
	public void testJSON() {
		Grade[] g = new Grade[3];
		g[0] = new Grade("TI1216", 7);
		g[1] = new Grade("TI1216", 8);
		g[2] = new Grade("TI1216", 9);
		User u1 = new User("Username213", "1243AB", "descripion bla bla ble", g);
		assertTrue(u1.equals(User.fromJSON(u1.toJSON())));
	}

	@Test
	public void testJSON2() {
		Grade[] g = new Grade[3];
		g[0] = new Grade("TI1216", 7);
		g[1] = new Grade("TI1216", 9);
		User u1 = new User("Username213", "1243AB", "descripion bla bla ble", g);
		assertTrue(u1.equals(User.fromJSON(u1.toJSON())));
	}

	@Test
	public void testEquals1() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		User u2 = new User("User", "PC", null, g);
		assertTrue(u1.equals(u2));
	}

	@Test
	public void testEquals2() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		User u2 = new User("User2", "PC", "descrip", g);
		assertFalse(u1.equals(u2));
	}

	@Test
	public void testEquals3() {
		Grade[] g = new Grade[2];
		User u1 = new User("User", "PC", "descrip", g);
		assertFalse(u1.equals(5));
	}

	@Test
	public void testEquals4() {
		Grade[] g = new Grade[2];
		g[0]=new Grade("String", 4);
		User u1 = new User("User", "PC", "descrip", g);
		assertTrue(u1.equals(u1));
	}
}
