package ml.vandenheuvel.ti1216.data;

import java.io.Serializable;

import org.json.JSONObject;

/**
 * Class "ChatMessage".
 * 
 * @author Andreas Theys, OOP Project [TI1216], Project Group A1.2, TU Delft,
 *         2015-2016.
 */

public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Class-instances/variables.
	 */
	String sender;
	String message;
	String receiver;

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
