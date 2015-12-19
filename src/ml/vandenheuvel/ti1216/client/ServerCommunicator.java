package ml.vandenheuvel.ti1216.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

import org.json.JSONObject;

import ml.vandenheuvel.ti1216.api.http.*;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.User;

/**
 * ServerCommunicator is a communicator class to communicate in a API-based way
 * with the server (from the client).
 * 
 * @author Arnoud van der Leer
 *
 */
public class ServerCommunicator {
	
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
	 * @return true if the user could be registerd, otherwise false
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
	 * Sends a HTTP message to the server and returns the response.
	 * 
	 * @param message the message to send
	 * @return the response
	 */
	public static Message send(Message message) {
		try {
			Socket socket = new Socket("127.0.0.1", 80);
			DataInputStream inputStream = new DataInputStream(socket.getInputStream());
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.write(message.toString());
			out.flush();
			Message result = Message.read(inputStream, false);
			return result;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
