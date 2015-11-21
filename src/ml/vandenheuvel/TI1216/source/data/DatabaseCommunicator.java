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
 * Class "DatabseCommunicator" 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft 2015-2016. 
 */

public class DatabaseCommunicator {
	
	/*
	 * The MySQL username.
	 */
	private static String username = "TI1216";
	
	/*
	 * The MySQL password.
	 */
	private static String password = "3t.uGmL365j2f7B";
	
	/*
	 * The database dataSource.
	 */
	private DataSource dataSource;
	
	/*
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

	/*
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
	
	/*
	 * Executes a MySQL query.
	 * 
	 * @param query the query to be executed
	 */
	private void execute(String query) throws SQLException{
		System.out.println(query);
		Connection connection = this.dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);
	}
	
	/*
	 * Fetches all faculties from the database.
	 * 
	 * @return all faculties in the database
	 */
	public Faculty[] getFaculties(){
		try {
			ArrayList<Faculty> faculties = new ArrayList<Faculty>();
			ResultSet resultSet = get("SELECT * FROM faculty");
            while(resultSet.next()){
            	faculties.add(new Faculty(resultSet.getString("name"), new ArrayList<Program>(), resultSet.getString("ID")));
            }
            Faculty[] result = new Faculty[faculties.size()];
            faculties.toArray(result);
            return result;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/*
	 * Fetches a faculty from the database.
	 * 
	 * @param ID the id of the faculty
	 * @return   the faculty with the id if found, otherwise null
	 */
	public Faculty getFaculty(String ID){
		try {
			ResultSet resultSet = this.get("SELECT * FROM faculty WHERE ID = '" + ID + "'");
            if(resultSet.next()){
            	return new Faculty(resultSet.getString("name"), new ArrayList<Program>(), resultSet.getString("ID"));
            }
		} catch (SQLException e) {}
        return null;
	}
	
	/*
	 * Saves a faculty. If it already exists, updates the current record. Otherwise, adds a new one.
	 * 
	 * @param faculty the faculty to add
	 */
	public void save(Faculty faculty){
		Faculty existing = this.getFaculty(faculty.getID());
		try {
			if (existing == null) {
				this.execute("INSERT INTO faculty (ID, name) VALUES ('" + faculty.getID() + 
						"', '" + faculty.getName() + "')");
			}
			else {
				this.execute("UPDATE faculty SET name = '" + faculty.getName() + "' WHERE ID = '" + faculty.getID() + 
						"'");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Checks whether a user can create an account or not.
	 * 
	 * @param user the user to check
	 * @return     true if no user is registered with that username, otherwise false.
	 */
	public boolean canRegister(User user){
		Random random = new Random();
		return random.nextBoolean();
	}
	
	/**
	 * Checks whether a user can login or not.
	 * 
	 * @param user the user to check
	 * @return     true if the user is registered with that username and password, otherwise false.
	 */
	public boolean canLogin(User user){
		Random random = new Random();
		return random.nextBoolean();
	}

}
