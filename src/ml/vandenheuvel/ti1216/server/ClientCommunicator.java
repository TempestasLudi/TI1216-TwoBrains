package ml.vandenheuvel.ti1216.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import ml.vandenheuvel.ti1216.http.*;

/**
 * ClientCommunicator handles one HTTP request.
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
	 * The processor for processing the requests.
	 */
	private Processor processor;

	/**
	 * Class constructor, initializes input and output.
	 * 
	 * @param socket the connection socket.
	 */
	public ClientCommunicator(Socket socket, Processor processor) {
		this.socket = socket;
		this.processor = processor;
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
		
		Message response = processor.process(message);
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
