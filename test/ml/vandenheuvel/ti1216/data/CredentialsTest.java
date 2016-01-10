package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.ti1216.data.Credentials;

public class CredentialsTest {

	@Test
	public void testCredentialsUsername() {
		Credentials credentials = new Credentials("a1", "b2");
		assertEquals(credentials.getUsername(), "a1");
	}

	@Test
	public void testCredentialsPassword() {
		Credentials credentials = new Credentials("a1", "b2");
		assertEquals(credentials.getPassword(), "b2");
	}

	@Test
	public void testGetSetUsername() {
		Credentials credentials = new Credentials(null, "b2");
		credentials.setUsername("a1");
		assertEquals(credentials.getUsername(), "a1");
	}

	@Test
	public void testGetSetPassword() {
		Credentials credentials = new Credentials("a1", null);
		credentials.setPassword("b2");
		assertEquals(credentials.getPassword(), "b2");
	}

	@Test
	public void testFromToJSON() {
		Credentials credentials = new Credentials("azaidman", "Andyyy");
		assertEquals(credentials, Credentials.fromJSON(credentials.toJSON()));
	}

	@Test
	public void testEquals1() {
		Credentials credentials1 = new Credentials("azaidman", "Andyyy");
		Credentials credentials2 = new Credentials("azaidman", "Andyyy");
		assertTrue(credentials1.equals(credentials2));
	}

	@Test
	public void testEquals2() {
		Credentials credentials1 = new Credentials("azaidman", "Andyyy");
		assertFalse(credentials1.equals(null));
	}

	@Test
	public void testEquals3() {
		Credentials credentials1 = new Credentials("azaidman", "Andyyy");
		assertFalse(credentials1.equals(4));
	}

	@Test
	public void testEquals4() {
		Credentials credentials1 = new Credentials("azaidman", "Andyyy");
		Credentials credentials2 = new Credentials("azaidman", "Andyy");
		assertFalse(credentials1.equals(credentials2));
	}

	@Test
	public void testEquals5() {
		Credentials credentials1 = new Credentials("azaidman", "Andyyy");
		Credentials credentials2 = new Credentials("azaidmann", "Andyyy");
		assertFalse(credentials1.equals(credentials2));
	}

}
