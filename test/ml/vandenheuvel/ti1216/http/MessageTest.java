package ml.vandenheuvel.ti1216.api.http;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ml.vandenheuvel.ti1216.http.*;

import java.util.Date;

public class MessageTest {

	@Test
	public void testConstructorHeader() {
		Header header = new Header();
		Body body = new Body("Content");
		Message testMessage = new Message(header, body);
		
		assertEquals(header, testMessage.getHeader());
	}
	
	@Test
	public void testConstructorBody() {
		Header header = new Header();
		Body body = new Body("Content");
		Message testMessage = new Message(header, body);
		
		assertEquals(body, testMessage.getBody());
	}
	
	@Test
	public void testGetHeader() {
		Header header = new Header();
		Body body = new Body("Content");
		Message testMessage = new Message(header, body);
		
		assertEquals(header, testMessage.getHeader());
	}
	
	@Test
	public void testGetBody() {
		Header header = new Header();		
		Body body = new Body("Content");
		Body body2 = new Body("Content");
		Message testMessage = new Message(header, body);
		
		assertEquals(body2, testMessage.getBody());
	}
	
	@Test
	public void testSetHeader() {
		Header header = new Header();
		Header header2 = new Header();
		Body body = new Body("Content");
		Message testMessage = new Message(header, body);
		
		assertEquals(header, testMessage.getHeader());
		testMessage.setHeader(header2);
		assertEquals(header2, testMessage.getHeader());
	}
	
	@Test 
	public void testSetBody() {
		Header header = new Header();
		Body body = new Body("Content");
		Body body2 = new Body("Content 2");
		Message testMessage = new Message(header, body);
		
		assertEquals(body, testMessage.getBody());
		testMessage.setBody(body2);
		assertEquals(body2, testMessage.getBody());
	}
	
	@Test
	public void testMerge() {
		RequestLine reqLine = new RequestLine("GET /uri HTTP/1.1");
		ArrayList<HeaderField> hField = new ArrayList<HeaderField>();
		
		Header header = new Header(reqLine, hField);
		
		Header header2 = new Header(reqLine, hField);
		Body body = new Body("Content");
		Message testMessage1 = new Message(header, body);
		Message testMessage2 = new Message(header2, body);
		
		testMessage1.merge(testMessage2);
		assertEquals(testMessage1.getHeader(), header2);
		
	}
	
	@Test
	public void testToString1() {
		RequestLine reqLine = new RequestLine("GET /chat HTTP/1.1");
		ArrayList<HeaderField> hField = new ArrayList<HeaderField>();
		Header header = new Header(reqLine, hField);
		Body body = new Body("Content");
		Message testMessage1 = new Message(header, body);
		
		assertEquals("GET /chat HTTP/1.1\r\n"
				+ "Content-Length: 7\r\n"
				+ "Content-Type: text/json\r\n"
				+ "Date: " + new Date().toString()
				+ "\r\n\r\n"
				+ "Content\r\n\r\n", testMessage1.toString());
	}
	
	@Test
	public void testToString2() {
		ResponseLine resLine = new ResponseLine("HTTP/1.1 200 OK");
		Header header = new Header(resLine);
		Body body = new Body("Content");
		Message testMessage1 = new Message(header, body);
		
		assertEquals("HTTP/1.1 200 OK\r\n"
				+ "Connection: close\r\n"
				+ "Content-Length: 7\r\n"
				+ "Content-Type: text/json\r\n"
				+ "Date: " + new Date().toString()
				+ "\r\n\r\n"
				+ "Content\r\n\r\n", testMessage1.toString());
	
	}
	
	@Test
	public void testToString3() {
		ResponseLine resLine = new ResponseLine("HTTP/1.1", "404", "File Not Found");
		Header header = new Header(resLine);
		Body body = new Body("Content");
		Message testMessage1 = new Message(header, body);
		assertEquals("HTTP/1.1 404 File Not Found\r\n"
				+ "Connection: close\r\n"
				+ "Date: " + new Date().toString()
				+ "\r\n\r\n"
				+ "Content\r\n\r\n", testMessage1.toString());
	
	}
	
	@Test
	public void testToString4() {
		ResponseLine resLine = new ResponseLine("HTTP/1.1 200 OK");
		Header header = new Header(resLine);
		Body body = new Body("");
		Message testMessage1 = new Message(header, body);
		
		assertEquals("HTTP/1.1 200 OK\r\n"
				+ "Connection: close\r\n"
				+ "Content-Length: 0\r\n"
				+ "Content-Type: text/json\r\n"
				+ "Date: " + new Date().toString()
				+ "\r\n\r\n", testMessage1.toString());
	
	}
}
