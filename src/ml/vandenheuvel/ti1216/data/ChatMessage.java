package ml.vandenheuvel.ti1216.data;

import org.json.JSONObject;

/**
 * Class "ChatMessage".
 * 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft,
 *         2015-2016.
 */
/**
 * ChatMessage represents a chat message.
 */
public class ChatMessage {

	/**
	 * Class-instances/variables.
	 */
	private String sender;
	private String message;
	private String receiver;
	
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
	 * General constructor.
	 * 
	 * @param sender The username of the person who sends the message
	 * @param message The actual content of the message
	 * @param receiver The username of the person who should receive the message
	 */
	public ChatMessage(String sender, String message, String receiver) {
		this.sender = sender;
		this.message = message;
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
	 * @return a ChatMessage constructed out of the JSON input
	 */
	public static ChatMessage fromJSON(JSONObject json) {
		return new ChatMessage(json.getString("sender"),
				json.getString("message"), json.getString("receiver"));
	}
}
