package ml.vandenheuvel.TI1216.source.data;
import java.util.*;
/**
 * Class "Program".
 * @author Andreas Theys, Project Group A1.2, TU Delft 2015-2016. 
 */
public class Program {
	
	/**
	 * Class-instances/variables.
	 */
	String name;
	ArrayList<Course> listOfCourses;
	String ID;
	
	
	// BEGIN CONSTRUCTORS
	
	/**
	 * Default Constructor
	 */
	public Program(){}
	
	/**
	 * General constructor. All class-instance required for input.
	 * @param name
	 * @param listOfCourses
	 * @param ID
	 */
	public Program(String name, ArrayList<Course> listOfCourses, String ID)
		{this.name=name; this.listOfCourses=listOfCourses; this.ID=ID;}
	
	// END CONSTRUCTORS
	
	/**
	 * Returns name of the program.
	 * @return String
	 */
	public String getName()
		{return this.name;}
	
	/**
	 * Returns the course list of the program.
	 * @return String
	 */
	public ArrayList<Course> getCourses()
		{return this.listOfCourses;}
	
	/**
	 * Returns ID of the program.
	 * @return String
	 */
	public String getID()
		{return this.ID;}
	
	// BEGIN MODIFIERS
	
	/**
	 * Changes the name of the program.
	 * @param name
	 */
	public void setName(String name)
		{this.name = name;}
	
	/**
	 * Changes the ID of the program.
	 * @param ID
	 */
	public void setID(String ID)
		{this.ID = ID;}
	
	// END MODIFIERS
	
	/**
	 * Compares Course-objects, based on the respective class-instances.
	 * @param Object 
	 * @return boolean
	 */
	public boolean equals(Object obj){
		boolean result = false;
		if (obj instanceof Program){
			Program that = (Program) obj;
			result = (this.name.equals(that.name))
					&&(this.listOfCourses.equals(that.listOfCourses))
					&&(this.ID.equals(that.ID));		
			}
		return result;
		}
}
