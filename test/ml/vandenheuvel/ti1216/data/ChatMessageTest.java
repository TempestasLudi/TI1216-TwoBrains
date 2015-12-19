package ml.vandenheuvel.ti1216.data;

import static org.junit.Assert.*;

import org.junit.Test;

import ml.vandenheuvel.ti1216.data.ChatMessage;

public class ChatMessageTest {

	@Test
	public void testConstructorChatMessage() 
	{
		ChatMessage message = new ChatMessage("1", "Hello, world!", "2");
		assertNotNull(message);
	}

}
