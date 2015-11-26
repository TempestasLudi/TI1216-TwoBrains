package ml.vandenheuvel.TI1216.source.api;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.json.JSONObject;

public class ApiThread implements Runnable {
	private Socket socket;
	private PrintWriter out;
	private DataInputStream in;

	public ApiThread(Socket socket) {
		this.socket = socket;
		try {
			this.out = new PrintWriter(socket.getOutputStream());
			this.in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
//			System.out.println(e.getMessage());
		}

	}

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
	
	private String readLine() {
		String line = "";
		int oneLine = 0;
		while (oneLine < 2) {
			try {
				char character = (char) this.in.readByte();
				line += character;
				if (character == "\r".charAt(0)) {
					oneLine = 1;
				}
				else {
					if (oneLine == 1 && character == "\n".charAt(0)) {
						oneLine = 2;
					}
					else {
						oneLine = 0;
					}
				}
			} catch (EOFException e) {
				System.out.println(e.getMessage());
//				e.printStackTrace();
				return line;
			} catch (IOException e){
				System.out.println(e.getMessage());
//				e.printStackTrace();
				return line;
			} 
		}
		return line;
	}
	
	private String readBytes(int count) {
		String line = "";
		for (int i = 0; i < count; i++) {
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
