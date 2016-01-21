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
import ml.vandenheuvel.ti1216.data.Match;
import ml.vandenheuvel.ti1216.data.User;
import ml.vandenheuvel.ti1216.http.Body;
import ml.vandenheuvel.ti1216.http.Header;
import ml.vandenheuvel.ti1216.http.HeaderField;
import ml.vandenheuvel.ti1216.http.Message;
import ml.vandenheuvel.ti1216.http.RequestLine;
import ml.vandenheuvel.ti1216.http.ResponseLine;

/**
 * ServerCommunicator is a communicator class to communicate in a API-based way
 * with the server (from the client).
 */
public abstract class ServerCommunicator {

	public static final String targetAddress = "127.0.0.1";
	public static final String targetHost = "127.0.0.1";
	
	private ServerCommunicator(){
		//Private constructor to hide the implicit public one
	}

	/**
	 * Tries to log in a user and fetches that user from the server.
	 * 
	 * @param credentials
	 *            the credentials to login with
	 * @return the user with specified credentials if present on the server,
	 *         otherwise null
	 */
	public static User login(Credentials credentials) {
		Message request = createMessage("get", "user", credentials);
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
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
	 * @param credentials
	 *            the credentials to register with
	 * @param user
	 *            the user to register
	 * @return true if the user could be registered, otherwise false
	 */
	public static boolean register(Credentials credentials, User user) {
		Message request = createMessage("put", "user", credentials);
		request.getBody().setContent(user.toJSON().toString());
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
			return false;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return body.getBoolean("success");
	}

	/**
	 * Tries to update a user on the server.
	 * 
	 * @param credentials
	 *            the credentials of the user
	 * @param user
	 *            the user to update
	 * @return true if the user could be updated, otherwise false
	 */
	public static boolean updateUser(Credentials credentials, User user) {
		Message request = createMessage("update", "user", credentials);
		request.getBody().setContent(user.toJSON().toString());
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
			return false;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return body.getBoolean("success");
	}

	/**
	 * Tries to send a chat message.
	 * 
	 * @param credentials
	 *            the credentials of the user
	 * @param chatMessage
	 *            the message to send
	 * @return true if the message could be sent, otherwise false
	 */
	public static boolean sendChat(Credentials credentials, ChatMessage message) {
		Message request = createMessage("put", "chat", credentials);
		request.getBody().setContent(message.toJSON().toString());
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
			return false;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return body.getBoolean("success");
	}

	/**
	 * Tries to fetch a faculty.
	 * 
	 * @param credentials
	 *            the credentials of the user
	 * @param facultyId
	 *            the id of the faculty to fetch
	 * @return a faculty if found, otherwise null
	 */
	public static Faculty fetchFaculty(Credentials credentials, int facultyId) {
		Message request = createMessage("get", "faculty/" + facultyId, credentials);
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
			return null;
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		return Faculty.fromJSON(body.getJSONObject("faculty"));
	}

	/**
	 * Tries to fetch all faculties.
	 * 
	 * @param credentials
	 *            the credentials of the user
	 * @return a faculty if found, otherwise null
	 */
	public static ArrayList<Faculty> fetchFaculties(Credentials credentials) {
		Message request = createMessage("get", "faculty", credentials);
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
			return new ArrayList<Faculty>();
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		ArrayList<Faculty> faculties = new ArrayList<>();
		JSONArray facultyData = body.getJSONArray("faculties");
		for (int i = 0; i < facultyData.length(); i++) {
			faculties.add(Faculty.fromJSON(facultyData.getJSONObject(i)));
		}
		return faculties;
	}

	/**
	 * Sends a HTTP message to the server and returns the response.
	 * 
	 * @param message
	 *            the message to send
	 * @return the response
	 */
	public static Message send(Message message) {
		try {
			if (message.getHeader().getField("Host") == null) {
				message.getHeader().addField(new HeaderField("Host", targetHost));
			}
			Socket socket = new Socket(targetAddress, 80);
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

	/**
	 * Tries to fetch chat messages.
	 * 
	 * @param credentials
	 *            the credentials of the user
	 * @return an arrayList of chat messages if they could be fetched, otherwise
	 *         an empty arrayList
	 */
	public static ArrayList<ChatMessage> fetchChats(Credentials credentials) {
		Message request = createMessage("get", "chat", credentials);
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
			return new ArrayList<ChatMessage>();
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		JSONArray messages = body.getJSONArray("messages");
		ArrayList<ChatMessage> result = new ArrayList<>();
		for (int i = 0; i < messages.length(); i++) {
			result.add(ChatMessage.fromJSON(messages.getJSONObject(i)));
		}
		return result;
	}

	/**
	 * Tries to fetch matches
	 * 
	 * @param credentials
	 *            the credentials of the user
	 * @return an arrayList of matches if they could be fetched, otherwise an
	 *         empty arrayList
	 */
	public static ArrayList<Match> fetchMatches(Credentials credentials) {
		Message request = createMessage("get", "match", credentials);
		Message response = send(request);
		ResponseLine responseLine = (ResponseLine) response.getHeader().getHeaderLine();
		String statusCode = responseLine.getCode();
		if (!("200".equals(statusCode))) {
			return new ArrayList<Match>();
		}
		JSONObject body = new JSONObject(response.getBody().getContent());
		JSONArray matches = body.getJSONArray("matches");
		ArrayList<Match> result = new ArrayList<>();
		for (int i = 0; i < matches.length(); i++) {
			result.add(Match.fromJSON(matches.getJSONObject(i)));
		}
		return result;
	}

	/**
	 * Creates a default HTTP message with Basic authentication.
	 * 
	 * @param method
	 *            the HTTP method to use
	 * @param endpoint
	 *            the endpoint to request
	 * @param credentials
	 *            the credentials to authenticate with
	 * @return
	 */
	private static Message createMessage(String method, String endpoint, Credentials credentials) {
		Header header = new Header(new RequestLine(method.toUpperCase() + " /" + endpoint + " HTTP/1.1"));
		Message result = new Message(header, new Body(""));
		result.getHeader().addField(new HeaderField("Authorization", "Basic " + Base64.getEncoder()
				.encodeToString((credentials.getUsername() + ":" + credentials.getPassword()).getBytes())));
		return result;
	}

}
