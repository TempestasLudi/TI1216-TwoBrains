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
		
		boolean evaluate = hlString.equals(hHeaderLine);
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testFirstConstructor4(){
		HeaderLine hl = new RequestLine("method", "URI", "version");
		Header h = new Header(hl);
		
		boolean evaluate = hl.equals(h.getHeaderLine());
		assertTrue(evaluate);
	
	}
	
	
	@Test
	public void testSecondConstructor1(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		
		Header h = new Header(rl, fields);
		boolean evaluate1 = h.getHeaderLine().equals(rl);
		
		boolean evaluate2 = true;
		for(int i=0; i<fields.size();i++){
			evaluate2 = evaluate2 && fields.get(i).toString().equals(h.getFields()[i].toString());
			}
		
		assertTrue(evaluate1&&evaluate2);
		
	}

	
	@Test
	public void testSecondConstructor2(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		
		Header h = new Header(rl, fields);
		boolean evaluate1 = h.getHeaderLine().equals(rl);
		
		boolean evaluate2 = true;
		for(int i=0; i<fields.size();i++){
			evaluate2 = evaluate2 && fields.get(i).equals(h.getFields()[i]);
			}
		
		assertTrue(evaluate1&&evaluate2);	
	}
	
	
	@Test
	public void testGetFields1(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		HeaderField hf = new HeaderField("Connection","close"); 
		Header h = new Header(hl);
		
		HeaderField[] hfs1 = {hf};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].toString().equals(hfs1[i].toString());
			}
		
		assertTrue(evaluate);
	}
	
	@Test
	public void testGetFields2(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		HeaderField hf = new HeaderField("Connection","close"); 
		Header h = new Header(hl);
		
		HeaderField[] hfs1 = {hf};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].equals(hfs1[i]);
			}
		
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testGetFields3(){
		HeaderLine hl = new RequestLine("method", "URI", "version");
		Header h = new Header(hl);
		
		HeaderField[] hfs1 = new HeaderField[h.getFields().length];
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].equals(hfs1[i]);
			}
		
		assertTrue(evaluate);	
	}
	
	
	@Test
	public void testGetFields4(){
		HeaderLine hl = new RequestLine("method", "URI", "version");
		Header h = new Header(hl);
		
		HeaderField[] hfs1 = new HeaderField[h.getFields().length];
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].toString().equals(hfs1[i].toString());
			}
		
		assertTrue(evaluate);	
	}
	
	
	@Test
	public void testGetFields5(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		HeaderField hf3 = new HeaderField("Server","lazy"); 
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		fields.add(hf3);
		
		Header h = new Header(rl, fields);
		
		HeaderField[] hfs1 = {hf1,hf2,hf3};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].toString().equals(hfs1[i].toString());
			}
		
		assertTrue(evaluate);
		
	}
	
	
	@Test
	public void testGetFields6(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		HeaderField hf3 = new HeaderField("Server","lazy"); 
		HeaderField hf4 = new HeaderField("Server","wrong");
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		fields.add(hf4);
		
		Header h = new Header(rl, fields);
		
		HeaderField[] hfs1 = {hf1,hf2,hf3};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].toString().equals(hfs1[i].toString());
			}
		
		assertFalse(evaluate);
	}
	
	
	@Test
	public void testGetFields7(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		HeaderField hf3 = new HeaderField("Server","lazy"); 
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		fields.add(hf3);
		
		Header h = new Header(rl, fields);
		
		HeaderField[] hfs1 = {hf1,hf2,hf3};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].equals(hfs1[i]);
			}
		
		assertTrue(evaluate);
		
	}
	
	
	@Test
	public void testGetFields8(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		HeaderField hf3 = new HeaderField("Server","lazy"); 
		HeaderField hf4 = new HeaderField("Server","wrong");
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		fields.add(hf4);
		
		Header h = new Header(rl, fields);
		
		HeaderField[] hfs1 = {hf1,hf2,hf3};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].equals(hfs1[i]);
			}
		
		assertFalse(evaluate);
	}
	
	
	@Test
	public void testGetFields9(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		HeaderField hf3 = new HeaderField("Server","lazy"); 
		HeaderField hf4 = new HeaderField("Server","wrong");
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		fields.add(hf3);
		fields.add(hf4);
		
		Header h = new Header(rl, fields);
		
		HeaderField[] hfs1 = {hf1,hf2,hf3,hf4,hf4};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].toString().equals(hfs1[i].toString());
			}
		
		assertFalse(evaluate);
	}
	
	
	@Test
	public void testGetFields10(){
		RequestLine rl = new RequestLine("method", "URI", "version");
		HeaderField hf1 = new HeaderField("Connection","close"); 
		HeaderField hf2 = new HeaderField("Server","working"); 
		HeaderField hf3 = new HeaderField("Server","lazy"); 
		HeaderField hf4 = new HeaderField("Server","wrong");
		
		List<HeaderField> fields = new ArrayList<HeaderField>();
		fields.add(hf1);
		fields.add(hf2);
		fields.add(hf4);
		
		Header h = new Header(rl, fields);
		
		HeaderField[] hfs1 = {hf1,hf2,hf3,hf4,hf4,hf1};
		HeaderField[] hfs2 = h.getFields();
		
		boolean evaluate = true && (hfs1.length == hfs2.length);
		for(int i=0; i<hfs2.length;i++){
			evaluate = evaluate && hfs2[i].equals(hfs1[i]);
			}
		
		assertFalse(evaluate);
	}
	

	
	@Test
	public void testGetField1(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		Header h = new Header(hl);
		
		HeaderField hf1 = new HeaderField("Connection","close");
		HeaderField hf2 = h.getField("Connection");
		
		boolean evaluate = (hf1.toString().equals(hf2.toString()));
		
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testGetField2(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		Header h = new Header(hl);
		
		HeaderField hf1 = new HeaderField("Connection","close");
		HeaderField hf2 = h.getField("Connection");
		
		boolean evaluate = (hf1.equals(hf2));
		
		assertTrue(evaluate);
	}
	
	
	@Test
	public void testGetField3(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		Header h = new Header(hl);
		
		HeaderField hf1 = new HeaderField("Connection","close");
		HeaderField hf2 = h.getField("Network");
		
		boolean evaluate = (hf1.toString().equals(hf2));
		
		assertFalse(evaluate);
	}
	
	
	@Test
	public void testGetField4(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		Header h = new Header(hl);
		
		HeaderField hf1 = new HeaderField("Connection","close");
		HeaderField hf2 = h.getField("Network");
		
		boolean evaluate = (hf1.equals(hf2));
		
		assertFalse(evaluate);
	}
	
	
	@Test
	public void testGetField5(){
		HeaderLine hl = new ResponseLine("HTTP/1.1", "200", "OK");
		Header h = new Header(hl);
		
		HeaderField hf1 = h.getField("Random");
		
		boolean evaluate = (hf1==null);
		
		assertTrue(evaluate);
	}
	
	
	// To do --> Andreas
	@Test
	public void testGetField6(){}
	
	
	// To do --> Andreas
	@Test
	public void testGetField7(){}
	

}
