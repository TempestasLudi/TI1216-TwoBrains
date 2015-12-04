package ml.vandenheuvel.TI1216.test.api.http;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.api.http.ResponseLine;

public class ResponseLineTest {

	@Test
	public void testConstructorVersion1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("HTTP/1.1", testLine.getVersion());
	}
	
	@Test
	public void testConstructorVersion2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1 200 OK");
		assertEquals("HTTP/1.1", testLine.getVersion());
	}
	
	@Test
	public void testConstructorCode1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("200", testLine.getCode());
	}
	
	@Test
	public void testConstructorCode2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1 200 OK");
		assertEquals("200", testLine.getCode());
	}
	
	@Test
	public void testConstructorStatus1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("OK", testLine.getStatus());
	}
	
	@Test
	public void testConstructorStatus2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1 200 OK");
		assertEquals("OK", testLine.getStatus());
	}
	
	@Test
	public void testGetVersion1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("HTTP/1.1", testLine.getVersion());
		}
	
	@Test
	public void testGetVersion2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertFalse("HTTP/1.2".equals(testLine.getVersion()));
		}
	
	@Test
	public void testGetCode1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("200", testLine.getCode());
		}
	
	@Test
	public void testGetCode2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertFalse("201".equals(testLine.getCode()));
		}
	
	@Test
	public void testGetStatus1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("OK", testLine.getStatus());
		}
	
	@Test
	public void testGetStatus2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertFalse("OK ".equals(testLine.getStatus()));
		}
	
	@Test
	public void testSetVersion1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("HTTP/1.1", testLine.getVersion());
		
		testLine.setVersion("HTTP/1.2");
		assertEquals("HTTP/1.2", testLine.getVersion());
		}
	
	@Test
	public void testSetVersion2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("HTTP/1.1", testLine.getVersion());
		testLine.setVersion("HTTP/1.2");
		assertFalse("HTTP/1.1".equals(testLine.getVersion()));
		}
	
	@Test
	public void testSetCode1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("200", testLine.getCode());
		
		testLine.setVersion("201");
		assertEquals("201", testLine.getCode());
		}
	
	@Test
	public void testSetCode2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("200", testLine.getCode());
		testLine.setVersion("201");
		assertFalse("200".equals(testLine.getCode()));
		}
	
	@Test
	public void testSetStatus1() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("OK", testLine.getStatus());
		
		testLine.setVersion("Permanently Moved");
		assertEquals("Permanently Moved", testLine.getVersion());
		}
	
	@Test
	public void testSetStatus2() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("OK", testLine.getStatus());
		testLine.setStatus("Permanently moved");
		assertFalse("OK ".equals(testLine.getStatus()));
		}
	
	@Test
	public void testToString() {
		ResponseLine testLine = new ResponseLine("HTTP/1.1", "200", "OK");
		assertEquals("HTTP/1.1 200 OK \r\n", testLine.toString());
	}
	
}
