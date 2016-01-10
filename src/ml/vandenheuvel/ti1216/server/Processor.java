package ml.vandenheuvel.ti1216.server;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import ml.vandenheuvel.ti1216.data.*;
import ml.vandenheuvel.ti1216.http.*;

/**
 * Processor processes API requests and generates output for those.
 */
public class Processor {
	private DatabaseCommunicator communicator;

	/**
	 * The list of chat messages sent by all users.
	 */
	private ArrayList<ChatMessage> chatMessages;

	/**
	 * This constructor creates a new DatabaseCommunicator instance and a new
	 * ArrayList to store chatMessages in as long as the server is running.
	 * 
	 * @param databaseAddress the address on which the database server runs
	 * @param databaseUsername the username with which to log in on the databasehost
	 * @param databaseName the name of the database on the host
	 * @param databasePassword the password with which one can log on to the databasehost
	 */
	public Processor(String databaseAddress, String databaseUsername, String databaseName, String databasePassword) {
		this.communicator = new DatabaseCommunicator(databaseAddress, databaseName, databaseUsername, databasePassword);
		this.chatMessages = new ArrayList<ChatMessage>();
	}

	/**
	 * Processes a HTTP request.
	 * 
	 * @param request the request to process
	 * @return the response to the request
	 */
	public Message process(Message request) {
		Message response = new Message(new Header(), new Body(""));
		response.setHeader(new Header(new ResponseLine("HTTP/1.1", "400", "Bad Request")));
		if (request == null) {
			return response;
		}
		RequestLine headerLine = (RequestLine) request.getHeader().getHeaderLine();
		String[] uriParts = headerLine.getUri().split("/");
		if (uriParts.length > 1) {
			if ("user".equals(uriParts[1])) {
				return this.processUser(request);
			}
			if (!checkAuth(request)) {
				response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 401 Unauthorized"));
				response.getHeader().addField(new HeaderField("WWW-Authenticate", "Basic realm=\"MCRealms\""));
				return response;
			}
			switch (uriParts[1]) {
			case "chat":
				return processChat(request);
			case "faculty":
				return processFaculty(request);
			}
		}
		return response;
	}

	/**
	 * Checks whether a HTTP request is properly authorized or not.
	 * 
	 * @param request the request to check
	 * @return true if the HTTP request is authorized, otherwise false
	 */
	private boolean checkAuth(Message request) {
		Credentials credentials = getCredentials(request);
		return credentials != null && this.communicator.canLogin(credentials);
	}

	/**
	 * Retrieves the credentials in the authorization field of a HTTP request.
	 * 
	 * @param request the request to extract from
	 * @return a Credentials object containing the credentials if provided,
	 *         otherwise null
	 */
	private static Credentials getCredentials(Message request) {
		HeaderField authorization = request.getHeader().getField("Authorization");
		if (authorization == null) {
			return null;
		}
		String[] typeCredentials = authorization.getValue().split(" ");
		String[] usernamePassword = new String(
				Base64.getDecoder().decode(typeCredentials[1].getBytes(StandardCharsets.UTF_8))).split(":");
		if (usernamePassword.length < 2) {
			return null;
		}
		Credentials credentials = new Credentials(usernamePassword[0], usernamePassword[1]);
		return credentials;
	}

