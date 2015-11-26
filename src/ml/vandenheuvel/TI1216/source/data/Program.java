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


	/**
	 * General constructor. All class-instance required for input.
	 * 
	 * @param id the unique ID of the program
	 * @param name the name of the program
	 * @param faculty the faculty the program belongs to
	 * @param courses an ArrayList containing all courses of the program
	 */
	public Program(String id, String name, Faculty faculty, ArrayList<Course> courses) {
		this.id = id;
		this.name = name;
		this.setFaculty(faculty);
		this.courses = courses;
		if(this.courses.size()>0){
			for (int i = 0; i < this.courses.size(); i++) {
				this.courses.get(i).setProgram(this);
			}
		}
	}

	/**
	 * Returns ID of the program.
	 * 
	 * @return the unique ID of the program
	 */
	public String getID(){
		return this.id;
	}
	
	/**
	 * Returns name of the program.
	 * 
	 * @return the name of the program
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Returns the course list of the program.
	 * 
	 * @return an ArrayList containing all courses of the program
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
	 * @param name the new name of the program
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Sets the faculty the program belongs to.
	 * 
	 * @param faculty the new faculty the course belongs to
	 */
	public void setFaculty(Faculty faculty){
		if (this.faculty == null || !this.faculty.equals(faculty)) {
			Faculty oldFaculty = this.faculty;
			this.faculty = null;
			if (oldFaculty != null) {
				oldFaculty.removeProgram(this);
			}
			if (this.faculty != null && this.faculty.getPrograms()!=null) {
				this.faculty.addProgram(this);
			}
			this.faculty=faculty;
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
	 * checks whether two Programs are equal to each other
	 * 
	 * @param obj the object to which the Program is compared
	 * @return true if the Programs have the same unique ID, otherwise false
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
