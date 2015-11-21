package ml.vandenheuvel.TI1216.source.data;

import java.util.ArrayList;

/**
 * Class "Faculty"
 * 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft
 *         2015-2016.
 */

public class Faculty {

	/**
	 * Class-instances/variables.
	 */
	private String name;
	private ArrayList<Program> programs;
	private String ID;

	// BEGIN CONSTRUCTORS

	/**
	 * Default Constructor
	 */
	public Faculty() {
	}

	/**
	 * General constructor; all class-instances must be given.
	 * 
	 * @param ID
	 * @param name
	 * @param programs
	 */
	public Faculty(String ID, String name, ArrayList<Program> programs) {
		this.ID = ID;
		this.name = name;
		this.programs = programs;
		for (int i = 0; i < programs.size(); i++) {
			programs.get(i).setFaculty(this);
		}
	}

	// END CONSTRUCTORS

	/**
	 * Returns the id of the faculty.
	 * 
	 * @return String
	 */
	public String getID(){
		return this.ID;
	}

	/**
	 * Returns the name of the faculty.
	 * 
	 * @return String
	 */
	public String getName(){
		return this.name;
	}

	/*
	 * Returns the programs of the faculty.
	 * 
	 * @return the programs of the faculty
	 */
	public ArrayList<Program> getPrograms(){
		return this.programs;
	}

	// BEGIN MODIFIERS

	/**
	 * Changes the name-instance of the faculty.
	 * 
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Adds a program to the list of programs, if it does not exist yet and adds the faculty to the program.
	 * 
	 * @param program the program to add
	 */
	public void addProgram(Program program){
		if (!this.programs.contains(program)) {
			this.programs.add(program);
			program.setFaculty(this);
		}
	}

	/**
	 * Removes a program from the list of programs and removes the faculty from the program.
	 * 
	 * @param program the program to remove
	 */
	public void removeProgram(Program program){
		if (this.programs.contains(program)) {
			this.programs.remove(program);
			program.setFaculty(null);
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
		if (obj instanceof Faculty) {
			Faculty that = (Faculty) obj;
			result = (this.name.equals(that.name)) && (this.programs.equals(that.programs))
					&& (this.ID.equals(that.ID));
		}
		return result;
	}
}
