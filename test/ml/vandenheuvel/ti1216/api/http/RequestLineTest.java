package ml.vandenheuvel.ti1216.api.http;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.ti1216.api.http.RequestLine;
import ml.vandenheuvel.ti1216.api.http.ResponseLine;

public class RequestLineTest {
	
	@Test
	public void testConstructorMethod1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("GET", testLine.getMethod());		
	}
	
	@Test
	public void testConstructorMethod2() {
		RequestLine testLine = new RequestLine("GET /uri HTTP/1.1");
		assertEquals("GET", testLine.getMethod());		
	}
	
	@Test
	public void testConstructorURI1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("/uri", testLine.getUri());	
	}
	
	@Test
	public void testConstructorURI2() {
		RequestLine testLine = new RequestLine("GET /uri HTTP/1.1");
		assertEquals("/uri", testLine.getUri());
	}
	
	@Test
	public void testConstructorVersion1(){
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("HTTP/1.1", testLine.getVersion());
	}
	
	@Test
	public void testConstructorVersion2(){
		RequestLine testLine = new RequestLine("GET /uri HTTP/1.1");
		assertEquals("HTTP/1.1", testLine.getVersion());
	}
	
	@Test
	public void testGetMethod1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("GET", testLine.getMethod());
		
	}
	
	@Test
	public void testGetMethod2() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertFalse("POST".equals(testLine.getMethod()));
		
	}
	
	@Test
	public void testGetURI1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("/uri", testLine.getUri());
	}
	
	@Test
	public void testGetURI2() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertFalse("/url".equals(testLine.getUri()));
	}
	
	@Test
	public void testGetVersion1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("HTTP/1.1", testLine.getVersion());
		}
	
	@Test
	public void testGetVersion2() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertFalse("HTTP/1.2".equals(testLine.getVersion()));
		}
	
	@Test
	public void testSetMethod1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("GET", testLine.getMethod());
		testLine.setMethod("POST");
		assertEquals("POST", testLine.getMethod());
	}
	
	@Test
	public void testSetMethod2() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("GET", testLine.getMethod());
		testLine.setMethod("POST");
		assertFalse(testLine.getMethod().equals("GET"));
	}
	
	@Test
	public void testSetUri1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("/uri", testLine.getUri());
		testLine.setUri("/url");
		assertEquals("/url", testLine.getUri());
	}
	
	@Test
	public void testSetUri2() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("/uri", testLine.getUri());
		testLine.setUri("/url");
		assertFalse(testLine.getUri().equals("/uri"));
	}
	
	@Test
	public void testSetVersion1() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("HTTP/1.1", testLine.getVersion());
		testLine.setVersion("HTTP/1.2");
		assertEquals("HTTP/1.2", testLine.getVersion());
	}
	
	@Test
	public void testSetVersion2() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("/uri", testLine.getUri());
		testLine.setVersion("/url");
		assertFalse(testLine.getVersion().equals("/uri"));
	}
	
	@Test
	public void testToString() {
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals("GET /uri HTTP/1.1\r\n", testLine.toString());
	}
	
	@Test
	public void testEqualsTrue(){
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		RequestLine testLine2 = new RequestLine("GET", "/uri", "HTTP/1.1");
		assertEquals(testLine, testLine2);
	}
	
	@Test
	public void testEqualsFalse1(){
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		RequestLine testLine2 = new RequestLine("POST", "/uri", "HTTP/1.1");		
		assertFalse(testLine.equals(testLine2));				
	}
	
	@Test
	public void testEqualsFalse2(){
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		RequestLine testLine2 = new RequestLine("GET", "/chat", "HTTP/1.1");		
		assertFalse(testLine.equals(testLine2));				
	}
	
	@Test
	public void testEqualsFalse3(){
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		RequestLine testLine2 = new RequestLine("GET", "/uri", "HTTP/1.2");		
		assertFalse(testLine.equals(testLine2));				
	}
	@Test
	public void testEqualsNotRL(){
		RequestLine testLine = new RequestLine("GET", "/uri", "HTTP/1.1");
		ResponseLine testLine2 = new ResponseLine("HTTP/1.1 200 OK");	
		assertFalse(testLine.equals(testLine2));
		
	}
}
