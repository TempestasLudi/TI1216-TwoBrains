/**
 * 
 */
package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.Course;
import ml.vandenheuvel.TI1216.source.data.Credentials;
import ml.vandenheuvel.TI1216.source.data.DatabaseCommunicator;
import ml.vandenheuvel.TI1216.source.data.Faculty;
import ml.vandenheuvel.TI1216.source.data.Grade;
import ml.vandenheuvel.TI1216.source.data.Program;
import ml.vandenheuvel.TI1216.source.data.User;

/**
 * @author Arnoud van der Leer
 *
 */
public class DatabaseCommunicatorTest {
	/*/private DatabaseCommunicator communicator = new DatabaseCommunicator("192.168.1.111", "TI1216");/*/
	private DatabaseCommunicator communicator = new DatabaseCommunicator("85.151.128.10", "TI1216-test");/**/
	
	/**
	 * "Tests" the constructor and cleans the database (or at least tries to..).
	 */
	@BeforeClass
	public static void testConstructor(){
		// Clear FPC
		DatabaseCommunicator communicator = new DatabaseCommunicator("85.151.128.10", "TI1216-test");
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
		Credentials c1 = new Credentials("Credentials 1", "Pass 1");
		User u1 = new User("User 1", "Pc1", "This is a first description.", new Grade[0]);
		Credentials c2 = new Credentials("Credentials 2", "Pass 2");
		User u2 = new User("User 2", "Pc2", "This is a second description.", new Grade[0]);
		communicator.save(u1, c1);
		communicator.save(u2, c2);
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
		return new Faculty[]{f1, f2};
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
			Faculty faculty = this.communicator.getFaculty("F" + (i+1));
			Faculty[] referenceFaculties = referenceFaculties();
			compareFPCTrees(new Faculty[]{faculty}, new Faculty[]{referenceFaculties[i]});
		}
	}
	
	@Test
	public void testGetFacultyNull() {
		Faculty faculty = this.communicator.getFaculty("F3");
		assertEquals(null, faculty);
	}
	
	@Test
	public void testDeleteAddFaculty() {
		Faculty[] referenceFaculties = referenceFaculties();
		this.communicator.delete(referenceFaculties[0]);
		Faculty[] dbFaculties = this.communicator.getFaculties();
		compareFPCTrees(dbFaculties, new Faculty[]{referenceFaculties[1]});
		
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
		compareFPCTrees(new Faculty[]{referenceFaculty}, new Faculty[]{dbFaculty});
		
		referenceFaculty = referenceFaculties()[0];
		this.communicator.save(referenceFaculty);
		dbFaculty = this.communicator.getFaculty(referenceFaculty.getID());
		compareFPCTrees(new Faculty[]{referenceFaculty}, new Faculty[]{dbFaculty});
	}
	
//	@Test
//	public void testFaculties() {
// 		System.out.println("Fetch 1:");
// 		Faculty[] faculties = this.communicator.getFaculties();
// 		for (int i = 0; i < faculties.length; i++) {
// 			System.out.println("<Faculty(" + faculties[i].getID() + ", " + faculties[i].getName() + ")>");
// 		}
// 		
//		Faculty lr = new Faculty("LR", "Lucht- en ruimtevaart", new ArrayList<Program>());
//		this.communicator.save(lr);
//		Faculty me = new Faculty("3Me", "3-thing-stuff", new ArrayList<Program>());
//		this.communicator.save(me);
//		
// 		System.out.println("Fetch 2:");
// 		faculties = this.communicator.getFaculties();
// 		for (int i = 0; i < faculties.length; i++) {
// 			System.out.println("<Faculty(" + faculties[i].getID() + ", " + faculties[i].getName() + ")>");
// 		}
// 		
//		this.communicator.delete(lr);
//		this.communicator.delete(me);
//	}
}