	/**
	 * Processes a HTTP request to the /user endpoint.
	 * 
	 * @param request the request to process
	 * @return the response to the request
	 */
	private Message processUser(Message request) {
		RequestLine headerLine = (RequestLine) request.getHeader().getHeaderLine();
		Message response = new Message(new Header(), new Body(""));
		if ("PUT".equals(headerLine.getMethod())) {
			Credentials credentials = getCredentials(request);
			JSONObject userData = new JSONObject(request.getBody().getContent());
			User newUser = User.fromJSON(userData);
			if (credentials != null && this.communicator.canRegister(credentials)) {
				this.communicator.save(newUser, credentials);
				response.getBody().setContent("{\"success\":true}");
				return response;
			}
			response.getBody().setContent(
					"{\"success\":false,\"reason\":\"You are not allowed to " + "register with those credentials.\"}");
			return response;
		}
		if ("GET".equals(headerLine.getMethod())) {
			Credentials credentials = getCredentials(request);
			if (credentials == null || !this.communicator.canLogin(credentials)) {
				response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 401 Unauthorized"));
				response.getHeader().addField(new HeaderField("WWW-Authenticate", "Basic realm=\"MCRealms\""));
				return response;
			}
			User user = this.communicator.getUser(credentials.getUsername());
			response.getBody().setContent(new JSONObject().put("success", true).put("user", user.toJSON()).toString());
			return response;
		}
		if ("UPDATE".equals(headerLine.getMethod())) {
			Credentials credentials = getCredentials(request);
			if (credentials == null || !this.communicator.canLogin(credentials)) {
				response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 401 Unauthorized"));
				response.getHeader().addField(new HeaderField("WWW-Authenticate", "Basic realm=\"MCRealms\""));
				return response;
			}
			User user = User.fromJSON(new JSONObject(request.getBody().getContent()));
			user.setUsername(credentials.getUsername());
			this.communicator.save(user);
			return response;
		}
		response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 405 Method Not Allowed"));
		response.getHeader().addField(new HeaderField("Allow", "PUT, GET, UPDATE"));
		return response;
	}

	/**
	 * Processes a HTTP request to the /chat endpoint.
	 * 
	 * @param request the request to process
	 * @return the response to the request
	 */
	private Message processChat(Message request) {
		RequestLine headerLine = (RequestLine) request.getHeader().getHeaderLine();
		Message response = new Message(new Header(), new Body(""));
		Credentials credentials = getCredentials(request);
		if ("PUT".equals(headerLine.getMethod())) {
			JSONObject data = new JSONObject(request.getBody().getContent());
			this.chatMessages.add(ChatMessage.fromJSON(data));
			return response;
		}
		if ("GET".equals(headerLine.getMethod())) {
			JSONArray messages = new JSONArray();
			JSONObject result = new JSONObject();
			for (int i = 0; i < this.chatMessages.size(); i++) {
				if (this.chatMessages.get(i).getReceiver().equals(credentials.getUsername())) {
					messages.put(this.chatMessages.get(i).toJSON());
					this.chatMessages.remove(i);
					i--;
				}
			}
			result.put("messages", messages);
			response.getBody().setContent(result.toString());
			return response;
		}
		response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 405 Method Not Allowed"));
		response.getHeader().addField(new HeaderField("Allow", "PUT, GET"));
		return response;
	}

	/**
	 * Processes a HTTP request to the /faculty endpoint.
	 * 
	 * @param request the request to process
	 * @return the response to the request
	 */
	private Message processFaculty(Message request) {
		RequestLine headerLine = (RequestLine) request.getHeader().getHeaderLine();
		Message response = new Message(new Header(), new Body(""));
		String[] uriParts = headerLine.getUri().split("/");
		if ("GET".equals(headerLine.getMethod())) {
			if (uriParts.length < 3) {
				JSONArray faculties = new JSONArray();
				Faculty[] facultyData = this.communicator.getFaculties();
				JSONObject result = new JSONObject();
				for (int i = 0; i < facultyData.length; i++) {
					faculties.put(facultyData[i].toJSON());
				}
				result.put("faculties", faculties);
				response.getBody().setContent(result.toString());
			} else {
				String id = uriParts[2];
				Faculty faculty = this.communicator.getFaculty(id);
				if (faculty == null) {
					response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 404 Not Found"));
				} else {
					JSONObject result = new JSONObject();
					result.put("faculty", faculty.toJSON());
					response.getBody().setContent(result.toString());
				}
			}
			return response;
		}
		response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 405 Method Not Allowed"));
		response.getHeader().addField(new HeaderField("Allow", "GET"));
		return response;
	}
}
