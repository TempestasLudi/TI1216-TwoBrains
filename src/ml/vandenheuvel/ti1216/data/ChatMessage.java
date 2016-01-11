package ml.vandenheuvel.ti1216.data;

import org.json.JSONObject;

/**
 * Instances of this data class represents a chat message.
 */
public class ChatMessage {

	/**
	 * Class-instances/variables.
	 */
	private String sender;
	private String message;
	private String receiver;
	
	/**
	 * @param sender the username of the person who sends the message
	 * @param message the actual content of the message
	 * @param receiver the username of the person who should receive the message
	 */
	public ChatMessage(String sender, String message, String receiver) {
		this.sender = sender;
		this.message = message;
		this.receiver = receiver;
	}
	
	/**
	 * Gets the sender of the chat message.
	 * 
	 * @return the sender of the chat message
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Gets the contents of the chat message.
	 * 
	 * @return the contents of the chat message
	 */

	public String getMessage() {
		return message;
	}

	/**
	 * Gets the receiver of the chat message.
	 * 
	 * @return the receiver of the chat message
	 */

	public String getReceiver() {
		return receiver;
	}
	
	/**
	 * Changes the sender of the chat message.
	 * 
	 * @param sender the sender to change to
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Changes the contents of the chat message.
	 * 
	 * @param message the contents to change to
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Changes the receiver of the chat message.
	 * 
	 * @param receiver the receiver to change to
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * Creates a JSON object out of this ChatMessage object
	 * 
	 * @return a JSON object out of this ChatMessage object
	 */
	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		result.put("sender", this.sender);
		result.put("message", this.message);
		result.put("receiver", this.receiver);
		return result;
	}

	/**
	 * Creates a ChatMessage object out of a JSON object.
	 * 
	 * @param json the JSON object
	 * @return a chatMessage constructed out of the JSON input
	 */
	public static ChatMessage fromJSON(JSONObject json) {
		return new ChatMessage(json.getString("sender"),
				json.getString("message"), json.getString("receiver"));
	}
	
	/**
	 * Checks whether two ChatMessages are equal to each other.
	 * 
	 * @param other the Object to which the ChatMessage is compared
	 * @return true if the two ChatMessages are equal, otherwise false
	 */
	@Override
	public boolean equals(Object other)
	{
		if(other instanceof ChatMessage)
		{
			ChatMessage that = (ChatMessage)other;
			return this.sender.equals(that.sender) && this.message.equals(that.message) && this.receiver.equals(that.receiver);
		}
		return false;
	}
	
}
