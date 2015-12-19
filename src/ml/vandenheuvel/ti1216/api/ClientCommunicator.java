package ml.vandenheuvel.ti1216.api;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONObject;

import ml.vandenheuvel.ti1216.api.http.Body;
import ml.vandenheuvel.ti1216.api.http.Header;
import ml.vandenheuvel.ti1216.api.http.HeaderField;
import ml.vandenheuvel.ti1216.api.http.Message;
import ml.vandenheuvel.ti1216.api.http.RequestLine;
import ml.vandenheuvel.ti1216.api.http.ResponseLine;

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
	@Override
	public void run(){
		System.out.println("Incoming:");
		
		Message message = Message.read(this.in, true);
		
		System.out.print(message);
		
		Message response = new Processor().process(message);
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
