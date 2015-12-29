package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.ti1216.data.ChatMessage;

public class ChatMessageTest {
	private ChatMessage create() {
		return new ChatMessage("1", "Hello, world!", "2");
	}
	
	@Test
	public void testConstructorSender() {
		ChatMessage message = create();
		assertEquals("1", message.getSender());
	}
	
	@Test
	public void testConstructorMessage() {
		ChatMessage message = create();
		assertEquals("Hello, world!", message.getMessage());
	}
	
	@Test
	public void testConstructorReceiver() {
		ChatMessage message = create();
		assertEquals("2", message.getReceiver());
	}
	
	@Test
	public void testSender() {
		ChatMessage message = create();
		message.setSender("a");
		assertEquals("a", message.getSender());
	}
	
	@Test
	public void testMessage() {
		ChatMessage message = create();
		message.setMessage("a");
		assertEquals("a", message.getMessage());
	}
	
	@Test
	public void testReceiver() {
		ChatMessage message = create();
		message.setReceiver("a");
		assertEquals("a", message.getReceiver());
	}

}
