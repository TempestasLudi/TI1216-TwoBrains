package ml.vandenheuvel.TI1216.source.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * DatabaseCommunicator is a class that handles communication with a database.
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft 2015-2016. 
 */

public class DatabaseCommunicator {
	
	/**
	 * The MySQL username.
	 */
	private static String username = "TI1216";
	
	/**
	 * The MySQL password.
	 */
	private static String password = "3t.uGmL365j2f7B";
	
	/**
	 * The database dataSource.
	 */
	private DataSource dataSource;
	
	/**
	 * Constructor, initializes the dataSource.
	 * 
	 * @param hostname the database host name
	 * @param database the database name
	 */
	public DatabaseCommunicator(String hostname, String database){
		MysqlDataSource mysqlDS = null;
        mysqlDS = new MysqlDataSource();
        mysqlDS.setURL("jdbc:mysql://" + hostname + "/" + database);
        mysqlDS.setUser(username);
        mysqlDS.setPassword(password);
        this.dataSource = mysqlDS;
	}

	/**
	 * Fetches data from the database.
	 * 
	 * @param query the query to be executed
	 * @return      the data matching the query
	 */
	private ResultSet get(String query) throws SQLException{
		Connection connection = this.dataSource.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
	}
	
	/**
	 * Executes a MySQL query.
	 * 
	 * @param query the query to be executed
	 */
	private void execute(String query) throws SQLException{
		Connection connection = this.dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);
	}

	/**
	 * Fetches all faculties from the database.
	 * 
	 * @return all faculties in the database
	 */
	public Faculty[] getFaculties(){
		String query = "SELECT f.ID as facultyID, f.name AS facultyName, p.id AS programID, p.name AS programName, c.ID as courseID, c.name AS courseName "
				+ "FROM faculty AS f "
				+ "LEFT JOIN program AS p ON f.ID = p.facultyID "
				+ "LEFT JOIN course AS c ON p.ID = c.programID "
				+ "ORDER BY facultyID ASC, programID ASC ";
		try {
			ResultSet resultSet = this.get(query);
			return createTree(resultSet);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Fetches a faculty from the database.
	 * 
	 * @param ID the id of the faculty
	 * @return   the faculty with the id if found, otherwise null
	 */
	public Faculty getFaculty(String ID){
		String query = "SELECT f.ID as facultyID, f.name AS facultyName, p.id AS programID, p.name AS programName, c.ID as courseID, c.name AS courseName "
				+ "FROM faculty AS f "
				+ "LEFT JOIN program AS p ON f.ID = p.facultyID "
				+ "LEFT JOIN course AS c ON p.ID = c.programID "
				+ "WHERE facultyID = '" + ID + "' "
				+ "ORDER BY facultyID ASC, programID ASC ";
		try {
			ResultSet resultSet = this.get(query);
			Faculty[] faculties = createTree(resultSet);
			if (faculties.length > 0) {
				return faculties[0];
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Creates a Faculty-Program-Course tree structure from a MySQL result set.
	 * 
	 * @param resultSet the result set
	 * @return          a Faculty-Program-Course tree structure from the result set
	 */
	private Faculty[] createTree(ResultSet resultSet) throws SQLException{
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		Faculty faculty = null;
		Program program = null;
		while (resultSet.next()) {
			if (faculty == null || !resultSet.getString("facultyID").equals(faculty.getID())) {
				faculty = new Faculty(resultSet.getString("facultyID"), resultSet.getString("facultyName"), new ArrayList<Program>());
				faculties.add(faculty);
			}
			if (resultSet.getString("programID") != null) {
				if (program == null || !resultSet.getString("programID").equals(program.getID())) {
					program = new Program(resultSet.getString("programID"), resultSet.getString("programName"), faculty, new ArrayList<Course>());
				}
			}
			if (resultSet.getString("courseID") != null) {
				new Course(resultSet.getString("courseID"), resultSet.getString("courseName"), program);
			}
		}
        Faculty[] result = new Faculty[faculties.size()];
        faculties.toArray(result);
		return result;
	}
	
	/**
	 * Updates a faculty if it already exists, otherwise, adds a new one.
	 * Also, the programs of the faculty are saved.
	 * 
	 * @param faculty the faculty to add
	 */
	public void save(Faculty faculty){
		Faculty existing = this.getFaculty(faculty.getID());
		try {
			ArrayList<Program> programs = faculty.getPrograms();
			if (existing == null) {
				this.execute("INSERT INTO faculty (ID, name) VALUES ('" + faculty.getID() + 
						"', '" + faculty.getName() + "')");
				for (int i = 0; i < programs.size(); i++) {
					this.save(programs.get(i));
				}
			}
			else {
				this.execute("UPDATE faculty SET name = '" + faculty.getName() + "' WHERE ID = '" + faculty.getID() + 
						"'");
				ArrayList<Program> existingPrograms = existing.getPrograms();
				for (int i = 0; i < programs.size(); i++) {
					this.save(programs.get(i));
				}
				for (int i = 0; i < existingPrograms.size(); i++) {
					if (!programs.contains(existingPrograms.get(i))) {
						this.delete(existingPrograms.get(i));
					}
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Removes a faculty from the database.
	 * 
	 * @param faculty the faculty to remove
	 */
	public void delete(Faculty faculty){
		try {
			this.execute("DELETE FROM faculty WHERE ID = \'"+faculty.getID()+"\'");
			ArrayList<Program> programs = faculty.getPrograms();
			for (int i = 0; i < programs.size(); i++) {
				this.delete(programs.get(i));
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Fetches all programs from the database.
	 * 
	 * @return all programs in the database
	 */
	public Program[] getPrograms(){
		ArrayList<Program> programs = new ArrayList<Program>();
		Faculty[] faculties = this.getFaculties();
		for (int i = 0; i < faculties.length; i++) {
			programs.addAll(faculties[i].getPrograms());
		}
		Program[] result = new Program[programs.size()];
		programs.toArray(result);
        return result;
	}
	
	/**
	 * Fetches a program from the database.
	 * 
	 * @param ID the id of the program
	 * @return   the program with the id if found, otherwise null
	 */
	public Program getProgram(String ID){
		try {
			ResultSet resultSet = this.get("SELECT * FROM program WHERE ID = '" + ID + "'");
			if (resultSet.next()) {
				Faculty faculty = this.getFaculty(resultSet.getString("facultyID"));
				ArrayList<Program> programs = faculty.getPrograms();
				for (int i = 0; i < programs.size(); i++) {
					if (programs.get(i).getID().equals(ID)) {
						return programs.get(i);
					}
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Saves a program. If it already exists, updates the current record. Otherwise, adds a new one.
	 * 
	 * @param program the faculty to add
	 */
	public void save(Program program){
		Program existing = this.getProgram(program.getID());
		try {
			if (existing == null) {
				this.execute("INSERT INTO program (ID, name) VALUES ('" + program.getID() + 
						"', '" + program.getName() + "')");
			}
			else {
				this.execute("UPDATE program SET name = '" + program.getName() + "' WHERE ID = '" + program.getID() + 
						"'");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Removes a program from the database.
	 * 
	 * @param program the program to remove
	 */
	public void delete(Program program){
		try {
			this.execute("DELETE FROM program WHERE ID = \'"+program.getID()+"\'");
			ArrayList<Course> courses = program.getCourses();
			for (int i = 0; i < courses.size(); i++) {
				this.delete(courses.get(i));
			}
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Fetches all courses from the database.
	 * 
	 * @return all courses in the database
	 */
	public Course[] getCourses(){
		ArrayList<Course> courses = new ArrayList<Course>();
		Program[] programs = this.getPrograms();
		for (int i = 0; i < programs.length; i++) {
			courses.addAll(programs[i].getCourses());
		}
		Course[] result = new Course[courses.size()];
		courses.toArray(result);
        return result;
	}
	
	/**
	 * Fetches a course from the database.
	 * 
	 * @param ID the id of the course
	 * @return   the course with the id if found, otherwise null
	 */
	public Course getCourse(String ID){
		try {
			ResultSet resultSet = this.get("SELECT * FROM program WHERE ID = '" + ID + "'");
			if (resultSet.next()) {
				Program program = this.getProgram(resultSet.getString("programID"));
				ArrayList<Course> courses = program.getCourses();
				for (int i = 0; i < courses.size(); i++) {
					if (courses.get(i).getID().equals(ID)) {
						return courses.get(i);
					}
				}
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Updates a course if it already exists, otherwise, adds a new one.
	 * 
	 * @param course the course to add
	 */
	public void save(Course course){
		Course existing = this.getCourse(course.getID());
		try {
			if (existing == null) {
				this.execute("INSERT INTO course (ID, programID, name) VALUES ('" + course.getID() 
				+ "', '" + course.getProgram().getID() + "', '" + course.getName() + "')");
			}
			else {
				this.execute("UPDATE course SET name = '" + course.getName() + "', programID = '" + course.getProgram().getID() + "' WHERE ID = '" + course.getID() + 
						"'");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Removes a course from the database.
	 * 
	 * @param course the course to remove
	 */
	public void delete(Course course){
		try {
			this.execute("DELETE FROM course WHERE ID = \'"+course.getID()+"\'");
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Checks whether a user can create an account or not.
	 * 
	 * @param username the username to check
	 * @return         true if no user is registered with that username, otherwise false.
	 */
	public boolean canRegister(String username){
		try {
			ResultSet resultSet = this.get("SELECT * FROM user WHERE name = '" + username + "'");
			return !resultSet.next();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
	 * Checks whether a user can login or not.
	 * 
	 * @param username the username to check for
	 * @param password the password to check for
	 * @return         true if the user is registered with that username and password, otherwise false.
	 */
	public boolean canLogin(String username, String password){
		try {
			ResultSet resultSet = this.get("SELECT * FROM user WHERE name = '" + username + "' "
					+ "AND password = '" + this.encryptPassword(password) + "'");
			return !resultSet.next();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
	 * Encrypts a password.
	 * 
	 * @param password the password to encrypt
	 * @return         the encrypted password
	 */
	private String encryptPassword(String password){
		return password;
	}

	/**
	 * Fetches all users from the database.
	 * 
	 * @return all users in the database
	 */
	public User[] getUsers(){
		// TODO: implement grades
		try {
			ResultSet resultSet = this.get("SELECT name, postalCode, description FROM user");
			ArrayList<User> users = new ArrayList<User>();
			while (resultSet.next()) {
				users.add(new User(resultSet.getString("name"), resultSet.getString("postalCode"), 
						resultSet.getString("description"), new Grade[0]));
			}
			User[] result = new User[users.size()];
			users.toArray(result);
			return result;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Fetches a user from the database.
	 * 
	 * @param name the name of the user to fetch
	 * @return     a user in the database with the specified name
	 */
	public User getUser(String name){
		// TODO: implement grades
		try {
			ResultSet resultSet = this.get("SELECT * FROM user WHERE name = '" + name + "'");
			if (resultSet.next()) {
				return new User(resultSet.getString("name"), resultSet.getString("postalCode"), 
						resultSet.getString("description"), new Grade[0]);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a user if it already exists, otherwise, adds a new one.
	 * 
	 * @param user the user to save
	 */
	public void save(User user){
		// TODO: implement grades
		User existing = this.getUser(user.getUsername());
		try {
			if (existing == null) {
				this.execute("INSERT INTO user (name, postalCode, description) VALUES ('" + user.getUsername() 
				+ "', '" + user.getPostalCode() + "', '" + user.getDescription() + "')");
			}
			else {
				this.execute("UPDATE user SET postalCode = '" + user.getPostalCode() + "', description = '" + user.getDescription() + "' WHERE name = '" + 
						user.getUsername() + "'");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Removes a user from the database.
	 * 
	 * @param user the user to remove
	 */
	public void delete(User user){
		// TODO: implement grades
		try {
			this.execute("DELETE FROM user WHERE name = '" + user.getUsername() + "'");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
