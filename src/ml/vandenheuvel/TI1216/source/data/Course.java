package ml.vandenheuvel.TI1216.source.data;

/**
 * Class "Course".
 * 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft
 *         2015-2016.
 * @author Arnoud van der Leer
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

	// BEGIN CONSTRUCTORS

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

	// END CONSTRUCTORS

	/**
	 * Gathers the ID of the course.
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

	// BEGIN MODIFIERS

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
			if (program != null) {
				program.addCourse(this);
			}
		}
	}
	
	// END MODIFIERS

	/**
	 * Compares Course objects, based on the respective class-instances.
	 * 
	 * @param obj the object to compare with
	 * @return    true if both objects are the same, otherwise false
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
