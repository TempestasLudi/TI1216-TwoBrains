package ml.vandenheuvel.ti1216.data;

import org.json.JSONObject;

/**
 * The data class Course holds a unique ID for this course, a name and a program it's part of.
 */
public class Course {
	
	/**
	 * The course ID.
	 */
	private String id;
	
	/**
	 * The course name.
	 */
	private String name;
	
	/**
	 * The program of study the course belongs to.
	 */
	private Program program;
	
	/**
	 * General constructor.
	 * 
	 * @param name    the course name
	 * @param id      the course ID
	 * @param program the program the course belongs to
	 */
	public Course(String id, String name, Program program) {
		this.id = id;
		this.name = name;
		this.setProgram(program);
	}

	/**
	 * Get the ID of the course.
	 * 
	 * @return the ID of the course
	 */
	public String getID(){
		return this.id;
	}
	
	/**
	 * Gathers the name of the course.
	 * 
	 * @return the name of the course
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Gathers the program of the course.
	 * 
	 * @return the program the course belongs to
	 */
	public Program getProgram(){
		return this.program;
	}

	/**
	 * Changes the name of the course.
	 * 
	 * @param name the name of the course
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Changes the program the course belongs to.
	 * 
	 * @param program the program the course belongs to
	 */
	public void setProgram(Program program){
		if (this.program == null || !this.program.equals(program)) {
			Program oldProgram = this.program;
			this.program = null;
			if (oldProgram != null) {
				oldProgram.removeCourse(this);
			}
			this.program = program;
			if (this.program != null) {
				this.program.addCourse(this);
			}
		}
	}
	
	/**
	 * Creates a JSON object based on the Course object.
	 * 
	 * @return a JSON object based on the Course object
	 */
	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("name", this.name);
		result.put("program", this.program.getID());
		return result;
	}
	
	/**
	 * Creates a Course object based on a JSON object.
	 * 
	 * @param json the JSON object
	 * @return a Course object based on the JSON object
	 */
	public static Course fromJSON(JSONObject json) {
		return new Course(json.getString("id"), json.getString("name"), null);
	}

	/**
	 * Compares Course objects, based on the respective class-instances.
	 * 
	 * @param obj the object to compare with
	 * @return true if both objects are the same, otherwise false
	 */
	@Override
	public boolean equals(Object obj){
		boolean result = false;
		if (obj instanceof Course) {
			Course that = (Course) obj;
			result = this.id.equals(that.getID());
		}
		return result;
	}

}
