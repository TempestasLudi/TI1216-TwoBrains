package ml.vandenheuvel.TI1216.test.api.http;


import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.api.http.*;


public class HeaderTest {

	
	@Test
	public void DefaultConstructorTest() {
		ResponseLine r = new ResponseLine("HTTP/1.1", "200", "OK");
		String rString = r.toString();
		HeaderField rHeaderField = new HeaderField("Connection","close"); 
		String rHeaderFieldString = rHeaderField.toString();
		
		Header h = new Header();
		String hHeaderLine = h.getHeaderLine().toString();
		String hHeaderField = h.getField("Connection").toString();
		
		boolean evaluate = rString.equals(hHeaderLine) && rHeaderFieldString.equals(hHeaderField) ;
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testFirstConstructor1(){
		HeaderField hf = new HeaderField("Connection","close"); 
		String hfString = hf.toString();
		
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		String hlString = hl.toString();
		
		Header h = new Header(hl);
		String hHeaderLine = h.getHeaderLine().toString();
		String hHeaderField = h.getField("Connection").toString();
		
		boolean evaluate = hlString.equals(hHeaderLine) && hfString.equals(hHeaderField);
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testFirstConstructor2(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		HeaderField hf = new HeaderField("Connection","close"); 
		Header h = new Header(hl);

		boolean evaluate = hl.equals(h.getHeaderLine()) && hf.equals(h.getField("Connection"));
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testFirstConstructor3(){
		
		HeaderLine hl = new RequestLine("method", "URI", "version");
		String hlString = hl.toString();
		
		Header h = new Header(hl);
		String hHeaderLine = h.getHeaderLine().toString();
		
		boolean evaluate = hlString.equals(hHeaderLine) && h.getFields().equals(new HeaderField[0]);
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testFirstConstructor4(){
		HeaderLine hl = new RequestLine("method", "URI", "version");
		Header h = new Header(hl);
		
		boolean evaluate = hl.equals(h.getHeaderLine())&& h.getFields().equals(new HeaderField[0]);
		assertTrue(evaluate);
	}
	
	// To do --> Andreas
	@Test
	public void testSecondConstructor1(){}

	
	// To do --> Andreas
	@Test
	public void testSecondConstructor2(){}
	
	
	// To do --> Andreas
	@Test
	public void testSecondConstructor3(){}
	
	
	// To do --> Andreas
	@Test
	public void testSecondConstructor4(){}
}
