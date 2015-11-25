package ml.vandenheuvel.TI1216.source.data;

import java.util.*;

/**
 * Class "Program".
 * 
 * @author Andreas Theys, Project Group A1.2, TU Delft 2015-2016.
 */
public class Program {

	/**
	 * Class-instances/variables.
	 */
	private String name;
	private ArrayList<Course> courses;
	private String id;
	private Faculty faculty;

	// BEGIN CONSTRUCTORS

	public Program() {
		//The default constructor
	}

	/**
	 * General constructor. All class-instance required for input.
	 * 
	 * @param id
	 * @param name
	 * @param faculty the faculty the program belongs to
	 * @param courses
	 */
	public Program(String id, String name, Faculty faculty, ArrayList<Course> courses) {
		this.id = id;
		this.name = name;
		this.setFaculty(faculty);
		this.courses = courses;
		for (int i = 0; i < this.courses.size(); i++) {
			this.courses.get(i).setProgram(this);
		}
	}

	// END CONSTRUCTORS

	/**
	 * Returns ID of the program.
	 * 
	 * @return String
	 */
	public String getID(){
		return this.id;
	}
	
	/**
	 * Returns name of the program.
	 * 
	 * @return String
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Returns the course list of the program.
	 * 
	 * @return String
	 */
	public ArrayList<Course> getCourses(){
		return this.courses;
	}

	/**
	 * Returns the faculty of the program.
	 * 
	 * @return the faculty of the program
	 */
	public Faculty getFaculty(){
		return this.faculty;
	}

	// BEGIN MODIFIERS

	/**
	 * Changes the name of the program.
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Sets the faculty the program belongs to.
	 * 
	 * @param faculty the faculty the course belongs to
	 */
	public void setFaculty(Faculty faculty){
		if (this.faculty == null || !this.faculty.equals(faculty)) {
			Faculty oldFaculty = this.faculty;
			this.faculty = faculty;
			if (oldFaculty != null) {
				oldFaculty.removeProgram(this);
			}
			if (this.faculty != null) {
				this.faculty.addProgram(this);
			}
		}
	}

	/**
	 * Adds a course to the list of courses, if it does not exist yet.
	 * 
	 * @param course the course to add
	 */
	public void addCourse(Course course){
		if (!this.courses.contains(course)) {
			this.courses.add(course);
			course.setProgram(this);
		}
	}

	/**
	 * Removes a course from the list of courses.
	 * 
	 * @param course the course to remove
	 */
	public void removeCourse(Course course){
		if (this.courses.contains(course)) {
			this.courses.remove(course);
			course.setProgram(null);
		}
	}

	// END MODIFIERS

	/**
	 * Compares Course-objects, based on the respective class-instances.
	 * 
	 * @param Object
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj){
		boolean result = false;
		if (obj instanceof Program) {
			Program that = (Program) obj;
			result = this.id.equals(that.getID());
		}
		return result;
	}
}
