package ml.vandenheuvel.TI1216.test.api.http;

import static org.junit.Assert.*;
import org.junit.Test;
import ml.vandenheuvel.TI1216.source.api.http.Body;

public class BodyTest {

	@Test
	public void testConstructorBody() 
	{
		Body body = new Body("Hello, World!");
		assertEquals("Hello, World!", body.getContent());
	}
	
	@Test
	public void testGetSetContent()
	{
		Body body = new Body(null);
		body.setContent("Hello, World!");
		assertEquals("Hello, World!", body.getContent());
	}
	
	@Test
	public void testToString()
	{
		Body body = new Body("Hello, World!");
		assertEquals(body.getContent(), body.toString());
	}
	
	@Test
	public void testEqualsPositive()
	{
		Body body1 = new Body("Hello, World!");
		Body body2 = new Body("Hello, World!");
		assertTrue(body1.equals(body2));
	}
	
	@Test
	public void testEqualsNegative1()
	{
		Body body1 = new Body("Hello, World!");
		Body body2 = new Body("Hello, world!");
		assertFalse(body1.equals(body2));
	}
	
	@Test
	public void testEqualsNegative2()
	{
		Body body = new Body("Hello, World!");
		assertFalse(body.equals(21));
	}

}
