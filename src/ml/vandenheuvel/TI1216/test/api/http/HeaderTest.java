package ml.vandenheuvel.TI1216.test.api.http;


import static org.junit.Assert.*;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.api.http.*;
import java.util.*;


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
		assertTrue(rString.equals(hHeaderLine));
		assertTrue(rHeaderFieldString.equals(hHeaderField));
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
		
		assertTrue(hlString.equals(hHeaderLine));
		assertTrue(hfString.equals(hHeaderField));
	}
	
	
	@Test
	public void testFirstConstructor2(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		HeaderField hf = new HeaderField("Connection","close"); 
		Header h = new Header(hl);

		assertTrue(hl.equals(h.getHeaderLine()));
		assertTrue(hf.equals(h.getField("Connection")));
	}
	
	
	@Test
	public void testFirstConstructor3(){
		
		HeaderLine hl = new RequestLine("method", "URI", "version");
		String hlString = hl.toString();
		
		Header h = new Header(hl);
		String hHeaderLine = h.getHeaderLine().toString();
		
		assertTrue(hlString.equals(hHeaderLine));
		assertTrue(h.getFields().equals(new HeaderField[0]));
	}
	
	
	@Test
	public void testFirstConstructor4(){
		HeaderLine hl = new RequestLine("method", "URI", "version");
		Header h = new Header(hl);
		
		assertTrue(hl.equals(h.getHeaderLine()));
		assertTrue(h.getFields().equals(new HeaderField[0]));
	}
	
	// To do --> Andreas
	@Test
	public void testSecondConstructor1(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		HeaderField hf = new HeaderField("Connection","close"); 
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf);
		
		HeaderField[] afields = new HeaderField[fields.size()];
		fields.toArray(afields);
		
		Header h = new Header(rl, fields);
		boolean evaluate = true;
		
		
	}

	
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
