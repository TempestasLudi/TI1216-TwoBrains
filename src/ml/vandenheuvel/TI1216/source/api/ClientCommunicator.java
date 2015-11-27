package ml.vandenheuvel.TI1216.source.api;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.json.JSONObject;

import ml.vandenheuvel.TI1216.source.api.http.Body;
import ml.vandenheuvel.TI1216.source.api.http.Header;
import ml.vandenheuvel.TI1216.source.api.http.HeaderField;
import ml.vandenheuvel.TI1216.source.api.http.Message;
import ml.vandenheuvel.TI1216.source.api.http.RequestLine;
import ml.vandenheuvel.TI1216.source.api.http.ResponseLine;

/**
 * ClientCommunicator handles one HTTP request.
 * 
 * @author Arnoud van der Leer
 */
public class ClientCommunicator implements Runnable {
	/**
	 * The connection socket.
	 */
	private Socket socket;
	
	/**
	 * The output writer.
	 */
	private PrintWriter out;
	
	/**
	 * The input reader.
	 */
	private DataInputStream in;

	/**
	 * Class constructor, initializes input and output.
	 * 
	 * @param socket the connection socket.
	 */
	public ClientCommunicator(Socket socket) {
		this.socket = socket;
		try {
			this.out = new PrintWriter(socket.getOutputStream());
			this.in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * The runner method, reads all incoming data, processes it and generates output accordingly.
	 */
	public void run(){
		System.out.println("Incoming:");
		
		Message message = Message.read(this.in);
		
		System.out.print(message);
		
		JSONObject data = new JSONObject();
		String uri = "/";
		if (message != null) {
			uri = ((RequestLine)message.getHeader().getHeaderLine()).getUri();
			data.put("uri", uri);
		}
		Message response = new Message(new Header(null), new Body(data.toString()));
		if (message != null && !uri.equals("/")) {
			response.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1", "301", "Moved permanently"));
			response.getHeader().addField(new HeaderField("Location", "/"));
		}
		this.finish(response);
	}
	
	/**
	 * Sends data and closes the connection.
	 * 
	 * @param data the data to send
	 */
	public void finish(Message message) {
		Header header = new Header();
		Body body = new Body("");
		Message outputMessage = new Message(header, body);
		outputMessage.merge(message);
		this.out.write(outputMessage.toString());
		this.close();
	}
	
	/**
	 * Closes the connection.
	 */
	private void close(){
		this.out.flush();
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
