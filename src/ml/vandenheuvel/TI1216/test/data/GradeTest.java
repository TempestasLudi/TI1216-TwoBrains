package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import ml.vandenheuvel.TI1216.source.data.*;

public class GradeTest 
{
	private Course createCourse(String subject){
		Faculty faculty = new Faculty("EWI", "Elektrotechniek, wis- en informatica", new ArrayList<Program>());
		Program program = new Program("TI", "Technische Informatica", faculty, new ArrayList<Course>());
		return new Course(subject, "OOP", program);
	}

	@Test
	public void testConstructorGrade() 
	{
		Course course = createCourse("TI1206");
		Grade test = new Grade(course,8);
		assertNotNull(test);
	}

	@Test
	public void testGetCourse() 
	{
		Course course = createCourse("TI1206");
		Grade test = new Grade(course,8);
		assertEquals(course, test.getCourse());
	}

	@Test
	public void testGetGrade() 
	{
		Course course = createCourse("TI1206");
		Grade test = new Grade(course,8);
		assertEquals(8, test.getGrade());
	}

	@Test
	public void testSetCourse() 
	{
		Course course = createCourse("TI1206");
		Grade test = new Grade(null,8);
		test.setCourse(course);
		assertEquals(course,test.getCourse());
	}

	@Test
	public void testSetGrade() 
	{
		Course course = createCourse("TI1206");
		Grade test = new Grade(course,7);
		test.setGrade(8);
		assertEquals(8,test.getGrade());
	}

	@Test
	public void testEqualsPositive() 
	{
		Course course = createCourse("TI1206");
		Grade test1 = new Grade(course,8);
		Grade test2 = new Grade(course,8);
		assertTrue(test1.equals(test2));
	}
	
	@Test
	public void testEqualsNegative() 
	{
		Course course1 = createCourse("TI1206");
		Course course2 = createCourse("TI1205");
		Grade test1 = new Grade(course1,8);
		Grade test2 = new Grade(course2,8);
		assertFalse(test1.equals(test2));
	}
}
