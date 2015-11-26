package ml.vandenheuvel.TI1216.test.data;
import ml.vandenheuvel.TI1216.source.data.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;



public class FacultyTest {

	@Test
	public void testConstructorFaculty1() 
	{
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		assertEquals("EWI", faculty.getID());
	}
	
	@Test
	public void testConstructorFaculty2() 
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		assertEquals("Electrical Engineering, Mathematics and Computer Science", faculty.getName());
	}
	
	@Test
	public void testConstructorFaculty3()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", null, list2);
		Program program2 = new Program("TW", "Technische Wiskunde", null, list2);
		list.add(program);
		list.add(program2);
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		assertEquals(faculty,program.getFaculty());
	}
	
	@Test
	public void testGetSetName()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", null, list);
		faculty.setName("Electrical Engineering, Mathematics and Computer Science");
		assertEquals("Electrical Engineering, Mathematics and Computer Science", faculty.getName());
	}
	
	@Test
	public void testAddProgram1()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, list2);
		faculty.addProgram(program);
		assertEquals(program, faculty.getPrograms().get(0));
	}
	
	@Test
	public void testAddProgram2()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, list2);
		faculty.addProgram(program);
		faculty.addProgram(program);
		assertEquals(1,faculty.getPrograms().size());
	}
	
	@Test
	public void testAddProgram3()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		Faculty faculty2 = new Faculty("INF", "Informatica", list);
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty2, list2);
		faculty.addProgram(program);
		assertEquals(faculty, program.getFaculty());
	}
	
	@Test
	public void testRemoveProgram1()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, list2);
		faculty.addProgram(program);
		faculty.removeProgram(program);
		assertEquals(0,faculty.getPrograms().size());
	}
	
	@Test
	public void testRemoveProgram2()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, list2);
		faculty.addProgram(program);
		faculty.removeProgram(program);
		assertNull(program.getFaculty());
	}
	
	@Test
	public void testRemoveProgram3()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program("TI", "Technische Informatica", faculty, list2);
		Program program2 = new Program("TW", "Technische Wiskunde", faculty, list2);
		faculty.addProgram(program);
		faculty.removeProgram(program2);
		assertEquals(1,faculty.getPrograms().size());
	}
	
	@Test
	public void testEqualsPositive()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty1 = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		Faculty faculty2 = new Faculty("EWI", "Elektrotechniek, Wiskunde en Informatica", list);
		assertTrue(faculty1.equals(faculty2));
	}
	
	@Test
	public void testEqualsNegative()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty1 = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		Faculty faculty2 = new Faculty("ewi", "Electrical Engineering, Mathematics and Computer Science", list);
		assertFalse(faculty1.equals(faculty2));
	}

}
