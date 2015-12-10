package ml.vandenheuvel.TI1216.test.api.http;


import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.api.http.*;


public class HeaderTest {

	@Test
	public void DefaultConstructorTest() {
		ResponseLine r1 = new ResponseLine("HTTP/1.1", "200", "OK");
		String r1String = r1.toString();
		HeaderField r1HeaderField = new HeaderField("Connection","close"); 
		String r1HeaderFieldString = r1HeaderField.toString();
		
		Header h1 = new Header();
		String h1HeaderLine = h1.getHeaderLine().toString();
		String h1HeaderField = h1.getField("Connection").toString();
		
		boolean evaluate = r1String.equals(h1HeaderLine) && r1HeaderFieldString.equals(h1HeaderField) ;
		assertTrue(evaluate);
	}
	
	// To do --> Andreas
	@Test
	public void ConstructorTest1(){}
	
	// To do --> Andreas
	@Test
	public void ConstructorTest2(){}
	
	// To do --> Andreas
	@Test
	public void ConstructorTest3(){}
	
	// To do --> Andreas
	@Test
	public void ConstructorTest4(){}
	
}
