package ml.vandenheuvel.TI1216.source.data;

/**
 * Class "Course".
 * 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft
 *         2015-2016.
 */

public class Course {

	/**
	 * Class-instances/variables.
	 */
	private String name;
	private String ID;
	private Program program;

	// BEGIN CONSTRUCTORS

	/**
	 * Default Constructor
	 */
	public Course() {
	}

	/**
	 * General constructor. All class-instance required for input.
	 * 
	 * @param name
	 * @param ID
	 * @param program
	 */
	public Course(String ID, String name, Program program) {
		this.ID = ID;
		this.name = name;
		this.program = program;
	}

	// END CONSTRUCTORS

	/**
	 * Gathers the ID of the course.
	 * 
	 * @return String
	 */
	public String getID(){
		return this.ID;
	}
	
	/**
	 * Gathers the name of the course.
	 * 
	 * @return String
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Gathers the program name of the course.
	 * 
	 * @return String
	 */
	public Program getProgram(){
		return this.program;
	}

	// BEGIN MODIFIERS

	/**
	 * Changes the name of the course.
	 * 
	 * @param name
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
		if (!this.program.equals(program)) {
			Program oldProgram = this.program;
			this.program = program;
			if (oldProgram != null) {
				oldProgram.removeCourse(this);
			}
			if (this.program != null) {
				this.program.addCourse(this);
			}
		}
	}
	// END MODIFIERS

	/**
	 * Compares Course-objects, based on the respective class-instances.
	 * 
	 * @param Object
	 * @return boolean
	 */
	public boolean equals(Object obj){
		boolean result = false;
		if (obj instanceof Course) {
			Course that = (Course) obj;
			result = (this.name.equals(that.name)) && (this.ID.equals(that.ID)) && (this.program.equals(that.program));
		}
		return result;
	}

}
