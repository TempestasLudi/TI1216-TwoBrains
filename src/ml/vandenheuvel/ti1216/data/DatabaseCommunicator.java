package ml.vandenheuvel.ti1216.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * DatabaseCommunicator is a class that handles communication with a database.
 */

public class DatabaseCommunicator {

	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.server");

	/**
	 * The database dataSource.
	 */
	private DataSource dataSource;

	/**
	 * The database connection.
	 */
	private Connection connection;

	/**
	 * Constructor, initializes the dataSource.
	 * 
	 * @param hostname
	 *            the database host name
	 * @param database
	 *            the database name
	 */
	public DatabaseCommunicator(String hostname, String database, String username, String password) {
		MysqlDataSource mysqlDS = null;
		mysqlDS = new MysqlDataSource();
		mysqlDS.setURL("jdbc:mysql://" + hostname + "/" + database);
		mysqlDS.setUser(username);
		mysqlDS.setPassword(password);
		this.dataSource = mysqlDS;
		try {
			this.connection = this.dataSource.getConnection();
			logger.info("Connected to the database.");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Failed to make the connection to SQL database.", e);
		}
	}

	/**
	 * Fetches data from the database.
	 * 
	 * @param query
	 *            the query to be executed
	 * @return the data matching the query
	 */
	private ResultSet get(String query) throws SQLException {
		Statement statement = this.connection.createStatement();
		return statement.executeQuery(query);
	}

