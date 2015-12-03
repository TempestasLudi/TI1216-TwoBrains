package ml.vandenheuvel.TI1216.test.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.TI1216.source.data.ChatMessage;

public class ChatMessageTest {

	@Test
	public void testConstructorChatMessage() 
	{
		ChatMessage message = new ChatMessage("1", "Hello, world!", "2");
		assertNotNull(message);
	}

}
