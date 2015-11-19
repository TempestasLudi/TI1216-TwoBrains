import static org.junit.Assert.*;

import org.junit.Test;

public class GradeTest 
{

	@Test
	public void testGrade() 
	{
		Course course = null;
		Grade test = new Grade(course,8);
		assertNotNull(test);
	}

	@Test
	public void testGetCourse() 
	{
		Course course = null;
		Grade test = new Grade(course,8);
		assertEquals(null, test.getCourse());
	}

	@Test
	public void testGetGrade() 
	{
		Course course = null;
		Grade test = new Grade(course,8);
		assertEquals(8, test.getGrade());
	}

	@Test
	public void testSetCourse() 
	{
		Course course = null;
		Grade test = new Grade(course,8);
		test.setCourse(null);
		assertEquals(null,test.getCourse());
	}

	@Test
	public void testSetGrade() 
	{
		Course course = null;
		Grade test = new Grade(course,8);
		test.setGrade(8);
		assertEquals(8,test.getGrade());
	}

	@Test
	public void testEqualsObject() 
	{
		Course course = null;
		Grade test1 = new Grade(course,8);
		Grade test2 = new Grade(course,8);
		Grade test3 = new Grade(course,9);
		assertTrue(test1.equals(test2));
		assertFalse(test1.equals(test3));
	}
}
