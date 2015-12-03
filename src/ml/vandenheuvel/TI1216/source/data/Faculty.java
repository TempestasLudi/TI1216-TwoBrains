package ml.vandenheuvel.TI1216.source.data;

import java.util.ArrayList;

import org.json.JSONObject;

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
	private String id;

	// BEGIN CONSTRUCTORS
	
	/**
	 * General constructor.
	 * 
	 * @param id
	 * @param name
	 * @param programs
	 */
	public Faculty(String ID, String name, ArrayList<Program> programs){
		this.id = ID;
		this.name = name;
		if(programs==null){
			this.programs = new ArrayList<Program>();
		}
		else{
			this.programs = programs;
		}
		for (int i = 0; i < this.programs.size(); i++) {
			this.programs.get(i).setFaculty(this);
		}
	}

	// END CONSTRUCTORS

	/**
	 * Returns the id of the faculty.
	 * 
	 * @return String
	 */
	public String getID(){
		return this.id;
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
	
	/**
	 * Creates a JSON object based on the Faculty object.
	 * 
	 * @return a JSON object based on the Faculty object
	 */
	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("name", this.name);
		return result;
	}
	
	/**
	 * Creates a Faculty object based on a JSON object.
	 * 
	 * @param json the JSON object
	 * @param data a container containing the additional objects
	 * @return a Faculty object based on the JSON object
	 */
	public static Faculty fromJSON(JSONObject json, Container data) {
		return new Faculty(json.getString("id"), json.getString("name"), new ArrayList<Program>());
	}

	// END MODIFIERS

	/**
	 * Compares Faculty-objects, based on the respective class-instances.
	 * 
	 * @param obj the Object to which the Faculty is compared
	 * @return true if the two Faculties are equal, otherwise false
	 */
	@Override
	public boolean equals(Object obj){
		boolean result = false;
		if (obj instanceof Faculty) {
			Faculty that = (Faculty) obj;
			result = this.id.equals(that.id);
		}
		return result;
	}
}
