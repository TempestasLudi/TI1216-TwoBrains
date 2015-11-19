package ml.vandenheuvel.TI1216.source.data;
import java.util.ArrayList;

/**
 * Class "DatabseCommunicator" 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft 2015-2016. 
 */

public class DatabaseCommunicator {
	
	/**
	 * Class-instances/variables.
	 * Temporarily, only a list of known academic faculties is given.
	 */
	ArrayList<Faculty> listOfFaculties;
	
	// BEGIN CONSTRUCTORS
	
	/**
	 * Default-constructor
	 */
	public DatabaseCommunicator(){}
	
	// END CONSTRUCTORS
	
	/**
	 * Return the most recently updated list of known academic faculties.
	 * It is a key component in establishing clear 
	 * @return ArrayList<Faculty>
	 */
	public ArrayList<Faculty> getFaculties()
		{return this.listOfFaculties;}
}
