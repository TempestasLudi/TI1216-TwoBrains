package ml.vandenheuvel.TI1216.test.api.http;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.api.http.Body;
import ml.vandenheuvel.TI1216.source.api.http.Header;
import ml.vandenheuvel.TI1216.source.api.http.HeaderField;
import ml.vandenheuvel.TI1216.source.api.http.Message;
import ml.vandenheuvel.TI1216.source.api.http.RequestLine;

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
}
