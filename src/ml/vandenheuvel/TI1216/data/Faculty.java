package ml.vandenheuvel.TI1216.data;
import java.util.*;

/**
 * Class "Faculty"
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft 2015-2016. 
 */

public class Faculty {

	/**
	 * Class-instances/variables.
	 */
	String name;
	ArrayList<Program> listOfPrograms;
	String ID;
	
	// BEGIN CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public Faculty(){}
	
	/**
	 * General constructor; all class-instances must be given.
	 * @param name
	 * @param listOfPrograms
	 * @param ID
	 */
	public Faculty (String name,ArrayList<Program> listOfPrograms,String ID)
		{this.name=name; this.listOfPrograms=listOfPrograms; this.ID=ID;}
	
	// END CONSTRUCTORS
	
	/**
	 * Returns the name of the faculty.
	 * @return String
	 */
	public String getName()
		{return this.name;}
	
	// BEGIN MODIFIERS
	
	/**
	 * Changes the name-instance of the faculty.
	 * @param name
	 */
	public void setName(String name)
		{this.name = name;}
	
	// END MODIFIERS
	
	/**
	 * Compares Course-objects, based on the respective class-instances.
	 * @param Object 
	 * @return boolean
	 */
	public boolean equals(Object obj){
		boolean result = false;
		if (obj instanceof Faculty){
			Faculty that = (Faculty) obj;
			result = (this.name.equals(that.name))
					&&(this.listOfPrograms.equals(that.listOfPrograms))
					&&(this.ID.equals(that.ID));
			}
		return result;
		}
}
