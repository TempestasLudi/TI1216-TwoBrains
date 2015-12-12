package ml.vandenheuvel.TI1216.source.api.http;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Date;

/**
 * Message represents an HTTP message.
 * 
 * @author Arnoud van der Leer
 */
public class Message {

	/**
	 * The message header.
	 */
	private Header header;

	/**
	 * The message body.
	 */
	private Body body;

	/**
	 * Class constructor.
	 * 
	 * @param header the message header
	 * @param body the message body
	 */
	public Message(Header header, Body body) {
		this.header = header;
		this.body = body;
	}

	/**
	 * Gets the message header.
	 * 
	 * @return the message header
	 */
	public Header getHeader(){
		return this.header;
	}

	/**
	 * Gets the message body.
	 * 
	 * @return the message body
	 */
	public Body getBody(){
		return this.body;
	}

	/**
	 * Changes the message header.
	 * 
	 * @param header the message header
	 */
	public void setHeader(Header header){
		this.header = header;
	}

	/**
	 * Changes the message body.
	 * 
	 * @param body the message body
	 */
	public void setBody(Body body){
		this.body = body;
	}
	
	/**
	 * Merges this message with another one.
	 * 
	 * Merges the header, replaces the body.
	 * 
	 * @param message the message to merge with
	 */
	public void merge(Message message) {
		this.header.merge(message.getHeader());
		this.body = message.getBody();
	}
	
	/**
	 * Creates a new message from a data input stream.
	 * 
	 * @param in the input stream to read from
	 * @return a new message based on the data from the input stream
	 */
	public static Message read(DataInputStream in){
		String requestLineString = readLine(in);
		if (requestLineString.split(" ").length != 3) {
			return null;
		}
		RequestLine requestLine = new RequestLine(requestLineString);

		Header header = new Header(requestLine);
		boolean read = true;
		while (read) {
			String line = readLine(in);
			if ("\r\n".equals(line)) {
				read = false;
				continue;
			}
			if (line.contains(":")) {
				header.addField(new HeaderField(line));
			}
		}
		HeaderField contentLength = header.getField("Content-Length");
		Body body = new Body("");
		if (contentLength != null && Integer.valueOf(contentLength.getValue()) > 0) {
			body = new Body(readBytes(in, Integer.valueOf(contentLength.getValue())));
		}
		return new Message(header, body);
	}

	/**
	 * Reads one line from the input stream (including \r\n).
	 * 
	 * @param in the input stream
	 * @return one line from the input stream
	 */
	private static String readLine(DataInputStream in){
		String line = "";
		boolean lineBreak = false;
		while (!lineBreak) {
			try {
				char character = (char) in.readByte();
				line += Character.toString(character);
				if (line.length() >= 2 && "\r\n".equals(line.substring(line.length() - 2))) {
					lineBreak = true;
				}
			} catch (EOFException e) {
				System.out.println("Another end of file.");
				return line;
			} catch (IOException e) {
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
	 * @param in the input stream to read from
	 * @param n the number of bytes to read
	 * @return a string of n bytes from the input stream
	 */
	private static String readBytes(DataInputStream in, int n){
		String line = "";
		for (int i = 0; i < n; i++) {
			try {
				char character = (char) in.readByte();
				line += character;
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return line;
	}

	/**
	 * Generates the HTTP message in string format.
	 * 
	 * @return the HTTP message in string format
	 */
	@Override
	public String toString(){
		if (!(this.header.getHeaderLine() instanceof ResponseLine) || "200".equals(((ResponseLine)this.header.getHeaderLine()).getCode())) {
			this.header.addField(new HeaderField("Content-Length", 
					String.valueOf(body.getContent().length())));
			this.header.addField(new HeaderField("Content-Type", "text/json"));
		}
		this.header.addField(new HeaderField("Date", 
				new Date().toString()));
		String result = this.header.toString() + "\r\n" + this.body.toString();
		if (this.body.toString().length() > 0) {
			result += "\r\n\r\n";
		}
		return result;
	}

}
