package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ml.vandenheuvel.ti1216.data.Container;
import ml.vandenheuvel.ti1216.data.Course;
import ml.vandenheuvel.ti1216.data.Faculty;
import ml.vandenheuvel.ti1216.data.Program;

public class ContainerTest {

	@Test
	public void testConstructorContainer() {
		Container container = new Container();
		assertNotNull(container);
	}

	@Test
	public void testGetProgram1() {
		Container container = new Container();
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		container.addProgram(program);
		assertEquals(program, container.getProgram(program.getID()));
	}

	@Test
	public void testGetProgram2() {
		Container container = new Container();
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		container.addProgram(program);
		assertNull(container.getProgram("RandomID"));
	}

	@Test
	public void testGetFaculty1() {
		Container container = new Container();
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		container.addFaculty(faculty);
		assertEquals(faculty, container.getFaculty(faculty.getID()));
	}

	@Test
	public void testGetFaculty2() {
		Container container = new Container();
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		container.addFaculty(faculty);
		assertNull(container.getFaculty("RandomID"));
	}

	@Test
	public void testAddProgram() {
		Container container = new Container();
		ArrayList<Course> list = new ArrayList<Course>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", null);
		Program program = new Program("TI", "Technische Informatica", faculty, list);
		container.addProgram(program);
		container.addProgram(program);
		assertEquals(program, container.getProgram(program.getID()));
	}

	@Test
	public void testAddFaculty() {
		Container container = new Container();
		ArrayList<Program> list = new ArrayList<Program>();
		Faculty faculty = new Faculty("EWI", "Electrical Engineering, Mathematics and Computer Science", list);
		container.addFaculty(faculty);
		container.addFaculty(faculty);
		assertEquals(faculty, container.getFaculty(faculty.getID()));
	}

}
