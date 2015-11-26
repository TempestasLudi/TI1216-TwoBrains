package ml.vandenheuvel.TI1216.source.api;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.json.JSONObject;

/**
 * ApiThread handles one HTTP request.
 * 
 * @author Arnoud van der Leer
 */
public class ApiThread implements Runnable {
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
	public ApiThread(Socket socket) {
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
		boolean run = true;
		System.out.println("Incoming:");
		String requestLineString = this.readLine();
		if (requestLineString.split(" ").length != 3) {
			return;
		}
		HttpRequestLine requestLine = new HttpRequestLine(requestLineString);
		
		HttpHeader header = new HttpHeader(requestLine);
		while (run) {
			String line = this.readLine();
			if (line.equals("\r\n")) {
				run = false;
				continue;
			}
			if (line.contains(":")) {
				header.addField(new HttpHeaderField(line));
			}
		}
		System.out.println(header);
		HttpHeaderField contentLength = header.getField("Content-Length");
		if (contentLength != null && Integer.valueOf(contentLength.getValue()) > 0) {
			String line = this.readBytes(Integer.valueOf(contentLength.getValue()));
			System.out.println(line);
			System.out.println();
			System.out.println();
		}
		
		JSONObject data = new JSONObject();
		this.finish(data);
	}
	
	/**
	 * Reads one line from the input stream (including \r\n).
	 * 
	 * @return one line from the input stream
	 */
	private String readLine() {
		String line = "";
		boolean lineBreak = false;
		while (!lineBreak) {
			try {
				char character = (char) this.in.readByte();
				line += character;
				if (line.length() >= 2 && line.substring(line.length()-2).equals("\r\n")) {
					lineBreak = true;
				}
			} catch (EOFException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return line;
			} catch (IOException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
				return line;
			} 
		}
		return line;
	}
	
	/**
	 * Reads n bytes from the input stream.
	 * 
	 * @param n the number of bytes to read
	 * @return      a string of n bytes from the input stream
	 */
	private String readBytes(int n) {
		String line = "";
		for (int i = 0; i < n; i++) {
			try {
				char character = (char) this.in.readByte();
				line += character;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return line;
	}
	
	/**
	 * Sends data and closes the connection.
	 * 
	 * @param data the data to send
	 */
	public void finish(JSONObject data) {
		HttpHeader header = new HttpHeader(new HttpResponseLine("HTTP/1.1", "200", "OK"));
		header.addField(new HttpHeaderField("Status", "200 OK"));
		header.addField(new HttpHeaderField("Date", new Date().toString()));
		header.addField(new HttpHeaderField("Connection", "close"));
		header.addField(new HttpHeaderField("Content-Type", "text/json"));
		header.addField(new HttpHeaderField("Content-Length", String.valueOf(data.toString().length())));
		this.out.write(header.toString());
		this.out.write("\r\n");
		this.out.write(data.toString());
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
