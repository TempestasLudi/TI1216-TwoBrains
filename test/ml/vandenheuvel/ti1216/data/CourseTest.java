package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CourseTest {

	@Test
	public void testCourseID(){
		Course course = new Course("a1", null, null);
		assertEquals("a1", course.getID());
	}
	
	@Test
	public void testCourseName(){
		Course course = new Course("a2", "a1", null);
		assertEquals("a1", course.getName());
	}

	@Test
	public void testCourseProgramSet(){
		Program program = new Program("a1", null, null, new ArrayList<Course>());
		Course course = new Course("a2", null, program);
		assertEquals(program, course.getProgram());
	}
	
	@Test
	public void testCourseProgramAdd(){
		Program program = new Program("a1", null, null, new ArrayList<Course>());
		Course course = new Course("a2", null, program);
		assertTrue(program.getCourses().contains(course));
	}

	@Test
	public void testGetID(){
		Course course = new Course("a1", null, null);
		assertEquals("a1", course.getID());
	}

	@Test
	public void testGetName(){
		Course course = new Course("a2", "a1", null);
		assertEquals("a1", course.getName());
	}

	@Test
	public void testGetProgram(){
		Program program = new Program("a1", null, null, new ArrayList<Course>());
		Course course = new Course("a2", null, program);
		assertEquals(program, course.getProgram());
	}

	@Test
	public void testSetName(){
		Course course = new Course("a2", null, null);
		course.setName("a1");
		assertEquals("a1", course.getName());
	}

	@Test
	public void testSetProgramSet(){
		Program program1 = new Program("a1", null, null, new ArrayList<Course>());
		Program program2 = new Program("a2", null, null, new ArrayList<Course>());
		Course course = new Course("a2", null, program1);
		course.setProgram(program2);
		assertEquals(program2, course.getProgram());
	}

	@Test
	public void testSetProgramAdd(){
		Program program1 = new Program("a1", null, null, new ArrayList<Course>());
		Program program2 = new Program("a2", null, null, new ArrayList<Course>());
		Course course = new Course("a2", null, program1);
		course.setProgram(program2);
		assertTrue(program2.getCourses().contains(course));
	}

	@Test
	public void testSetProgramRemove(){
		Program program1 = new Program("a1", null, null, new ArrayList<Course>());
		Program program2 = new Program("a2", null, null, new ArrayList<Course>());
		Course course = new Course("a2", null, program1);
		course.setProgram(program2);
		assertFalse(program1.getCourses().contains(course));
	}
	
	@Test
	public void testFromToJSON1()
	{
		ArrayList<Program> programlist = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Elektrotechniek, wiskunde en informatica", programlist);
		ArrayList<Course> courselist = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, courselist);
		Course course = new Course("TI1216", "OOP Project", program);
		assertEquals(course, Course.fromJSON(course.toJSON(), new Container()));
	}
	
	@Test
	public void testFromToJSON2()
	{
		ArrayList<Program> programlist = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Elektrotechniek, wiskunde en informatica", programlist);
		ArrayList<Course> courselist = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, courselist);
		Course course = new Course("TI1216", "OOP Project", program);
		assertEquals(course.getName(), Course.fromJSON(course.toJSON(), new Container()).getName());
	}
	
	@Test
	public void testFromToJSON3()
	{
		ArrayList<Program> programlist = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Elektrotechniek, wiskunde en informatica", programlist);
		ArrayList<Course> courselist = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, courselist);
		Course course = new Course("TI1216", "OOP Project", program);
		Container container = new Container();
		container.addProgram(program);
		assertEquals(course.getProgram(), Course.fromJSON(course.toJSON(), container).getProgram());
	}

	@Test
	public void testEqualsPositive(){
		Course course1 = new Course("a1", "b35", null);
		Course course2 = new Course("a1", "a24", null);
		assertEquals(course2, course1);
	}

	@Test
	public void testEqualsNegative(){
		Course course1 = new Course("a1", "b2", null);
		Course course2 = new Course("a2", "b2", null);
		assertNotEquals(course2, course1);
	}

	@Test
	public void testEqualsNull(){
		Course course = new Course("a1", null, null);
		assertFalse(course.equals(1));
	}

}
