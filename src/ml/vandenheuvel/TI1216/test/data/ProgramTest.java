package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.data.*;

public class ProgramTest {

	@Test
	public void testConstructorProgram1() 
	{
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, null);
		assertEquals("TI", program.getID());
	}
	
	@Test
	public void testConstructorProgram2() 
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		assertEquals("Technische Informatica", program.getName());
	}
	
	@Test
	public void testConstructorProgram3() 
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		assertEquals(faculty, program.getFaculty());
	}
	
	@Test
	public void testConstructorProgram4() 
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Course course = new Course("TI1216", "OOP Project", null);
		list.add(course);
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		assertEquals(program,course.getProgram());
	}
	
	@Test
	public void testGetSetName()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", null, faculty, list);
		program.setName("Technische Informatica");
		assertEquals("Technische Informatica", program.getName());
	}
	
	@Test
	public void testGetSetFaculty()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", null, list);
		program.setFaculty(faculty);
		assertEquals(faculty, program.getFaculty());
	}
	
	@Test
	public void testAddCourse1()
	{
		Course course = new Course("TI1216", "OOP Project", null);
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		program.addCourse(course);
		assertEquals(course, program.getCourses().get(0));
	}
	
	@Test
	public void testAddCourse2()
	{
		Course course = new Course("TI1216", "OOP Project", null);
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		program.addCourse(course);
		program.addCourse(course);
		assertEquals(1, program.getCourses().size());
	}
	
	@Test
	public void testAddCourse3()
	{
		Course course = new Course("TI1216", "OOP Project", null);
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		program.addCourse(course);
		assertEquals(program, course.getProgram());
	}
	
	@Test
	public void testRemoveCourse1()
	{
		Course course = new Course("TI1216", "OOP Project", null);
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		program.addCourse(course);
		program.removeCourse(course);
		assertEquals(0, program.getCourses().size());
	}
	
	@Test
	public void testRemoveCourse2()
	{
		Course course = new Course("TI1216", "OOP Project", null);
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		program.addCourse(course);
		program.removeCourse(course);
		assertNull(course.getProgram());
	}
	
	@Test
	public void testRemoveCourse3()
	{
		Course course = new Course("TI1216", "OOP Project", null);
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		program.removeCourse(course);
		assertEquals(0, program.getCourses().size());
	}
	
	@Test
	public void testFromToJSON1()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		assertEquals(program, Program.fromJSON(program.toJSON(), new Container()));
	}
	
	@Test
	public void testFromToJSON2()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		assertEquals(program.getName(), Program.fromJSON(program.toJSON(), new Container()).getName());
	}
	
	@Test
	public void testFromToJSON3()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		ArrayList<Program> list2 = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list2);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		Container container = new Container();
		container.addFaculty(faculty);
		assertEquals(program.getFaculty(), Program.fromJSON(program.toJSON(), container).getFaculty());
	}
	
	@Test
	public void testEqualsPositive()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program1 = new Program("TI", "Technische Informatica", faculty, list);
		Program program2 = new Program("TI", "Computer Science", faculty, list);
		assertTrue(program1.equals(program2));
	}
	
	@Test
	public void testEqualsNegative()
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program1 = new Program("TI", "Technische Informatica", faculty, list);
		Program program2 = new Program("CS", "Computer Science", faculty, list);
		assertFalse(program1.equals(program2));
	}

}
