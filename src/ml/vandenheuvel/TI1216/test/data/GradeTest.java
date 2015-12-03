package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.*;

public class GradeTest {

	@Test
	public void testConstructorGrade() {
		Grade test = new Grade("TI1216", 8);
		assertNotNull(test);
	}

	@Test
	public void testGetGrade() {
		Grade test = new Grade("TI1216", 8);
		assertEquals(8, test.getGrade());
	}

	@Test
	public void testSetCourse() {
		Grade test = new Grade("TI1216", 8);
		test.setCourse("TI1216");
		assertEquals("TI1216", test.getCourse());
	}

	@Test
	public void testSetGrade() {
		Grade test = new Grade("TI1216", 8);
		test.setGrade(8);
		assertEquals(8, test.getGrade());
	}

	@Test
	public void testJSON() {
		Grade test1 = new Grade("TI1216", 8);
		JSONObject jsonObject1 = test1.toJSON();
		assertTrue(Grade.fromJSON(jsonObject1).equals(test1));
	}

	@Test
	public void testEqualsPositive() {
		Grade test1 = new Grade("TI1216", 8);
		Grade test2 = new Grade("TI1216", 8);
		assertTrue(test1.equals(test2));
	}

	@Test
	public void testEqualsNegative1() {
		Grade test1 = new Grade("TI1216", 8);
		Grade test2 = new Grade("TI1216", 7);
		assertFalse(test1.equals(test2));
	}

	@Test
	public void testEqualsNegative2() {
		Grade test = new Grade("TI1216", 8);
		assertFalse(test.equals(76));
	}

	@Test
	public void testEqualsNegative3() {
		Grade test1 = new Grade("TI1216", 8);
		Grade test2 = new Grade("TI1214", 8);
		assertFalse(test1.equals(test2));
	}
}
