package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.data.*;

public class GradeTest 
{

	@Test
	public void testConstructorGrade() 
	{
		Course course = new Course("OOP", "TI1206", "Technische Informatica");
		Grade test = new Grade(course,8);
		assertNotNull(test);
	}

	@Test
	public void testGetCourse() 
	{
		Course course = new Course("OOP", "TI1206", "Technische Informatica");
		Grade test = new Grade(course,8);
		assertEquals(course, test.getCourse());
	}

	@Test
	public void testGetGrade() 
	{
		Course course = new Course("OOP", "TI1206", "Technische Informatica");
		Grade test = new Grade(course,8);
		assertEquals(8, test.getGrade());
	}

	@Test
	public void testSetCourse() 
	{
		Course course = new Course("OOP", "TI1206", "Technische Informatica");
		Grade test = new Grade(null,8);
		test.setCourse(course);
		assertEquals(course,test.getCourse());
	}

	@Test
	public void testSetGrade() 
	{
		Course course = new Course("OOP", "TI1206", "Technische Informatica");
		Grade test = new Grade(course,7);
		test.setGrade(8);
		assertEquals(8,test.getGrade());
	}

	@Test
	public void testEqualsPositive() 
	{
		Course course = new Course("OOP", "TI1206", "Technische Informatica");
		Grade test1 = new Grade(course,8);
		Grade test2 = new Grade(course,8);
		assertTrue(test1.equals(test2));
	}
	
	@Test
	public void testEqualsNegative() 
	{
		Course course1 = new Course("OOP", "TI1206", "Technische Informatica");
		Course course2 = new Course("OOP", "TI1205", "Technische Informatica");
		Grade test1 = new Grade(course1,8);
		Grade test2 = new Grade(course2,8);
		assertFalse(test1.equals(test2));
	}
}
