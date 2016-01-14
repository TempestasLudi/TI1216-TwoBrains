package ml.vandenheuvel.ti1216.data;

import org.json.JSONObject;

/**
 * Instances of this data class represents a chat message.
 */
public class ChatMessage {

	/**
	 * Class-instances/variables.
	 */
	private int id;
	private String sender;
	private String message;
	private String receiver;
	private boolean seen;

	/**
	 * @param id
	 *            the unique identifier of the message
	 * @param sender
	 *            the username of the person who sends the message
	 * @param message
	 *            the actual content of the message
	 * @param receiver
	 *            the username of the person who should receive the message
	 * @param seen
	 *            whether the message has been seen or not
	 */
	public ChatMessage(int id, String sender, String message, String receiver, boolean seen) {
		this.id = id;
		this.sender = sender;
		this.message = message;
		this.receiver = receiver;
		this.seen = seen;
	}

	/**
	 * Gets the id of the message.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * Gets whether the chat has been seen or not.
	 * 
	 * @return the seen
	 */
	public boolean isSeen() {
		return seen;
	}

	/**
	 * Changes the sender of the chat message.
	 * 
	 * @param sender
	 *            the sender to change to
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Changes the contents of the chat message.
	 * 
	 * @param message
	 *            the contents to change to
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Changes the receiver of the chat message.
	 * 
	 * @param receiver
	 *            the receiver to change to
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * Changes whether the chat has been seen or not.
	 * 
	 * @param seen
	 *            the seen to set
	 */
	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	/**
	 * Creates a JSON object out of this ChatMessage object
	 * 
	 * @return a JSON object out of this ChatMessage object
	 */
	public JSONObject toJSON() {
		JSONObject result = new JSONObject();
		result.put("id", this.id);
		result.put("sender", this.sender);
		result.put("message", this.message);
		result.put("receiver", this.receiver);
		result.put("seen", this.seen);
		return result;
	}

	/**
	 * Creates a ChatMessage object out of a JSON object.
	 * 
	 * @param json
	 *            the JSON object
	 * @return a chatMessage constructed out of the JSON input
	 */
	public static ChatMessage fromJSON(JSONObject json) {
		return new ChatMessage(json.getInt("id"), json.getString("sender"), json.getString("message"),
				json.getString("receiver"), json.getBoolean("seen"));
	}

	/**
	 * Checks whether two ChatMessages are equal to each other.
	 * 
	 * @param other
	 *            the Object to which the ChatMessage is compared
	 * @return true if the two ChatMessages have the same id, otherwise false
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof ChatMessage) {
			ChatMessage that = (ChatMessage) other;
			return this.id == that.getId();
		}
		return false;
	}

}