	/**
	 * Executes a MySQL query.
	 * 
	 * @param query
	 *            the query to be executed
	 */
	private void execute(String query) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.execute(query);
	}

	/**
	 * Fetches all faculties from the database.
	 * 
	 * @return all faculties in the database
	 */
	public Faculty[] getFaculties() {
		String query = "SELECT f.ID as facultyID, f.name AS facultyName, p.id AS programID, p.name AS programName, c.ID as courseID, c.name AS courseName "
				+ "FROM faculty AS f " + "LEFT JOIN program AS p ON f.ID = p.facultyID "
				+ "LEFT JOIN course AS c ON p.ID = c.programID " + "ORDER BY facultyID ASC, programID ASC ";
		try {
			ResultSet resultSet = this.get(query);
			return createFPCTree(resultSet);
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getFaculties().", e);
		}
		return new Faculty[0];
	}

	/**
	 * Fetches a faculty from the database.
	 * 
	 * @param id
	 *            the id of the faculty
	 * @return the faculty with the id if found, otherwise null
	 */
	public Faculty getFaculty(String id) {
		String query = "SELECT f.ID as facultyID, f.name AS facultyName, p.id AS programID, p.name AS programName, c.ID as courseID, c.name AS courseName "
				+ "FROM faculty AS f " + "LEFT JOIN program AS p ON f.ID = p.facultyID "
				+ "LEFT JOIN course AS c ON p.ID = c.programID " + "WHERE f.ID = '" + id + "' "
				+ "ORDER BY programID ASC ";
		try {
			ResultSet resultSet = this.get(query);
			Faculty[] faculties = createFPCTree(resultSet);
			if (faculties.length > 0) {
				return faculties[0];
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getFaculty(" + id + ").", e);
		}
		return null;
	}

	/**
	 * Creates a Faculty-Program-Course tree structure from a MySQL result set.
	 * 
	 * @param resultSet
	 *            the result set
	 * @return a Faculty-Program-Course tree structure from the result set
	 */
	private Faculty[] createFPCTree(ResultSet resultSet) throws SQLException {
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		Faculty faculty = null;
		Program program = null;
		while (resultSet.next()) {
			if (faculty == null || !resultSet.getString("facultyID").equals(faculty.getID())) {
				faculty = new Faculty(resultSet.getString("facultyID"), resultSet.getString("facultyName"),
						new ArrayList<Program>());
				faculties.add(faculty);
			}
			if ((resultSet.getString("programID") != null)
					&& ((program == null) || !resultSet.getString("programID").equals(program.getID()))) {
				program = new Program(resultSet.getString("programID"), resultSet.getString("programName"), faculty,
						new ArrayList<Course>());
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
	 * Updates a faculty if it already exists, otherwise, adds a new one. Also,
	 * the programs of the faculty are saved.
	 * 
	 * @param faculty
	 *            the faculty to save
	 */
	public void save(Faculty faculty) {
		Faculty existing = this.getFaculty(faculty.getID());
		try {
			ArrayList<Program> programs = faculty.getPrograms();
			if (existing == null) {
				this.execute("INSERT INTO faculty (ID, name) VALUES ('" + faculty.getID() + "', '" + faculty.getName()
						+ "')");
				for (int i = 0; i < programs.size(); i++) {
					this.save(programs.get(i));
				}
			} else {
				this.execute(
						"UPDATE faculty SET name = '" + faculty.getName() + "' WHERE ID = '" + faculty.getID() + "'");
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
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed save(Faculty faculty).", e);
			logger.log(Level.FINER, "String representation of the faculty we were trying to save",
					new LogRecord(Level.FINER, faculty.toString()));
		}
	}

	/**
	 * Removes a faculty from the database.
	 * 
	 * @param faculty
	 *            the faculty to remove
	 */
	public void delete(Faculty faculty) {
		try {
			this.execute("DELETE FROM faculty WHERE ID = \'" + faculty.getID() + "\'");
			ArrayList<Program> programs = faculty.getPrograms();
			for (int i = 0; i < programs.size(); i++) {
				this.delete(programs.get(i));
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed delete(Faculty faculty).", e);
			logger.log(Level.FINER, "String representation of the faculty we were trying to delete",
					new LogRecord(Level.FINER, faculty.toString()));
		}
	}

	/**
	 * Fetches all programs from the database.
	 * 
	 * @return all programs in the database
	 */
	public Program[] getPrograms() {
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
	 * @param id
	 *            the id of the program
	 * @return the program with the id if found, otherwise null
	 */
	public Program getProgram(String id) {
		try {
			ResultSet resultSet = this.get("SELECT * FROM program WHERE ID = '" + id + "'");
			if (resultSet.next()) {
				Faculty faculty = this.getFaculty(resultSet.getString("facultyID"));
				ArrayList<Program> programs = faculty.getPrograms();
				for (int i = 0; i < programs.size(); i++) {
					if (programs.get(i).getID().equals(id)) {
						return programs.get(i);
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getProgram(String " + id + ")", e);
		}
		return null;
	}

	/**
	 * Saves a program. If it already exists, updates the current record.
	 * Otherwise, adds a new one.
	 * 
	 * @param program
	 *            the faculty to save
	 */
	public void save(Program program) {
		Program existing = this.getProgram(program.getID());
		try {
			ArrayList<Course> courses = program.getCourses();
			if (existing == null) {
				this.execute("INSERT INTO program (ID, facultyID, name) VALUES ('" + program.getID() + "', '"
						+ program.getFaculty().getID() + "', '" + program.getName() + "')");
				for (int i = 0; i < courses.size(); i++) {
					this.save(courses.get(i));
				}
			} else {
				this.execute("UPDATE program SET name = '" + program.getName() + "', facultyID = '"
						+ program.getFaculty().getID() + "' WHERE ID = '" + program.getID() + "'");
				ArrayList<Course> existingCourses = existing.getCourses();
				for (int i = 0; i < existingCourses.size(); i++) {
					if (!courses.contains(existingCourses.get(i))) {
						this.delete(existingCourses.get(i));
					}
				}
				for (int i = 0; i < courses.size(); i++) {
					if (!existingCourses.contains(courses.get(i))) {
						this.save(courses.get(i));
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed save(Program program).", e);
			logger.log(Level.FINER, "String represenation of the program:",
					new LogRecord(Level.FINER, program.toString()));
		}
	}

	/**
	 * Removes a program from the database.
	 * 
	 * @param program
	 *            the program to remove
	 */
	public void delete(Program program) {
		try {
			this.execute("DELETE FROM program WHERE ID = \'" + program.getID() + "\'");
			ArrayList<Course> courses = program.getCourses();
			for (int i = 0; i < courses.size(); i++) {
				this.delete(courses.get(i));
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed delete(Program program).", e);
			logger.log(Level.FINER, "String represenation of the program:",
					new LogRecord(Level.FINER, program.toString()));
		}
	}

	/**
	 * Fetches all courses from the database.
	 * 
	 * @return all courses in the database
	 */
	public Course[] getCourses() {
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
	 * @param id
	 *            the id of the course
	 * @return the course with the id if found, otherwise null
	 */
	public Course getCourse(String id) {
		try {
			ResultSet resultSet = this.get("SELECT * FROM course WHERE ID = '" + id + "'");
			if (resultSet.next()) {
				Program program = this.getProgram(resultSet.getString("programID"));
				ArrayList<Course> courses = program.getCourses();
				for (int i = 0; i < courses.size(); i++) {
					if (courses.get(i).getID().equals(id)) {
						return courses.get(i);
					}
				}
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getCourse(String " + id + ").", e);
		}
		return null;
	}

	/**
	 * Updates a course if it already exists, otherwise, adds a new one.
	 * 
	 * @param course
	 *            the course to save
	 */
	public void save(Course course) {
		Course existing = this.getCourse(course.getID());
		try {
			if (existing == null) {
				this.execute("INSERT INTO course (ID, programID, name) VALUES ('" + course.getID() + "', '"
						+ course.getProgram().getID() + "', '" + course.getName() + "')");
			} else {
				this.execute("UPDATE course SET name = '" + course.getName() + "', programID = '"
						+ course.getProgram().getID() + "' WHERE ID = '" + course.getID() + "'");
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed save(Course course).", e);
			logger.log(Level.FINER, "String represenation of the course:",
					new LogRecord(Level.FINER, course.toString()));
		}
	}

	/**
	 * Removes a course from the database.
	 * 
	 * @param course
	 *            the course to remove
	 */
	public void delete(Course course) {
		try {
			this.execute("DELETE FROM course WHERE ID = \'" + course.getID() + "\'");
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed delete(Course course).", e);
			logger.log(Level.FINER, "String represenation of the course:",
					new LogRecord(Level.FINER, course.toString()));
		}
	}

	/**
	 * Checks whether a user can create an account or not.
	 * 
	 * @param credentials
	 *            the credentials object containing the username to check
	 * @return true if no user is registered with that username, otherwise
	 *         false.
	 */
	public boolean canRegister(Credentials credentials) {
		try {
			if (credentials.getUsername() == null || "".equals(credentials.getUsername())
					|| credentials.getPassword() == null || "".equals(credentials.getPassword())) {
				return false;
			}
			ResultSet resultSet = this.get("SELECT * FROM user WHERE name = '" + credentials.getUsername() + "'");
			return !resultSet.next();
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed canRegister(Credentials credentials).", e);
			logger.log(Level.FINER, "String represenation of the credentials:",
					new LogRecord(Level.FINER, credentials.toString()));
		}
		return false;
	}

	/**
	 * Checks whether a user can login or not.
	 * 
	 * @param credentials
	 *            the username-password pair to check for
	 * @return true if the user is registered with that username and password,
	 *         otherwise false.
	 */
	public boolean canLogin(Credentials credentials) {
		try {
			ResultSet resultSet = this.get("SELECT * FROM user WHERE name = '" + credentials.getUsername() + "' "
					+ "AND password = '" + DatabaseCommunicator.encryptPassword(credentials.getPassword()) + "'");
			return resultSet.next();
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed canLogin(Credentials credentials).", e);
			logger.log(Level.FINER, "String represenation of the credentials:",
					new LogRecord(Level.FINER, credentials.toString()));
		}
		return false;
	}

	/**
	 * Encrypts a password.
	 * 
	 * @param password
	 *            the password to encrypt
	 * @return the encrypted password
	 */
	private static String encryptPassword(String password) {
		// TODO: encryption
		return password;
	}

	/**
	 * Fetches all users from the database.
	 * 
	 * @return all users in the database
	 */
	public User[] getUsers() {
		try {
			String query = "SELECT u.name AS username, u.postalCode, u.description AS userDescription, IF(g.ID IS NULL, -1, g.ID) AS gradeId, g.courseId, g.value AS gradeValue "
					+ "FROM user AS u " + "LEFT JOIN grade AS g " + "ON g.username = u.name";
			ResultSet resultSet = this.get(query);
			ArrayList<User> users = new ArrayList<User>();
			User user = null;
			ArrayList<Grade> grades = new ArrayList<Grade>();
			while (resultSet.next()) {
				if (user == null) {
					user = new User(resultSet.getString("username"), resultSet.getString("postalCode"),
							resultSet.getString("userDescription"), new Grade[0]);
					users.add(user);
				} else if (!resultSet.getString("username").equals(user.getUsername())) {
					Grade[] gradeArray = new Grade[grades.size()];
					grades.toArray(gradeArray);
					user.setGradeList(gradeArray);
					grades = new ArrayList<Grade>();
					users.add(new User(resultSet.getString("username"), resultSet.getString("postalCode"),
							resultSet.getString("userDescription"), new Grade[0]));
				}
				if (resultSet.getInt("gradeId") != -1) {
					grades.add(new Grade(resultSet.getString("courseId"), resultSet.getInt("gradeValue")));
				}
			}
			if (user != null) {
				Grade[] gradeArray = new Grade[grades.size()];
				grades.toArray(gradeArray);
				user.setGradeList(gradeArray);
			}
			User[] result = new User[users.size()];
			users.toArray(result);
			return result;
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getUsers().", e);
		}
		return new User[0];
	}

	/**
	 * Fetches a user from the database.
	 * 
	 * @param name
	 *            the name of the user to fetch
	 * @return a user in the database with the specified name
	 */
	public User getUser(String name) {
		try {
			ResultSet resultSet = this
					.get("SELECT u.name AS username, u.postalCode, u.description AS userDescription, IF(g.ID IS NULL, -1, g.ID) AS gradeId, g.courseId, g.value AS gradeValue "
							+ "FROM user AS u " + "LEFT JOIN grade AS g " + "ON g.username = u.name "
							+ "WHERE u.name = '" + name + "'");
			if (resultSet.next()) {
				ArrayList<Grade> gradeList = new ArrayList<Grade>();
				User user = new User(resultSet.getString("username"), resultSet.getString("postalCode"),
						resultSet.getString("userDescription"), new Grade[0]);
				do {
					gradeList.add(new Grade(resultSet.getString("courseId"), resultSet.getInt("gradeValue")));
				} while (resultSet.next());
				Grade[] gradeArray = new Grade[gradeList.size()];
				gradeList.toArray(gradeArray);
				user.setGradeList(gradeArray);
				return user;
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getUser(" + name + ").", e);
		}
		return null;
	}

	/**
	 * Updates a user if it already exists.
	 * 
	 * @param user
	 *            the user to update
	 */
	public void save(User user) {
		User existing = this.getUser(user.getUsername());
		try {
			if (existing != null) {
				this.execute("UPDATE user SET postalCode = '" + user.getPostalCode() + "', description = '"
						+ user.getDescription() + "' WHERE name = '" + user.getUsername() + "'");
				this.execute("DELETE FROM grade WHERE username = '" + user.getUsername() + "'");
				Grade[] gradeList = user.getGradeList();
				if (gradeList.length > 0) {
					String addQuery = "INSERT INTO grade (courseID, username, value) VALUES ";
					for (int i = 0; i < gradeList.length; i++) {
						Grade grade = gradeList[i];
						addQuery += "('" + grade.getCourseId() + "', '" + user.getUsername() + "', " + grade.getGrade()
								+ ")";
						if (i != gradeList.length - 1) {
							addQuery += ", ";
						}
					}
					this.execute(addQuery);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed save(User user).", e);
			logger.log(Level.FINER, "String represenation of the user:", new LogRecord(Level.FINER, user.toString()));
		}
	}

	/**
	 * Adds a new user.
	 * 
	 * @param user
	 *            the user to add
	 */
	public void save(User user, Credentials credentials) {
		User existing = this.getUser(user.getUsername());
		try {
			if (existing == null) {
				this.execute("INSERT INTO user (name, password, postalCode, description) VALUES ('"
						+ credentials.getUsername() + "', '" + credentials.getPassword() + "', '" + user.getPostalCode()
						+ "', '" + user.getDescription() + "')");
				Grade[] gradeList = user.getGradeList();
				if (gradeList.length > 0) {
					String addQuery = "INSERT INTO grade (courseID, username, value) VALUES ";
					for (int i = 0; i < gradeList.length; i++) {
						Grade grade = gradeList[i];
						addQuery += "('" + grade.getCourseId() + "', '" + user.getUsername() + "', " + grade.getGrade()
								+ ")";
						if (i != gradeList.length - 1) {
							addQuery += ", ";
						}
					}
					this.execute(addQuery);
				}
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed save(User user, Credentials credentials.", e);
			logger.log(Level.FINER, "String represenation of the user:", new LogRecord(Level.FINER, user.toString()));
			logger.log(Level.FINER, "String represenation of the credentials",
					new LogRecord(Level.FINER, credentials.toString()));
		}
	}

	/**
	 * Removes a user from the database.
	 * 
	 * @param user
	 *            the user to remove
	 */
	public void delete(User user) {
		try {
			this.execute("DELETE FROM user WHERE name = '" + user.getUsername() + "'");
			this.execute("DELETE FROM grade WHERE username = '" + user.getUsername() + "'");
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed delete(User user).", e);
			logger.log(Level.FINER, "String represenation of the user:", new LogRecord(Level.FINER, user.toString()));
		}
	}

	/**
	 * Fetches all matches from the database.
	 * 
	 * @return all matches from the database
	 */
	public Match[] getMatches() {
		try {
			ResultSet resultSet = this.get("SELECT * FROM match");
			ArrayList<Match> matches = new ArrayList<Match>();
			while (resultSet.next()) {
				matches.add(new Match(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("matchUsername"), resultSet.getBoolean("seen"),
						resultSet.getBoolean("approved")));
			}
			Match[] result = new Match[matches.size()];
			matches.toArray(result);
			return result;
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getMatches().", e);
		}
		return null;
	}

	/**
	 * Fetches all matches for a certain user.
	 * 
	 * @param username
	 *            the name of the user to fetch matches for
	 * @return all matches for the user
	 */
	public Match[] getMatches(String username) {
		try {
			ResultSet resultSet = this.get("SELECT * FROM match WHERE username = '" + username + "'");
			ArrayList<Match> matches = new ArrayList<Match>();
			while (resultSet.next()) {
				matches.add(new Match(resultSet.getInt("id"), resultSet.getString("username"),
						resultSet.getString("matchUsername"), resultSet.getBoolean("seen"),
						resultSet.getBoolean("approved")));
			}
			Match[] result = new Match[matches.size()];
			matches.toArray(result);
			return result;
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getMatches(String " + username + ").", e);
		}
		return null;
	}

	/**
	 * Updates a match if it already exists, otherwise, adds a new one.
	 * 
	 * @param match
	 *            the match to save
	 */
	public void save(Match match) {
		try {
			if (match.getId() < 0 || !this.get("SELECT * FROM match WHERE ID = " + match.getId()).next()) {
				this.execute("INSERT INTO match (username, matchUsername) VALUES ('" + match.getUsername() + "', '"
						+ match.getMatchUsername() + "')");
			} else {
				this.execute("UPDATE match SET username = '" + match.getUsername() + "', matchUsername = '"
						+ match.getMatchUsername() + "', seen=" + match.isSeen() + ", approved=" + match.isApproved()
						+ " WHERE ID = '" + match.getId() + "'");
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed save(Match match).", e);
			logger.log(Level.FINER, "String represenation of the match:", new LogRecord(Level.FINER, match.toString()));
		}
	}

	/**
	 * Removes a match from the database.
	 * 
	 * @param match
	 *            the match to delete
	 */
	public void delete(Match match) {
		try {
			this.execute("DELETE FROM match WHERE ID = " + match.getId());
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed delete(Match match).", e);
			logger.log(Level.FINER, "String represenation of the match:", new LogRecord(Level.FINER, match.toString()));
		}
	}

	/**
	 * Fetches all chat messages from the database.
	 * 
	 * @return all chat messages from the database
	 */
	public ChatMessage[] getChats() {
		try {
			ResultSet resultSet = this.get("SELECT * FROM chat");
			ArrayList<ChatMessage> chats = new ArrayList<ChatMessage>();
			while (resultSet.next()) {
				chats.add(new ChatMessage(resultSet.getInt("ID"), resultSet.getString("sender"),
						resultSet.getString("message"), resultSet.getString("receiver"), resultSet.getBoolean("seen")));
			}
			ChatMessage[] result = new ChatMessage[chats.size()];
			chats.toArray(result);
			return result;
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getChats().", e);
		}
		return null;
	}

	/**
	 * Fetches all chat messages for a certain receiver.
	 * 
	 * @param username
	 *            the user to fetch for
	 * @param onlyNew
	 *            whether only new messages should be fetched or not
	 * @return all chat messages for the user
	 */
	public ChatMessage[] getChats(String username, boolean onlyNew) {
		try {
			String query = "SELECT * FROM chat WHERE username = '" + username + "'";
			if (onlyNew) {
				query += "WHERE seen = false";
			}
			ResultSet resultSet = this.get(query);
			ArrayList<ChatMessage> chats = new ArrayList<ChatMessage>();
			while (resultSet.next()) {
				chats.add(new ChatMessage(resultSet.getInt("ID"), resultSet.getString("sender"),
						resultSet.getString("message"), resultSet.getString("receiver"), resultSet.getBoolean("seen")));
			}
			ChatMessage[] result = new ChatMessage[chats.size()];
			chats.toArray(result);
			return result;
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed getChats(" + username + ", " + onlyNew + ").", e);
		}
		return null;
	}

	/**
	 * Updates a chat message if it already exists, otherwise, adds a new one.
	 * 
	 * @param message
	 *            the message to save
	 */
	public void save(ChatMessage message) {
		try {
			if (message.getId() < 0 || !this.get("SELECT * FROM chat WHERE ID = " + message.getId()).next()) {
				this.execute("INSERT INTO chat (sender, receiver, message, seen) VALUES ('" + message.getSender()
						+ "', '" + message.getReceiver() + "', '" + message.getMessage() + "', " + message.isSeen()
						+ ")");
			} else {
				this.execute("UPDATE chat SET sender = '" + message.getSender() + "', receiver = '"
						+ message.getReceiver() + "', message = '" + message.getMessage() + "', seen = " + message.isSeen()
						+ " WHERE ID = '" + message.getId() + "'");
			}
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed save(ChatMessage message).", e);
			logger.log(Level.FINER, "String represenation of the message:",
					new LogRecord(Level.FINER, message.toString()));
		}
	}

	/**
	 * Removes a chat message from the database.
	 * 
	 * @param message
	 *            the message to delete
	 */
	public void delete(ChatMessage message) {
		try {
			this.execute("DELETE FROM chat WHERE ID = " + message.getId());
		} catch (SQLException e) {
			logger.log(Level.FINE, "Failed delete(ChatMessage message).", e);
			logger.log(Level.FINER, "String represenation of the message:", new LogRecord(Level.FINER, message.toString()));
		}
	}

}
