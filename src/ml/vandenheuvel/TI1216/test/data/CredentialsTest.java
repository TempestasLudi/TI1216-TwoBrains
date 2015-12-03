package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.Credentials;

public class CredentialsTest {

	@Test
	public void testCredentialsUsername(){
		Credentials credentials = new Credentials("a1", "b2", true);
		assertEquals(credentials.getUsername(), "a1");
	}
	
	@Test
	public void testCredentialsPassword(){
		Credentials credentials = new Credentials("a1", "b2", true);
		assertEquals(credentials.getPassword(), "b2");
	}

	@Test
	public void testGetSetUsername(){
		Credentials credentials = new Credentials(null, "b2", true);
		credentials.setUsername("a1");
		assertEquals(credentials.getUsername(), "a1");
	}

	@Test
	public void testGetSetPassword(){
		Credentials credentials = new Credentials("a1", null, false);
		credentials.setPassword("b2");
		assertEquals(credentials.getPassword(), "b2");
	}
	
	@Test
	public void testJSON(){
		Credentials credentials = new Credentials("a1", "a2", true);
		System.out.println(credentials.toJSON());
		assertTrue(credentials.equals(Credentials.fromJSON(credentials.toJSON())));
	}

}
