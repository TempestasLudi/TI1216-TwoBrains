package ml.vandenheuvel.ti1216.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Faculty;
import ml.vandenheuvel.ti1216.data.User;
import ml.vandenheuvel.ti1216.http.*;

/**
 * ServerCommunicator is a communicator class to communicate in a API-based way
 * with the server (from the client).
 *
 */
public class ServerCommunicator {
	
	public static String targetIp = "127.0.0.1";
	
	/**
	 * Tries to log in a user and fetches that user from the server.
	 * 
	 * @param credentials the credentials to login with
	 * @return the user with specified credentials if present on the server, otherwise null
	 */
	public static User login(Credentials credentials) {
		Header header = new Header(new RequestLine("GET /user HTTP/1.1"));
		header.addField(new HeaderField("Authorization", "Basic " + Base64.getEncoder()
				.encodeToString((credentials.getUsername() + ":" + credentials.getPassword()).getBytes())));
		Message request = new Message(header, new Body(""));
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))){
			return null;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		if (body.getBoolean("success")) {
			return User.fromJSON(body.getJSONObject("user"));
		}
		return null;
	}

	/**
	 * Tries to register a user on the server.
	 * 
	 * @param credentials the credentials to register with
	 * @param user the user to register
	 * @return true if the user could be registered, otherwise false
	 */
	public static boolean register(Credentials credentials, User user) {
		Header header = new Header(new RequestLine("PUT /user HTTP/1.1"));
		header.addField(new HeaderField("Authorization", "Basic " + Base64.getEncoder()
				.encodeToString((credentials.getUsername() + ":" + credentials.getPassword()).getBytes())));
		Message request = new Message(header, new Body(user.toJSON().toString()));
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))){
			return false;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return body.getBoolean("success");
	}

	/**
	 * Tries to update a user on the server.
	 * 
	 * @param credentials the credentials of the user
	 * @param user the user to update
	 * @return true if the user could be updated, otherwise false
	 */
	public static boolean updateUser(Credentials credentials, User user) {
		Header header = new Header(new RequestLine("UPDATE /user HTTP/1.1"));
		header.addField(new HeaderField("Authorization", "Basic " + Base64.getEncoder()
				.encodeToString((credentials.getUsername() + ":" + credentials.getPassword()).getBytes())));
		Message request = new Message(header, new Body(user.toJSON().toString()));
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))){
			return false;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return body.getBoolean("success");
	}

	/**
	 * Tries to send a chat message.
	 * 
	 * @param credentials the credentials of the user
	 * @param message the message to send
	 * @return true if the message could be sent, otherwise false
	 */
	public static boolean sendChat(Credentials credentials, ChatMessage message) {
		Header header = new Header(new RequestLine("PUT /chat HTTP/1.1"));
		header.addField(new HeaderField("Authorization", "Basic " + Base64.getEncoder()
				.encodeToString((credentials.getUsername() + ":" + credentials.getPassword()).getBytes())));
		Message request = new Message(header, new Body(message.toJSON().toString()));
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))){
			return false;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return body.getBoolean("success");
	}

	/**
	 * Tries to fetch a faculty.
	 * 
	 * @param credentials the credentials of the user
	 * @param facultyId the id of the faculty to fetch
	 * @return a faculty if found, otherwise null
	 */
	public static Faculty fetchFaculty(Credentials credentials, int facultyId) {
		Header header = new Header(new RequestLine("PUT /faculty/" + facultyId + " HTTP/1.1"));
		header.addField(new HeaderField("Authorization", "Basic " + Base64.getEncoder()
				.encodeToString((credentials.getUsername() + ":" + credentials.getPassword()).getBytes())));
		Message request = new Message(header, new Body(""));
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))){
			return null;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return Faculty.fromJSON(body.getJSONObject("faculty"));
	}

	/**
	 * Tries to fetch all faculties.
	 * 
	 * @param credentials the credentials of the user
	 * @return a faculty if found, otherwise null
	 */
	public static ArrayList<Faculty> fetchFaculties(Credentials credentials) {
		Header header = new Header(new RequestLine("PUT /faculty HTTP/1.1"));
		header.addField(new HeaderField("Authorization", "Basic " + Base64.getEncoder()
				.encodeToString((credentials.getUsername() + ":" + credentials.getPassword()).getBytes())));
		Message request = new Message(header, new Body(""));
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))){
			return new ArrayList<Faculty>();
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		ArrayList<Faculty> faculties = new ArrayList<Faculty>();
		JSONArray facultyData = body.getJSONArray("faculties");
		for (int i = 0; i < facultyData.length(); i++) {
			faculties.add(Faculty.fromJSON(facultyData.getJSONObject(i)));
		}
		return faculties;
	}
	
	/**
	 * Sends a HTTP message to the server and returns the response.
	 * 
	 * @param message the message to send
	 * @return the response
	 */
	public static Message send(Message message) {
		try {
			Socket socket = new Socket(targetIp, 80);
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.write(message.toString());
			out.flush();
			Message result = Message.read(inputStream, false);
			socket.close();
			return result;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<ChatMessage> fetchChats(Credentials credentials){
		//TODO: Implement this method
		return null;
	}

}
