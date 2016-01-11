package ml.vandenheuvel.ti1216.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import ml.vandenheuvel.ti1216.http.*;

/**
 * ClientCommunicator handles one HTTP request.
 */
public class ClientCommunicator implements Runnable {
	
	private static Logger logger = Logger.getLogger("ml.vandenheuvel.ti1216.server");
	
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
			logger.finest("Opened OutputStream and PrintWriter over socket.");
		} catch (IOException e) {
			logger.fine(e.getMessage());
		}
		try{
			this.in = new DataInputStream(socket.getInputStream());
			logger.finest("Opened InputStream and DataInputStream ovoer socket.");
		} catch (IOException e) {
			logger.fine(e.getMessage());
		}
	}

	/**
	 * The runner method, reads all incoming data, processes it and generates output accordingly.
	 */
	@Override
	public void run(){
		logger.finer("Receiving message...");
		Message message = Message.read(this.in, true);
		logger.finer("Message received. Processing... ");
		Message response = processor.process(message);
		logger.finer("Message processed. Finishing up... ");
		this.finish(response);
		logger.finer("Closing thread.");
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
		logger.finer("Writing message...");
		this.out.write(outputMessage.toString());
		logger.finer("Message written. Closing connections...");
		this.close();
		logger.finer("Connections closed.");
	}
	
	/**
	 * Flushes streams and closes the connection.
	 */
	private void close() {
		logger.finest("Flushing out stream...");
		this.out.flush();
		logger.finest("Out stream flushed.");
		try {
			logger.finest("Closing in stream...");
			this.in.close();
			logger.finest("In stream closed.");
			logger.finest("Closing out stream...");
			this.out.close();
			logger.finest("Out stream closed.");
			logger.finest("Closing socket...");
			this.socket.close();
			logger.finest("Socket closed.");
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
	}

}
