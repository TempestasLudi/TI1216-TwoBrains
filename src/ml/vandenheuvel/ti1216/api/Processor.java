package ml.vandenheuvel.ti1216.api;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONObject;

import ml.vandenheuvel.ti1216.api.http.Body;
import ml.vandenheuvel.ti1216.api.http.Header;
import ml.vandenheuvel.ti1216.api.http.HeaderField;
import ml.vandenheuvel.ti1216.api.http.Message;
import ml.vandenheuvel.ti1216.api.http.RequestLine;
import ml.vandenheuvel.ti1216.api.http.ResponseLine;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.DatabaseCommunicator;
import ml.vandenheuvel.ti1216.data.Grade;
import ml.vandenheuvel.ti1216.data.User;

/**
 * Processor processes API requests and generates output for those.
 * 
 * @author Arnoud van der Leer
 */
public class Processor {
	/**/private DatabaseCommunicator communicator = new DatabaseCommunicator("85.151.128.10", "TI1216");/*/
	private DatabaseCommunicator communicator = new DatabaseCommunicator("192.168.1.111", "TI1216");/**/
	
	public Message process(Message request) {
		Message response = new Message(new Header(), new Body(""));
		response.setHeader(new Header(new ResponseLine("HTTP/1.1", "400", "Bad Request")));
		if (request == null) {
			return response;
		}
		RequestLine headerLine = (RequestLine)request.getHeader().getHeaderLine();
		String[] uriParts = headerLine.getUri().split("/");
		if (uriParts.length > 1){
			if ("user".equals(uriParts[1])) {
				return this.processUser(request);
			}
			if (!checkAuth(request)) {
				response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 401 Unauthorized"));
				response.getHeader().addField(new HeaderField("WWW-Authenticate", "Basic realm=\"MCRealms\""));
				return response;
			}
			switch (uriParts[1]) {
				case "user":
				break;
			}
		}
		return response;
	}
	
	private boolean checkAuth(Message request) {
		Credentials credentials = getCredentials(request);
		return credentials != null && this.communicator.canLogin(credentials);
	}
	
	private static Credentials getCredentials(Message request) {
		HeaderField authorization = request.getHeader().getField("Authorization");
		if (authorization == null) {
			return null;
		}
		String[] typeCredentials = authorization.getValue().split(" ");
		String[] usernamePassword = new String(Base64.getDecoder().decode(
				typeCredentials[1].getBytes(StandardCharsets.UTF_8))).split(":");
		if (usernamePassword.length < 2) {
			return null;
		}
		Credentials credentials = new Credentials(usernamePassword[0], usernamePassword[1]);
		return credentials;
	}
	
	private Message processUser(Message request) {
		RequestLine headerLine = (RequestLine)request.getHeader().getHeaderLine();
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
			response.getBody().setContent("{\"success\":false,\"reason\":\"You are not allowed to "
					+ "register with those credentials.\"}");
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
		response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 405 Method Not Allowed"));
		response.getHeader().addField(new HeaderField("Allow", "PUT"));
		return response;
	}
	
}
