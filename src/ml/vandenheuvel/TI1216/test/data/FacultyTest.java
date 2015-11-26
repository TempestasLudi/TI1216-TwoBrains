package ml.vandenheuvel.TI1216.test.data;
import ml.vandenheuvel.TI1216.source.data.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;



public class FacultyTest {

	@Test
	public void testConstructorFaculty1() 
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
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
	public void testGetSetName()
	{
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", null, list);
		faculty.setName("Electrical Engineering, Mathematics and Computer Science");
		assertEquals("Electrical Engineering, Mathematics and Computer Science", faculty.getName());
	}
	
	@Test
	public void testAddProgram()
	{
		/*ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		ArrayList<Course> list2 = new ArrayList<Course>();
		Program program = new Program(null, null, null, list2);
		faculty.addProgram(program);
		assertEquals(program, faculty.getPrograms().get(0));*/
	}
	
	@Test
	public void testRemoveProgram()
	{
		//same problem as above, discuss with Andreas
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
