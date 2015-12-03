package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.Login;

public class LoginTest 
{

	@Test
	public void testConstructorLogin1() 
	{
		Login login = new Login("azaidman","AndyAntwerpen2015",true);
		assertEquals("azaidman",login.getUsername());
	}
	
	@Test
	public void testConstructorLogin2() 
	{
		Login login = new Login("azaidman","AndyAntwerpen2015",true);
		assertEquals("AndyAntwerpen2015",login.getPassword());
	}
	
	@Test
	public void testConstructorLogin3() 
	{
		Login login = new Login("azaidman","AndyAntwerpen2015",true);
		assertTrue(login.getSignUp());
	}
	
	@Test
	public void testGetSetSignUp()
	{
		Login login = new Login("azaidman","AndyAntwerpen2015",false);
		login.setSignUp(true);
		assertTrue(login.getSignUp());
	}

}
