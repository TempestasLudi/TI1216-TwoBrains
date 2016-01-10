package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseCommunicatorTest {
	/*
	 * /private DatabaseCommunicator communicator = new
	 * DatabaseCommunicator("192.168.1.111", "TI1216-test");/
	 */
	private DatabaseCommunicator communicator = new DatabaseCommunicator("tempestasludi.com", "TI1216-test", "TI1216", "3t.uGmL365j2f7B");

	/**
	 * "Tests" the constructor and cleans the database (or at least tries to..).
	 */
	@BeforeClass
	public static void testConstructor() {
		// Clear FPC
		/*
		 * /DatabaseCommunicator communicator = new
		 * DatabaseCommunicator("192.168.1.111", "TI1216-test");/
		 */
		DatabaseCommunicator communicator = new DatabaseCommunicator("tempestasludi.com", "TI1216-test", "TI1216",
				"3t.uGmL365j2f7B");/**/
		Faculty[] faculties = communicator.getFaculties();
		for (int i = 0; i < faculties.length; i++) {
			communicator.delete(faculties[i]);
		}

		// Insert FPC
		Faculty[] referenceFaculties = referenceFaculties();
		communicator.save(referenceFaculties[0]);
		communicator.save(referenceFaculties[1]);

		// Clear Users
		User[] users = communicator.getUsers();
		for (int i = 0; i < users.length; i++) {
			communicator.delete(users[i]);
		}

		// Insert Users
		Credentials[] referenceCredentials = referenceCredentials();
		User[] referenceUsers = referenceUsers();
		communicator.save(referenceUsers[0], referenceCredentials[0]);
		communicator.save(referenceUsers[1], referenceCredentials[1]);
	}

	private static void compareFPCTrees(Faculty[] dbFaculties, Faculty[] referenceFaculties) {
		assertEquals(referenceFaculties.length, dbFaculties.length);
		for (int rf = 0; rf < referenceFaculties.length; rf++) {
			boolean found = false;
			for (int df = 0; df < dbFaculties.length; df++) {
				if (dbFaculties[df].equals(referenceFaculties[rf])) {
					Faculty dbFaculty = dbFaculties[df];
					Faculty referenceFaculty = referenceFaculties[rf];
					assertEquals(referenceFaculty.getName(), dbFaculty.getName());
					found = true;
					ArrayList<Program> dbPrograms = dbFaculty.getPrograms();
					ArrayList<Program> referencePrograms = referenceFaculty.getPrograms();
					assertEquals(referencePrograms.size(), dbPrograms.size());
					for (int dp = 0; dp < dbPrograms.size(); dp++) {
						int rp = referencePrograms.indexOf(dbPrograms.get(dp));
						assertNotEquals(-1, rp);
						Program dbProgram = dbPrograms.get(dp);
						Program referenceProgram = referencePrograms.get(rp);
						assertEquals(referenceProgram.getFaculty(), dbProgram.getFaculty());
						assertEquals(referenceProgram.getName(), dbProgram.getName());
						ArrayList<Course> dbCourses = dbProgram.getCourses();
						ArrayList<Course> referenceCourses = referenceProgram.getCourses();
						assertEquals(dbCourses.size(), referenceCourses.size());
						for (int dc = 0; dc < dbCourses.size(); dc++) {
							int rc = referenceCourses.indexOf(dbCourses.get(dc));
							assertNotEquals(-1, rc);
							Course dbCourse = dbCourses.get(dc);
							Course referenceCourse = referenceCourses.get(rc);
							assertEquals(referenceCourse.getProgram(), dbCourse.getProgram());
							assertEquals(referenceCourse.getName(), dbCourse.getName());
						}
					}
				}
			}
			assertTrue(found);
		}
	}

	private static Faculty[] referenceFaculties() {
		Faculty f1 = new Faculty("F1", "Faculty 1", new ArrayList<Program>());
		Faculty f2 = new Faculty("F2", "Faculty 2", new ArrayList<Program>());
		Program p11 = new Program("P1.1", "Program 1.1", f1, new ArrayList<Course>());
		new Program("P1.2", "Program 1.2", f1, new ArrayList<Course>());
		Program p21 = new Program("P2.1", "Program 2.1", f2, new ArrayList<Course>());
		new Course("C1.1.1", "Course 1.1.1", p11);
		new Course("C1.1.2", "Course 1.1.2", p11);
		new Course("C1.1.3", "Course 1.1.3", p11);
		new Course("C1.1.4", "Course 1.1.4", p11);
		new Course("C2.1.1", "Course 2.1.1", p21);

		return new Faculty[] { f1, f2 };
	}

	private static Program[] referencePrograms() {
		Faculty[] faculties = referenceFaculties();
		Program[] programs = new Program[3];
		programs[0] = faculties[0].getPrograms().get(0);
		programs[1] = faculties[0].getPrograms().get(1);
		programs[2] = faculties[1].getPrograms().get(0);
		return programs;
	}

	private static Course[] referenceCourses() {
		Program[] programs = referencePrograms();
		Course[] courses = new Course[5];
		courses[0] = programs[0].getCourses().get(0);
		courses[1] = programs[0].getCourses().get(1);
		courses[2] = programs[0].getCourses().get(2);
		courses[3] = programs[0].getCourses().get(3);
		courses[4] = programs[2].getCourses().get(0);
		return courses;
	}

	private static User[] referenceUsers() {
		User[] users = new User[2];
		// users[0] = new User("User 1", "Pc1", "This is a first description.",
		// new Grade[] {new Grade("TI1216",10),new Grade("TI1506",9)});
		users[0] = new User("User 1", "Pc1", "This is a first description.", new Grade[0]);
		users[1] = new User("User 2", "Pc2", "This is a second description.", new Grade[0]);
		return users;
	}

	private static Credentials[] referenceCredentials() {
		Credentials[] credentials = new Credentials[2];
		credentials[0] = new Credentials("User 1", "Pass 1");
		credentials[1] = new Credentials("User 2", "Pass 2");
		return credentials;
	}

	@Test
	public void testGetFaculties() {
		Faculty[] dbFaculties = this.communicator.getFaculties();
		Faculty[] referenceFaculties = referenceFaculties();
		compareFPCTrees(dbFaculties, referenceFaculties);
	}

	@Test
	public void testGetFacultyExists() {
		for (int i = 0; i < 2; i++) {
			Faculty faculty = this.communicator.getFaculty("F" + (i + 1));
			Faculty[] referenceFaculties = referenceFaculties();
			compareFPCTrees(new Faculty[] { faculty }, new Faculty[] { referenceFaculties[i] });
		}
	}

	@Test
	public void testGetFacultyNull() {
		Faculty faculty = this.communicator.getFaculty("F3");
		assertNull(faculty);
	}

	@Test
	public void testDeleteAddFaculty() {
		Faculty[] referenceFaculties = referenceFaculties();
		this.communicator.delete(referenceFaculties[0]);
		Faculty[] dbFaculties = this.communicator.getFaculties();
		compareFPCTrees(dbFaculties, new Faculty[] { referenceFaculties[1] });

		this.communicator.save(referenceFaculties[0]);
		dbFaculties = this.communicator.getFaculties();
		compareFPCTrees(dbFaculties, referenceFaculties);
	}

	@Test
	public void testSaveFaculty() {
		Faculty referenceFaculty = referenceFaculties()[0];
		referenceFaculty.removeProgram(referenceFaculty.getPrograms().get(0));
		referenceFaculty.setName("Faculty 5");
		this.communicator.save(referenceFaculty);
		Faculty dbFaculty = this.communicator.getFaculty(referenceFaculty.getID());
		compareFPCTrees(new Faculty[] { referenceFaculty }, new Faculty[] { dbFaculty });

		referenceFaculty = referenceFaculties()[0];
		this.communicator.save(referenceFaculty);
		dbFaculty = this.communicator.getFaculty(referenceFaculty.getID());
		compareFPCTrees(new Faculty[] { referenceFaculty }, new Faculty[] { dbFaculty });
	}

	@Test
	public void testGetPrograms() {
		Program[] databasePrograms = this.communicator.getPrograms();
		Program[] referencePrograms = referencePrograms();
		for (int i = 0; i < 3; i++) {
			assertEquals(referencePrograms[i], databasePrograms[i]);
		}
	}

	@Test
	public void testGetProgramExists() {
		Program[] referencePrograms = referencePrograms();
		Program p11 = this.communicator.getProgram("P1.1");
		Program p12 = this.communicator.getProgram("P1.2");
		Program p21 = this.communicator.getProgram("P2.1");
		assertEquals(referencePrograms[0], p11);
		assertEquals(referencePrograms[1], p12);
		assertEquals(referencePrograms[2], p21);
	}

	@Test
	public void testGetProgramNull() {
		Program program = this.communicator.getProgram("RandomID");
		assertNull(program);
	}

	@Test
	public void testGetProgramEmpty() {
		Program[] referencePrograms = referencePrograms();
		this.communicator.delete(referencePrograms[0]);
		this.communicator.delete(referencePrograms[1]);
		this.communicator.delete(referencePrograms[2]);
		assertNull(this.communicator.getProgram("P1.1"));
		assertNull(this.communicator.getProgram("P1.2"));
		assertNull(this.communicator.getProgram("P2.1"));
		this.communicator.save(referencePrograms[0]);
		this.communicator.save(referencePrograms[1]);
		this.communicator.save(referencePrograms[2]);
		assertNotNull(this.communicator.getProgram("P1.1"));
		assertNotNull(this.communicator.getProgram("P1.2"));
		assertNotNull(this.communicator.getProgram("P2.1"));
	}

	@Test
	public void testDeleteAddProgram() {
		Program[] referencePrograms = referencePrograms();
		assertNotNull(this.communicator.getProgram("P1.1"));
		this.communicator.delete(referencePrograms[0]);
		assertNull(this.communicator.getProgram("P1.1"));
		this.communicator.save(referencePrograms[0]);
		assertNotNull(this.communicator.getProgram("P1.1"));
	}

	@Test
	public void testSaveProgram() {
		Program program = referencePrograms()[0];
		program.removeCourse(program.getCourses().get(0));
		program.setName("RandomProgram");
		this.communicator.save(program);
		Program databaseProgram = this.communicator.getProgram(program.getID());
		assertEquals(program, databaseProgram);
		program = referencePrograms()[0];
		this.communicator.save(program);
		databaseProgram = this.communicator.getProgram(program.getID());
		assertEquals(program, databaseProgram);
	}

	@Test
	public void testGetCourses() {
		Course[] databaseCourses = this.communicator.getCourses();
		Course[] referenceCourses = referenceCourses();
		for (int i = 0; i < 5; i++) {
			assertEquals(referenceCourses[i], databaseCourses[i]);
		}
	}

	@Test
	public void testGetCourseExists() {
		Course[] referenceCourses = referenceCourses();
		Course c111 = this.communicator.getCourse("C1.1.1");
		Course c112 = this.communicator.getCourse("C1.1.2");
		Course c113 = this.communicator.getCourse("C1.1.3");
		Course c114 = this.communicator.getCourse("C1.1.4");
		Course c211 = this.communicator.getCourse("C2.1.1");
		assertEquals(referenceCourses[0], c111);
		assertEquals(referenceCourses[1], c112);
		assertEquals(referenceCourses[2], c113);
		assertEquals(referenceCourses[3], c114);
		assertEquals(referenceCourses[4], c211);
	}

	@Test
	public void testGetCourseNull() {
		Course course = this.communicator.getCourse("RandomID");
		assertNull(course);
	}

	@Test
	public void testDeleteAddCourse() {
		Course[] referenceCourses = referenceCourses();
		assertNotNull(this.communicator.getCourse("C1.1.1"));
		this.communicator.delete(referenceCourses[0]);
		assertNull(this.communicator.getCourse("C1.1.1"));
		this.communicator.save(referenceCourses[0]);
		assertNotNull(this.communicator.getCourse("C1.1.1"));
	}

	@Test
	public void testSaveCourse() {
		Course course = referenceCourses()[0];
		course.setName("RandomCourse");
		this.communicator.save(course);
		Course databaseCourse = this.communicator.getCourse(course.getID());
		assertEquals(course, databaseCourse);
		course = referenceCourses()[0];
		this.communicator.save(course);
		databaseCourse = this.communicator.getCourse(course.getID());
		assertEquals(course, databaseCourse);
	}

	@Test
	public void testCanRegister1() {
		Credentials credentials = new Credentials(null, null);
		assertFalse(this.communicator.canRegister(credentials));
	}

	@Test
	public void testCanRegister2() {
		Credentials credentials = new Credentials("", "");
		assertFalse(this.communicator.canRegister(credentials));
	}

	@Test
	public void testCanRegister3() {
		Credentials credentials = new Credentials("yinghaodai", "wachtwoord");
		assertTrue(this.communicator.canRegister(credentials));
	}

	@Test
	public void testCanRegister4() {
		Credentials credentials = new Credentials("yinghaodai", "");
		assertFalse(this.communicator.canRegister(credentials));
	}

	@Test
	public void testCanRegister5() {
		Credentials credentials = new Credentials("", "wachtwoord");
		assertFalse(this.communicator.canRegister(credentials));
	}

	@Test
	public void testCanRegister6() {
		Credentials credentials = new Credentials("User 1", "Pass 1");
		assertFalse(this.communicator.canRegister(credentials));
	}

	@Test
	public void testCanRegister7() {
		Credentials credentials = new Credentials("User 1", null);
		assertFalse(this.communicator.canRegister(credentials));
	}

	@Test
	public void testCanLogin1() {
		Credentials credentials = new Credentials("yinghaodai", "wachtwoord");
		assertFalse(this.communicator.canLogin(credentials));
	}

	@Test
	public void testCanLogin2() {
		Credentials credentials = new Credentials("User 1", "Pass 1");
		assertTrue(this.communicator.canLogin(credentials));
	}

	@Test
	public void testGetUsers() {
		User[] databaseUsers = this.communicator.getUsers();
		User[] referenceUsers = referenceUsers();
		assertEquals(referenceUsers[0], databaseUsers[0]);
		assertEquals(referenceUsers[1], databaseUsers[1]);
	}

	@Test
	public void testGetUserExists() {
		User[] referenceUsers = referenceUsers();
		User user1 = this.communicator.getUser("User 1");
		User user2 = this.communicator.getUser("User 2");
		assertEquals(referenceUsers[0], user1);
		assertEquals(referenceUsers[1], user2);
	}

	@Test
	public void testGetUserNull() {
		User user = this.communicator.getUser("Non-existing User");
		assertNull(user);
	}

}
