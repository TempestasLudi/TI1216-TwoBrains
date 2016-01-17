package ml.vandenheuvel.ti1216.server;

import static org.junit.Assert.*;

import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
<<<<<<< HEAD
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.http.HeaderField;
=======

import ml.vandenheuvel.ti1216.*;
import ml.vandenheuvel.ti1216.data.ChatMessage;
import ml.vandenheuvel.ti1216.data.Credentials;
import ml.vandenheuvel.ti1216.data.DatabaseCommunicator;
import ml.vandenheuvel.ti1216.data.User;
import ml.vandenheuvel.ti1216.http.Body;
import ml.vandenheuvel.ti1216.http.Header;
import ml.vandenheuvel.ti1216.http.HeaderField;
import ml.vandenheuvel.ti1216.http.Message;
import ml.vandenheuvel.ti1216.http.ResponseLine;
import ml.vandenheuvel.ti1216.server.*;

>>>>>>> ProcessorTest continued. Instances must still be concretized.

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
	
	// Alle andere test moeten nog gepreciseerd worden.
	
	
	@Test
	public void testGetCredentials1() {
		Credentials cred1 = new Credentials("username1","password1");
<<<<<<< HEAD
		HeaderField hf1 = new HeaderField("Authorization: "+cred1.toString());
		fail("Not yet implemented");
=======
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("","","");
		Header h = new Header(hl1,fields1);
		
		Message request = new Message(h,new Body(""));
		Credentials cred2 = Processor.getCredentials(request);
		
		assertTrue(cred1.equals(cred2));
		
>>>>>>> ProcessorTest continued. Instances must still be concretized.
	}
	
	
	@Test
	public void testGetCredentials2() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("","","");
		Header h = new Header(hl1,fields1);
		
		Message request = new Message(h,new Body(""));
		cred1 = new Credentials("","");
		Credentials cred2 = Processor.getCredentials(request);
		
		assertFalse(cred1.equals(cred2));
	}
	
	
	@Test
	public void testGetCredentials3() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Random: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		cred1 = new Credentials("","");
		Credentials cred2 = Processor.getCredentials(request);
		
		assertFalse(cred1.equals(cred2));
		assertNull(cred2);
	}
	
	
	
	@Test
	public void testCheckAuth() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Random: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		Credentials cred2 = Processor.getCredentials(request);
		
		boolean test1 = cred1 != null && communicator.canLogin(cred1);
		boolean test2 = cred2 != null && communicator.canLogin(cred2);
		
		assertTrue((test1&&test2)||(!test1&&!test2));
		
		boolean test3 = proc.checkAuth(request);
		assertTrue((test1&&test3)||(!test1&&!test3));
		
	}
	
	
	@Test
	public void testProcessUser1() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("PUT","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response1 = new Message(new Header(), new Body("{\"success\":true}");
		Message response2 = new Message(new Header(), new Body("{\"success\":false,\"reason\":\"You are not allowed to " + "register with those credentials.\"}");
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(resonse1));
		Message result2 = proc.processUser(request);
		assertTrue(result2.equals(resonse2));
		
	}
	
	
	@Test
	public void testProcessUser2() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("PUT","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response2 = new Message(new Header(), new Body("{\"success\":false,\"reason\":\"You are not allowed to " + "register with those credentials.\"}");
		
		Message result2 = proc.processUser(request);
		assertTrue(result2.equals(resonse2));
		
	}
	
	
	@Test
	public void testProcessUser3() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("GET","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response1 = new Message(new Header(), new Body("");
		response1.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 401 Unauthorized"));
		response1.getHeader().addField(new HeaderField("WWW-Authenticate", "Basic realm=\"MCRealms\""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1)); 
	
	}
	
	
	
	@Test
	public void testProcessUser4() {
		Credentials cred1 = new Credentials("a1","p1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("GET","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response1 = new Message(new Header(), new Body("");
	
		User user1 = this.communicator.getUser(cred1.getUsername());
		ChatMessage[] messagesData = this.communicator.getChats(user1.getUsername(), false);
		JSONArray messages1 = new JSONArray();
		for (int i = 0; i < messagesData.length; i++) {
			if (!messagesData[i].isSeen()) {
				messagesData[i].setSeen(true);
				this.communicator.save(messagesData[i]);
			}
			messages1.put(messagesData[i].toJSON());
		}
		response1.getBody().setContent(new JSONObject().put("success", true).put("user", user.toJSON()).put("messages", messages1).toString());
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1)); 
	
	}
	
	
	public void testProcessUser5() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("UPDATE","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response1 = new Message(new Header(), new Body("");
		response1.getHeader().setHeaderLine(new ResponseLine("HTTP/1.1 401 Unauthorized"));
		response1.getHeader().addField(new HeaderField("WWW-Authenticate", "Basic realm=\"MCRealms\""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1)); 
	
	}
	

	@Test
	public void testProcessUser6() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("UPDATE","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response1 = new Message(new Header(), new Body("");
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1)); 
	
	}
	
	
	@Test
	public void testProcessUser7() {
		Credentials cred1 = new Credentials("username1","password1");
		HeaderField hf1 = new HeaderField("Authorization: "+Base64.getEncoder().encode(cred1.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("NONE","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		HeaderField hfres = new HeaderField("Allow", "PUT, GET, UPDATE");
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hfres);
		Message response1 = new Message(new Header(new ResponseLine("HTTP/1.1 405 Method Not Allowed"),fields), new Body(""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1)); 
	}
	
	
	@Test
	public void testProcessChat1() {
		Chatmessage cm = new ChatMessage("","","","",true);
		HeaderField hf1 = new HeaderField(Base64.getEncoder().encode(cm.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("PUT","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response1 = new Message(new Header(new ResponseLine(), new Body(""));
		
		Message result1 = proc.processChat(request);
		assertTrue(result1.equals(response1)); 
	}
	
	
	@Test
	public void testProcessChat2() {
		Chatmessage cm = new ChatMessage("","","","",true);
		HeaderField hf1 = new HeaderField(Base64.getEncoder().encode(cm.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("GET","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		Message response1 = new Message(new Header(new ResponseLine(), new Body(""));
		
		
		
		Message result1 = proc.processChat(request);
		assertTrue(result1.equals(response1)); 
	}
	
	
	@Test
	public void testProcessChat3() {
		Chatmessage cm = new ChatMessage("","","","",true);
		HeaderField hf1 = new HeaderField(Base64.getEncoder().encode(cm.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("NONE","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		HeaderField hfres = new HeaderField("Allow", "PUT, GET, UPDATE");
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hfres);
		Message response1 = new Message(new Header(new ResponseLine("HTTP/1.1 405 Method Not Allowed"),fields), new Body(""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1)); 
	}
	
	
	@Test
	public void testProcessFaculty1() {
		Factulty f = new Faculty("","",new ArrayList<Programs>());
		HeaderField hf1 = new HeaderField(Base64.getEncoder().encode(f.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("GET","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		HeaderField hfres = new HeaderField("Allow", "GET");
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hfres);
		Message response1 = new Message(new Header(new ResponseLine("HTTP/1.1 405 Method Not Allowed"),fields), new Body(""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1));
	}
	
	
	@Test
	public void testProcessFaculty2() {
		Factulty f = new Faculty("","",new ArrayList<Programs>());
		HeaderField hf1 = new HeaderField(Base64.getEncoder().encode(f.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("GET","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		HeaderField hfres = new HeaderField("Allow", "GET");
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hfres);
		Message response1 = new Message(new Header(new ResponseLine("HTTP/1.1 405 Method Not Allowed"),fields), new Body(""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1));
	}
	
	
	@Test
	public void testProcessFaculty3() {
		Factulty f = new Faculty("","",new ArrayList<Programs>());
		HeaderField hf1 = new HeaderField(Base64.getEncoder().encode(f.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("GET","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		HeaderField hfres = new HeaderField("Allow", "GET");
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hfres);
		Message response1 = new Message(new Header(new ResponseLine("HTTP/1.1 405 Method Not Allowed"),fields), new Body(""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1));
	}
	
	
	@Test
	public void testProcessFaculty4() {
		Factulty f = new Faculty("","",new ArrayList<Programs>());
		HeaderField hf1 = new HeaderField(Base64.getEncoder().encode(f.toJSON().toString()));
		List<HeaderField> fields1 = new ArrayList<HeaderField>();
		fields1.add(hf1);
		Headerline hl1 = new RequestLine("NONE","","");
		Header h = new Header(hl1,fields1);
		Message request = new Message(h,new Body(""));
		DatabaseCommunicator communicator = new DatabaseCommunicator("","","","");
		Processor proc = new Processor("","","","");
		
		HeaderField hfres = new HeaderField("Allow", "GET");
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hfres);
		Message response1 = new Message(new Header(new ResponseLine("HTTP/1.1 405 Method Not Allowed"),fields), new Body(""));
		
		Message result1 = proc.processUser(request);
		assertTrue(result1.equals(response1));
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
