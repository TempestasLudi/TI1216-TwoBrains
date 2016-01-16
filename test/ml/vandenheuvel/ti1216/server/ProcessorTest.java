package ml.vandenheuvel.ti1216.server;

import static org.junit.Assert.*;
import org.junit.Test;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.http.HeaderField;

public class ProcessorTest {

	/**
	Weet niet hoe ik dit moet testen. Er is geen vergelijkingsmethode gedefinieerd in zowel 
	Processor- als DBC-klasse. 
	
	@Test
	public void testConstructor1() {	
		fail("Not yet implemented");
	}
	@Test
	public void testConstructor2() {
		fail("Not yet implemented");
	}
	*/
	
	@Test
	public void testGetCredentials1() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+cred1.toString());
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testGetCredentials2() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testCheckAuth1() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testCheckAuth2() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessUser1() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessUser2() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessChat1() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessChat2() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessFaculty1() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessFaculty2() {
		fail("Not yet implemented");
	}
	

	@Test
	public void testProcess1() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcess2() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testProcess3() {
		fail("Not yet implemented");
	}
	

}
