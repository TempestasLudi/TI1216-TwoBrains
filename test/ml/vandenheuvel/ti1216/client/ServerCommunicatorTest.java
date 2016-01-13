package ml.vandenheuvel.ti1216.client;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.Faculty;
import ml.vandenheuvel.ti1216.data.Match;
import ml.vandenheuvel.ti1216.data.User;
import ml.vandenheuvel.ti1216.http.Body;
import ml.vandenheuvel.ti1216.http.Header;
import ml.vandenheuvel.ti1216.http.HeaderField;
import ml.vandenheuvel.ti1216.http.Message;
import ml.vandenheuvel.ti1216.http.RequestLine;
import ml.vandenheuvel.ti1216.http.ResponseLine;

public class ServerCommunicatorTest {

	@Test
	public void testLogin() {
		Credentials cred = new Credentials("Test", "Test");
		
		
		
		
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendChat() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchFaculty() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchFaculties() {
		fail("Not yet implemented");
	}

	@Test
	public void testSend() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchChats() {
		fail("Not yet implemented");
	}

	@Test
	public void testFetchMatches() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateMessage() {
		//TODO 
		fail("Not yet implemented");
	}
}
