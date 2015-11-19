package ml.vandenheuvel.TI1216.source.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public Faculty[] getFaculties(){
		try {
			ArrayList<Faculty> faculties = new ArrayList<Faculty>();
			Connection connection = this.dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM faculty");
            while(resultSet.next()){
            	faculties.add(new Faculty(resultSet.getString("name"), new ArrayList<Program>(), resultSet.getString("ID")));
            }
            Faculty[] result = new Faculty[faculties.size()];
            faculties.toArray(result);
            try {
            	resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {}
            return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
