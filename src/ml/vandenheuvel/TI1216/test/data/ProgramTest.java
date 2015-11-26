package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.data.*;

public class ProgramTest {

	@Test
	public void testConstructorProgram1() 
	{
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
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
	public void testAddCourse()
	{
		/*Course course = new Course(null, null, null);
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		program.addCourse(course);
		assertEquals(course, program.getCourses().get(0));*/
	}
	
	@Test
	public void testRemoveCourse()
	{
		//same problem as above, discuss with Andreas
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
