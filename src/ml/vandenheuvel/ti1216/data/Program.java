package ml.vandenheuvel.ti1216.data;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The data class which holds a list of all courses of that program
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
		if(courses==null){
			this.courses = new ArrayList<>();
		}
		else{
			this.courses = courses;
		}
		for (int i = 0; i < this.courses.size(); i++) {
			this.courses.get(i).setProgram(this);
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
			this.faculty=faculty;
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
	
	/**
	 * Creates a JSON object based on this Program object.
	 * 
	 * @return a JSON object based on this Program object
	 */
	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("name", this.name);
		result.put("faculty", this.faculty.getID());
		JSONArray jsonCourses = new JSONArray();
		for (int i = 0; i < this.courses.size(); i++) {
			jsonCourses.put(this.courses.get(i).toJSON());
		}
		result.put("courses", jsonCourses);
		return result;
	}
	
	/**
	 * Creates a Program object based on a JSON object.
	 * 
	 * @param json the JSON object
	 * @return a Program object based on the JSON object
	 */
	public static Program fromJSON(JSONObject json) {
		JSONArray coursesJSON = json.getJSONArray("courses");
		ArrayList<Course> courses = new ArrayList<>();
		for (int i = 0; i < coursesJSON.length(); i++) {
			courses.add(Course.fromJSON(coursesJSON.getJSONObject(i)));
		}
		return new Program(json.getString("id"), json.getString("name"), null, courses);
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
