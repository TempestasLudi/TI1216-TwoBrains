package ml.vandenheuvel.TI1216.source.api;

import java.util.Date;
import java.util.Random;

import org.json.JSONObject;

import ml.vandenheuvel.TI1216.source.api.http.Body;
import ml.vandenheuvel.TI1216.source.api.http.Header;
import ml.vandenheuvel.TI1216.source.api.http.HeaderField;
import ml.vandenheuvel.TI1216.source.api.http.Message;
import ml.vandenheuvel.TI1216.source.api.http.RequestLine;
import ml.vandenheuvel.TI1216.source.api.http.ResponseLine;
import ml.vandenheuvel.TI1216.source.data.DatabaseCommunicator;

/**
 * Processor processes API requests and generates output for those.
 * 
 * @author Arnoud van der Leer
 */
public class Processor {
//	private DatabaseCommunicator communicator = new DatabaseCommunicator("85.151.128.10", "TI1216");
	
	public Message process(Message request) {
		Message response = new Message(new Header(), new Body(""));
		response.setHeader(new Header(new ResponseLine("HTTP/1.1", "400", "Bad Request")));
		if (request == null) {
			return response;
		}
		RequestLine headerLine = (RequestLine)request.getHeader().getHeaderLine();
		String[] uriParts = headerLine.getUri().split("/");
		JSONObject result = new JSONObject();
		if (uriParts.length > 1){
			if (uriParts[1].equals("login") || uriParts[1].equals("register")) {
				result.put("accepted", true);
				result.put("authKey", new Random().nextInt());
				response.getBody().setContent(result.toString());
				response.setHeader(new Header());
			}
			else {
				if (!checkAuth(request)) {
					response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 401 Unauthorized"));
					response.getHeader().addField(new HeaderField("WWW-Authenticate", "Basic realm=\"myRealm\""));
					return response;
				}
				switch (uriParts[1]) {
					case "faculties":
						
					break;
					case "programs":
						
					break;
					case "courses":
						
					break;
				}
			}
		}
		return response;
	}
	
	private boolean checkAuth(Message request) {
		HeaderField authorization = request.getHeader().getField("Authorization");
		return authorization != null;
	}
	
}
